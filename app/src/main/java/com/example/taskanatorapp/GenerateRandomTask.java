package com.example.taskanatorapp;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.Random;
import java.util.ArrayList;
import android.view.View.OnClickListener;

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
    private TextView errorView;
    private ArrayList<Task> taskList;
    private ArrayList<Task> activeTasks;
    private ArrayList<Task> tasksForRandom;

    private System system;

    public static final String EXTRA_MESSAGE = "com.example.taskanatorapp.MESSAGE";
    public static final String INTEGER_ARRAY = "com.example.taskanatorapp.INTEGER_ARRAY";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_generate_random_task);
        errorView = (TextView) findViewById(R.id.textViewErrorMessageGRT);
        errorView.setText("");
        durationInput = (EditText) findViewById(R.id.editTextNumberGRT);
        durationInputView = (TextView) findViewById(R.id.editTextNumberGRT);
        categories = getResources().getStringArray(R.array.categories);
        //System system = new System();
        random = new Random();

        system = PrefConfig.loadSystem(this);

        taskList = system.getAllTasks();
        activeTasks = system.getActiveTasks();
        tasksForRandom = new ArrayList<>();
        ImageView mascotImage = (ImageView) findViewById(R.id.imageViewTaskanatorMascot);


        RotateAnimation anim = new RotateAnimation(-4.0f, 4.0f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        RotateAnimation anim2 = new RotateAnimation(-2.0f, 2.0f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);


//Setup anim with desired properties
        anim.setInterpolator(new LinearInterpolator());
        //anim.setRepeatCount(Animation.INFINITE); //Repeat animation indefinitely
        anim.setRepeatCount(1);
        anim2.setRepeatMode(1);
        anim.setDuration(400); //Put desired duration per anim cycle here, in milliseconds
        anim2.setDuration(300);

//Start animation
        mascotImage.startAnimation(anim);
//Later on, use view.setAnimation(null) to stop it.

        spinner = (Spinner) findViewById(R.id.spinnerGRT);

        Button button = (Button) findViewById(R.id.buttonGoGRT);
        button.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            public void onClick(View v) {

                Intent intent = new Intent(GenerateRandomTask.this, YourRandomTask.class);
                errorView.setText("");
                mascotImage.setImageDrawable(getDrawable(R.drawable.taskanator_concept_alpha));

                tasksForRandom.clear();

                if (durationInput.getText() == null || durationInput.getText().length() == 0 || durationInput.getText().toString().equals("0")) {
                    errorView.setText("The duration must be set to something, either using the bar or the input text box.");
                    mascotImage.setImageDrawable(getDrawable(R.drawable.taskanator_concepts_alpha_2__resize_));
                    mascotImage.startAnimation(anim2);
                    return;
                } else {
                    duration = Integer.parseInt(durationInput.getText().toString());
                    if (taskList.isEmpty()) {
                        errorView.setText("Error: the system task list is empty, cannot generate random task.");
                        mascotImage.setImageDrawable(getDrawable(R.drawable.taskanator_concepts_alpha_2__resize_));
                        mascotImage.startAnimation(anim2);
                        return;
                    } else if (selectedCategory.equals("All")) {
                        for (Task task : taskList) {
                            boolean isInActiveTasks = false;
                            for (Task taskActive : activeTasks) {
                                if (taskActive.getTaskName().equals(task.getTaskName()) && taskActive.getTaskCategory().equals(task.getTaskCategory())
                                        && taskActive.getTaskDescription().equals(task.getTaskDescription())) {
                                    isInActiveTasks = true;
                                    break;
                                }
                            }
                            if (task.getTaskLength() <= duration && !isInActiveTasks) {
                                tasksForRandom.add(task);
                            }
                        }
                    } else {
                        for (Task task : taskList) {
                            boolean isInActiveTasks = false;
                            for (Task taskActive : activeTasks) {
                                Log.d("taskActiveName: ", taskActive.getTaskName() + "-" + task.getTaskName());
                                //Log.d("TaskName: ", task.getTaskName());
                                if (taskActive.getTaskName().equals(task.getTaskName()) && taskActive.getTaskCategory().equals(task.getTaskCategory())
                                        && taskActive.getTaskDescription().equals(task.getTaskDescription())) {
                                    isInActiveTasks = true;
                                    break;
                                }
                            }
                            if (task.getTaskCategory().contains(selectedCategory) && task.getTaskLength() <= duration && !isInActiveTasks) {
                                tasksForRandom.add(task);
                            }
                        }
                    }
                    if (tasksForRandom.isEmpty()) {
                        errorView.setText("Error: all tasks available in the system with the desired category and duration are already in the active tasks list or don't exist.");
                        mascotImage.setImageDrawable(getDrawable(R.drawable.taskanator_concepts_alpha_2__resize_));
                        mascotImage.startAnimation(anim2);
                    } else {
                        int randomIndex = random.nextInt(tasksForRandom.size());
                        Task randomTask = tasksForRandom.get(randomIndex);
                        String indexInSystemTasks = String.valueOf(taskList.indexOf(randomTask));
                        //String message = indexInSystemTasks;

                        //array of needed info in order: duration, selectedCategory, indexInSystemTasks
                        ArrayList<String> intentArrayInfo = new ArrayList<>();
                        intentArrayInfo.add(durationInput.getText().toString());
                        intentArrayInfo.add(selectedCategory);
                        intentArrayInfo.add(indexInSystemTasks);
                        intent.putStringArrayListExtra(EXTRA_MESSAGE, intentArrayInfo);
                        ArrayList<String> indexRandomArray = new ArrayList<>();
                        //int indexInArray = 0;
                        for (Task task : tasksForRandom) {
                            String index = String.valueOf(taskList.indexOf(task));
                            indexRandomArray.add(index);
                        }
                        intent.putStringArrayListExtra(INTEGER_ARRAY, indexRandomArray);
                        startActivity(intent);
                    }
                }
            }

        });


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
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                durationInputView.setText(String.valueOf(progressChange));
            }
        });
        durationInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (durationInput.getText().length() == 0 || durationInput.getText() == null) {
                    seekBarInstanceVariable.setProgress(0);
                } else {
                    seekBarInstanceVariable.setProgress(Integer.parseInt(durationInput.getText().toString()));
                }
            }
        });
    }


    /**
     * getter for duration of random task.
     *
     * @return the user's input desired max duration of a task.
     */
    public int getDuration() {
        return duration;
    }

    /**
     * getter for selected category of random task.
     *
     * @return the user's input desired category of a task.
     */
    public String getSelectedCategory() {
        return selectedCategory;
    }

    /**
     * getter for task list.
     *
     * @return the system task list.
     */
    public ArrayList<Task> getTaskList() {
        return taskList;
    }

    /**
     * getter for active task list.
     *
     * @return the active task list.
     */
    public ArrayList<Task> getActiveTasks() {
        return activeTasks;
    }

    /**
     * getter for list of tasks to be randomly generated from.
     *
     * @return the random task list from the chosen category and duration.
     */
    public ArrayList<Task> getTasksForRandom() {
        return tasksForRandom;
    }

    public void showPopupView(View view) {
        LinearLayout popupLayout = findViewById(R.id.popup);
        popupLayout.setVisibility(View.VISIBLE);
        TextView popupTitle = (TextView) findViewById(R.id.textViewPopupTitle);
        TextView popupInfo = (TextView) findViewById(R.id.textViewPopupInfo);
        String title = "Random Task Generator";
        String info = "Select a duration of up to 99 minutes using the text input box or the slider and select a category to " +
                "generate from.\n\nA random task will be selected from your existing tasks in the app which are not already in your active tasks list.\n\nGo to manage tasks to add to/edit these.";
        popupTitle.setText(title);
        popupInfo.setText(info);
        Button closePopup = (Button) findViewById(R.id.buttonPopupClose);
        closePopup.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                popupLayout.setVisibility(View.INVISIBLE);
            }
        });
    }
}