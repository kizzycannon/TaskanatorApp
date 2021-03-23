package com.example.taskanatorapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;

public class ManageTasks extends AppCompatActivity {
    //System system = (System)getApplicationContext();
    //ArrayList<Task> allTasks = system.getAllTasks();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_tasks);
    }

    public void buttonActiveTasks(View view) {
        Intent intent1 = new Intent(this, MainActivity.class);
        startActivity(intent1);
    }

    public void buttonAddTask(View view) {
        Intent intent2 = new Intent(this, AddTask.class);
        startActivity(intent2);
    }
}