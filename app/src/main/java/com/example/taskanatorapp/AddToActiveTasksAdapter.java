package com.example.taskanatorapp;

import android.content.ClipData;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class AddToActiveTasksAdapter extends RecyclerView.Adapter<AddToActiveTasksAdapter.ViewHolder> {

    interface OnItemCheckListener {
        void onItemCheck(Task item);
        void onItemUncheck(Task item);
    }

    private OnItemCheckListener onItemClick;
    private ArrayList<Task> allTasks;

    public AddToActiveTasksAdapter(ArrayList<Task> allTasks, OnItemCheckListener onItemCheckListener) {
        this.allTasks = allTasks;
        this.onItemClick = onItemCheckListener;
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
        Task currentTask = allTasks.get(position);
        CardView cardView = holder.cardView;
        TextView taskTitle = cardView.findViewById(R.id.Task_Title);
        taskTitle.setText(currentTask.getTaskName());

        holder.setOnClickListener(v -> {
            holder.check.setChecked(!holder.check.isChecked());
            if(holder.check.isChecked()){
                onItemClick.onItemCheck(currentTask);

            } else {
                onItemClick.onItemUncheck(currentTask);
            }
        });
    }

    @Override
    public int getItemCount() {
        return allTasks.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private CardView cardView;
        private CheckBox check;
        private TextView title;

        AdapterView.OnClickListener itemClickListener;
        private ArrayList<Task> checkedTasks = new ArrayList<>();
        private ArrayList<Task> allTasks;

        public ViewHolder(CardView v) {
            super(v);
            cardView = v;
            check = v.findViewById(R.id.addToActiveCheckBox);
            check.setClickable(false);
            title = v.findViewById(R.id.Task_Title);
        }
        public void setOnClickListener(View.OnClickListener onClickListener){
            cardView.setOnClickListener(onClickListener);
        }
    }
}










