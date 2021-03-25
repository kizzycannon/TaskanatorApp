package com.example.taskanatorapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;

import java.util.ArrayList;

public class AddTask extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);

        System system = PrefConfig.loadSystem(this);

        Button buttonAddTask = (Button)findViewById(R.id.buttonAddTask);
        buttonAddTask.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                EditText taskNameView = (EditText) findViewById(R.id.addNewTaskName);
                Spinner taskCategoryView = (Spinner)findViewById(R.id.addTaskCategory);
                EditText taskDescriptionView = (EditText) findViewById(R.id.addNewTaskDescription);
                EditText taskDurationView = (EditText) findViewById(R.id.addNewTaskDuration);

                String taskName = taskNameView.getText().toString();
                String taskCategory = taskCategoryView.getSelectedItem().toString();
                String taskDescription = taskDescriptionView.getText().toString();
                int taskDuration = Integer.parseInt(taskDurationView.getText().toString());

                ArrayList<Task> taskList = system.getAllTasks();
                for (Task task: taskList) {
                    if(task.getTaskName().equals(taskName)){
                        TextView taskNameTitle = (TextView)findViewById(R.id.addTaskNameTitle);
                        TextView taskCategoryTitle = (TextView)findViewById(R.id.addTaskNameCategory);

                        taskNameTitle.setText(R.string.add_edit_tasks_task_name_error);
                        taskCategoryTitle.setText(R.string.add_edit_tasks_task_category);
                        return;
                    }
                }
                if(taskCategory.equals("Select Category")){
                    TextView taskCategoryTitle = (TextView)findViewById(R.id.addTaskNameCategory);
                    TextView taskNameTitle = (TextView)findViewById(R.id.addTaskNameTitle);

                    taskNameTitle.setText(R.string.add_edit_tasks_task_name);
                    taskCategoryTitle.setText(R.string.add_edit_tasks_task_category_error);
                    return;
                }

                system.createNewTask(taskName, taskCategory, taskDescription, taskDuration);

                Switch activeSwitch = findViewById(R.id.AddTaskAddToActiveTasksSwitch);
                Boolean switchStatus = activeSwitch.isChecked();

                if(switchStatus){
                    system.getActiveTasks().add(new Task(taskName, taskCategory, taskDescription, taskDuration));
                }

                PrefConfig.saveSystem(AddTask.this, system);

                Intent intent = new Intent(AddTask.this, ManageTasks.class);
                startActivity(intent);
            }
        });
    }
}