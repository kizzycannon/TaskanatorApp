package com.example.taskanatorapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

public class EditProgressBar extends AppCompatActivity {
public android.widget.ProgressBar progbar;
private SystemProgressBar bar;
private EditText newvalue;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_progress_bar);

        bar = PrefConfig.loadProgressBar(this);
        if (bar == null){
            bar = new SystemProgressBar();
        }

        progbar = (android.widget.ProgressBar) findViewById(R.id.progressBarPage);
        newvalue = (EditText) findViewById(R.id.editProgress);
        progbar.setProgress(bar.returnCurrentProgress());
        progbar.setMax(bar.getProgressCap());
        TextView currentCapView = (TextView) findViewById(R.id.textViewEditProgCurrentCap);
        String currentCapText = "Current Progress Bar Goal: " + bar.getProgressCap() + " minutes";
        currentCapView.setText(currentCapText);
    }
    public void saveChanges(View view) {
        Intent backToMain = new Intent(this, MainActivity.class);
        int currentProgress = bar.returnCurrentProgress();
        bar.setProgressCap(Integer.valueOf(String.valueOf(newvalue.getText())));
        if (currentProgress <= bar.returnCurrentProgress()) {
            bar.setCurrentProgress(currentProgress);
        }
        else {
            bar.setCurrentProgress(Integer.valueOf(String.valueOf(newvalue.getText())));
        }
        progbar.setProgress(bar.returnCurrentProgress());
        PrefConfig.saveProgressBar(this, bar);
        startActivity(backToMain);
    }

    public void resetBar (View view){
       progbar.setProgress(0);
        bar.setCurrentProgress(0);
        PrefConfig.saveProgressBar(this, bar);
    }
}