package com.example.test.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.test.BD.ResWork;
import com.example.test.R;

import java.util.List;

public class ResAdapterForStudentResScreen extends RecyclerView.Adapter<ResAdapterForStudentResScreen.ResViewHolder> {
    Context context;
    List<ResWork> resWorks;

    public ResAdapterForStudentResScreen(Context context, List<ResWork> resWorks) {
        this.context = context;
        this.resWorks = resWorks;
    }

    @NonNull
    @Override
    public ResViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View resWorkItem = LayoutInflater.from(context).inflate(R.layout.res_list_item, parent, false);
        return new ResViewHolder(resWorkItem);
    }

    @Override
    public void onBindViewHolder(@NonNull ResViewHolder holder, int position) {
        holder.workType.setText(resWorks.get(position).getWorkType());
        holder.points.setText(resWorks.get(position).getPoint());
        holder.grade.setText(resWorks.get(position).getGrade());
    }

    @Override
    public int getItemCount() {
        return resWorks.size();
    }

    public static final class ResViewHolder extends RecyclerView.ViewHolder {
        TextView workType;
        TextView points;
        TextView grade;

        public ResViewHolder(@NonNull View itemView) {
            super(itemView);
            workType = itemView.findViewById(R.id.workType);
            points = itemView.findViewById(R.id.points);
            grade = itemView.findViewById(R.id.grade);
        }
    }
}
