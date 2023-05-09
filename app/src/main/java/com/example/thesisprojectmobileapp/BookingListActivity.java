package com.example.thesisprojectmobileapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class BookingListActivity extends AppCompatActivity {

    private TextView textViewReserve1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking_list);

        reserveValues();




    }

    //METHOD HERE
    public void reserveValues(){
        DatabaseReference databaseRef = FirebaseDatabase.getInstance().getReference("Room").child("Room1").child("Reserve1");
        databaseRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    textViewReserve1 = findViewById(R.id.textViewReserve1);
                    String timeStart = dataSnapshot.child("TimeStart").getValue(String.class);
                    String timeEnd = dataSnapshot.child("TimeEnd").getValue(String.class);
                    String fullName = dataSnapshot.child("FullName").getValue(String.class);
                    String date = dataSnapshot.child("Date").getValue(String.class);
                    // Do something with the retrieved values
                    // For example, set them to TextViews
                    textViewReserve1.setText(fullName + " " + date + " " + timeStart + " - " + timeEnd);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // Handle the error
            }
        });
    }

}
