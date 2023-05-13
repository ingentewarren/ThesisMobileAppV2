package com.example.thesisprojectmobileapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    Context context;
    ArrayList<schedule> list;

    public MyAdapter(Context context, ArrayList<schedule> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        schedule schedules = list.get(position);

        holder.time_start.setText(schedules.getTime_start());
        holder.time_end.setText(schedules.getTime_end());
        holder.subject.setText(schedules.getSubject());
        holder.section.setText(schedules.getSection());
        holder.instructor.setText(schedules.getInstructor());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView time_start, time_end, subject, section, instructor;

        public MyViewHolder(@NonNull View itemView){
            super(itemView);

            time_start = itemView.findViewById(R.id.time_starts);
            time_end = itemView.findViewById(R.id.time_ends);
            subject = itemView.findViewById(R.id.subject_code);
            section = itemView.findViewById((R.id.sections));
            instructor = itemView.findViewById(R.id.instructors);
        }
    }
}