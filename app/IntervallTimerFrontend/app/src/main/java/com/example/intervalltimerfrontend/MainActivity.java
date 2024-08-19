package com.example.intervalltimerfrontend;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.view.View;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.example.intervalltimerfrontend.Dialog.ExerciseDialog;
import com.example.intervalltimerfrontend.Model.Exercise;

import com.example.intervalltimerfrontend.databinding.ActivityMainBinding;


import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements ExerciseDialog.CreateExerciseDialogListener {

    private final String EXERCISEDETAILKEY = "DATA";

    private AppBarConfiguration appBarConfiguration;
    private ActivityMainBinding binding;
    private RecyclerView recyclerView;

    private ExerciseDialog exerciseDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        recyclerView = findViewById(R.id.recyclerview);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        //
        //getAllExercises


        setSupportActionBar(binding.toolbar);

        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        appBarConfiguration = new AppBarConfiguration.Builder(navController.getGraph()).build();
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);

        binding.createExercise.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openCreationDialog();
            }
        });
    }

    private void openCreationDialog() {
        exerciseDialog = new ExerciseDialog();
        exerciseDialog.show(getSupportFragmentManager(), String.valueOf(R.string.create_new_exercise));
    }

    public void openEditDialog(Exercise exercise) {
        exerciseDialog = new ExerciseDialog(exercise);
        exerciseDialog.show(getSupportFragmentManager(), String.valueOf(R.string.edit_exercise));
    }

    public void onSave(View v) {
        exerciseDialog.save();
    }

    public void onCancel(View v) {
        exerciseDialog.cancel();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        return NavigationUI.navigateUp(navController, appBarConfiguration)
                || super.onSupportNavigateUp();
    }





    public void saveExercise(String name, int repCount, int lapTime, int lapBreaktime, int startCountdown) {
        Exercise createdExercise = new Exercise(name, repCount, lapTime, lapBreaktime, startCountdown);
        //...
    }

    public void editExercise(Exercise editedExercise) {
        //...
    }

}