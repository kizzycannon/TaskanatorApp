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
import android.widget.Toast;

public class TaskDetails extends AppCompatActivity {

    public static final String EXTRA_TASK_ID = "task id";

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_details);

        System system = PrefConfig.loadSystem(this);

        int taskID = (Integer)getIntent().getExtras().get(EXTRA_TASK_ID);
        Task task = system.getActiveTasks().get(taskID);
        String taskName = task.getTaskName();
        String taskCategory = task.getTaskCategory();
        String taskDescription = task.getTaskDescription();
        int taskDuration = task.getTaskLength();

        TextView taskNameView = (TextView)findViewById(R.id.TaskDetailsTaskName);
        TextView taskCategoryView = (TextView)findViewById(R.id.textViewTaskCategoryTD);
        TextView taskDescriptionView = (TextView)findViewById(R.id.TaskDetailsTaskDescription);
        TextView taskDurationView = (TextView) findViewById(R.id.textViewTaskDurationTD);
        ImageView taskCategoryImage = (ImageView) findViewById(R.id.imageViewTD);

        taskNameView.setText(taskName);
        //taskCategoryView.setText(taskCategory);
        taskDescriptionView.setText(taskDescription);
        taskDurationView.setText(taskDuration + " minutes");
        taskCategoryView.setText(taskCategory);
        taskCategoryImage.setImageDrawable(getDrawable(system.getIconID(taskCategory)));

        Button buttonMarkComplete = (Button) findViewById(R.id.TaskDetailsCompleteTask);
        buttonMarkComplete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // remove completed task from active task list
                Intent backToMain = new Intent(TaskDetails.this, MainActivity.class);
                int progressTime = system.getActiveTasks().get(taskID).getTaskLength();
                SystemProgressBar bar = PrefConfig.loadProgressBar(TaskDetails.this);
                //bar.addProgressPoints(progressTime);
                system.addToCompletedTasks(system.getActiveTasks().get(taskID));
                system.getActiveTasks().remove(taskID);
                PrefConfig.saveSystem(TaskDetails.this, system);
                // update the progress bar
                //SystemProgressBar progressBar = new SystemProgressBar();
                //progressBar.addProgressPoints(progressTime);
                bar.addProgressPoints(progressTime);
                PrefConfig.saveProgressBar(TaskDetails.this,bar);
                Toast.makeText(TaskDetails.this, "Task removed from Active Tasks. Your progress is updated!", Toast.LENGTH_SHORT).show();
                startActivity(backToMain);
            }
        });
        Button buttonRemoveFromActive = (Button) findViewById(R.id.buttonRemoveTaskTD);
        buttonRemoveFromActive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                system.getActiveTasks().remove(taskID);
                PrefConfig.saveSystem(TaskDetails.this, system);
                Toast.makeText(TaskDetails.this, "Task removed from Active Tasks", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(TaskDetails.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}