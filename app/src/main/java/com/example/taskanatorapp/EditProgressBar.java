package com.example.taskanatorapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

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
        progbar.setMax(bar.getProgressCap());
        progbar.setProgress(bar.returnCurrentProgress());
        TextView currentCapView = (TextView) findViewById(R.id.textViewEditProgCurrentCap);
        String currentCapText = "Current Progress Bar Goal: " + bar.getProgressCap() + " minutes";
        currentCapView.setText(currentCapText);
    }
    public void saveChanges(View view) {
        Intent backToMain = new Intent(this, MainActivity.class);
        int currentProgress = bar.returnCurrentProgress();
        if (newvalue.getText() != null && !newvalue.getText().toString().equals("")) {
            bar.setProgressCap(Integer.valueOf(String.valueOf(newvalue.getText())));
            if (currentProgress <= bar.returnCurrentProgress()) {
                bar.setCurrentProgress(currentProgress);
            } else {
                bar.setCurrentProgress(Integer.valueOf(String.valueOf(newvalue.getText())));
            }
            progbar.setProgress(bar.returnCurrentProgress());
            PrefConfig.saveProgressBar(this, bar);
            Toast.makeText(EditProgressBar.this, "Progress Bar updated", Toast.LENGTH_SHORT).show();

        }
        startActivity(backToMain);
    }

    public void resetBar (View view){
       progbar.setProgress(0);
        bar.setCurrentProgress(0);
        PrefConfig.saveProgressBar(this, bar);
    }

    public void showPopupView(View view) {
        LinearLayout popupLayout = findViewById(R.id.popup);
        popupLayout.setVisibility(View.VISIBLE);
        TextView popupTitle = (TextView) findViewById(R.id.textViewPopupTitle);
        TextView popupInfo = (TextView) findViewById(R.id.textViewPopupInfo);
        String titlePopup = "Edit Progress Bar";
        String infoPopup = "The target time you wish to set as a goal to have spent on your tasks can be changed here by entering a number of minutes into the text box and clicking on 'save changes'."
        + "\n\nThe 'reset progress bar' button will reset the progress of the bar to zero. The bar fills by the set duration of each task you mark as completed while there is still progress to be made.";
        popupTitle.setText(titlePopup);
        popupInfo.setText(infoPopup);
        Button closePopup = (Button) findViewById(R.id.buttonPopupClose);
        closePopup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupLayout.setVisibility(View.INVISIBLE);
            }
        });
    }
}