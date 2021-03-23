package com.example.taskanatorapp;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class ActiveTasksFragment extends Fragment {

    System system;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        RecyclerView activeTasksRecycler = (RecyclerView)inflater.inflate(R.layout.fragment_active_tasks, container, false);

        //Code here to transfer information to adapter

        ActiveTasksAdapter adapter = new ActiveTasksAdapter(system);
        activeTasksRecycler.setAdapter(adapter);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        activeTasksRecycler.setLayoutManager(layoutManager);
        return activeTasksRecycler;
    }
}