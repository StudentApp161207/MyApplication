    package com.example.myapplication;

    import androidx.annotation.NonNull;
    import androidx.appcompat.app.AppCompatActivity;
    import androidx.recyclerview.widget.LinearLayoutManager;
    import androidx.recyclerview.widget.RecyclerView;

    import android.content.Intent;
    import android.os.Bundle;
    import android.view.View;

    import com.google.android.material.floatingactionbutton.FloatingActionButton;
    import com.google.firebase.database.DataSnapshot;
    import com.google.firebase.database.DatabaseError;
    import com.google.firebase.database.DatabaseReference;
    import com.google.firebase.database.FirebaseDatabase;
    import com.google.firebase.database.ValueEventListener;

    import java.util.ArrayList;
    import java.util.List;

    public class Calendar extends AppCompatActivity {

        private RecyclerView rvEvents;
        FloatingActionButton fb;
        List<Event> event;
        DatabaseReference databaseReference;

       EventAdapter adapter;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_calendar);

            rvEvents = findViewById(R.id.rvEvents);
            rvEvents.setHasFixedSize(true);
            rvEvents.setLayoutManager(new LinearLayoutManager(this));
            String url ="https://my-application-c0d64-default-rtdb.firebaseio.com/";

            event = new ArrayList<>();
            fb = findViewById(R.id.fab);
            fb.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(Calendar.this,AddEvent.class);
                    startActivity(intent);
                }
            });

            databaseReference = FirebaseDatabase.getInstance(url).getReference("Events");
            databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    for (DataSnapshot ds:dataSnapshot.getChildren()){
                        Event data = ds.getValue(Event.class);
                        event.add(data);
                    }
                    adapter=new EventAdapter(event);
                    rvEvents.setAdapter(adapter);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });



            //   EventAdapter eventsAdapter = new EventAdapter(Calendar.this, events);


        }



    }
