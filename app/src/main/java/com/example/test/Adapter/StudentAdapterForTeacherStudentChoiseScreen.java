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

import com.example.test.R;

import java.util.ArrayList;
import java.util.List;

public class StudentAdapterForTeacherStudentChoiseScreen extends RecyclerView.Adapter<StudentAdapterForTeacherStudentChoiseScreen.StudentViewHolder> {
    Context context;
    List<String> studentList;
    String groupAddr;
    String teacherDescipline;
    String numGroup;
    public List<String> groupsNameList;
    public List<String> groupsAddrList;

    public StudentAdapterForTeacherStudentChoiseScreen(Context context, List<String> studentList, String groupAddr, String teacherDescipline, String numGroup, List<String> groupsNameList, List<String> groupsAddrList) {
        this.context = context;
        this.studentList = studentList;
        this.groupAddr = groupAddr;
        this.teacherDescipline = teacherDescipline;
        this.numGroup = numGroup;
        this.groupsNameList = groupsNameList;
        this.groupsAddrList = groupsAddrList;
    }

    public StudentAdapterForTeacherStudentChoiseScreen(Context context, List<String> studentList, String groupAddr, String teacherDescipline) {
        this.context = context;
        this.studentList = studentList;
        this.groupAddr = groupAddr;
        this.teacherDescipline = teacherDescipline;
    }

    public StudentAdapterForTeacherStudentChoiseScreen(Context context, List<String> studentList, String groupAddr) {
        this.context = context;
        this.studentList = studentList;
        this.groupAddr = groupAddr;
    }

    public StudentAdapterForTeacherStudentChoiseScreen(Context context, List<String> studentList) {
        this.context = context;
        this.studentList = studentList;
    }

    public StudentAdapterForTeacherStudentChoiseScreen() {
    }

    @NonNull
    @Override
    public StudentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View StudentItem = LayoutInflater.from(context).inflate(R.layout.descipline_list_item, parent, false);
        return new StudentViewHolder(StudentItem);
    }

    @Override
    public void onBindViewHolder(@NonNull StudentAdapterForTeacherStudentChoiseScreen.StudentViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.studentName.setText(studentList.get(position));

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent("com.example.test.WorkExhibitingResultScreen");
                intent.putExtra("StudentNameClick", studentList.get(position));
                intent.putExtra("groupAddr", groupAddr);
                intent.putExtra("StudentNumClick", Integer.toString(position));
                intent.putExtra("teacherDescipline", teacherDescipline);
                intent.putExtra("groupClick", numGroup);
                intent.putStringArrayListExtra("GroupsNameList", (ArrayList<String>) groupsNameList);
                intent.putStringArrayListExtra("GroupsAddrList", (ArrayList<String>) groupsAddrList);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return studentList.size();
    }

    public static final class StudentViewHolder extends RecyclerView.ViewHolder { //с какими полями взаимодействуем

        TextView studentName;

        public StudentViewHolder(@NonNull View itemView) {
            super(itemView);

            studentName = itemView.findViewById(R.id.desciplineName);
        }
    }
}
