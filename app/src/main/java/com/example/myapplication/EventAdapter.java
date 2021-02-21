package com.example.myapplication;

import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;


import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

public class EventAdapter extends RecyclerView.Adapter<EventAdapter.ViewHolderClass>{





    private List<Event> events;


    public EventAdapter(List<Event> events) {

        this.events = events;
    }


    @NonNull
    @Override
    public ViewHolderClass onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.eventlayout,parent,false);
        ViewHolderClass viewHolderClass = new ViewHolderClass(view);

        return viewHolderClass;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderClass holder, int position) {
        String url="https://my-application-c0d64-default-rtdb.firebaseio.com/";
        Event event = events.get(position);
        holder.name.setText(event.getName());
        holder.desc.setText(event.getDescription());
        holder.menupopup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu popupMenu=new PopupMenu(holder.menupopup.getContext(),v);
                popupMenu.getMenuInflater().inflate(R.menu.popup,popupMenu.getMenu());
                popupMenu.show();
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()){
                            case R.id.Delete:
                                events.remove(position);
                                notifyItemRemoved(position);

                        }
                        return true;
                    }
                });
            }
        });

    }



    @Override
    public int getItemCount() {
        return events.size();
    }

    public static class ViewHolderClass extends RecyclerView.ViewHolder{
        TextView name,desc;
        ImageView menupopup;
        public ViewHolderClass(@NonNull View itemView) {
            super(itemView);
            name=itemView.findViewById(R.id.tvEventName);
            desc=itemView.findViewById(R.id.tvEventDescription);
            menupopup=itemView.findViewById(R.id.menumore);
        }
    }
}

