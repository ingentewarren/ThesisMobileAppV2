package com.example.thesisprojectmobileapp;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class fragmentOne extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    RecyclerView recyclerView;
    MyAdapter adapter;
    ArrayList<schedule> scheduleList;
    DatabaseReference databaseReference;


    public fragmentOne() {
        // Required empty public constructor
    }

    public static fragmentOne newInstance(String param1, String param2) {
        fragmentOne fragment = new fragmentOne();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_one, container, false);
        recyclerView = view.findViewById(R.id.schedule_lists);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        scheduleList = new ArrayList<>();
        adapter = new MyAdapter(getContext(), scheduleList);
        recyclerView.setAdapter(adapter);

        DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference();
        DatabaseReference roomRef = rootRef.child("Room").child("Room1").child("Schedule").child("Monday");

        roomRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot schedSnapshot : dataSnapshot.getChildren()) {
                    if (schedSnapshot.hasChild("instructor") && schedSnapshot.hasChild("subject") &&
                            schedSnapshot.hasChild("section") && schedSnapshot.hasChild("time_start") &&
                            schedSnapshot.hasChild("time_end")) {
                        schedule schedules = schedSnapshot.getValue(schedule.class);
                        scheduleList.add(schedules);
                    }
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Handle error
            }
        });

        return view;
    }
}


