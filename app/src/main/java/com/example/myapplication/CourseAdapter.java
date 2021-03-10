package com.example.myapplication;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

public class CourseAdapter extends FirebaseRecyclerAdapter<model,CourseAdapter.viewholder> {
    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public CourseAdapter(@NonNull FirebaseRecyclerOptions<model> options) {
        super(options);
    }


    @NonNull
    @Override
    public viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rowy1,parent,false);
        return new viewholder(view);
    }

    @Override
    protected void onBindViewHolder(@NonNull viewholder viewholder, int i, @NonNull model model) {
        viewholder.cname.setText(model.getCname());
        viewholder.ccode.setText(model.getCcode());
        viewholder.ctutor.setText(model.getCtutor());
        viewholder.img.setText(model.getCname().substring(0,1));
        viewholder.mainLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(viewholder.mainLayout.getContext(),Coursepage_y1.class);

                intent.putExtra("name",model.getCname());
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                viewholder.mainLayout.getContext().startActivity(intent);

            }
        });

    }


    public class viewholder extends RecyclerView.ViewHolder {
        TextView img;
        TextView cname,ccode,ctutor;
        LinearLayout mainLayout;
        public viewholder(@NonNull View itemView) {
            super(itemView);
            img = (TextView) itemView.findViewById(R.id.img1);
            cname = (TextView)itemView.findViewById(R.id.maintext);
            ccode = (TextView)itemView.findViewById(R.id.subtext1);
            ctutor = (TextView)itemView.findViewById(R.id.subtext2);
            mainLayout = (LinearLayout)itemView.findViewById(R.id.mainLayout);
        }
    }
}
