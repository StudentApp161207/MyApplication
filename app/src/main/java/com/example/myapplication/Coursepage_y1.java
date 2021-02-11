package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Coursepage_y1 extends AppCompatActivity {
    TextView name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coursepage_y1);

        name = findViewById(R.id.Coursetitle);
        Intent intent = getIntent();
        name.setText(intent.getStringExtra("name"));
    }
}