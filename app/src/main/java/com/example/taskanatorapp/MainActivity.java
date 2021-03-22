package com.example.taskanatorapp;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.ProgressBar;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ArrayList<String> testData = new ArrayList<>();
    ArrayAdapter<String> testAdapter;
    ListView listView;
    System system = (System)getApplicationContext(); //Persisting Data - do not edit
    ArrayList<Task> activeTasks = system.getActiveTasks(); // Persisting Data - do not edit

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        testData.add("HELLO");
        testData.add("WORLD");
        testAdapter = new ArrayAdapter<String>(this, R.layout.activity_list, R.id.listText, testData);
        listView = findViewById(R.id.MyTasks);
        listView.setAdapter(testAdapter);
        listView.setBackground(this.getDrawable(R.drawable.list_view_border));

        //Initiating Default Example Tasks
        system.createNewTask("Play Tennis", "Sport", "Play 5 games of Tennis", 20);
        system.createNewTask("Watch TV", "Leisure", "Watch an Episode of GOT", 60);
        system.createNewTask("Water the Plants", "Chores", "Water the plants in the Kitchen", 5);
        system.createNewTask("Walk the Dog", "Chores", "Take the Dog to the park!", 30);
        system.createNewTask("Read a Book", "Leisure", "Read a chapter of Harry Potter!", 30);
        system.createNewTask("Work on Coursework", "Studying", "Work on CS991 Assignment", 60);
        system.addToActiveTasks(system.getAllTasks().get(0));
        system.addToActiveTasks(system.getAllTasks().get(1));
    }

    public void GoToAddTasks(View view){
        Intent intent = new Intent(this, AddTask.class);
        startActivity(intent);
    }


    /** temp method for testing generate random task
    public void GoToGenerateRandomTask(View view){
        Intent intent = new Intent(this, GenerateRandomTask.class);
        startActivity(intent);
    } */


}