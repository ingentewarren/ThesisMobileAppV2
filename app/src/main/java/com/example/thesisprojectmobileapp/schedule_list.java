package com.example.thesisprojectmobileapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class schedule_list extends AppCompatActivity {

    RecyclerView recyclerView;
    DatabaseReference database;
    myadapter myAdapter;
    ArrayList<schedule> list;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule_list);

        recyclerView = findViewById(R.id.schedule_lists);
        database = FirebaseDatabase.getInstance().getReference("Room");
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        list = new ArrayList<>();
        myAdapter = new myadapter(this, list);
        recyclerView.setAdapter(myAdapter);

        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot roomSnapshot : snapshot.getChildren()){
                    for(DataSnapshot scheduleSnapshot : roomSnapshot.child("Schedule").getChildren()){
                        schedule schedules = scheduleSnapshot.getValue(schedule.class);
                        list.add(schedules);
                    }
                }
                myAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
}