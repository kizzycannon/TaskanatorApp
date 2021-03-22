package com.example.taskanatorapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.SeekBar;

import java.util.Random;
import java.util.ArrayList;

public class GenerateRandomTask extends AppCompatActivity {

    //ArrayList of tasks to be chosen from which meet the criteria
    private ArrayList<Task> tasksMeetingCriteriaList;
    //duration specified as maximum time for random task
    private int duration;
    private String category;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_generate_random_task);
    }

    public void onProgressChanged (SeekBar seekBar, int progressValue, boolean fromUser) {}

    public void onStartTrackingTouch(SeekBar seekBar) {}

    public void onStopTrackingTouch(SeekBar seekBar) {}

    public void updateFromSeekBar(View view) {
        EditText editDuration = (EditText) findViewById(R.id.editTextNumberGRT);
        SeekBar seekBarProgress = (SeekBar) findViewById(R.id.seekBarGRT);
        editDuration.setText(seekBarProgress.getProgress());
    }

    public void buttonGenerateRandomTask(View view) {
        Intent intent = new Intent(this, YourRandomTask.class);
        EditText editDuration = (EditText) findViewById(R.id.editTextNumberGRT);
        startActivity(intent);
    }
}