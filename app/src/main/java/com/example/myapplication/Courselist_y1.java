package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Courselist_y1 extends AppCompatActivity {
    FloatingActionButton addcourse;
    RecyclerView recview;
    CourseAdapter adapter;
    DatabaseReference courseref;
    List<model> courselist = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_courselist_y1);
        addcourse=(FloatingActionButton)findViewById(R.id.addbtn);
        recview = (RecyclerView)findViewById(R.id.rview);
        recview.setLayoutManager(new LinearLayoutManager(this));
        String url="https://my-application-c0d64-default-rtdb.firebaseio.com/";

        addcourse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Courselist_y1.this,AddCourse.class);
                startActivity(intent);
            }
        });
        courseref=FirebaseDatabase.getInstance(url).getReference("courselist");
        courseref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                courselist.clear();
                for (DataSnapshot ds:dataSnapshot.getChildren()){
                    model data = ds.getValue(model.class);
                    courselist.add(data);
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
               FirebaseRecyclerOptions<model> options = new FirebaseRecyclerOptions.Builder<model>()
                .setQuery(FirebaseDatabase.getInstance(url).getReference("courselist"), model.class)
                .build();

        adapter = new CourseAdapter(options);
        recview.setAdapter(adapter);
    }

    @Override
    protected void onStart(){
        super.onStart();
        adapter.startListening();
    }
    @Override
    protected void onStop(){
        super.onStop();
        adapter.stopListening();
    }
}
