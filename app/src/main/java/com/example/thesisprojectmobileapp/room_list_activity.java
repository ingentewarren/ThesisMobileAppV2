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

import java.util.ArrayList;
import java.util.List;

public class room_list_activity extends AppCompatActivity {

    private Button btnRoomReserve;
    private Button btnProfile;
    private Button btnSeeBooking;
    private Button btnBookingHistory;
    private  Button btnSchedule;
    private  Button btnSchedule2;

    private TextView textViewRoom1;
    private TextView textViewRoom2;
    private TextView textViewRoom3;
    private TextView textViewRoom4;
    private TextView textViewRoom5;
    private TextView textViewRoom6;
    private TextView textViewStatus1;
    private TextView textViewStatus2;
    private TextView textViewStatus3;
    private TextView textViewStatus4;
    private TextView textViewStatus5;
    private TextView textViewStatus6;



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


       // Features to be added soon.
        btnProfile = (Button) findViewById(R.id.btnProfile);
        btnBookingHistory = (Button) findViewById(R.id.btnBookingHistory);

        btnProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(room_list_activity.this, "Feature Coming Soon", Toast.LENGTH_SHORT).show();
            }
        });
        btnBookingHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(room_list_activity.this, "Feature Coming Soon", Toast.LENGTH_SHORT).show();
            }
        });

        //See Booking
        btnSeeBooking = (Button) findViewById(R.id.btnSeeBooking);
        btnSeeBooking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(room_list_activity.this, BookingListActivity.class);
                startActivity(intent);
            }
        });

        //Displaying data from Firebase (Room Numbers)
        // Get a reference to the "Room" node in your Firebase Realtime Database
        DatabaseReference roomRef = FirebaseDatabase.getInstance().getReference().child("Room");


        // Initialize the TextView
        textViewRoom1 = findViewById(R.id.textViewRoom1);
        textViewRoom2 = findViewById(R.id.textViewRoom2);
        textViewRoom3 = findViewById(R.id.textViewRoom3);
        textViewRoom4 = findViewById(R.id.textViewRoom4);
        textViewRoom5 = findViewById(R.id.textViewRoom5);
        textViewRoom6 = findViewById(R.id.textViewRoom6);
        textViewStatus1 = findViewById(R.id.textViewStatus1);
        textViewStatus2 = findViewById(R.id.textViewStatus2);
        textViewStatus3 = findViewById(R.id.textViewStatus3);
        textViewStatus4 = findViewById(R.id.textViewStatus4);
        textViewStatus5 = findViewById(R.id.textViewStatus5);
        textViewStatus6 = findViewById(R.id.textViewStatus6);
        btnSchedule = (Button) findViewById(R.id.btnSchedule);
        btnSchedule2 = (Button) findViewById(R.id.btnSchedule2);

        // Add a ValueEventListener to listen for changes to the data at the "Room" node
        roomRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // Get the String and Bool from the "room1" node
                List<Integer> numberList = new ArrayList<>();

                for (int i = 1; i <= 10; i++) {
                    if (dataSnapshot.child("Room" + i).exists()) {
                        numberList.add(i);
                        switch (i) {
                            case 1:
                                if(dataSnapshot.child("Room" + i).child("Room Number").exists()){
                                    Integer roomNumber = dataSnapshot.child("Room" + i).child("Room Number").getValue(Integer.class);
                                    textViewRoom1.setText(String.valueOf(roomNumber));
                                }
                                if(dataSnapshot.child("Room" + i).child("Availability").exists()) {
                                    Boolean roomStatus = dataSnapshot.child("Room" + i).child("Availability").getValue(Boolean.class);
                                    if (roomStatus) {
                                        textViewStatus1.setText("Vacant");
                                        textViewStatus1.setTextColor(Color.GREEN);
                                    } else {
                                        textViewStatus1.setText("Occupied");
                                        textViewStatus1.setTextColor(Color.RED);
                                    }
                                }
                                btnSchedule.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        Intent intent = new Intent(room_list_activity.this, weekly_schedule.class);
                                        startActivity(intent);
                                    }
                                });

                                break;
                            case 2:
                                if(dataSnapshot.child("Room" + i).child("Room Number").exists()){
                                    Integer roomNumber = dataSnapshot.child("Room" + i).child("Room Number").getValue(Integer.class);
                                    textViewRoom2.setText(String.valueOf(roomNumber));
                                }
                                if(dataSnapshot.child("Room" + i).child("Availability").exists()) {
                                    Boolean roomStatus = dataSnapshot.child("Room" + i).child("Availability").getValue(Boolean.class);
                                    if (roomStatus) {
                                        textViewStatus2.setText("Vacant");
                                        textViewStatus2.setTextColor(Color.GREEN);
                                    } else {
                                        textViewStatus2.setText("Occupied");
                                        textViewStatus2.setTextColor(Color.RED);
                                    }
                                }
                                btnSchedule2.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        Intent intent = new Intent(room_list_activity.this, weekly_schedule2.class);
                                        startActivity(intent);
                                    }
                                });

                                break;
                            case 3:
                                if(dataSnapshot.child("Room" + i).child("Room Number").exists()){
                                    Integer roomNumber = dataSnapshot.child("Room" + i).child("Room Number").getValue(Integer.class);
                                    textViewRoom3.setText(String.valueOf(roomNumber));
                                }
                                if(dataSnapshot.child("Room" + i).child("Availability").exists()) {
                                    Boolean roomStatus = dataSnapshot.child("Room" + i).child("Availability").getValue(Boolean.class);
                                    if (roomStatus) {
                                        textViewStatus3.setText("Vacant");
                                        textViewStatus3.setTextColor(Color.GREEN);
                                    } else {
                                        textViewStatus3.setText("Occupied");
                                        textViewStatus3.setTextColor(Color.RED);
                                    }
                                }
                                break;
                            case 4:
                                if(dataSnapshot.child("Room" + i).child("Room Number").exists()){
                                    Integer roomNumber = dataSnapshot.child("Room" + i).child("Room Number").getValue(Integer.class);
                                    textViewRoom4.setText(String.valueOf(roomNumber));
                                }
                                if(dataSnapshot.child("Room" + i).child("Availability").exists()) {
                                    Boolean roomStatus = dataSnapshot.child("Room" + i).child("Availability").getValue(Boolean.class);
                                    if (roomStatus) {
                                        textViewStatus4.setText("Vacant");
                                        textViewStatus4.setTextColor(Color.GREEN);
                                    } else {
                                        textViewStatus4.setText("Occupied");
                                        textViewStatus4.setTextColor(Color.RED);
                                    }
                                }
                                break;

                            case 5:
                                if(dataSnapshot.child("Room" + i).child("Room Number").exists()){
                                    Integer roomNumber = dataSnapshot.child("Room" + i).child("Room Number").getValue(Integer.class);
                                    textViewRoom5.setText(String.valueOf(roomNumber));
                                }
                                if(dataSnapshot.child("Room" + i).child("Availability").exists()) {
                                    Boolean roomStatus = dataSnapshot.child("Room" + i).child("Availability").getValue(Boolean.class);
                                    if (roomStatus) {
                                        textViewStatus5.setText("Vacant");
                                        textViewStatus5.setTextColor(Color.GREEN);
                                    } else {
                                        textViewStatus5.setText("Occupied");
                                        textViewStatus5.setTextColor(Color.RED);
                                    }
                                }
                                break;

                            case 6:
                                if(dataSnapshot.child("Room" + i).child("Room Number").exists()){
                                    Integer roomNumber = dataSnapshot.child("Room" + i).child("Room Number").getValue(Integer.class);
                                    textViewRoom6.setText(String.valueOf(roomNumber));
                                }
                                if(dataSnapshot.child("Room" + i).child("Availability").exists()) {
                                    Boolean roomStatus = dataSnapshot.child("Room" + i).child("Availability").getValue(Boolean.class);
                                    if (roomStatus) {
                                        textViewStatus6.setText("Vacant");
                                        textViewStatus6.setTextColor(Color.GREEN);
                                    } else {
                                        textViewStatus6.setText("Occupied");
                                        textViewStatus6 .setTextColor(Color.RED);
                                    }
                                }
                                break;
                        }
                    }
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