package com.example.taskanatorapp;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
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

    public void showPopupView(View view) {
        LinearLayout popupLayout = findViewById(R.id.popup);
        Button addToTasks = (Button) findViewById(R.id.button_addtask);
        addToTasks.setAlpha((float) 0.3);
        popupLayout.setVisibility(View.VISIBLE);
        TextView popupTitle = (TextView) findViewById(R.id.textViewPopupTitle);
        TextView popupInfo = (TextView) findViewById(R.id.textViewPopupInfo);
        String title = "Add To Active Tasks";
        String info = "Select one or more tasks from your app's task list to add to your currently active tasks list by checking all tasks you wish to add then pressing the 'add to my tasks!' button.\n\n " +
                "Clicking the 'manage tasks' button will take you to a full list of tasks that have been added to your app's task list where you can edit or add more.\n\nClicking on 'generate random task' will "
        + "allow you to randomly select from your available tasks to add to your active tasks list so you can start working on them.";
        popupTitle.setText(title);
        popupInfo.setText(info);
        Button closePopup = (Button) findViewById(R.id.buttonPopupClose);
        closePopup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupLayout.setVisibility(View.INVISIBLE);
                addToTasks.setAlpha((float) 1);
            }
        });
    }
}