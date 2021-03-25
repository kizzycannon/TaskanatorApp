package com.example.taskanatorapp;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.ArrayList;

public class AddToActiveTasks extends AppCompatActivity {
    //System system = (System)getApplicationContext(); //Tasks Persist?
    //ArrayList<Task> allTasks = system.getAllTasks(); //Tasks Persist?

    private RecyclerView recyclerView;
    private System system;
    private AddToActiveTasksAdapter adapter;
    private ArrayList<Task> selectedTasks = new ArrayList<>();

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_to_active_tasks);

        recyclerView = findViewById(R.id.addActiveRecyclerView);
        system = PrefConfig.loadSystem(this);
        if (system == null){
            system = new System();
        }
        LinearLayoutManager linearLayout = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayout);
        adapter = new AddToActiveTasksAdapter(system.getAllTasks(), new AddToActiveTasksAdapter.OnItemCheckListener() {
            @Override
            public void onItemCheck(Task item) {
                selectedTasks.add(item);
            }
            @Override
            public void onItemUncheck(Task item) {
                selectedTasks.remove(item);
            }
        });
        recyclerView.setAdapter(adapter);


    }

    public void buttonAddToActiveTasks(View view) {
        /*
        CheckBox taskCheck =  findViewById(R.id.addToActiveCheckBox);
        TextView taskName = findViewById(R.id.Task_Title);*/
        for (Task item :selectedTasks) {
            system.addToActiveTasks(item);
        }
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }


    public void buttonGenerateRandomTasksActivity(View view) {
        Intent intent = new Intent(this, GenerateRandomTask.class);
        startActivity(intent);
    }

    public void buttonManageTasks(View view) {
        Intent intent = new Intent(this, ManageTasks.class);
        startActivity(intent);
    }
}