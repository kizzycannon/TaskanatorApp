package com.example.taskanatorapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.TextView;

public class AddToActiveTasks extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_to_active_tasks);
    }

    public void buttonGenerateRandomTasksActivity(View view) {
        Intent intent = new Intent(this, GenerateRandomTask.class);
        startActivity(intent);
    }
}