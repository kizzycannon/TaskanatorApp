package com.example.taskanatorapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

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

                ArrayList<Task> activeTaskList = system.getActiveTasks();

                TextView taskCategoryTitle = (TextView)findViewById(R.id.addTaskNameCategory);
                TextView taskNameTitle = (TextView)findViewById(R.id.addTaskNameTitle);
                TextView taskDescriptionTitle = (TextView) findViewById(R.id.editTaskDescriptionTitle);
                TextView taskDurationTitle = (TextView) findViewById(R.id.editTaskDurationTitle);
                EditText taskNameView = (EditText) findViewById(R.id.addNewTaskName);
                Spinner taskCategoryView = (Spinner)findViewById(R.id.addTaskCategory);
                EditText taskDescriptionView = (EditText) findViewById(R.id.addNewTaskDescription);
                EditText taskDurationView = (EditText) findViewById(R.id.addNewTaskDuration);

                // error check for null inputs
                if (taskNameView.getText() == null || taskNameView.getText().length() == 0) {
                    taskNameTitle.setText(R.string.add_edit_tasks_task_name_empty_error);
                    taskCategoryTitle.setText(R.string.add_edit_tasks_task_category);
                    taskDescriptionTitle.setText(R.string.add_edit_tasks_task_description);
                    taskDurationTitle.setText(R.string.add_edit_tasks_task_duration);
                    return;
                }
                if (taskDescriptionView.getText() == null || taskDescriptionView.getText().length() == 0) {
                    taskNameTitle.setText(R.string.add_edit_tasks_task_name);
                    taskCategoryTitle.setText(R.string.add_edit_tasks_task_category);
                    taskDescriptionTitle.setText(R.string.add_edit_tasks_task_description_empty_error);
                    taskDurationTitle.setText(R.string.add_edit_tasks_task_duration);
                    return;
                }
                if (taskDurationView.getText() == null || taskDurationView.getText().length() == 0) {
                    taskNameTitle.setText(R.string.add_edit_tasks_task_name);
                    taskCategoryTitle.setText(R.string.add_edit_tasks_task_category);
                    taskDescriptionTitle.setText(R.string.add_edit_tasks_task_description);
                    taskDurationTitle.setText(R.string.add_edit_tasks_task_duration_empty_error);
                    return;
                }

                String taskName = taskNameView.getText().toString();
                String taskCategory = taskCategoryView.getSelectedItem().toString();
                String taskDescription = taskDescriptionView.getText().toString();
                int taskDuration = Integer.parseInt(taskDurationView.getText().toString());

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

                system.createNewTask(taskName, taskCategory, taskDescription, taskDuration);

                Switch activeSwitch = findViewById(R.id.AddTaskAddToActiveTasksSwitch);
                Boolean switchStatus = activeSwitch.isChecked();

                if(switchStatus){
                    int indexOfNew = (system.getAllTasks().size())-1;
                    system.addToActiveTasks(system.getAllTasks().get(indexOfNew));
                }
                PrefConfig.saveSystem(AddTask.this, system);
                Toast.makeText(AddTask.this, "Task added", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(AddTask.this, ManageTasks.class);
                startActivity(intent);
            }
        });
    }
    public void showPopupView(View view) {
        LinearLayout popupLayout = findViewById(R.id.popup);
        popupLayout.setVisibility(View.VISIBLE);
        TextView popupTitle = (TextView) findViewById(R.id.textViewPopupTitle);
        TextView popupInfo = (TextView) findViewById(R.id.textViewPopupInfo);
        String title = "Add Task";
        String info = "Adding a task here will create a new task and add it into your manage tasks list.\n\nYou must enter a unique name for the task (max 20 char).\n\nSelect a "
        + "category for the task.\n\nEnter a description of the task (max 72 char).\n\nSelect an estimated duration for the task (max 99 mins).\n\nSetting the 'add task to active "
        + "tasks' switch to on will also add the tasks straight to your active tasks list.";
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