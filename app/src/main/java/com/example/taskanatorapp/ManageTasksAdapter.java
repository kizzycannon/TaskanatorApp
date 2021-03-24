package com.example.taskanatorapp;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class ManageTasksAdapter extends RecyclerView.Adapter<ManageTasksAdapter.ViewHolder>{

    private ArrayList<Task> allTasks;

    public static class ViewHolder extends RecyclerView.ViewHolder{
        private CardView cardView;

        public ViewHolder(CardView v){
            super(v);
            cardView = v;
        }
    }

    public ManageTasksAdapter(ArrayList<Task> allTasks){
        this.allTasks = allTasks;
    }

    @Override
    public int getItemCount(){
        return allTasks.size(); //getAllTasks to be defined in Task Class.
    }

    @Override
    public ManageTasksAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        CardView cv = (CardView) LayoutInflater.from(parent.getContext()).inflate(R.layout.card_manage_tasks, parent, false);
        return new ViewHolder(cv);
    }

    @Override
    public void onBindViewHolder(ManageTasksAdapter.ViewHolder holder, int position){
        CardView cardView = holder.cardView;
        TextView taskTitle = (TextView)cardView.findViewById(R.id.Task_Title);
        TextView taskCategory = (TextView)cardView.findViewById(R.id.Task_Category);
        TextView taskDescription = (TextView)cardView.findViewById(R.id.Task_Description);

        Task currentTask = allTasks.get(position);
        taskTitle.setText(currentTask.getTaskName());
        taskCategory.setText(currentTask.getTaskCategory());
        taskDescription.setText(currentTask.getTaskDescription());
    }
}
