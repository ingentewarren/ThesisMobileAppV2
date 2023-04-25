package com.example.thesisprojectmobileapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class ReserveActivity<Calendar> extends AppCompatActivity {

    private EditText editTextDate;
    private Button btnSubmit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reserve);

        // Find the EditText view by its ID
        EditText editTextDate = findViewById(R.id.editTextDate);

        // Get the current date and format it as "yyyy-MM-dd"
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        String currentDate = sdf.format(new Date());

        // Set the current date as the text of the EditText
        editTextDate.setText(currentDate);

        //Submit Button
        btnSubmit = (Button) findViewById(R.id.btn_submit);
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openSubmitConfirmation();
            }
        });


        EditText editTextName = findViewById(R.id.editText_fullname);
        String fullName = editTextName.getText().toString();

        Intent intent = new Intent(ReserveActivity.this, ReserveConfirmation_activity.class);
        intent.putExtra("fullName", fullName);
        startActivity(intent);


    }

    public void openSubmitConfirmation() {
        Intent intent = new Intent(this, ReserveConfirmation_activity.class);
        startActivity(intent);
    }
}