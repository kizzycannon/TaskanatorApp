package com.example.taskanatorapp;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class ManageTasksFragment extends Fragment {

    System system;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        RecyclerView manageTasksRecycler = (RecyclerView)inflater.inflate(R.layout.fragment_manage_tasks, container, false);

        //Code here to transfer information to adapter

        ManageTasksAdapter adapter = new ManageTasksAdapter(system);
        manageTasksRecycler.setAdapter(adapter);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        manageTasksRecycler.setLayoutManager(layoutManager);
        return manageTasksRecycler;
    }
}
