package com.example.taskanatorapp;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.widget.ProgressBar;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private System system;
    private RecyclerView recyclerView;
    private ActiveTasksAdapter adapter;


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.ActiveTasks);
        system = PrefConfig.loadSystem(this);
        if (system == null){
            system = new System();
        }

        LinearLayoutManager linearLayout = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayout);
        adapter = new ActiveTasksAdapter(system.getActiveTasks());
        recyclerView.setAdapter(adapter);


        //system.getAllTasks().clear(); //clear the system of the tasks
        //system.getActiveTasks().clear();

        //Initiating Default Example Tasks

        if (system.getAllTasks().size() == 0) {
            system.createNewTask("Play Tennis", "Sport", "Play 5 games of Tennis", 20);
            system.createNewTask("Watch TV", "Leisure", "Watch an Episode of GOT", 60);
            system.createNewTask("Water the Plants", "Chores", "Water the plants in the Kitchen", 5);
            system.createNewTask("Walk the Dog", "Chores", "Take the Dog to the park!", 30);
            system.createNewTask("Read a Book", "Leisure", "Read a chapter of Harry Potter!", 30);
            system.createNewTask("Work on Coursework", "Studying", "Work on CS991 Assignment", 60);

        }
        // temporary initialisation of active tasks if none in system
        if (system.getActiveTasks().size() == 0) {
            system.addToActiveTasks(system.getAllTasks().get(0));
            system.addToActiveTasks(system.getAllTasks().get(1));
            system.addToActiveTasks(system.getAllTasks().get(2));
            system.addToActiveTasks(system.getAllTasks().get(3));
        }
        PrefConfig.saveSystem(this, system);
    }

    public void goToManageTasks(View view){
        Intent intent = new Intent(this, ManageTasks.class);
        startActivity(intent);
    }

    public void goToAddToActiveTasks(View view){
        Intent intent = new Intent(this, AddToActiveTasks.class);
        startActivity(intent);
    }

}