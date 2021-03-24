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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class YourRandomTask extends AppCompatActivity {
    private String taskName;
    private String taskDetails;
    private Random random;
    private System system;

    //need to have the input duration and category from before to reroll
    private int duration;
    private String category;
    private int taskIndex;
    TextView textViewTaskDetails;
    TextView textViewTaskName;
    TextView errorView;
    ImageView categoryImage;


    /** test data fields */
    private ArrayList<Task> taskList;
    private ArrayList<Task> activeTasks;
    private ArrayList<Task> tasksForRandom;
    /** test data fields ^ */

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_your_random_task);

        system = PrefConfig.loadSystem(this);
        textViewTaskDetails = (TextView) findViewById(R.id.textViewTaskDetailsYRT);
        textViewTaskName = (TextView) findViewById(R.id.textViewTaskNameYRT);
        errorView = (TextView) findViewById(R.id.textViewErrorYRT);
        categoryImage = (ImageView) findViewById(R.id.imageViewTaskCategoryYRT);
        errorView.setText("");
        Intent intent = getIntent();
        ArrayList<String> randomGenerateInfo = intent.getStringArrayListExtra(GenerateRandomTask.EXTRA_MESSAGE);
        duration = Integer.parseInt(randomGenerateInfo.get(0));
        category = randomGenerateInfo.get(1);
        taskIndex = Integer.parseInt(randomGenerateInfo.get(2));
        ArrayList<String> randomIndicesArray = intent.getStringArrayListExtra(GenerateRandomTask.INTEGER_ARRAY);

        activeTasks = system.getActiveTasks();
        taskList = system.getAllTasks();

        //turn message into index to retrieve the task
        //int taskIndex = Integer.parseInt(message);

        //ArrayList<Task> systemTasks = system.getAllTasks();
        //Task randomTask = systemTasks.get(taskIndex);
        Task randomTask = taskList.get(taskIndex);
        taskName = randomTask.getTaskName();
        taskDetails = randomTask.getTaskDescription();


        textViewTaskDetails.setText(taskDetails);
        textViewTaskName.setText(taskName);
        //in case All is selected obtain category from task selected
        String taskCategory = randomTask.getTaskCategory();
        //category images
        String[] categoryNames = {"Chores", "Sport", "Leisure", "Studying", "Other"};
        int[] images = {R.drawable.taskanator_icon___chores, R.drawable.taskanator_icon___sport, R.drawable.taskanator_icon___leisure, R.drawable.taskanator_icon___studying, R.drawable.taskanator_icon___other};
        HashMap<String, Integer> hashMap = new HashMap<>();
        for (int i = 0; i < categoryNames.length; i++) {
            hashMap.put(categoryNames[i], images[i]);
        }
        categoryImage.setImageDrawable(getDrawable(hashMap.get(taskCategory)));

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
                //sends back to active task page
                PrefConfig.saveSystem(YourRandomTask.this, system);
                Intent intent = new Intent(YourRandomTask.this, MainActivity.class);
                startActivity(intent);
            }
        });
        //when task is rejected
        buttonReroll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //temporarily go back to generate random task activity
                //Intent goBack = new Intent(YourRandomTask.this, GenerateRandomTask.class);
                //startActivity(goBack);

                //pick a new random task
                int rerollIndex;
                int newTaskIndex = 0;
                if (randomIndicesArray.size() == 1) {
                    errorView.setText("Error: Only one task available to add to active tasks with the chosen criteria.");
                }
                else {
                    random = new Random();

                    boolean isNewIndex = false;
                    while (!isNewIndex) {
                        rerollIndex = random.nextInt(randomIndicesArray.size());
                        newTaskIndex = Integer.parseInt(randomIndicesArray.get(rerollIndex));
                        if (newTaskIndex != taskIndex) {
                            isNewIndex = true;
                        }
                    }
                    taskIndex = newTaskIndex;
                    Task newRandomTask = taskList.get(taskIndex);
                    textViewTaskDetails.setText(newRandomTask.getTaskDescription());
                    textViewTaskName.setText(newRandomTask.getTaskName());
                    String newTaskCategory = newRandomTask.getTaskCategory();
                    categoryImage.setImageDrawable(getDrawable(hashMap.get(newTaskCategory)));
                }
            }
        });
    }
}