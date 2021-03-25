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
import java.util.Arrays;

public class EditTask extends AppCompatActivity {

    public static final String EXTRA_TASK_ID = "task id";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_task);

        System system = PrefConfig.loadSystem(this);

        int taskID = (Integer)getIntent().getExtras().get(EXTRA_TASK_ID);
        String taskName = system.getAllTasks().get(taskID).getTaskName();
        String taskCategory = system.getAllTasks().get(taskID).getTaskCategory();
        String taskDescription = system.getAllTasks().get(taskID).getTaskDescription();
        int taskDuration = system.getAllTasks().get(taskID).getTaskLength();

        EditText taskNameView = (EditText) findViewById(R.id.editTaskName);
        Spinner taskCategoryView = (Spinner)findViewById(R.id.editTaskCategory);
        EditText taskDescriptionView = (EditText) findViewById(R.id.editTaskDescription);
        EditText taskDurationView = (EditText) findViewById(R.id.editTaskDuration);

        String[] options = getResources().getStringArray(R.array.add_categories);

        taskNameView.setText(String.valueOf(taskName));
        taskCategoryView.setSelection(Arrays.asList(options).indexOf(taskCategory));
        taskDescriptionView.setText(String.valueOf(taskDescription));
        taskDurationView.setText(String.valueOf(taskDuration));

        Button buttonAddTask = (Button)findViewById(R.id.buttonSaveEditTask);
        Button buttonDeleteTask = (Button)findViewById(R.id.buttonDeleteTask);
        buttonAddTask.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                EditText taskNameView = (EditText) findViewById(R.id.editTaskName);
                Spinner taskCategoryView = (Spinner)findViewById(R.id.editTaskCategory);
                EditText taskDescriptionView = (EditText) findViewById(R.id.editTaskDescription);
                EditText taskDurationView = (EditText) findViewById(R.id.editTaskDuration);

                Task editingTask = system.getAllTasks().get(taskID);
                editingTask.setTaskName(taskNameView.getText().toString());
                editingTask.setTaskCategory(taskCategoryView.getSelectedItem().toString());
                editingTask.setTaskDescription(taskDescriptionView.getText().toString());
                editingTask.setTaskLength(Integer.parseInt(taskDurationView.getText().toString()));


                ArrayList<Task> taskList = system.getAllTasks();
                String newTaskName = taskNameView.getText().toString();

                for (Task task: taskList) {
                    if(task.getTaskName().equals(taskName) && taskName != newTaskName){
                        TextView taskNameTitle = (TextView)findViewById(R.id.editTaskNameTitle);
                        TextView taskCategoryTitle = (TextView)findViewById(R.id.editTaskNameCategory);

                        taskNameTitle.setText(R.string.add_edit_tasks_task_name_error);
                        taskCategoryTitle.setText(R.string.add_edit_tasks_task_category);
                        return;
                    }
                }
                if(taskCategory.equals("Select Category")){
                    TextView taskCategoryTitle = (TextView)findViewById(R.id.editTaskNameCategory);
                    TextView taskNameTitle = (TextView)findViewById(R.id.editTaskNameTitle);

                    taskNameTitle.setText(R.string.add_edit_tasks_task_name);
                    taskCategoryTitle.setText(R.string.add_edit_tasks_task_category_error);
                    return;
                }

                system.createNewTask(taskName, taskCategory, taskDescription, taskDuration);

                PrefConfig.saveSystem(EditTask.this, system);

                Intent intent = new Intent(EditTask.this, ManageTasks.class);
                startActivity(intent);
            }
        });

        buttonDeleteTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                system.getAllTasks().remove(taskID);

                ArrayList<Task> activeTaskList = system.getActiveTasks();
                int taskPosition = 0;

                for(Task task: activeTaskList){
                    if(task.getTaskName().equals(taskName)){
                        break;
                    } else {
                        taskPosition++;
                    }
                }

                if(taskPosition <= activeTaskList.size()-1){
                    system.getActiveTasks().remove(taskID);
                }

                PrefConfig.saveSystem(EditTask.this, system);

                Intent intent = new Intent(EditTask.this, ManageTasks.class);
                startActivity(intent);
            }
        });
    }
}