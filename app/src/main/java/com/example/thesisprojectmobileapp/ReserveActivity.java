package com.example.thesisprojectmobileapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class ReserveActivity<Calendar> extends AppCompatActivity {

    private EditText editTextDate;
    private Button btnSubmit;
    private String roomNumber;
    private DatabaseReference mDatabaseRef;
    private ArrayList<String> mRoomNumbersList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reserve);

        Intent intent = getIntent();
        roomNumber = intent.getStringExtra("roomNumber");

        // Find the EditText view by its ID
        EditText editTextDate = findViewById(R.id.editTextDate);

        // Get the current date and format it as "yyyy-MM-dd"
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        String currentDate = sdf.format(new Date());

        // Set the current date as the text of the EditText
        editTextDate.setText(currentDate);


        //List of Rooms
        mDatabaseRef = FirebaseDatabase.getInstance().getReference().child("Room");



        //Submit Button
        btnSubmit = (Button) findViewById(R.id.btn_submit);

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get the user's name from the EditText view
                EditText editTextName = findViewById(R.id.editText_fullname);
                Spinner spinnerRoomNumber = findViewById(R.id.spinnerRoomNumber);
                Spinner spinnerEvent = findViewById(R.id.spinnerEvent);
                EditText editTextTimeStart = findViewById(R.id.editTextTimeStart);
                EditText editTextTimeEnd = findViewById(R.id.editTextTimeEnd);
                EditText editTextSubjectCode = findViewById(R.id.editTextSubjectCode);

                String fullName = editTextName.getText().toString();
                String roomNumber = spinnerRoomNumber.getSelectedItem().toString();
                String event = spinnerEvent.getSelectedItem().toString();
                String timeStart = editTextTimeStart.getText().toString();
                String timeEnd = editTextTimeEnd.getText().toString();
                String subjectCode = editTextSubjectCode.getText().toString();

                // Create an intent to start the confirmation activity and pass the user's name
                Intent intent = new Intent(ReserveActivity.this, ReserveConfirmation_activity.class);
                intent.putExtra("fullName", fullName);
                intent.putExtra("roomNumber", roomNumber);
                intent.putExtra("event", event);
                intent.putExtra("timeStart", timeStart);
                intent.putExtra("timeEnd", timeEnd);
                intent.putExtra("subjectCode", subjectCode);
                startActivity(intent);
            }
        });


    }

    public void openSubmitConfirmation() {
        Intent intent = new Intent(this, ReserveConfirmation_activity.class);
        startActivity(intent);
    }
}