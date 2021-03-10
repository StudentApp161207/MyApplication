package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.FirebaseDatabase;

public class Coursepage_y1 extends AppCompatActivity {

    TextView name;
    FloatingActionButton fb;
    RecyclerView recview;
FileAdapter adapter;
@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coursepage_y1);

        name = findViewById(R.id.Coursetitle);
        Intent intent = getIntent();
        name.setText(intent.getStringExtra("name"));

    fb=(FloatingActionButton)findViewById(R.id.floatingActionButton);
    fb.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            startActivity(new Intent(getApplicationContext(),CourseFiles.class));
        }
    });

    recview = (RecyclerView) findViewById(R.id.recview);
    recview.setLayoutManager(new LinearLayoutManager(this));
    String url="https://my-application-c0d64-default-rtdb.firebaseio.com/";

    FirebaseRecyclerOptions<FileInfo> options =
            new FirebaseRecyclerOptions.Builder<FileInfo>()
                    .setQuery(FirebaseDatabase.getInstance(url).getReference("files"), FileInfo.class)
                    .build();

    adapter=new FileAdapter(options);
    recview.setAdapter(adapter);

}

    private void getData(){

    }

    private void setData(){

    }
}