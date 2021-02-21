package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

public class SelectType extends AppCompatActivity {
    private LinearLayout teacherCard,studentCard;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_select_type);

            teacherCard = findViewById(R.id.teacherCard);
            teacherCard.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(SelectType.this, TeacherLogin.class);
                    startActivity(intent);
                    finish();

                }
            });
            studentCard = findViewById(R.id.studentCard);
            studentCard.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(SelectType.this, StudentLogin.class);
                    startActivity(intent);
                    finish();

                }
            });
        }
    }
