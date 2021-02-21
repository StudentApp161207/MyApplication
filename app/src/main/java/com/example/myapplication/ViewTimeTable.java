package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;


public class ViewTimeTable extends AppCompatActivity {
    RecyclerView recview;
    StudAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_time_table);

        recview = (RecyclerView) findViewById(R.id.recview);
        recview.setLayoutManager(new LinearLayoutManager(this));
        String url="https://my-application-c0d64-default-rtdb.firebaseio.com/";

        FirebaseRecyclerOptions<FileInfo> options =
                new FirebaseRecyclerOptions.Builder<FileInfo>()
                        .setQuery(FirebaseDatabase.getInstance(url).getReference().child("mydocuments"), FileInfo.class)
                        .build();

        adapter=new StudAdapter(options);
        recview.setAdapter(adapter);

    }
    @Override
    protected void onStart() {
        super.onStart();
        adapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        adapter.stopListening();
    }
}