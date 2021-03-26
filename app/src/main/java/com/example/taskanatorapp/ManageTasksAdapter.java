package com.example.taskanatorapp;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.content.Context;

import androidx.appcompat.content.res.AppCompatResources;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.HashMap;

public class ManageTasksAdapter extends RecyclerView.Adapter<ManageTasksAdapter.ViewHolder>{

    private ArrayList<Task> allTasks;
    private HashMap<String,Integer> iconHashMap;
    private Context context;

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
        context = parent.getContext();
        return new ViewHolder(cv);
    }

    @Override
    public void onBindViewHolder(ManageTasksAdapter.ViewHolder holder, int position){
        CardView cardView = holder.cardView;
        TextView taskTitle = (TextView)cardView.findViewById(R.id.Task_Title);
        TextView taskCategoryDuration = (TextView)cardView.findViewById(R.id.Task_Category);
        TextView taskDescription = (TextView)cardView.findViewById(R.id.Task_Description);
        ImageView imageView = (ImageView)cardView.findViewById(R.id.imageView);

        Task currentTask = allTasks.get(position);
        String taskCat = currentTask.getTaskCategory();
        imageView.setImageDrawable(ContextCompat.getDrawable(context, System.getIconID(taskCat)));


        taskTitle.setText(currentTask.getTaskName());
        taskCategoryDuration.setText(currentTask.getTaskCategory() + " - " + currentTask.getTaskLength() + " minutes");
        taskDescription.setText(currentTask.getTaskDescription());

        cardView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(cardView.getContext(), EditTask.class);
                intent.putExtra(TaskDetails.EXTRA_TASK_ID, position);
                cardView.getContext().startActivity(intent);
            }
        });
    }
}
