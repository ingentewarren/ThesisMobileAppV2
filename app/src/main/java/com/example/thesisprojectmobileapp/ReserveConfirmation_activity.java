package com.example.thesisprojectmobileapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;

public class ReserveConfirmation_activity extends AppCompatActivity {

    private Button btnCancel;
    private TextView textViewDate;
    private Button btnConfirm;

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

        // Get the current date and format it as "yyyy-MM-dd"
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        String currentDate = sdf.format(new Date());

        // Set the current date as the text of the EditText
        textViewDate.setText(currentDate);





        TextView textViewFullName = findViewById(R.id.textViewFullName);
        TextView textViewRoomNumber = findViewById(R.id.textViewRoomNumber);
        TextView textViewEvent = findViewById(R.id.textViewEvent);
        TextView textViewTimeStart = findViewById(R.id.textViewTimeStart);
        TextView textViewTimeEnd = findViewById(R.id.textViewTimeEnd);
        TextView textViewSubjectCode = findViewById(R.id.textViewSubjectCode);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String fullName = extras.getString("fullName");
            String roomNumber = extras.getString("roomNumber");
            String event = extras.getString("event");
            String timeStart = extras.getString("timeStart");
            String timeEnd = extras.getString("timeEnd");
            String subjectCode = extras.getString("subjectCode");

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

        // Get the values from the TextViews
        String date = textViewDate.getText().toString();
        String fullName = textViewFullName.getText().toString();
        String roomNumber = textViewRoomNumber.getText().toString();
        String event = textViewEvent.getText().toString();
        String timeStart = textViewTimeStart.getText().toString();
        String timeEnd = textViewTimeEnd.getText().toString();
        String subjectCode = textViewSubjectCode.getText().toString();

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

}
