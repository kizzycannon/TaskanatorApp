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
                String taskName = null;
                String taskCategory = null;
                String taskDescription = null;
                int taskDuration = 0;

                TextView taskCategoryTitle = (TextView)findViewById(R.id.addTaskNameCategory);
                TextView taskNameTitle = (TextView)findViewById(R.id.addTaskNameTitle);
                EditText taskNameView = (EditText) findViewById(R.id.addNewTaskName);
                Spinner taskCategoryView = (Spinner)findViewById(R.id.addTaskCategory);
                EditText taskDescriptionView = (EditText) findViewById(R.id.addNewTaskDescription);
                EditText taskDurationView = (EditText) findViewById(R.id.addNewTaskDuration);

                if (taskNameView.getText() != null && taskDescriptionView.getText() != null && taskDescriptionView.getText() != null
                && taskDurationView.getText() != null) {
                    taskName = taskNameView.getText().toString();
                    taskCategory = taskCategoryView.getSelectedItem().toString();
                    taskDescription = taskDescriptionView.getText().toString();
                    taskDuration = Integer.parseInt(taskDurationView.getText().toString());
                }
                if (taskName == null || taskCategory == null || taskDescription == null || taskDuration == 0) {
                    taskNameTitle.setText(R.string.add_edit_tasks_task_error_null);
                    taskCategoryTitle.setText(R.string.add_edit_tasks_task_category);
                    return;
                }

                ArrayList<Task> taskList = system.getAllTasks();
                for (Task task: taskList) {
                    if(task.getTaskName().equals(taskName)){
                        taskNameTitle.setText(R.string.add_edit_tasks_task_name_error);
                        taskCategoryTitle.setText(R.string.add_edit_tasks_task_category);
                        return;
                    }
                }
                if(taskCategory.equals("Select Category")){
                    taskNameTitle.setText(R.string.add_edit_tasks_task_name);
                    taskCategoryTitle.setText(R.string.add_edit_tasks_task_category_error);
                    return;
                }
                if(taskNameView.getText().length() == 0){

                    taskNameTitle.setText(R.string.add_edit_tasks_task_name_empty_error);
                    taskCategoryTitle.setText(R.string.add_edit_tasks_task_category);
                    return;
                }
                if(taskDescriptionView.getText().length() == 0){

                    taskNameTitle.setText(R.string.add_edit_tasks_task_description_empty_error);
                    taskCategoryTitle.setText(R.string.add_edit_tasks_task_category);
                    return;
                }
                if(taskDurationView.getText().length() == 0){

                    taskNameTitle.setText(R.string.add_edit_tasks_task_duration_empty_error);
                    taskCategoryTitle.setText(R.string.add_edit_tasks_task_category);
                    return;
                }

                system.createNewTask(taskName, taskCategory, taskDescription, taskDuration);

                Switch activeSwitch = findViewById(R.id.AddTaskAddToActiveTasksSwitch);
                Boolean switchStatus = activeSwitch.isChecked();

                if(switchStatus){
                    //system.getActiveTasks().add(new Task(taskName, taskCategory, taskDescription, taskDuration));
                    int indexOfNew = (system.getAllTasks().size())-1;
                    system.getActiveTasks().add(system.getAllTasks().get(indexOfNew));
                }

                PrefConfig.saveSystem(AddTask.this, system);

                Intent intent = new Intent(AddTask.this, ManageTasks.class);
                startActivity(intent);
            }
        });
    }
}