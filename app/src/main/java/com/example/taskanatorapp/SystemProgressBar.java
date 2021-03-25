package com.example.taskanatorapp;

import android.widget.ProgressBar;

public class SystemProgressBar {


    //instance variables
    private android.widget.ProgressBar progressbar;
    private int progressCap = 120;
    private int currentProgress = 10;

    //set the target productivity goal in minutes
    public void setProgressCap(int input) {
        this.progressCap = input;
    }

    // add progress points to the progress bar in minutes
    public void addProgressPoints(int input) {
        this.currentProgress += input;

    }

    // reset the progress bar to 0
    public void resetProgressBar() {
        this.currentProgress = 0;
    }

    // reset the progress bar to 0
    public int returnCurrentProgress() {
        return currentProgress;
    }

    public void setCurrentProgress(int input) {
        this.currentProgress = input;
    }

    public int getProgressCap() {
        return progressCap;
    }

}