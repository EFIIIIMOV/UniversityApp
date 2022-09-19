package com.example.test.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.test.R;
import com.example.test.StudentVisitsScreen;

import java.util.ArrayList;
import java.util.List;

public class StudentVisitsAdapterForStudentsVisitsScreen extends RecyclerView.Adapter<StudentVisitsAdapterForStudentsVisitsScreen.VisitsViewHolder> {

    public Context context;
    public ArrayList<String> nameSurname;
    public ArrayList<String> visitsEx;
    public ArrayList<String> newVistsEx = new ArrayList<>();

    public List<StudentVisitsAdapterForStudentsVisitsScreen.VisitsViewHolder> holders = new ArrayList<>();

    public StudentVisitsAdapterForStudentsVisitsScreen(Context context, ArrayList<String> nameSurname, ArrayList<String> visitsEx) {
        this.context = context;
        this.nameSurname = nameSurname;
        this.visitsEx = visitsEx;
    }

    @NonNull
    @Override
    public VisitsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View visitsItem = LayoutInflater.from(context).inflate(R.layout.visits_list_item, parent, false);
        return new VisitsViewHolder(visitsItem);
    }

    @Override
    public void onBindViewHolder(@NonNull VisitsViewHolder holder, int position) {
        holder.name.setText(nameSurname.get(position));
        holder.visitsExt.setText(visitsEx.get(position));
        holders.add(holder);

    }

    public void testtest() {
        for (int i = 0; i < holders.size(); i++) {
            String temp = holders.get(i).visitsExt.getText().toString();
            newVistsEx.add(temp);
            System.out.println(temp);
        }
        System.out.println(newVistsEx);
    }

    @Override
    public int getItemCount() {
        return nameSurname.size();
    }


    public final class VisitsViewHolder extends RecyclerView.ViewHolder {
        TextView name;
        EditText visitsExt;

        public VisitsViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.userNameSurVisitsScreen);
            visitsExt = itemView.findViewById(R.id.visitsUserField);
        }
    }
}
