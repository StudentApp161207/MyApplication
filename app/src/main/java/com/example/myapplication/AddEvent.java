package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class AddEvent extends AppCompatActivity {

    DatabaseReference ref;
    TextInputEditText name,desc;
    Event event;
    AppCompatButton add;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_event);
        String url ="https://my-application-c0d64-default-rtdb.firebaseio.com/";

        name = findViewById(R.id.tvEventName);
        desc = findViewById((R.id.tvEventDescription));
        add = findViewById(R.id.add);

        event = new Event();
        ref= FirebaseDatabase.getInstance(url).getReference().child("Events");
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String evname = name.getText().toString();
                String evdesc = desc.getText().toString();
                event.setName(evname);
                event.setDescription(evdesc);
                ref.push().setValue(event);
                Toast.makeText(AddEvent.this,"Event added",Toast.LENGTH_SHORT).show();


            }
        });


    }
}