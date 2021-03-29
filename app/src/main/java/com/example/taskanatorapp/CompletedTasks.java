package com.example.taskanatorapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

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
                Toast.makeText(CompletedTasks.this, "Task history cleared", Toast.LENGTH_SHORT).show();
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

    public void showPopupView(View view) {
        LinearLayout popupLayout = findViewById(R.id.popup);
        popupLayout.setVisibility(View.VISIBLE);
        TextView popupTitle = (TextView) findViewById(R.id.textViewPopupTitle);
        TextView popupInfo = (TextView) findViewById(R.id.textViewPopupInfo);
        String title = "Completed Tasks";
        String info = "When a task in your active tasks list has been marked as completed it will be recorded here.\n\nDetails of the tasks can be viewed here and will remain when a task "
        + "is deleted from your managed list.\n\nThe 'clear completed tasks' button will empty the list and reset the stats of the number of tasks completed and the number of hours spent on tasks overall.";
        popupTitle.setText(title);
        popupInfo.setText(info);
        Button closePopup = (Button) findViewById(R.id.buttonPopupClose);
        closePopup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupLayout.setVisibility(View.INVISIBLE);
            }
        });
    }
}