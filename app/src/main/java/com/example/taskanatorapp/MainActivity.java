package com.example.taskanatorapp;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.widget.ProgressBar;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    System system = new System();
    //System system = (System)getApplicationContext(); //Persisting Data - do not edit
    //ArrayList<Task> activeTasks = system.getActiveTasks(); // Persisting Data - do not edit

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


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

    public void GoToManageTasks(View view){
        Intent intent = new Intent(this, ManageTasks.class);
        startActivity(intent);
    }


    /** temp method for testing generate random task
    public void GoToGenerateRandomTask(View view){
        Intent intent = new Intent(this, GenerateRandomTask.class);
        startActivity(intent);
    } */


}