package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class TeacherLogin extends AppCompatActivity {
    protected static Activity fa;
    private TextInputEditText teacherIdEt,teacherPassword;
    private final List<String> teacherIdList=new ArrayList<>();
    private final List<String> passwordList=new ArrayList<>();
    private DatabaseReference teacherRef,deptref;
    private String dept,shift,tdept;






        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);

            fa=this;
            setContentView(R.layout.activity_teacher_login);
            teacherIdEt=findViewById(R.id.teacher_Login_id);
            teacherPassword=findViewById(R.id.teacherLoginPassword);
            AppCompatButton teacherLogInBtn = findViewById(R.id.teacherLoginBtn);

            teacherLogInBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    teacherLogin();
                }
            });

        }

        private void teacherLogin(){
            final String teacherId= Objects.requireNonNull(teacherIdEt.getText()).toString();
            final String tpassword= Objects.requireNonNull(teacherPassword.getText()).toString();
            String url="https://my-application-c0d64-default-rtdb.firebaseio.com/";
            if (teacherId.isEmpty()){
                teacherIdEt.setError("Enter teacher ID");
                teacherIdEt.requestFocus();
            }else if(tpassword.isEmpty()){
                teacherPassword.setError("Enter Password");
                teacherPassword.requestFocus();
            }else {
                teacherRef= FirebaseDatabase.getInstance(url).getReference().child("Department");
                teacherRef.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        teacherIdList.clear();
                        passwordList.clear();

                        if(dataSnapshot.exists()){
                            for(DataSnapshot dataSnapshot1:dataSnapshot.getChildren()){
                                dept=dataSnapshot1.getKey();
                                deptref=teacherRef.child(dept).child("Teachers");
                                deptref.addValueEventListener(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                        if(dataSnapshot.exists()){
                                            for(DataSnapshot dataSnapshot2:dataSnapshot.getChildren()){
                                                if(dataSnapshot2.hasChildren()){
                                                        Teacher teacher=dataSnapshot2.getValue(Teacher.class);
                                                        if(Objects.requireNonNull(teacher).getId().equals(teacherId)){
                                                            String id=teacher.getId();
                                                            String password=teacher.getPassword();
                                                            teacherIdList.add(id);
                                                            passwordList.add(password);

                                                            shift=teacher.getShift();
                                                            tdept=teacher.getDepartment();

                                                            SaveUser saveUser=new SaveUser();
                                                            saveUser.saveTeacher(TeacherLogin.this,teacher);

                                                        }

                                                    }

                                                }


                                            }

                                            if(teacherIdList.contains(teacherId) && passwordList.contains(tpassword)){


                                                Toast.makeText(getApplicationContext(),"Successfully Logged in",Toast.LENGTH_SHORT).show();
                                                Intent intent=new Intent(TeacherLogin.this,MainMenu.class);
                                                intent.putExtra("TEACHERID",teacherId);

                                                SaveUser saveUser=new SaveUser();
                                                saveUser.teacher_IDsaveData(getApplicationContext(),teacherId);
                                                saveUser.teacher_ShiftSaveData(getApplicationContext(),shift);
                                                saveUser.teacher_saveData(TeacherLogin.this,true);
                                                saveUser.teacher_DeptSaveData(getApplicationContext(),tdept);
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

