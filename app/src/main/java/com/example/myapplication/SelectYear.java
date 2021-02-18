package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

public class SelectYear extends AppCompatActivity {
    private LinearLayout First,Second,Third;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_year);

        First = findViewById(R.id.FirstYear);
        First.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SelectYear.this, StudentLogin.class);
                startActivity(intent);
                finish();

            }
        });
        Second = findViewById(R.id.SecondYear);
        Second.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View v) {
                Intent intent = new Intent(SelectYear.this, StudentLogin.class);
                startActivity(intent);
                finish();

            }
        });
        Third = findViewById(R.id.ThirdYear);
        Third.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SelectYear.this, StudentLogin.class);
                startActivity(intent);
                finish();

            }
        });

    }
}