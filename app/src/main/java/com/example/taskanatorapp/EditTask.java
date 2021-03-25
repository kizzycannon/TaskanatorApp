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


        TextView taskNameView = (TextView) findViewById(R.id.editTaskName);
        TextView taskDescriptionView = (TextView) findViewById(R.id.editTaskDescription);
        TextView taskDurationView = (TextView) findViewById(R.id.editTaskDuration);
        Spinner taskCategoryView = (Spinner)findViewById(R.id.editTaskCategory);

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

                EditText taskNameViewEdit = (EditText) findViewById(R.id.editTaskName);
                EditText taskDescriptionViewEdit = (EditText) findViewById(R.id.editTaskDescription);
                EditText taskDurationViewEdit = (EditText) findViewById(R.id.editTaskDuration);
                TextView taskDescriptionTitle = (TextView) findViewById(R.id.editTaskDescriptionTitle);
                TextView taskDurationTitle = (TextView) findViewById(R.id.editTaskDurationTitle);
                TextView taskNameTitle = (TextView)findViewById(R.id.editTaskNameTitle);
                TextView taskCategoryTitle = (TextView)findViewById(R.id.editTaskNameCategory);

                // error check for null inputs
                if (taskNameViewEdit.getText() == null || taskNameViewEdit.getText().length() == 0) {
                    taskNameTitle.setText(R.string.add_edit_tasks_task_name_empty_error);
                    taskCategoryTitle.setText(R.string.add_edit_tasks_task_category);
                    taskDescriptionTitle.setText(R.string.add_edit_tasks_task_description);
                    taskDurationTitle.setText(R.string.add_edit_tasks_task_duration);
                    return;
                }
                if (taskDescriptionViewEdit.getText() == null || taskDescriptionViewEdit.getText().length() == 0) {
                    taskNameTitle.setText(R.string.add_edit_tasks_task_name);
                    taskCategoryTitle.setText(R.string.add_edit_tasks_task_category);
                    taskDescriptionTitle.setText(R.string.add_edit_tasks_task_description_empty_error);
                    taskDurationTitle.setText(R.string.add_edit_tasks_task_duration);
                    return;
                }
                if (taskDurationViewEdit.getText() == null || taskDurationViewEdit.getText().length() == 0) {
                    taskNameTitle.setText(R.string.add_edit_tasks_task_name);
                    taskCategoryTitle.setText(R.string.add_edit_tasks_task_category);
                    taskDescriptionTitle.setText(R.string.add_edit_tasks_task_description);
                    taskDurationTitle.setText(R.string.add_edit_tasks_task_duration_empty_error);
                    return;
                }

                ArrayList<Task> taskList = system.getAllTasks();
                ArrayList<Task> activeTaskList = system.getActiveTasks();
                String newTaskName = taskNameViewEdit.getText().toString();

                for (Task task: taskList) {
                    if(task.getTaskName().equals(newTaskName) && !taskName.equals(newTaskName)){
                        taskNameTitle = (TextView)findViewById(R.id.editTaskNameTitle);
                        taskCategoryTitle = (TextView)findViewById(R.id.editTaskNameCategory);

                        taskNameTitle.setText(R.string.add_edit_tasks_task_name_error);
                        taskCategoryTitle.setText(R.string.add_edit_tasks_task_category);
                        return;
                    }
                }
                if(taskCategory.equals("Select Category")){
                    taskCategoryTitle = (TextView)findViewById(R.id.editTaskNameCategory);
                    taskNameTitle = (TextView)findViewById(R.id.editTaskNameTitle);

                    taskNameTitle.setText(R.string.add_edit_tasks_task_name);
                    taskCategoryTitle.setText(R.string.add_edit_tasks_task_category_error);
                    return;
                }

                //update the task details with the user inputs
                Task editingTask = system.getAllTasks().get(taskID);
                editingTask.setTaskName(taskNameViewEdit.getText().toString());
                editingTask.setTaskCategory(taskCategoryView.getSelectedItem().toString());
                editingTask.setTaskDescription(taskDescriptionViewEdit.getText().toString());
                editingTask.setTaskLength(Integer.parseInt(taskDurationViewEdit.getText().toString()));
                for (Task task : activeTaskList) {
                    if (task.getTaskName().equals(taskName)) {
                        task.setTaskName(taskNameViewEdit.getText().toString());
                        task.setTaskCategory(taskCategoryView.getSelectedItem().toString());
                        task.setTaskDescription(taskDescriptionViewEdit.getText().toString());
                        task.setTaskLength(Integer.parseInt(taskDurationViewEdit.getText().toString()));
                        break;
                    }
                }

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