package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddCourse extends AppCompatActivity {
    EditText name;
    EditText code;
    EditText tutor;
    Spinner spinnercourse;
    Button btn;

    DatabaseReference coursedb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_course);

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout((int)(width*.9),(int)(height*.9));
        WindowManager.LayoutParams params = getWindow().getAttributes();
        params.gravity = Gravity.CENTER;
        params.x=0;
        params.y = -20;

        getWindow().setAttributes(params);

        name = findViewById(R.id.coursename);
        code = findViewById(R.id.coursecode);
        tutor = findViewById(R.id.Coursetutor);

        btn = findViewById(R.id.insertbtn);
        String url="https://my-application-c0d64-default-rtdb.firebaseio.com/";
        coursedb = FirebaseDatabase.getInstance(url).getReference("courselist");
        btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                insertcourse();
            }
        });
    }

    private void insertcourse(){
        String nm = name.getText().toString();
        String cd = code.getText().toString();
        String tu = tutor.getText().toString();
        model courseedit = new model(nm,cd,tu);
        coursedb.push().setValue(courseedit);
        Toast.makeText(AddCourse.this,"data inserted successfully",Toast.LENGTH_SHORT).show();
    }
}
