package com.example.taskanatorapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class TaskDetails extends AppCompatActivity {

    public static final String EXTRA_TASK_ID = "task id";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_details);

        System system = PrefConfig.loadSystem(this);

        int taskID = (Integer)getIntent().getExtras().get(EXTRA_TASK_ID);
        String taskName = system.getActiveTasks().get(taskID).getTaskName();
        //String taskCategory = System.getActiveTasks().get(taskID).getTaskCategory();
        String taskDescription = system.getActiveTasks().get(taskID).getTaskDescription();

        TextView taskNameView = (TextView)findViewById(R.id.TaskDetailsTaskName);
        //TextView taskCategoryView = (TextView)findViewById(R.id.Task_Category);
        TextView taskDescriptionView = (TextView)findViewById(R.id.TaskDetailsTaskDescription);

        taskNameView.setText(taskName);
        //taskCategoryView.setText(taskCategory);
        taskDescriptionView.setText(taskDescription);
        Button buttonMarkComplete = (Button) findViewById(R.id.TaskDetailsCompleteTask);
        buttonMarkComplete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // remove completed task from active task list
                Intent backToMain = new Intent(TaskDetails.this, MainActivity.class);
                int progressTime = system.getActiveTasks().get(taskID).getTaskLength();
                system.getActiveTasks().remove(taskID);
                PrefConfig.saveSystem(TaskDetails.this, system);
                // update the progress bar
                SystemProgressBar progressBar = new SystemProgressBar();
                progressBar.addProgressPoints(progressTime);
                startActivity(backToMain);
            }
        });
    }
}