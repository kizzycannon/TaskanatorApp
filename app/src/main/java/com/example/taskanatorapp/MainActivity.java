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
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private System system;
    private RecyclerView recyclerView;
    private ActiveTasksAdapter adapter;
    public SystemProgressBar bar;
    private ProgressBar progbar;
    TextView progressText;



    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bar = PrefConfig.loadProgressBar(this);
        if (bar == null){
            bar = new SystemProgressBar();
        }
        progbar = (ProgressBar) findViewById(R.id.progressBarMain);
        progbar.setProgress(bar.returnCurrentProgress());
        progbar.setMax(bar.getProgressCap());
        progressText = (TextView) findViewById(R.id.progressText);
        int progressToGo;
        if (bar.getProgressCap() - bar.returnCurrentProgress() > 0) {
            progressToGo = bar.getProgressCap() - bar.returnCurrentProgress();
            progressText.setText(progressToGo + " mins to go!");
        }
        else {
            progressText.setText("Current Progress Goal Complete!");
        }
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
            system.addToActiveTasks(system.getAllTasks().get(0));
            system.addToActiveTasks(system.getAllTasks().get(1));
            system.addToActiveTasks(system.getAllTasks().get(2));
            system.addToActiveTasks(system.getAllTasks().get(3));

        }

        PrefConfig.saveSystem(this, system);
        PrefConfig.saveProgressBar(this, bar);
    }

    public void goToManageTasks(View view){
        Intent intent = new Intent(this, ManageTasks.class);
        startActivity(intent);
    }

    public void goToAddToActiveTasks(View view){
        Intent intent = new Intent(this, AddToActiveTasks.class);
        startActivity(intent);
    }

    public void goToAddToCompletedTasks(View view){
        Intent intent = new Intent(this, CompletedTasks.class);
        startActivity(intent);
    }

    public void goToEditProgressBar(View view){
        Intent intent = new Intent(this, EditProgressBar.class);
        startActivity(intent);
    }

}