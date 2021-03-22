package com.example.taskanatorapp;

public class Task {
    //instance variables
    private String taskName;
    private String taskCategory;
    private String taskDescription;
    private int taskLength;
    private String taskNotes;

    public Task(String taskName, String taskCategory, String taskDescription, int taskLength){
        this.taskName = taskName;
        this.taskCategory = taskCategory;
        this.taskLength = taskLength;
        this.taskDescription = taskDescription;
        this.taskNotes = "";
    }

    public String getTaskName(){
        return this.taskName;
    }

    public String getTaskCategory(){
        return this.taskCategory;
    }

    public String getTaskDescription(){
        return this.taskDescription;
    }

    public int getTaskLength(){
        return this.taskLength;
    }

    public String getTaskNotes(){
        return this.taskNotes;
    }

    public void setTaskName(String taskName){
        this.taskName = taskName;
    }

    public void setTaskCategory(String taskCategory){
        this.taskCategory = taskCategory;
    }

    public void setTaskDescription(String taskDescription){
        this.taskDescription = taskDescription;
    }

    public void setTaskLength(int taskLength){
        this.taskLength = taskLength;
    }
}
