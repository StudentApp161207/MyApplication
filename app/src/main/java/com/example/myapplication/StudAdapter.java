package com.example.myapplication;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;



public class StudAdapter extends FirebaseRecyclerAdapter<FileInfo, StudAdapter.myviewholder> {





    public StudAdapter(@NonNull FirebaseRecyclerOptions<FileInfo> options){
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull myviewholder myviewholder, int i, @NonNull FileInfo fileInfo) {
        myviewholder.header.setText(fileInfo.getFilename());
        myviewholder.img1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(myviewholder.img1.getContext(),ViewPdf.class);
                intent.putExtra("filename",fileInfo.getFilename());
                intent.putExtra("fileurl",fileInfo.getFileUrl());
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                myviewholder.img1.getContext().startActivity(intent);
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
        ImageView img1;
        TextView header;

        public myviewholder(@NonNull View itemView){
            super(itemView);
            img1 = itemView.findViewById(R.id.pdf);
            header = itemView.findViewById(R.id.header);


        }

    }

}
