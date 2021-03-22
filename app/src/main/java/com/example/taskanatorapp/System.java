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

    public void addToAllTasks(Task task){
        this.allTasks.add(task);
    }

    public void addToActiveTasks(Task task){
        this.activeTasks.add(task);
    }

    public void removeFromAllTasks(Task task){
        this.allTasks.remove(task);
    }

    public void removeFromActiveTasks(Task task){
        this.activeTasks.remove(task);
    }

}
