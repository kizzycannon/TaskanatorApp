package com.example.taskanatorapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class TaskDetails extends AppCompatActivity {

    public static final String EXTRA_TASK_ID = "task id";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_details);

        int taskID = (Integer)getIntent().getExtras().get(EXTRA_TASK_ID);
        String taskName = System.getActiveTasks().get(taskID).getTaskName();
        //String taskCategory = System.getActiveTasks().get(taskID).getTaskCategory();
        String taskDescription = System.getActiveTasks().get(taskID).getTaskDescription();

        TextView taskNameView = (TextView)findViewById(R.id.TaskDetailsTaskName);
        //TextView taskCategoryView = (TextView)findViewById(R.id.Task_Category);
        TextView taskDescriptionView = (TextView)findViewById(R.id.TaskDetailsTaskDescription);

        taskNameView.setText(taskName);
        //taskCategoryView.setText(taskCategory);
        taskDescriptionView.setText(taskDescription);
    }
}