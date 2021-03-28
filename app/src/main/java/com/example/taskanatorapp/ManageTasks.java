package com.example.taskanatorapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class ManageTasks extends AppCompatActivity {
    private RecyclerView recyclerView;
    private System system;
    private ManageTasksAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_tasks);

        system = PrefConfig.loadSystem(this);
        recyclerView = findViewById(R.id.ManageTasksrecyclerView);

        if (system == null){
            system = new System();
        }

        LinearLayoutManager linearLayout = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayout);
        adapter = new ManageTasksAdapter(system.getAllTasks());
        recyclerView.setAdapter(adapter);
        PrefConfig.saveSystem(this, system);
    }

    public void buttonActiveTasks(View view) {
        Intent intent1 = new Intent(this, MainActivity.class);
        startActivity(intent1);
    }

    public void buttonAddTask(View view) {
        Intent intent2 = new Intent(this, AddTask.class);
        startActivity(intent2);
    }

    public void showPopupView(View view) {
        LinearLayout popupLayout = findViewById(R.id.popup);
        popupLayout.setVisibility(View.VISIBLE);
        TextView popupTitle = (TextView) findViewById(R.id.textViewPopupTitle);
        TextView popupInfo = (TextView) findViewById(R.id.textViewPopupInfo);
        String title = "Manage Tasks";
        String info = "Here you can view all of the tasks in your app's overall list.\n\n Clicking on a task will allow you to either edit the details of the task or delete it from the manage tasks list."
                + "\n\nClicking the '+' button will allow you to create a new task to add into the manage tasks list." +
                "\n\n'Back to active tasks' returns you to the app's main page.";
        popupTitle.setText(title);
        popupInfo.setText(info);
        Button closePopup = (Button) findViewById(R.id.buttonPopupClose);
        closePopup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupLayout.setVisibility(View.INVISIBLE);
            }
        });
    }
}