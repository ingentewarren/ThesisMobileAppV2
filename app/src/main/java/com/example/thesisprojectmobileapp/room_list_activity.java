package com.example.thesisprojectmobileapp;

import static androidx.constraintlayout.widget.StateSet.TAG;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class room_list_activity extends AppCompatActivity {

    private Button btnRoomReserve;
    private Button btnProfile;
    private Button btnSeeBooking;
    private Button btnBookingHistory;

    private TextView textViewRoom1;
    private TextView textViewRoom2;
    private TextView textViewRoom3;
    private TextView textViewStatus1;
    private TextView textViewStatus2;
    private TextView textViewStatus3;







    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room_list);

        btnRoomReserve = (Button) findViewById(R.id.btnRoomReserve);

        btnRoomReserve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openReserve();
            }
        });



        btnProfile = (Button) findViewById(R.id.btnProfile);
        btnSeeBooking = (Button) findViewById(R.id.btnSeeBooking);
        btnBookingHistory = (Button) findViewById(R.id.btnSeeBooking);

        btnProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(room_list_activity.this, "Feature Comming Soon", Toast.LENGTH_SHORT).show();
            }
        });

        btnSeeBooking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(room_list_activity.this, "Feature Comming Soon", Toast.LENGTH_SHORT).show();
            }
        });

        btnBookingHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(room_list_activity.this, "Feature Comming Soon", Toast.LENGTH_SHORT).show();
            }
        });



        //Displaying data from Firebase (Room Numbers)
        // Get a reference to your Firebase Realtime Database
        DatabaseReference databaseRef = FirebaseDatabase.getInstance().getReference();

        // Get a reference to the "Room" node in your Firebase Realtime Database
        DatabaseReference roomRef = FirebaseDatabase.getInstance().getReference().child("Room");


        // Initialize the TextView
        textViewRoom1 = findViewById(R.id.textViewRoom1);
        textViewRoom2 = findViewById(R.id.textViewRoom2);
        textViewRoom3 = findViewById(R.id.textViewRoom3);
        textViewStatus1 = findViewById(R.id.textViewStatus1);
        textViewStatus2 = findViewById(R.id.textViewStatus2);
        textViewStatus3 = findViewById(R.id.textViewStatus3);

        // Add a ValueEventListener to listen for changes to the data at the "Room" node
        roomRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // Get the message from the "room1" node
                String room1 = dataSnapshot.child("Room1").child("Room Number").getValue(String.class);
                String room2 = dataSnapshot.child("Room2").child("Room Number").getValue(String.class);
                String room3 = dataSnapshot.child("Room3").child("Room Number").getValue(String.class);
                Boolean roomStatus1 = dataSnapshot.child("Room1").child("Availability").getValue(Boolean.class);
                Boolean roomStatus2 = dataSnapshot.child("Room2").child("Availability").getValue(Boolean.class);
                Boolean roomStatus3 = dataSnapshot.child("Room3").child("Availability").getValue(Boolean.class);



                textViewRoom1.setText(room1);
                textViewRoom2.setText(room2);
                textViewRoom3.setText(room3);

                if (roomStatus1) {
                    textViewStatus1.setText("Vacant");
                    textViewStatus1.setTextColor(Color.GREEN);
                } else {
                    textViewStatus1.setText("Occupied");
                    textViewStatus1.setTextColor(Color.RED);
                }

                if (roomStatus2) {
                    textViewStatus2.setText("Vacant");
                    textViewStatus2.setTextColor(Color.GREEN);
                } else {
                    textViewStatus2.setText("Occupied");
                    textViewStatus2.setTextColor(Color.RED);
                }

                if (roomStatus3) {
                    textViewStatus3.setText("Vacant");
                    textViewStatus3.setTextColor(Color.GREEN);
                } else {
                    textViewStatus3.setText("Occupied");
                    textViewStatus3.setTextColor(Color.RED);
                }







            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Toast.makeText(room_list_activity.this, "Failed to read value", Toast.LENGTH_SHORT).show();
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });
    }



    public void openReserve() {
        Intent intent = new Intent(this, ReserveActivity.class);
        startActivity(intent);
    }

    public void openProfile() {
        Intent intent = new Intent(this, Profile_Activity.class);
        startActivity(intent);
    }
}