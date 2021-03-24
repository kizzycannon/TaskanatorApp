package com.example.taskanatorapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class AddToActiveTasksAdapter extends RecyclerView.Adapter<AddToActiveTasksAdapter.ViewHolder>{

     private ArrayList<Task> allTasks;

    public static class ViewHolder extends RecyclerView.ViewHolder{
            private CardView cardView;
            private CheckBox check;

            public ViewHolder(CardView v) {
                super(v);
                cardView = v;
                check = v.findViewById(R.id.addToActiveCheckBox);
            }
    }

    public AddToActiveTasksAdapter(ArrayList<Task>allTasks){
        this.allTasks = allTasks;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @NonNull
    @Override
    public AddToActiveTasksAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        CardView cv = (CardView) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_active_tasks, parent, false);
        return new ViewHolder(cv);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        CardView cardView = holder.cardView;
        TextView taskTitle = (TextView)cardView.findViewById(R.id.Task_Title);
        CheckBox taskCheck = (CheckBox) cardView.findViewById(R.id.addToActiveCheckBox);

        Task item = allTasks.get(position);
        taskTitle.setText(item.getTaskName());
    }

    @Override
    public int getItemCount() {
        return allTasks.size();
    }
}

