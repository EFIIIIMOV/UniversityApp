package com.example.test.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.test.BD.ResWork;
import com.example.test.R;

import java.util.ArrayList;
import java.util.List;

public class ExhibitingResultAdapterForExhibitingResultScreen extends RecyclerView.Adapter<ExhibitingResultAdapterForExhibitingResultScreen.ExhibitingViewHolder> {
    Context context;
    List<ResWork> studentResWorkList;
    public List<ExhibitingResultAdapterForExhibitingResultScreen.ExhibitingViewHolder> holders = new ArrayList<>();
    public ArrayList<String> points = new ArrayList<>();
    public ArrayList<String> grade = new ArrayList<>();


    public ExhibitingResultAdapterForExhibitingResultScreen(Context context, List<ResWork> studentResWorkList) {
        this.context = context;
        this.studentResWorkList = studentResWorkList;
    }

    public ExhibitingResultAdapterForExhibitingResultScreen() {
    }

    @NonNull
    @Override
    public ExhibitingViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View studentResItem = LayoutInflater.from(context).inflate(R.layout.res_list_item_for_teacher, parent, false);
        return new ExhibitingViewHolder(studentResItem);
    }

    @Override
    public void onBindViewHolder(@NonNull ExhibitingResultAdapterForExhibitingResultScreen.ExhibitingViewHolder holder, int position) {
        holder.workTypeName.setText(studentResWorkList.get(position).workType);
        holder.workPoints.setText(studentResWorkList.get(position).point);
        holder.workGrade.setText(studentResWorkList.get(position).grade);
        holders.add(holder);
    }

    public void testtest() {
        for (int i = 0; i < holders.size(); i++) {
            points.add(holders.get(i).workPoints.getText().toString());
            grade.add(holders.get(i).workGrade.getText().toString());
        }
    }

    @Override
    public int getItemCount() {
        return studentResWorkList.size();
    }

    public static final class ExhibitingViewHolder extends RecyclerView.ViewHolder { //с какими полями взаимодействуем
        TextView workTypeName;
        EditText workPoints;
        EditText workGrade;

        public ExhibitingViewHolder(@NonNull View itemView) {
            super(itemView);

            workTypeName = itemView.findViewById(R.id.workType_edit);
            workPoints = itemView.findViewById(R.id.points_edit);
            workGrade = itemView.findViewById(R.id.grade_edit);
        }
    }
}
