package com.example.thesisprojectmobileapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class ReserveConfirmation_activity extends AppCompatActivity {

    private Button btnCancel;
    private TextView textViewDate;

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
        TextView textViewDate = findViewById(R.id.textview_date);

        // Get the current date and format it as "yyyy-MM-dd"
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        String currentDate = sdf.format(new Date());

        // Set the current date as the text of the EditText
        textViewDate.setText(currentDate);




        TextView textViewFullName = findViewById(R.id.textView_fullname);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String fullName = extras.getString("fullName");
            textViewFullName.setText(fullName);
        }

    }
}