package com.example.thesisprojectmobileapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class weekly_schedule extends AppCompatActivity implements View.OnClickListener{

    TextView item1;
    TextView item2;
    TextView item3;
    TextView item4;
    TextView item5;
    TextView item6;
    TextView select;
    int def;

    private Button btn_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.weekly_tabs);
        item1 = findViewById(R.id.item1);
        item2 = findViewById(R.id.item2);
        item3 = findViewById(R.id.item3);
        item4 = findViewById(R.id.item4);
        item5 = findViewById(R.id.item5);
        item6 = findViewById(R.id.item6);
        item1.setOnClickListener(this);
        item2.setOnClickListener(this);
        item3.setOnClickListener(this);
        item4.setOnClickListener(this);
        item5.setOnClickListener(this);
        item6.setOnClickListener(this);
        select = findViewById(R.id.select);
        def = item2.getCurrentTextColor();

        getSupportFragmentManager().beginTransaction()
                .setReorderingAllowed(true)
                .replace(R.id.fragmentContainer, fragmentOne.class, null)
                .commit();

        btn_back = (Button) findViewById(R.id.back_button);
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.item1){
            select.animate().x(0).setDuration(100);
            item1.setTextColor(Color.WHITE);
            item2.setTextColor(def);
            item3.setTextColor(def);
            item4.setTextColor(def);
            item5.setTextColor(def);
            item6.setTextColor(def);

            getSupportFragmentManager().beginTransaction()
                    .setReorderingAllowed(true)
                    .replace(R.id.fragmentContainer, fragmentOne.class, null)
                    .commit();

        } else if (view.getId() == R.id.item2){
            item1.setTextColor(def);
            item2.setTextColor(Color.WHITE);
            item3.setTextColor(def);
            item4.setTextColor(def);
            item5.setTextColor(def);
            item6.setTextColor(def);
            int size = item2.getWidth();
            select.animate().x(size).setDuration(100);
            getSupportFragmentManager().beginTransaction()
                    .setReorderingAllowed(true)
                    .replace(R.id.fragmentContainer, fragmentTwo.class, null)
                    .commit();

        } else if (view.getId() == R.id.item3){
            item1.setTextColor(def);
            item3.setTextColor(Color.WHITE);
            item2.setTextColor(def);
            item4.setTextColor(def);
            item5.setTextColor(def);
            item6.setTextColor(def);
            int size = item2.getWidth() * 2;
            select.animate().x(size).setDuration(100);

            getSupportFragmentManager().beginTransaction()
                    .setReorderingAllowed(true)
                    .replace(R.id.fragmentContainer, fragmentThree.class, null)
                    .commit();

        } else if (view.getId() == R.id.item4){
            item1.setTextColor(def);
            item2.setTextColor(def);
            item3.setTextColor(def);
            item4.setTextColor(Color.WHITE);
            item5.setTextColor(def);
            item6.setTextColor(def);
            int size = item2.getWidth() * 3;
            select.animate().x(size).setDuration(100);
            getSupportFragmentManager().beginTransaction()
                    .setReorderingAllowed(true)
                    .replace(R.id.fragmentContainer, fragmentFour.class, null)
                    .commit();

        } else if (view.getId() == R.id.item5){
            item1.setTextColor(def);
            item2.setTextColor(def);
            item3.setTextColor(def);
            item4.setTextColor(def);
            item5.setTextColor(Color.WHITE);
            item6.setTextColor(def);
            int size = item2.getWidth() * 4;
            select.animate().x(size).setDuration(100);
            getSupportFragmentManager().beginTransaction()
                    .setReorderingAllowed(true)
                    .replace(R.id.fragmentContainer, fragmentFive.class, null)
                    .commit();

        } else if (view.getId() == R.id.item6){
            item1.setTextColor(def);
            item2.setTextColor(def);
            item3.setTextColor(def);
            item4.setTextColor(def);
            item5.setTextColor(def);
            item6.setTextColor(Color.WHITE);
            int size = item2.getWidth() * 5;
            select.animate().x(size).setDuration(100);
            getSupportFragmentManager().beginTransaction()
                    .setReorderingAllowed(true)
                    .replace(R.id.fragmentContainer, fragmentSix.class, null)
                    .commit();

        }
    }
}