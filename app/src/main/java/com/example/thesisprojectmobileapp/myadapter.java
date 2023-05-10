package com.example.thesisprojectmobileapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class myadapter extends RecyclerView.Adapter<myadapter.MyViewHolder> {

    Context context;
    ArrayList<schedule> list;

    public myadapter(Context context, ArrayList<schedule> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.item,parent,false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        schedule schedules = list.get(position);
        holder.instructor.setText(schedules.getInstructor());
        holder.section.setText(schedules.getSection());
        holder.subject.setText(schedules.getSubject());
        holder.time_start.setText(schedules.getTime_start());
        holder.time_end.setText(schedules.getTime_end());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView instructor, section, subject, time_start, time_end;

        public MyViewHolder(@NonNull View itemview){
            super(itemview);

            subject = itemview.findViewById(R.id.subject_code);
            instructor = itemview.findViewById(R.id.instructors);
            section = itemview.findViewById((R.id.sections));
            time_start = itemview.findViewById(R.id.time_starts);
            time_end = itemview.findViewById(R.id.time_ends);
        }
    }
}
