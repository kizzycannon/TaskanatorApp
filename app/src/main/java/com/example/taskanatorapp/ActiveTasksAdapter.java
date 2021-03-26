package com.example.taskanatorapp;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ActiveTasksAdapter extends RecyclerView.Adapter<ActiveTasksAdapter.ViewHolder> {

    private ArrayList<Task> taskList;
    private Context context;

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
        context = parent.getContext();
        return new ViewHolder(cv);
    }

    @Override
    public void onBindViewHolder(ActiveTasksAdapter.ViewHolder holder, int position){
        CardView cardView = holder.cardView;
        TextView taskTitle = (TextView)cardView.findViewById(R.id.Task_Title);
        TextView taskCategoryDuration = (TextView)cardView.findViewById(R.id.Task_Category);
        TextView taskDescription = (TextView)cardView.findViewById(R.id.Task_Description);
        ImageView imageView = (ImageView)cardView.findViewById(R.id.imageView);

        Task currentTask = taskList.get(position);
        String taskCat = currentTask.getTaskCategory();
        imageView.setImageDrawable(ContextCompat.getDrawable(context, System.getIconID(taskCat)));

        taskTitle.setText(currentTask.getTaskName());
        taskCategoryDuration.setText(currentTask.getTaskCategory() + " - " + currentTask.getTaskLength() + " minutes");
        taskDescription.setText(currentTask.getTaskDescription());

        cardView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(cardView.getContext(), TaskDetails.class);
                intent.putExtra(TaskDetails.EXTRA_TASK_ID, position);
                cardView.getContext().startActivity(intent);
            }
        });
    }
}
