package com.example.intervalltimerfrontend;

import androidx.appcompat.app.AppCompatActivity;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;

import android.util.Log;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;


import java.util.Locale;

import com.example.intervalltimerfrontend.Helper.NotificationHelper;
import com.example.intervalltimerfrontend.Model.Exercise;

public class ExerciseDetailsActivity extends AppCompatActivity {

    // Constants
    private final long COUNTDOWNINTERVAL = 1000;
//    private final BroadcastReceiver resetReceiver = new BroadcastReceiver() {
//        @Override
//        public void onReceive(Context context, Intent intent) {
//            // Reset Timer on receiving the message
//            resetTimer();
//        }
//    };

    // UI
    private TextView textViewName, textViewRepCount, textViewCountDown, textViewUserNotification;
    private Exercise currentExercise;
    private Button buttonStartPause;
    private Button buttonReset;
    private RelativeLayout layout;


    // Logic
    private int currentRepetition = 1;
    private boolean isBreakMode = false;
    private boolean isPauseMode = false;
    private boolean isStartCountdownFinished = false;

    private CountDownTimer countDownTimer;
    private boolean timerRunning;
    private long lapTimeLeftInMillis = 0;
    private boolean lastRound = false;

    MediaPlayer mediaPlayer;

    private GestureDetector gestureDetector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

       // getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise_detail);

        gestureDetector = new GestureDetector(this, new SwipeGestureListener());

        // TextViews
        textViewName = findViewById(R.id.textViewName);
        textViewRepCount = findViewById(R.id.textViewRepCount);
        textViewCountDown = findViewById(R.id.textViewCountDown);
        textViewUserNotification = findViewById(R.id.userNotification);


        // Layout
        layout = findViewById(R.id.timer_layout);

        Intent intent = getIntent();

        if(intent != null && intent.hasExtra(getString(R.string.exerciseDetailKey))) {
            currentExercise = (Exercise) intent.getSerializableExtra(getString(R.string.exerciseDetailKey));

            // Assign values
            // Exercise Name
            String exerciseName = currentExercise.getName();
            textViewName.setText(exerciseName);

            // Start Countdown / Timer Display
            lapTimeLeftInMillis = convertSecToMillis(currentExercise.getStartCountdown());

            // Initial Value for Repetition Count
            textViewRepCount.setText(getString(R.string.repcount) + " 1");
        }
        else {
            Log.e("ExerciseDetailsActivity", "Exercise is null");
        }

        //Buttons
        buttonStartPause = findViewById(R.id.button_start_pause);
        buttonReset = findViewById(R.id.button_reset);

        buttonStartPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(timerRunning) {
                    pauseTimer();
                } else {
                    startTimer();
                }
            }
        });

        buttonReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetTimer();
            }
        });
        updateCountDownText();

