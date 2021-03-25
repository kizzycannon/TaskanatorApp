package com.example.taskanatorapp;

import java.util.ArrayList;
import java.util.HashMap;

public class System {
    private ArrayList<Task> allTasks;
    private ArrayList<Task> activeTasks;
    private static HashMap<String,Integer> iconHashMap;

    public System(){
        this.allTasks = new ArrayList<Task>();
        this.activeTasks = new ArrayList<Task>();

        //category images
        String[] categoryNames = {"Chores", "Sport", "Leisure", "Studying", "Other"};
        int[] images = {R.drawable.taskanator_icon___chores__text_, R.drawable.taskanator_icon___sport__text_,
                R.drawable.taskanator_icon___leisure__text_, R.drawable.taskanator_icon___studying__text_, R.drawable.taskanator_icon___other__text_};

        iconHashMap = new HashMap<>();
        for (int i = 0; i < categoryNames.length; i++) {
            iconHashMap.put(categoryNames[i], images[i]);
        }
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

    /**
     * Get the image id of the category icon
     *
     * @param imageName String of category name from the hashmap to be returned
     * @return int id of the category image
     */
    public static int getIconID(String imageName) {
        return iconHashMap.get(imageName);
    }
}
