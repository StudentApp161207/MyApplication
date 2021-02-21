package com.example.myapplication;


import android.content.Intent;
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

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.FirebaseDatabase;


public class     myAdapter extends FirebaseRecyclerAdapter<FileInfo, myAdapter.myviewholder> {

    String url="https://my-application-c0d64-default-rtdb.firebaseio.com/";



    public myAdapter(@NonNull FirebaseRecyclerOptions<FileInfo> options){
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull myviewholder holder, int i, @NonNull FileInfo fileInfo) {
        holder.header.setText(fileInfo.getFilename());
        holder.img1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(holder.img1.getContext(),ViewPdf.class);
                intent.putExtra("filename",fileInfo.getFilename());
                intent.putExtra("fileurl",fileInfo.getFileUrl());
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                holder.img1.getContext().startActivity(intent);
            }
        });
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
                                FirebaseDatabase.getInstance(url).getReference()
                                        .child("mydocuments")
                                        .child(getRef(i).getKey())
                                        .setValue(null)
                                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                                            @Override
                                            public void onComplete(@NonNull Task<Void> task) {
                                                Toast.makeText(holder.menupopup.getContext(),"File is deleted",Toast.LENGTH_SHORT).show();
                                            }
                                        });

                        }
                        return true;
                    }
                });
            }
        });

    }

    @NonNull
    @Override
    public myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row,parent,false);

        return new myviewholder(view);
    }

    public class myviewholder extends RecyclerView.ViewHolder
    {
        ImageView img1,menupopup;
        TextView header;

        public myviewholder(@NonNull View itemView){
            super(itemView);
            menupopup=itemView.findViewById(R.id.menumore);
            img1 = itemView.findViewById(R.id.pdf);
            header = itemView.findViewById(R.id.header);


        }

    }

}
