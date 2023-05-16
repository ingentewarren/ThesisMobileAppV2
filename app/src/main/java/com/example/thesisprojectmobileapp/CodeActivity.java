package com.example.thesisprojectmobileapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class CodeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_code);

        // Find the EditText view by its ID
        TextView textViewCode = findViewById(R.id.textViewCode);
        Button btnHome = findViewById(R.id.btnHome);

        //Set text into CODE
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String code = extras.getString("code");
            textViewCode.setText(code);
        }

        //Set btn to return home when clicked
        btnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CodeActivity.this, room_list_activity.class);
                startActivity(intent);
            }
        });
    }
}