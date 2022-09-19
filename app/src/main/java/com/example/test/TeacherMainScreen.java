package com.example.test;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.test.Adapter.DesciplineAdapterForMainStudentScreen;
import com.example.test.Adapter.GroupAdapterForMainTeatherScreen;
import com.example.test.BD.Descipline;
import com.example.test.BD.Group;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class TeacherMainScreen extends AppCompatActivity {

    FirebaseDatabase database;
    DatabaseReference getDatavalue;
    GroupAdapterForMainTeatherScreen groupAdapterForMainTeatherScreen;
    RecyclerView group_list;
    ArrayList<String> groupsList = new ArrayList<>();
    ArrayList<String> groupListAddr = new ArrayList<>();
    String teacherDescipline;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_main_screen);
        groupsList.add("===");
        groupfind();

    }

    public void groupfind() {
        database = FirebaseDatabase.getInstance();
        getDatavalue = FirebaseDatabase.getInstance().getReference("dataBase");
        ValueEventListener valueEventListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String groupKey = getIntent().getStringExtra("groupKey");
                String teacherNum = getIntent().getStringExtra("teacherNum");

                Group groupTeacher = snapshot.child(groupKey).getValue(Group.class);
                assert groupTeacher != null;
                int groupCount = groupTeacher.groupList.get(Integer.parseInt(teacherNum)).userDesscipline.get(0).groupThisDescipline.size();
                groupsList.clear();
                teacherDescipline = groupTeacher.groupList.get(Integer.parseInt(teacherNum)).userDesscipline.get(0).name_descipline;
                for (int i = 0; i < groupCount; i++) {
                    String groupThisTeacher = groupTeacher.groupList.get(Integer.parseInt(teacherNum)).userDesscipline.get(0).groupThisDescipline.get(i);
                    Group groupStudent = snapshot.child(groupThisTeacher).getValue(Group.class);
                    String groupName = groupStudent.groupName;
                    groupsList.add(groupName);
                    groupListAddr.add(groupThisTeacher);
                }
                System.out.println(teacherNum);
                setGroupRecycler(groupsList, groupListAddr, teacherDescipline, teacherNum, groupKey);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        };
        getDatavalue.addValueEventListener(valueEventListener);
    }

    private void setGroupRecycler(ArrayList<String> groupList, ArrayList<String> groupAddrList, String teacherDescipline, String teacherNum, String groupKey) {
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        group_list = findViewById(R.id.teatherGroupList);
        group_list.setLayoutManager(layoutManager);
        groupAdapterForMainTeatherScreen = new GroupAdapterForMainTeatherScreen(this, groupList, groupAddrList, teacherDescipline, teacherNum, groupKey);
        group_list.setAdapter(groupAdapterForMainTeatherScreen);
    }
}