package com.example.test;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;

import com.example.test.Adapter.DateAdapterForDateChoiseScreen;
import com.example.test.Adapter.StudentVisitsAdapterForStudentsVisitsScreen;
import com.example.test.BD.Group;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class StudentVisitsScreen extends AppCompatActivity {

    RecyclerView visitsList;
    public ArrayList<String> name = new ArrayList<>();
    public ArrayList<String> visitsEx = new ArrayList<>();
    StudentVisitsAdapterForStudentsVisitsScreen studentVisitsAdapterForStudentsVisitsScreen = new StudentVisitsAdapterForStudentsVisitsScreen(this, name, visitsEx);
    public FirebaseDatabase database;
    public DatabaseReference getDataValue;
    public String groupKey;
    String teacherNum;
    String numGroup;
    String dateClick;
    String groupAddr;
    int groupCount;
    int desciplineNum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_visits_screen);

        groupKey = getIntent().getStringExtra("groupKey");
        teacherNum = getIntent().getStringExtra("teacherNum");
        numGroup = getIntent().getStringExtra("numGroup");
        dateClick = getIntent().getStringExtra("dateClick");
        System.out.println("dateClick = " + dateClick);
        findVisits();
        setVisitsRecycler(name, visitsEx);

    }

    public void findVisits() {
        database = FirebaseDatabase.getInstance();
        getDataValue = FirebaseDatabase.getInstance().getReference("dataBase");
        ValueEventListener valueEventListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Group groupTeacher = snapshot.child(groupKey).getValue(Group.class);
                groupAddr = groupTeacher.groupList.get(Integer.parseInt(teacherNum)).userDesscipline.get(0).groupThisDescipline.get(Integer.parseInt(numGroup));
                String teacherDescipline = groupTeacher.groupList.get(Integer.parseInt(teacherNum)).userDesscipline.get(0).name_descipline;
                Group groupStudent = snapshot.child(groupAddr).getValue(Group.class);
                groupCount = groupStudent.groupList.size();
                for (int i = 0; i < groupStudent.groupList.size(); i++) {
                    for (int j = 0; j < groupStudent.groupList.get(i).userDesscipline.size(); j++)
                        if (groupStudent.groupList.get(i).userDesscipline.get(j).name_descipline.equals(teacherDescipline)) {
                            desciplineNum = j;
                            System.out.println(groupStudent.groupList.get(i).userDesscipline.get(j).visits.get(Integer.parseInt(dateClick)));
                            name.add(groupStudent.groupList.get(i).name + " " + groupStudent.groupList.get(i).surname);
                            visitsEx.add(groupStudent.groupList.get(i).userDesscipline.get(j).visits.get(Integer.parseInt(dateClick)));
                        }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        };
        getDataValue.addValueEventListener(valueEventListener);
    }

    private void setVisitsRecycler(ArrayList<String> name, ArrayList<String> visitsEx) {
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);

        visitsList = findViewById(R.id.visitsList);
        visitsList.setLayoutManager(layoutManager);

        studentVisitsAdapterForStudentsVisitsScreen = new StudentVisitsAdapterForStudentsVisitsScreen(this, name, visitsEx);
        visitsList.setAdapter(studentVisitsAdapterForStudentsVisitsScreen);
    }

    public void onClickSaveVisitsButton(View view) {
        getDataValue = FirebaseDatabase.getInstance().getReference("dataBase");
        studentVisitsAdapterForStudentsVisitsScreen.testtest();
        for (int i = 0; i < groupCount; i++) {
            //System.out.println(getDataValue.child(groupAddr + "/groupList/" + Integer.toString(i) + "/userDesscipline/" + Integer.toString(desciplineNum) + "/visits/" + dateClick).setValue("000"));
            getDataValue.child(groupAddr + "/groupList/" + Integer.toString(i) + "/userDesscipline/" + Integer.toString(desciplineNum) + "/visits/" + dateClick).setValue(studentVisitsAdapterForStudentsVisitsScreen.newVistsEx.get(i));
        }
    }


}