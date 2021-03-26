package com.example.taskanatorapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class CompletedTasks extends AppCompatActivity {
    private RecyclerView recyclerView;
    private System system;
    private CompletedTasksAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_completed_tasks);

        system = PrefConfig.loadSystem(this);
        recyclerView = findViewById(R.id.CompletedTasksrecyclerView);
        Button buttonClear = (Button) findViewById(R.id.buttonClearCompletedTasks);
        Button buttonBackToActive = (Button) findViewById(R.id.buttonBackToActiveCT);
        TextView textViewNumCompletedTasks = (TextView) findViewById(R.id.textViewNumTasksCompletedCT);
        TextView textViewNumHrsCompleted = (TextView) findViewById(R.id.textViewNumHrsCompletedCT);
        ArrayList<Task> completedList = system.getCompletedTasks();

        if (system == null){
            system = new System();
        }

        float timeSpentMins = 0;
        for (Task task : completedList) {
            timeSpentMins += task.getTaskLength();
        }
        float timeSpent = timeSpentMins/60;
        String timeSpentString = String.format("%.1f", timeSpent);
        textViewNumCompletedTasks.setText("No. Tasks Completed: " + completedList.size());
        textViewNumHrsCompleted.setText("Time spent on tasks: " + timeSpentString + " hours");


        LinearLayoutManager linearLayout = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayout);
        adapter = new CompletedTasksAdapter(system.getCompletedTasks());
        recyclerView.setAdapter(adapter);
        PrefConfig.saveSystem(this, system);




        buttonClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                system.clearCompletedTasks();
                PrefConfig.saveSystem(CompletedTasks.this, system);
                Intent intent = new Intent(CompletedTasks.this, MainActivity.class);
                startActivity(intent);
            }

        });
        buttonBackToActive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goBack = new Intent(CompletedTasks.this, MainActivity.class);
                startActivity(goBack);
            }
        });
        PrefConfig.saveSystem(CompletedTasks.this, system);
    }
}