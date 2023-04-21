package com.example.thesisprojectmobileapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class room_list_activity extends AppCompatActivity {

    private Button btnRoomReserve312;
    private Button btnRoomReserve313;
    private Button btnRoomReserve314;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room_list);

        btnRoomReserve312 = (Button) findViewById(R.id.btn_ReserveRoom312);
        btnRoomReserve312.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openReserve();
            }
        });

        btnRoomReserve313 = (Button) findViewById(R.id.btn_RoomReserve313);
        btnRoomReserve313.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openReserve();
            }
        });

        btnRoomReserve314 = (Button) findViewById(R.id.btn_RoomReserve314);
        btnRoomReserve314.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openReserve();
            }
        });
    }

    public void openReserve() {
        Intent intent = new Intent(this, ReserveActivity.class);
        startActivity(intent);
    }
}