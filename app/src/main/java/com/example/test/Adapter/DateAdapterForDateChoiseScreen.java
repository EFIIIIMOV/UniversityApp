package com.example.test.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.test.BD.Descipline;
import com.example.test.R;

import java.util.ArrayList;

public class DateAdapterForDateChoiseScreen extends RecyclerView.Adapter<DateAdapterForDateChoiseScreen.DateViewHolder> {
    Context context;
    ArrayList<String> dateLesson;
    String groupKey;
    String teacherNum;
    String numGroup;

    public DateAdapterForDateChoiseScreen(Context context, ArrayList<String> dateLesson, String groupKey, String teacherNum, String numGroup) {
        this.context = context;
        this.dateLesson = dateLesson;
        this.groupKey = groupKey;
        this.teacherNum = teacherNum;
        this.numGroup = numGroup;
    }

    public DateAdapterForDateChoiseScreen(Context context, ArrayList<String> dateLesson, String groupKey) {
        this.context = context;
        this.dateLesson = dateLesson;
        this.groupKey = groupKey;
    }

    public DateAdapterForDateChoiseScreen(Context context, ArrayList<String> dateLesson) {
        this.context = context;
        this.dateLesson = dateLesson;
    }

    @NonNull
    @Override
    public DateViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View dateItem = LayoutInflater.from(context).inflate(R.layout.descipline_list_item, parent, false);
        return new DateViewHolder(dateItem);
    }

    @Override
    public void onBindViewHolder(@NonNull DateAdapterForDateChoiseScreen.DateViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.date.setText(dateLesson.get(position));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent("com.example.test.StudentVisitsScreen");
                intent.putExtra("groupKey", groupKey);
                intent.putExtra("teacherNum", teacherNum);
                intent.putExtra("numGroup", numGroup);
                intent.putExtra("dateClick", Integer.toString(position));
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return dateLesson.size();
    }

    public final class DateViewHolder extends RecyclerView.ViewHolder {
        TextView date;

        public DateViewHolder(@NonNull View itemView) {
            super(itemView);
            date = itemView.findViewById(R.id.desciplineName);
        }
    }
}
