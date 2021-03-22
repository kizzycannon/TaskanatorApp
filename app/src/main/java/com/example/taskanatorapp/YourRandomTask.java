package com.example.taskanatorapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class YourRandomTask extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_your_random_task);

        Intent intent = getIntent();
        String message = intent.getStringExtra(GenerateRandomTask.EXTRA_MESSAGE);

        TextView textView = (TextView) findViewById(R.id.textViewTaskDetailsYRT);
        textView.setText(message);
    }
}