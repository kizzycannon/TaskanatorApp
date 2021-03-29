package com.example.taskanatorapp;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.widget.Button;
import android.widget.LinearLayout;
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
    TextView progressCompletedText;



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
        progbar.setMax(bar.getProgressCap());
        progbar.setProgress(bar.returnCurrentProgress());
        progressText = (TextView) findViewById(R.id.progressText);
        progressCompletedText = (TextView) findViewById(R.id.textViewMainTimeCompleted);
        int progressToGo;
        if (bar.getProgressCap() - bar.returnCurrentProgress() > 0) {
            progressToGo = bar.getProgressCap() - bar.returnCurrentProgress();
            progressText.setText(progressToGo + " mins to go!");
        }
        else {
            progressText.setText("Current Progress Goal Complete!");
        }
        String progressCompletedString = bar.returnCurrentProgress() + " mins already completed.";
        progressCompletedText.setText(progressCompletedString);
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

    public void showPopupView(View view) {
        LinearLayout popupLayout = findViewById(R.id.popup);
        popupLayout.setVisibility(View.VISIBLE);
        TextView popupTitle = (TextView) findViewById(R.id.textViewPopupTitle);
        TextView popupInfo = (TextView) findViewById(R.id.textViewPopupInfo);
        Button completedTasks = (Button) findViewById(R.id.buttonGoToCompletedTasks);
        completedTasks.setVisibility(View.INVISIBLE);
        popupTitle.setTextSize(18);
        popupInfo.setTextSize(14);
        String title = "Active Tasks";
        String info = "This is the Taskanator App!\n\nYour active tasks page is the main page of the app. Here you can click on a "
        + "task in your active task list to see more details and mark it complete.\n\nThe 'completed tasks' button will take you to a list of all tasks you have previously completed.\n\n"
                + "The progress bar shows how much time you've spent on completed active tasks compared with the goal you set yourself. The pencil icon will allow you to set the time goal you wish to achieve with the tasks you complete and to reset the progress bar.\n\n"
                + "" +
                "The 'manage' button will let you manage your overall task list in the app. Here you'll be able to create new tasks, edit existing ones and delete unwanted tasks.\n\n"
                + "The '+' button takes you to the 'add to active tasks' page, where you can select tasks from the app to add to your currently active list and go to generate random task.";
        popupTitle.setText(title);
        popupInfo.setText(info);
        Button closePopup = (Button) findViewById(R.id.buttonPopupClose);
        closePopup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupLayout.setVisibility(View.INVISIBLE);
                completedTasks.setVisibility(View.VISIBLE);
            }
        });
    }

}