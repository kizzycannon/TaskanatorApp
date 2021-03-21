package com.example.taskanatorapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class GenerateRandomTask extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_generate_random_task);
    }

    public void buttonGenerateRandomTask(View view) {
        Intent intent = new Intent(this, YourRandomTask.class);
        startActivity(intent);
    }
}