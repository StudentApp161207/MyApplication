package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.NonNull;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


public class StudentLogin extends AppCompatActivity {
    private TextInputEditText studentIDET,studentPassET;
    private AppCompatButton studentLoginBtn;
    private DatabaseReference studentRef;
    private List<Student> studentList=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_login);
        studentIDET=findViewById(R.id.Student_Login_id);
        studentPassET=findViewById(R.id.studentLoginPassword);
        studentLoginBtn=findViewById(R.id.studentLoginBtn);
        studentLoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                studentLogIn();
            }
        });


    }

    private void studentLogIn() {

        final String  id=studentIDET.getText().toString();
        final String  pass=studentPassET.getText().toString();
        String url="https://my-application-c0d64-default-rtdb.firebaseio.com/";
        if(id.isEmpty()){
            studentIDET.setError("Enter Student ID");
            studentIDET.requestFocus();
        }else if(pass.isEmpty()){
            studentPassET.setError("Enter student pass");
            studentIDET.requestFocus();
        }else {
            studentRef= FirebaseDatabase.getInstance(url).getReference().child("Department");
            studentRef.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    studentList.clear();

                    if(dataSnapshot.exists()){
                        for(DataSnapshot dataSnapshot1:dataSnapshot.getChildren()){
                            String dept=dataSnapshot1.getKey();
                            DatabaseReference deptRef=studentRef.child(dept).child("Students");
                            deptRef.addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {



                                    for(DataSnapshot ds1:dataSnapshot.getChildren()){
                                        String year=ds1.getKey();
                                        for (DataSnapshot ds2:ds1.getChildren()){
                                            for (DataSnapshot ds3:ds2.getChildren()){

                                                Student student=ds3.getValue(Student.class);
                                                if(student.getId().equals(id)){

                                                    studentList.add(student);

                                                    SaveUser saveUser=new SaveUser();
                                                    saveUser.saveStudent(getApplicationContext(),student);

                                                }

                                            }

                                        }


                                    }

                                    if(studentList.contains(id) && studentList.contains(pass)){


                                        Toast.makeText(getApplicationContext(),"Successfully Logged in",Toast.LENGTH_SHORT).show();
                                        Intent intent=new Intent(getApplicationContext(),UserMainMenu.class);
                                        startActivity(intent);
                                        finish();

                                    }
                                    else {
                                        Toast.makeText(getApplicationContext(),"This id is not registered",Toast.LENGTH_SHORT).show();

                                    }

                                }


                                @Override
                                public void onCancelled(@NonNull DatabaseError databaseError) {

                                }
                            });



                        }
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
        }
    }
}

