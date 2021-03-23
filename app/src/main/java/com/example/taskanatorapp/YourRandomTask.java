package com.example.taskanatorapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class YourRandomTask extends AppCompatActivity {
    private String taskName;
    private String taskDetails;
    private Random random;

    //need to have the input duration and category from before to reroll
    private int duration;
    private String category;

    /** test data fields */
    private ArrayList<Task> taskList;
    private ArrayList<Task> activeTasks;
    private ArrayList<Task> tasksForRandom;
    /** test data fields ^ */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_your_random_task);

        Intent intent = getIntent();
        String message = intent.getStringExtra(GenerateRandomTask.EXTRA_MESSAGE);


        /** sample test data */

        System system = new System();

        Task task1 = new Task("Task name 1", "Leisure", "description 1", 20);
        Task task2 = new Task("Task name 2", "Sport", "description 2", 60);
        Task task3 = new Task("Task name 3", "Other", "description 3", 10);
        //taskList = new ArrayList<>();
        system.createNewTask("Task name 1", "Leisure", "description 1", 20);
        system.createNewTask("Task name 2", "Sport", "description 2", 60);
        system.createNewTask("Task name 3", "Other", "description 3", 10);

        activeTasks = new ArrayList<>();
        taskList = system.getAllTasks();
        system.addToActiveTasks(task1);

        /**sample test data ^ */

        //turn message into index to retrieve the task
        int taskIndex = Integer.parseInt(message);

        //ArrayList<Task> systemTasks = system.getAllTasks();
        //Task randomTask = systemTasks.get(taskIndex);
        Task randomTask = taskList.get(taskIndex);
        taskName = randomTask.getTaskName();
        taskDetails = randomTask.getTaskDescription();

        TextView textViewTaskDetails = (TextView) findViewById(R.id.textViewTaskDetailsYRT);
        textViewTaskDetails.setText(taskDetails);
        TextView textViewTaskName = (TextView) findViewById(R.id.textViewTaskNameYRT);
        textViewTaskName.setText(taskName);

        //TextView textViewMessageTest = (TextView) findViewById(R.id.textViewTaskDetailsYRT);
        //textViewMessageTest.setText(message);

        Button buttonAccept = (Button) findViewById(R.id.buttonAcceptTaskYRT);
        Button buttonReroll = (Button) findViewById(R.id.buttonPickAgainYRT);

        //when task is accepted
        buttonAccept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //add to active task list
                activeTasks.add(taskList.get(taskIndex));

            }
        });
        //when task is rejected
        buttonReroll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //pick a new random task

                random = new Random();
                int rerollIndex = random.nextInt(taskList.size());
                while (rerollIndex == taskIndex) {
                    rerollIndex = random.nextInt(taskList.size());
                }




            }
        });
    }
}