package com.example.intervalltimerfrontend.Helper;

import android.Manifest;
import android.app.Activity;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;

import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.example.intervalltimerfrontend.ExerciseDetailsActivity;
import com.example.intervalltimerfrontend.R;
import com.example.intervalltimerfrontend.Receiver.ResetReceiver;

public class NotificationHelper {

    private static final String CHANNEL_ID = "exercise_channel";
    private static final int REQUEST_NOTIFICATION_PERMISSION = 100;

    public static void showTimerFinishedNotification(Context context) {
        // Creates the Intent for the Reset
        Intent resetIntent = new Intent(context, ResetReceiver.class);
        resetIntent.setAction("ACTION_RESET_TIMER");
        PendingIntent resetPendingIntent = PendingIntent.getBroadcast(
                context,
                0,
                resetIntent,
                PendingIntent.FLAG_UPDATE_CURRENT
        );

        // Creates and shows the notification
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_notification)
                .setContentTitle("Timer abgelaufen")
                .setContentText("Möchtest du den Timer zurücksetzen?")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .addAction(R.drawable.ic_reset, "Zurücksetzen", resetPendingIntent);

        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(context);

        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) {
            // Permission has not been granted yet, request for Permission is called
            ActivityCompat.requestPermissions((Activity) context, new String[]{Manifest.permission.POST_NOTIFICATIONS}, REQUEST_NOTIFICATION_PERMISSION);
            return;
        }
        notificationManager.notify(1001, builder.build());
    }

    public static void createNotificationChannel(Context context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(
                    CHANNEL_ID,
                    "Exercise Notifications",
                    NotificationManager.IMPORTANCE_DEFAULT);
            channel.setDescription("Benachrichtigungen für Übungen");

            NotificationManager notificationManager = context.getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }
}
