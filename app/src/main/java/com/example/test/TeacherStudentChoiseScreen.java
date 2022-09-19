package com.example.test;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;

import com.example.test.Adapter.GroupAdapterForMainTeatherScreen;
import com.example.test.Adapter.StudentAdapterForTeacherStudentChoiseScreen;
import com.example.test.BD.Group;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class TeacherStudentChoiseScreen extends AppCompatActivity {

    StudentAdapterForTeacherStudentChoiseScreen studentAdapterForTeacherStudentChoiseScreen;
    RecyclerView student_list;
    FirebaseDatabase database;
    DatabaseReference getDataValue;
    List<String> groupsNameList = new ArrayList<>();
    List<String> groupsAddrList = new ArrayList<>();
    String numGroup;
    ArrayList<String> Students = new ArrayList<>();
    String teacherDescipline;
    String teacherNum;
    String groupKey;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_student_choise_screen);

        teacherDescipline = getIntent().getStringExtra("teacherDescipline");
        numGroup = getIntent().getStringExtra("groupClick");
        groupsNameList = (List<String>) getIntent().getSerializableExtra("GroupsNameList");
        groupsAddrList = (List<String>) getIntent().getSerializableExtra("GroupsAddrList");
        teacherNum = getIntent().getStringExtra("teacherNum");
        groupKey = getIntent().getStringExtra("groupKey");


        System.out.println(teacherDescipline);
        System.out.println("numGroup = " + numGroup);
        System.out.println(groupsNameList);
        System.out.println(groupsAddrList);

        createStudentList();
        setStudentRecycler(Students, groupsAddrList.get(Integer.parseInt(numGroup)), teacherDescipline, numGroup, groupsNameList, groupsAddrList);
    }


    private void setStudentRecycler(ArrayList<String> Students, String groupAddr, String teacherDescipline, String numGroup, List<String> groupsNameList, List<String> groupsAddrList) {
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);

        student_list = findViewById(R.id.studentChoiseList);
        student_list.setLayoutManager(layoutManager);
        studentAdapterForTeacherStudentChoiseScreen = new StudentAdapterForTeacherStudentChoiseScreen(this, Students, groupAddr, teacherDescipline, numGroup, groupsNameList, groupsAddrList);
        student_list.setAdapter(studentAdapterForTeacherStudentChoiseScreen);
    }

    public void createStudentList() {
        database = FirebaseDatabase.getInstance();
        getDataValue = FirebaseDatabase.getInstance().getReference("dataBase");
        ValueEventListener valueEventListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Group group = snapshot.child(groupsAddrList.get(Integer.parseInt(numGroup))).getValue(Group.class);
                for (int i = 0; i < group.groupList.size(); i++) {
                    Students.add(group.groupList.get(i).name + " " + group.groupList.get(i).surname);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        };
        getDataValue.addValueEventListener(valueEventListener);
    }

    public void onClickVisitsButton(View view) {
        System.out.println("Нажал кнопку посещений");
        Intent intent = new Intent("com.example.test.DateChoiseScreen");
        intent.putExtra("teacherNum", teacherNum);
        intent.putExtra("numGroup", numGroup);
        intent.putExtra("groupKey", groupKey);
        startActivity(intent);
    }

    public void onClickBackButton(View view) {
        Intent intent = new Intent("com.example.test.TeacherMainScreen");
        startActivity(intent);
    }


}