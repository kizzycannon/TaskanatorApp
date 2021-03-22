package com.example.taskanatorapp;

import android.app.Application;
import java.util.ArrayList;

public class System extends Application {
    private ArrayList<Task> allTasks;
    private ArrayList<Task> activeTasks;

    public System(){
        this.allTasks = new ArrayList<Task>();
        this.activeTasks = new ArrayList<Task>();
    }

    public ArrayList<Task> getAllTasks(){
        return this.allTasks;
    }

    public ArrayList<Task> getActiveTasks(){
        return this.activeTasks;
    }

    public void addToActiveTasks(Task task){
        this.activeTasks.add(task);
    }

    public void removeFromAllTasks(int taskID){
        this.allTasks.remove(taskID);
    }

    public void removeFromActiveTasks(int taskID){
        this.activeTasks.remove(taskID);
    }

    public void createNewTask(String taskName, String taskCategory, String taskDescription, int taskLength){
        this.allTasks.add(new Task(taskName, taskCategory, taskDescription, taskLength));
    }
}
