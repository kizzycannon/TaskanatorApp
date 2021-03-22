package com.example.taskanatorapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import java.util.ArrayList;

public class ManageTasks extends AppCompatActivity {
    System system = (System)getApplicationContext();
    ArrayList<Task> allTasks = system.getAllTasks();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_tasks);
    }
}