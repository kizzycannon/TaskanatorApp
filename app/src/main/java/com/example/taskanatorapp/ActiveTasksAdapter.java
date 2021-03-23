package com.example.taskanatorapp;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ActiveTasksAdapter extends RecyclerView.Adapter<ActiveTasksAdapter.ViewHolder> {

    private ArrayList<Task> taskList;

    public static class ViewHolder extends RecyclerView.ViewHolder{
        private CardView cardView;

        public ViewHolder(CardView v) {
            super(v);
            cardView = v;
        }
    }
    public ActiveTasksAdapter(ArrayList<Task> taskList){
        this.taskList = taskList;
    }

    @Override
    public int getItemCount(){
        return taskList.size(); //getAllTasks to be defined in Task Class.
    }

    @Override
    public ActiveTasksAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        CardView cv = (CardView) LayoutInflater.from(parent.getContext()).inflate(R.layout.card_manage_tasks, parent, false);
        return new ViewHolder(cv);
    }

    @Override
    public void onBindViewHolder(ActiveTasksAdapter.ViewHolder holder, int position){
        CardView cardView = holder.cardView;
        TextView taskTitle = (TextView)cardView.findViewById(R.id.Task_Title);
        TextView taskCategory = (TextView)cardView.findViewById(R.id.Task_Category);
        TextView taskDescription = (TextView)cardView.findViewById(R.id.Task_Description);

        Task currentTask = taskList.get(position); //Pending addition of Task class
        taskTitle.setText(currentTask.getTaskName());
        taskCategory.setText(currentTask.getTaskCategory());
        taskDescription.setText(currentTask.getTaskDescription());
    }
}