//        // Create NotificationChannel
//        NotificationHelper.createNotificationChannel(this);
//
//        // Check if Action "ACTION_RESET_TIMER" was executed
//        if (getIntent().getAction() != null && getIntent().getAction().equals("RESET_TIMER_ACTION")) {
//            resetTimer();
//        }
//
//        // Register BroadcastReceiver
//        IntentFilter filter = new IntentFilter("RESET_TIMER_ACTION");
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//            this.registerReceiver(resetReceiver, filter, RECEIVER_NOT_EXPORTED);
//        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        if(mediaPlayer.isPlaying()) mediaPlayer.stop();
        mediaPlayer.release();

        // unregister BroadcastReceiver, if Activity is closed (destroyed)
        //unregisterReceiver(resetReceiver);
    }

    private long convertSecToMillis(int lapTime) {
        return (lapTime * 1000);
    }

    /**
     * Recursive-Method to control the configurations of the timer
     * Consists 3 timer conditions
     * 1. Start-Countdown
     * 2. Exercise-Timer
     * 3. Break-Timer
     * Finishes after all repetitions are done
     * */
    private void startTimer() {
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        // isStartCountdownFinished is by default false
        if(isStartCountdownFinished) {
            //Break Mode Handling
            lapTimeLeftInMillis = handleBreakMode();
        } else {
            // startCountdown was assigned to lapTimeLeftInMillis at the onCreate() Method
            // UI
            layout.setBackgroundResource(R.drawable.gradient_background_blue);
        }

        // Pause-Mode is only being toggled if currently in Pause-Mode
        if(isPauseMode) {
            togglePauseMode();
        }

        if(lastRound){
            mediaPlayer = MediaPlayer.create(this, R.raw.ring_3);
        }
        else{
            mediaPlayer = MediaPlayer.create(this, R.raw.ring_1_1);
        }


        countDownTimer = new CountDownTimer(lapTimeLeftInMillis, COUNTDOWNINTERVAL) {
            @Override
            public void onTick(long millisUntilFinished) {
                updateCountDownText();
                lapTimeLeftInMillis = millisUntilFinished;
            }
            @Override
            public void onFinish() {

                if(mediaPlayer.isPlaying()) mediaPlayer.stop();
                mediaPlayer.start();

                if(isStartCountdownFinished) {
                    //Repetition only counts up after exercise NOT after breaks
                    currentRepetition = isBreakMode ? currentRepetition : currentRepetition+1;

                    // Adjusting the isBreakMode Flag
                    isBreakMode = !isBreakMode;

                    if(currentRepetition <= currentExercise.getRepCount()) {
                        // Adjusting the RepetitionCount Text
                        String repCount = getString(R.string.repcount) + " " + Integer.toString(currentRepetition);
                        textViewRepCount.setText(repCount);
                        timerRunning = false;

                        startTimer();
                        if(currentRepetition == currentExercise.getRepCount()){
                            lastRound = true;
                        }
                    } else { // Finished Exercise (All Intervals are finished)
                        //UI Adjustments
                        buttonStartPause.setText(getString(R.string.start));
                        buttonStartPause.setVisibility(View.INVISIBLE);
                        buttonReset.setVisibility(View.VISIBLE);
                        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

//                        //Show Notification
//                        NotificationHelper.showTimerFinishedNotification(ExerciseDetailsActivity.this);
                    }
                } else {
                    textViewUserNotification.setVisibility(View.INVISIBLE);
                    isStartCountdownFinished = true;

                    startTimer();
                }
                updateCountDownText();

            }
        }.start();

        timerRunning = true;
        buttonStartPause.setText(getString(R.string.pause));
        buttonReset.setVisibility(View.INVISIBLE);
    }

    /**
     * Sets the configurations for the Break-mode*/
    private long handleBreakMode() {
        long timeInMillis;
        if(isBreakMode) {
            layout.setBackgroundResource(R.drawable.gradient_background_red);
            timeInMillis = isPauseMode ? lapTimeLeftInMillis : convertSecToMillis(currentExercise.getLapBreakTime());
            textViewUserNotification.setText(getString(R.string.pause) + "!");
            textViewUserNotification.setVisibility(View.VISIBLE);

        } else {
            layout.setBackgroundResource(R.drawable.gradient_background_green);
            textViewUserNotification.setVisibility(View.INVISIBLE);
            timeInMillis = isPauseMode ? lapTimeLeftInMillis : convertSecToMillis(currentExercise.getLapTime());

        }
        return timeInMillis;
    }

    /**
     * Toggles the Pause-Mode if Pause-Mode is active
     *
     * */
    private void togglePauseMode() {
        isPauseMode = (!isPauseMode);
    }

    /**
     * Sets the configuration for the Pause-mode
     * */
    private void pauseTimer() {
        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        isPauseMode = true;
        layout.setBackgroundResource(R.drawable.gradient_background_yellow);
        countDownTimer.cancel();
        timerRunning = false;
        buttonStartPause.setText(getString(R.string.start));
        buttonReset.setVisibility(View.VISIBLE);
    }

    /**
     * Resets the timer configurations
     * */
    private void resetTimer() {
        // Resetting Logic
        lapTimeLeftInMillis = convertSecToMillis(currentExercise.getStartCountdown());
        isBreakMode = false;
        isPauseMode = false;
        isStartCountdownFinished = false;
        currentRepetition = 1;
        lastRound = false;
        // Resetting UI
        updateCountDownText();
        buttonReset.setVisibility(View.INVISIBLE);
        buttonStartPause.setVisibility(View.VISIBLE);
        layout.setBackgroundResource(R.drawable.gradient_background_blue);
        textViewUserNotification.setText(getString(R.string.get_ready));
        textViewUserNotification.setVisibility(View.VISIBLE);
        textViewRepCount.setText(getString(R.string.repcount) + Integer.toString(currentRepetition));

        //Toast Notification
        Toast.makeText(this, getString(R.string.reset_timer), Toast.LENGTH_SHORT).show();
    }

    /**
     * Sets the textview text for the timer
     * */
    private void updateCountDownText() {
        int minutes = (int) (lapTimeLeftInMillis / 1000) / 60;
        int seconds = (int) (lapTimeLeftInMillis / 1000) % 60;

        String timeLeftFormatted = String.format(Locale.getDefault(),"%02d:%02d", minutes, seconds);
        textViewCountDown.setText(timeLeftFormatted);
    }

    private class SwipeGestureListener extends GestureDetector.SimpleOnGestureListener {
        private static final int SWIPE_THRESHOLD = 100;
        private static final int SWIPE_VELOCITY_THRESHOLD = 100;

        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            float diffX = e2.getX() - e1.getX();
            float diffY = e2.getY() - e1.getY();
            if (Math.abs(diffX) > Math.abs(diffY)) {
                if (Math.abs(diffX) > SWIPE_THRESHOLD && Math.abs(velocityX) > SWIPE_VELOCITY_THRESHOLD) {
                    if (diffX > 0) {
                        onSwipeRight();
                    }
                    return true;
                }
            }
            return false;
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return gestureDetector.onTouchEvent(event) || super.onTouchEvent(event);
    }

    private void onSwipeRight() {
        // Perform the back action
        countDownTimer.cancel();
        onBackPressed();
    }
}