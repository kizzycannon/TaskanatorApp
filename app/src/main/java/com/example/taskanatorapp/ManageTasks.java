package com.example.taskanatorapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;

public class ManageTasks extends AppCompatActivity {
    private RecyclerView recyclerView;
    private System system;
    private ManageTasksAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_tasks);

        recyclerView = findViewById(R.id.ManageTasksrecyclerView);
        system = PrefConfig.loadSystem(this);
        if (system == null){
            system = new System();
        }

        LinearLayoutManager linearLayout = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayout); //< CRASHING HERE
        adapter = new ManageTasksAdapter(system.getAllTasks());
        recyclerView.setAdapter(adapter);
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