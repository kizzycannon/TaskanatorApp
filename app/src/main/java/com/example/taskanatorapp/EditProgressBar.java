package com.example.taskanatorapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;

public class EditProgressBar extends AppCompatActivity {
private ProgressBar progbar;
private TheProgressBar bar;
private EditText newvalue;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_progress_bar);
        progbar = (ProgressBar) findViewById(R.id.progressBarPage);
        newvalue = (EditText) findViewById(R.id.editProgress);
        progbar.setProgress(bar.returnCurrentProgress());
    }
    public void saveChanges(View view) {
        bar.setCurrentProgress(Integer.valueOf(String.valueOf(newvalue.getText())));
        progbar.setProgress(bar.returnCurrentProgress());
    }

    public void resetBar (View view){
       progbar.setProgress(0);
        bar.setCurrentProgress(0);
    }
}