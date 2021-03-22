package com.example.taskanatorapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.Random;
import java.util.ArrayList;

public class GenerateRandomTask extends AppCompatActivity {

    //ArrayList of tasks to be chosen from which meet the criteria
    private ArrayList<Task> tasksMeetingCriteriaList;
    //duration specified as maximum time for random task
    private int duration;
    private String[] categories;
    private String selectedCategory;
    private Random random;
    private Spinner spinner;
    private EditText durationInput;
    private TextView durationInputView;
    private ArrayList<Task> taskList;
    private ArrayList<Task> activeTasks;
    private ArrayList<Task> tasksForRandom;
    public static final String RANDOM_TASK_NAME = "com.example.taskanatorapp.RANDOMTASKNAME";

    public GenerateRandomTask() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_generate_random_task);

        durationInput = (EditText) findViewById(R.id.editTextNumberGRT);
        durationInputView = (TextView) findViewById(R.id.editTextNumberGRT);
        categories = getResources().getStringArray(R.array.categories);
        System system = new System();
        taskList = system.getAllTasks();
        activeTasks = system.getActiveTasks();
        tasksForRandom = new ArrayList<>();

        spinner = (Spinner) findViewById(R.id.spinnerGRT);


        ArrayAdapter adapter = new ArrayAdapter(getApplicationContext(), android.R.layout.simple_spinner_item, categories);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // get the selected element id
                int getid = parent.getSelectedItemPosition();
                //get the category at the selected position
                String getValue = String.valueOf(parent.getItemAtPosition(position));
                selectedCategory = getValue;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        SeekBar seekBarInstanceVariable = (SeekBar) findViewById(R.id.seekBarGRT);

        seekBarInstanceVariable.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            int progressChange = 0;
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                progressChange = progress;
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                //TODO Auto-generated method stub
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                durationInputView.setText(String.valueOf(progressChange));
            }
        });
    }

    public void buttonGenerateRandomTask(View view) {
        Intent intent = new Intent(this, YourRandomTask.class);
        duration = Integer.parseInt(durationInput.getText().toString());
        tasksForRandom.clear();

        if (selectedCategory.equals("All")) {
            for(Task task : taskList) {
                if(task.getTaskLength() <= duration && !activeTasks.contains(task)) {
                    tasksForRandom.add(task);
                }
            }
        }
        else {
            for (Task task : taskList) {
                if (task.getTaskCategory().equals(selectedCategory) && task.getTaskLength() <= duration && !activeTasks.contains(task)) {
                    tasksForRandom.add(task);
                }
            }
        }

        int randomIndex = random.nextInt(tasksForRandom.size());
        Task randomTask = tasksForRandom.get(randomIndex);

        //Spinner spinner = (Spinner) findViewById(R.id.spinnerGRT);
        //category = spinner.get
        startActivity(intent);
    }
}