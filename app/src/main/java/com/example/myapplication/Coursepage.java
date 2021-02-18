package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Coursepage extends AppCompatActivity {
    Button y1,y2,y3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coursepage);
        y1=(Button) findViewById(R.id.y1);
        y2=(Button) findViewById(R.id.y2);
        y3=(Button) findViewById(R.id.y3);

        y1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intenty1 = new Intent(Coursepage.this,Courselist_y1.class);
                startActivity(intenty1);
            }
        });

    }
}