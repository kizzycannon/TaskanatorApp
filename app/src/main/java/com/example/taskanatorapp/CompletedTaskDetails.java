package com.example.taskanatorapp;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class CompletedTaskDetails extends AppCompatActivity {

    public static final String EXTRA_TASK_ID = "task id";

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_completed_task_details);

        System system = PrefConfig.loadSystem(this);

        int taskID = (Integer)getIntent().getExtras().get(EXTRA_TASK_ID);
        Task task = system.getCompletedTasks().get(taskID);
        String taskName = task.getTaskName();
        String taskCategory = task.getTaskCategory();
        String taskDescription = task.getTaskDescription();
        int taskDuration = task.getTaskLength();

        TextView taskNameView = (TextView)findViewById(R.id.CompletedTaskDetailsTaskName);
        TextView taskCategoryView = (TextView)findViewById(R.id.textViewTaskCategoryCTD);
        TextView taskDescriptionView = (TextView)findViewById(R.id.CompletedTaskDetailsTaskDescription);
        TextView taskDurationView = (TextView) findViewById(R.id.textViewTaskDurationCTD);
        ImageView taskCategoryImage = (ImageView) findViewById(R.id.imageViewCTD);
        Button buttonBackCompleted = (Button) findViewById(R.id.buttonBackToCompletedCDT);
        Button buttonBackActive = (Button) findViewById(R.id.buttonBackToActiveCDT);

        taskNameView.setText(taskName);
        taskDescriptionView.setText(taskDescription);
        taskDurationView.setText(taskDuration + " minutes");
        taskCategoryView.setText(taskCategory);
        taskCategoryImage.setImageDrawable(getDrawable(system.getIconID(taskCategory)));

        buttonBackActive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goBackActive = new Intent(CompletedTaskDetails.this, MainActivity.class);
                startActivity(goBackActive);
            }
        });
        buttonBackCompleted.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goBackCompleted = new Intent(CompletedTaskDetails.this, CompletedTasks.class);
                startActivity(goBackCompleted);
            }
        });
    }
}