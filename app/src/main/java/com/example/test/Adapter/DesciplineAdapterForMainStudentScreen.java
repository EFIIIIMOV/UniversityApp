package com.example.test.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.test.BD.Descipline;
import com.example.test.R;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class DesciplineAdapterForMainStudentScreen extends RecyclerView.Adapter<DesciplineAdapterForMainStudentScreen.DesciplineViewHolder> {

    Context context;
    ArrayList<Descipline> desciplines;

    public DesciplineAdapterForMainStudentScreen(Context context, ArrayList<Descipline> desciplines) {
        this.context = context;
        this.desciplines = desciplines;
    }

    @NonNull
    @Override
    public DesciplineViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) { //указание дизайна
        View desciplineItems = LayoutInflater.from(context).inflate(R.layout.descipline_list_item, parent, false);
        return new DesciplineViewHolder(desciplineItems);
    }

    @Override
    public void onBindViewHolder(@NonNull DesciplineViewHolder holder, @SuppressLint("RecyclerView") int position) { //работаем с полями
        holder.desciplineName.setText(desciplines.get(position).getName_descipline());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent("com.example.test.StudentResScreen");
                intent.putExtra("DesciplineNameClick", desciplines.get(position).getName_descipline());
                intent.putExtra("resWorkList", desciplines.get(position).getResWorks());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return desciplines.size();
    }

    public static final class DesciplineViewHolder extends RecyclerView.ViewHolder { //с какими полями взаимодействуем
        TextView desciplineName;

        public DesciplineViewHolder(@NonNull View itemView) {
            super(itemView);
            desciplineName = itemView.findViewById(R.id.desciplineName);
        }
    }
}
