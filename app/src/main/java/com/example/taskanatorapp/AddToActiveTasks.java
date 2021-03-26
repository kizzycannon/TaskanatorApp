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
import android.widget.Toast;

import java.util.ArrayList;

public class AddToActiveTasks<adapter> extends AppCompatActivity {
    //System system = (System)getApplicationContext(); //Tasks Persist?
    //ArrayList<Task> allTasks = system.getAllTasks(); //Tasks Persist?

    private RecyclerView recyclerView;
    private System system;
    private AddToActiveTasksAdapter adapter;
    private ArrayList<Task> selectedTasks = new ArrayList<>();
    private ArrayList<Task> availableTasks = new ArrayList<>();

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

        ArrayList<Task> allTasks = system.getAllTasks();
        ArrayList<Task> activeTasks = system.getActiveTasks();

        adapter = new AddToActiveTasksAdapter(availableTasks, new AddToActiveTasksAdapter.OnItemCheckListener() {
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

        for(Task task: allTasks) {
            boolean isInActiveTasks = false;
            for(Task taskActive: activeTasks) {
                if (taskActive.getTaskName().equals(task.getTaskName()) && taskActive.getTaskCategory().equals(task.getTaskCategory())
                        && taskActive.getTaskDescription().equals(task.getTaskDescription())) {
                    isInActiveTasks = true;
                    break;
                }
            }
            if(!isInActiveTasks){
                availableTasks.add(task);
            }
        }
    }

    public void buttonAddToActiveTasks(View view) {
        for (Task item :selectedTasks) {
            system.addToActiveTasks(item);
        }
        PrefConfig.saveSystem(this, system);
        Toast.makeText(AddToActiveTasks.this, "Task(s) added", Toast.LENGTH_SHORT).show();
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