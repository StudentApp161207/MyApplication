package com.example.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.Student1;

import java.util.ArrayList;
import java.util.List;

class TakeattendanceRVAdapter extends RecyclerView.Adapter<TakeattendanceRVAdapter.TakeattendanceRVviewholder>{
    private List<Student1> studentList=new ArrayList<>();
    private Context context;
    public  static List<String> presentList=new ArrayList<>();
    public  static List<String> absentList=new ArrayList<>();

    public TakeattendanceRVAdapter(Context context, List<Student1> studentList) {

        this.context = context;
        this.studentList = studentList;
    }
    public TakeattendanceRVAdapter(){

    }


    @NonNull
    @Override
    public TakeattendanceRVAdapter.TakeattendanceRVviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.takeattendance_layout,parent,false);
        return new TakeattendanceRVviewholder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull TakeattendanceRVAdapter.TakeattendanceRVviewholder holder, int position) {
        holder.Student_name.setText(studentList.get(holder.getAdapterPosition()).getName());

        holder.Student_Id.setText(studentList.get(holder.getAdapterPosition()).getId());

        holder.presentRBtn.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    if(!presentList.contains(studentList.get(holder.getAdapterPosition()).getId())){
                        presentList.add(studentList.get(holder.getAdapterPosition()).getId());

                    }

                }else {
                    presentList.remove(studentList.get(holder.getAdapterPosition()).getId());
                }
            }
        });
        holder.absentRBtn.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){

                    absentList.add(studentList.get(holder.getAdapterPosition()).getId());
                }
                else {
                    absentList.remove(studentList.get(holder.getAdapterPosition()).getId());
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return studentList.size();
    }

    public class TakeattendanceRVviewholder extends RecyclerView.ViewHolder {
        TextView Student_name;
        TextView Student_Id;
        RadioButton presentRBtn;
        RadioButton absentRBtn;
        public TakeattendanceRVviewholder(@NonNull View itemView) {
            super(itemView);
            Student_name=itemView.findViewById(R.id.takeattendanceStudentName);
            Student_Id =itemView.findViewById(R.id.takeattendanceStudentID);
            presentRBtn=itemView.findViewById(R.id.presentRadioBtn);
            absentRBtn=itemView.findViewById(R.id.absentRadioBtn);

        }
    }
    public void updateCollection(List<Student1> studentList){
        this.studentList=studentList;
        notifyDataSetChanged();
    }
}


