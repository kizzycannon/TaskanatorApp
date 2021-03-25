package com.example.taskanatorapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class EditProgressBar extends AppCompatActivity {
public android.widget.ProgressBar progbar;
private SystemProgressBar bar;
private EditText newvalue;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_progress_bar);

        progbar = (android.widget.ProgressBar) findViewById(R.id.progressBarPage);
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