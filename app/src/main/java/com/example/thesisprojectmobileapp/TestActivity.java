package com.example.thesisprojectmobileapp;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class TestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        // Get a reference to your Firebase Realtime Database
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("rooms");

        // Add a ValueEventListener to retrieve the data
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // Iterate through the data and log each room number to the console
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    String roomNumber = snapshot.child("roomNumber").getValue(String.class);
                    Log.d(TAG, "Room number: " + roomNumber);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.e(TAG, "Failed to read value.", databaseError.toException());
            }
        });

    }
}