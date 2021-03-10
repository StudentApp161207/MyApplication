package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;


import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import xyz.hasnat.sweettoast.SweetToast;


public class TakeAttendance extends AppCompatActivity {
    private  String intentded_course,intentDate;
    private DatabaseReference studentRef,deptref,attendanceRef,presentRef,absentRef;
    private String dept;
    private ListView listView;
    List<Student1> studentList=new ArrayList<>();


    private RecyclerView taRV;
    private TakeattendanceRVAdapter takeAttendanceAdapter;
    private AppCompatButton submitBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_take_attendance);
        Toolbar toolbar=findViewById(R.id.toolbar);
        Intent intent=getIntent();
        intentded_course=intent.getStringExtra("SC");
        intentDate=intent.getStringExtra("DATE");
        submitBtn=findViewById(R.id.submitbtn);
        taRV=findViewById(R.id.tARv);
        studentList = getData();
        takeAttendanceAdapter = new TakeattendanceRVAdapter(getApplicationContext(), studentList);
        taRV.setLayoutManager(new LinearLayoutManager(TakeAttendance.this));
        takeAttendanceAdapter.notifyDataSetChanged();
        taRV.setAdapter(takeAttendanceAdapter);
        String url ="https://my-application-c0d64-default-rtdb.firebaseio.com/";

        attendanceRef=FirebaseDatabase.getInstance(url).getReference("Attendance");
        presentRef=attendanceRef.child("Present");
        absentRef=attendanceRef.child("Absent");

       /* attendanceRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                        String course = dataSnapshot1.getKey();
                        deptref = attendanceRef.child(new SaveUser().getTeacher(getApplicationContext()).getDepartment()).child("Student");
                        deptref.addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                studentList.clear();
                                if (dataSnapshot.exists()) {
                                    for (DataSnapshot ds1 : dataSnapshot.getChildren()) {
                                        if (ds1.hasChildren()) {
                                            Student1 student = ds1.getValue(Student1.class);
                                            if (student.getCourse().contains(intentded_course)) {
                                                studentList.add(student);
                                            }
                                        }


                                    }
                                }

                                    takeAttendanceAdapter = new TakeattendanceRVAdapter(getApplicationContext(), studentList);
                                    taRV.setLayoutManager(new LinearLayoutManager(Takeattendance.this));
                                    takeAttendanceAdapter.notifyDataSetChanged();
                                    taRV.setAdapter(takeAttendanceAdapter);
                                }


                            @Override
                            public void onCancelled(@NonNull DatabaseError databaseError) {

                            }
                        });

                    }
                }
            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {

                            }

                        });*/
        TakeattendanceRVAdapter.presentList.clear();
        TakeattendanceRVAdapter.absentList.clear();
        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final AlertDialog dialog = new AlertDialog.Builder(TakeAttendance.this).create();
                view = LayoutInflater.from(TakeAttendance.this).inflate(R.layout.attendancepopup, null);
                TextView total, present, absent;
                Button cancleBtn, confirmBtn;
                total = view.findViewById(R.id.TotalStudentTV);
                present = view.findViewById(R.id.presentStudentTV);
                absent = view.findViewById(R.id.absentStudentTV);
                cancleBtn = view.findViewById(R.id.canclebtn);
                confirmBtn = view.findViewById(R.id.confirmbtn);
                total.setText(Integer.toString(studentList.size()));
                present.setText(Integer.toString(TakeattendanceRVAdapter.presentList.size()));
                absent.setText(Integer.toString(TakeattendanceRVAdapter.absentList.size()));
                dialog.setCancelable(true);

                dialog.setView(view);

                cancleBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }

                });
                confirmBtn.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View view) {
                        String presentstudentID = "";
                        for (int i = 0; i < TakeattendanceRVAdapter.presentList.size(); i++) {
                            presentstudentID = TakeattendanceRVAdapter.presentList.get(i);
                            presentRef.child(presentstudentID).setValue(presentstudentID);
                        }
                        String absentstudentID = "";
                        for (int i = 0; i < TakeattendanceRVAdapter.absentList.size(); i++) {
                            absentstudentID = TakeattendanceRVAdapter.absentList.get(i);
                            absentRef.child(absentstudentID).setValue(absentstudentID);
                        }
                        TakeattendanceRVAdapter.presentList.clear();
                        TakeattendanceRVAdapter.absentList.clear();

                        dialog.cancel();


                        SweetToast.success(getApplicationContext(),"Attendance data added successfully");
                    }
                });

                dialog.show();
            }
        });


    }

    private List<Student1> getData() {
        List<Student1> student1List = new ArrayList<>();
        student1List.add(new Student1("Iqshana","18csc16","Java","UCS/CO/80"));
        student1List.add(new Student1("Gunaseeli","18csc12","Java","UCS/CO/80"));
        student1List.add(new Student1("Chalce","18csc09","Java","UCS/CO/80"));
        return student1List;



    }

    private void popup() {


    }
    @Override
    public boolean onOptionsItemSelected (MenuItem item){

        if (item.getItemId() == android.R.id.home) {
            this.finish();
        }
        return super.onOptionsItemSelected(item);
    }
}

