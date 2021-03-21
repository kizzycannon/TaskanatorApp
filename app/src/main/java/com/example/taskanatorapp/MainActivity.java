package com.example.taskanatorapp;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

/* Main Actvity */
public class MainActivity extends AppCompatActivity {
    ArrayList<String> testData = new ArrayList<>();
    ArrayAdapter<String> testAdapter;
    ListView listView;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        testData.add("HELLO");
        testData.add("WORLD");
        testAdapter = new ArrayAdapter<String>(this, R.layout.activity_list, R.id.listText, testData);
        listView = findViewById(R.id.MyTasks);
        listView.setAdapter(testAdapter);
        listView.setBackground(this.getDrawable(R.drawable.list_view_border));
    }
}