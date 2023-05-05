package com.example.thesisprojectmobileapp;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.DateFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;

public class ReserveConfirmation_activity extends AppCompatActivity {

    private Button btnCancel;
    private TextView textViewDate;
    private Button btnConfirm;
    private Button btnChecker;
    private TextView textViewTimeEnd;
    private TextView textViewTimeStart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reserve_confirmation);

        btnCancel = (Button) findViewById(R.id.btn_cancel);
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });


        // Find the EditText view by its ID
        TextView textViewDate = findViewById(R.id.textViewDate);
        TextView textViewFullName = findViewById(R.id.textViewFullName);
        TextView textViewRoomNumber = findViewById(R.id.textViewRoomNumber);
        TextView textViewEvent = findViewById(R.id.textViewEvent);
        TextView textViewTimeStart = findViewById(R.id.textViewTimeStart);
        TextView textViewTimeEnd = findViewById(R.id.textViewTimeEnd);
        TextView textViewSubjectCode = findViewById(R.id.textViewSubjectCode);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String date = extras.getString("date");
            String fullName = extras.getString("fullName");
            String roomNumber = extras.getString("roomNumber");
            String event = extras.getString("event");
            String timeStart = extras.getString("timeStart");
            String timeEnd = extras.getString("timeEnd");
            String subjectCode = extras.getString("subjectCode");

            textViewDate.setText(date);
            textViewFullName.setText(fullName);
            textViewFullName.setText(fullName);
            textViewRoomNumber.setText(roomNumber);
            textViewEvent.setText(event);
            textViewTimeStart.setText(timeStart);
            textViewTimeEnd.setText(timeEnd);
            textViewSubjectCode.setText(subjectCode);

        }


        DatabaseReference roomRef = FirebaseDatabase.getInstance().getReference().child("Room");

        // Find the views by their IDs
        textViewDate = findViewById(R.id.textViewDate);
        textViewFullName = findViewById(R.id.textViewFullName);
        textViewRoomNumber = findViewById(R.id.textViewRoomNumber);
        textViewEvent = findViewById(R.id.textViewEvent);
        textViewTimeStart = findViewById(R.id.textViewTimeStart);
        textViewTimeEnd = findViewById(R.id.textViewTimeEnd);
        textViewSubjectCode = findViewById(R.id.textViewSubjectCode);
        btnConfirm = findViewById(R.id.btnConfirm);
        btnChecker = findViewById(R.id.btnChecker);

        // Get the values from the TextViews
        String date = textViewDate.getText().toString();
        String fullName = textViewFullName.getText().toString();
        String roomNumber = textViewRoomNumber.getText().toString();
        String event = textViewEvent.getText().toString();
        String timeStart = textViewTimeStart.getText().toString();
        String timeEnd = textViewTimeEnd.getText().toString();
        String subjectCode = textViewSubjectCode.getText().toString();
        String weekDay = getDayOfWeek(date);


        //Sets the confirm button invisible;
        btnConfirm.setVisibility(View.INVISIBLE);

        // Add an OnClickListener to the "Check Availability" button
        btnChecker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                //Fetch User Input and Database
                //fetchData(timeStart, timeEnd);
                Log.d(TAG, "Weekday is: " + weekDay);


            }
        });


        // Add an OnClickListener to the "Confirm" button
        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                // Create a new HashMap to store the data
                HashMap<String, Object> reservationData = new HashMap<>();
                reservationData.put("Date", date);
                reservationData.put("FullName", fullName);
                reservationData.put("Event", event);
                reservationData.put("TimeStart", timeStart);
                reservationData.put("TimeEnd", timeEnd);
                reservationData.put("SubjectCode", subjectCode);

                // Write the data to the "Reserve" node
                roomRef.child("Room" + roomNumber).child("Reserve").setValue(reservationData)
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                Toast.makeText(ReserveConfirmation_activity.this, "Reservation saved successfully", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(ReserveConfirmation_activity.this, room_list_activity.class);
                                startActivity(intent);
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(ReserveConfirmation_activity.this, "Failed to save reservation: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        });
            }


        });
    }



    //Methods Here

    //Schedule conflict checker
    public static boolean checkTimeConflict(String startTime1, String endTime1, String startTime2, String endTime2) {
        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm", Locale.getDefault());
        try {
            Date start1 = timeFormat.parse(startTime1);
            Date end1 = timeFormat.parse(endTime1);
            Date start2 = timeFormat.parse(startTime2);
            Date end2 = timeFormat.parse(endTime2);

            Log.e(TAG, "start1 : " + startTime1);
            Log.e(TAG, "end1 : " + endTime1);
            Log.e(TAG, "start2 : " + startTime2);
            Log.e(TAG, "end2 : " + endTime2);

            // check if start time of one timeframe is between the start and end time of the other timeframe
            if (startTime1.equals(startTime2) || endTime1.equals(endTime2)) {
                // the start or end time of either time frame is the same
                return true;
            } else if (start1.before(start2) && end1.after(start2)) {
                // the first time frame starts before the second one and ends after it starts
                return true;
            } else if (start2.before(start1) && end2.after(start1)) {
                // the second time frame starts before the first one and ends after it starts
                return true;
            } else {
                return false;
            }

        } catch (ParseException e) {
            e.printStackTrace();
            return false;
        }
    }

    //String Input and Database Fetcher
    private void fetchData(String Start, String End) {


        String weekDay = "Monday";
        String roomNumber = "1";
        String timeNumber = "1";
        //Get the values from the Database
        // Fetch the values from the Firebase Realtime Database
        DatabaseReference databaseRef = FirebaseDatabase.getInstance().getReference("Room").child("Room" + roomNumber).child("Schedule").child(weekDay);
        databaseRef.child("time_start" + timeNumber).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String firebaseValue1 = dataSnapshot.getValue(String.class);
                Log.d(TAG, "Firebase Value 1: " + firebaseValue1);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.e(TAG, "Failed to read value1.", databaseError.toException());
            }
        });

        databaseRef.child("time_end" + timeNumber).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String firebaseValue2 = dataSnapshot.getValue(String.class);
                Log.d(TAG, "Firebase Value 2: " + firebaseValue2);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.e(TAG, "Failed to read value2.", databaseError.toException());
            }
        });

    }

    //Converts a Date into day of the Week
    public String getDayOfWeek(String dateStr) {
        try {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.US);
            Date date = format.parse(dateStr);
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
            return new DateFormatSymbols(Locale.US).getWeekdays()[dayOfWeek];
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }


}
