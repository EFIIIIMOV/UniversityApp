package com.example.test;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.test.Adapter.ExhibitingResultAdapterForExhibitingResultScreen;
import com.example.test.Adapter.StudentAdapterForTeacherStudentChoiseScreen;
import com.example.test.BD.Group;
import com.example.test.BD.ResWork;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class WorkExhibitingResultScreen extends AppCompatActivity {
    public TextView StudentName;
    public RecyclerView res_list_edit;
    public String groupAddr;
    public FirebaseDatabase database;
    public DatabaseReference getDataValue;
    public String StudentNumClick;
    public String teacherDescipline;
    public ArrayList<ResWork> resWork = new ArrayList<>();
    public EditText points;
    public EditText grade;
    public ExhibitingResultAdapterForExhibitingResultScreen exhibitingResultAdapterForExhibitingResultScreen = new ExhibitingResultAdapterForExhibitingResultScreen(this, resWork);
    public int teacherDesciplineNum;
    public List<String> groupsNameList = new ArrayList<>();
    public List<String> groupsAddrList = new ArrayList<>();
    public String numGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_work_exhibiting_result_screen);
        System.out.println("РАБОТАЮ");
        teacherDescipline = getIntent().getStringExtra("teacherDescipline");
        StudentName = findViewById(R.id.student_name_res_screen);
        StudentName.setText(getIntent().getStringExtra("StudentNameClick"));
        groupAddr = getIntent().getStringExtra("groupAddr");
        StudentNumClick = getIntent().getStringExtra("StudentNumClick");
        numGroup = getIntent().getStringExtra("groupClick");

        groupsNameList = (List<String>) getIntent().getSerializableExtra("GroupsNameList");
        groupsAddrList = (List<String>) getIntent().getSerializableExtra("GroupsAddrList");
        findStudentRes();
        setResRecycler(resWork);
    }

//    @Override
//    public void onBackPressed(){
//
//    }

    public void onClickSaveButton(View view) {
        points = findViewById(R.id.points_edit);
        grade = findViewById(R.id.grade_edit);
        getDataValue = FirebaseDatabase.getInstance().getReference("dataBase");
        exhibitingResultAdapterForExhibitingResultScreen.testtest();
        for (int i = 0; i < exhibitingResultAdapterForExhibitingResultScreen.points.size(); i++) {
            getDataValue.child(groupAddr + "/groupList/" + StudentNumClick + "/userDesscipline/" + Integer.toString(teacherDesciplineNum) + "/resWorks/" + Integer.toString(i) + "/point").setValue(exhibitingResultAdapterForExhibitingResultScreen.points.get(i));
            getDataValue.child(groupAddr + "/groupList/" + StudentNumClick + "/userDesscipline/" + Integer.toString(teacherDesciplineNum) + "/resWorks/" + Integer.toString(i) + "/grade").setValue(exhibitingResultAdapterForExhibitingResultScreen.grade.get(i));
//            if (i + 1 == exhibitingResultAdapterForExhibitingResultScreen.points.size()) {
//                Intent intent = new Intent("com.example.test.TeacherStudentChoiseScreen");
//
//                intent.putExtra("groupClick", numGroup);
//                intent.putStringArrayListExtra("GroupsNameList", (ArrayList<String>) groupsNameList);
//                intent.putStringArrayListExtra("GroupsAddrList", (ArrayList<String>) groupsAddrList);
//
//                intent.putExtra("teacherDescipline", teacherDescipline);
//                startActivity(intent);
//                finish();
//            }
        }

//        startActivity(getIntent());
//        finish();
//        overridePendingTransition(0, 0);
    }

    public void findStudentRes() {
        database = FirebaseDatabase.getInstance();
        getDataValue = FirebaseDatabase.getInstance().getReference("dataBase");
        ValueEventListener valueEventListener = new ValueEventListener() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Group group = snapshot.child(groupAddr).getValue(Group.class);
                int desciplineCount = group.groupList.get(Integer.parseInt(StudentNumClick)).userDesscipline.size();
                for (int i = 0; i < desciplineCount; i++) {
                    if (group.groupList.get(Integer.parseInt(StudentNumClick)).userDesscipline.get(i).name_descipline.equals(teacherDescipline)) {
                        teacherDesciplineNum = i;
                        for (int j = 0; j < group.groupList.get(Integer.parseInt(StudentNumClick)).userDesscipline.get(i).resWorks.size(); j++) {
                            resWork.add(group.groupList.get(Integer.parseInt(StudentNumClick)).userDesscipline.get(i).resWorks.get(j));
                        }
                    }
                }
                //exhibitingResultAdapterForExhibitingResultScreen.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        };
        getDataValue.addValueEventListener(valueEventListener);
    }

    private void setResRecycler(ArrayList<ResWork> resWork) {
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        res_list_edit = findViewById(R.id.res_list_edit);
        res_list_edit.setLayoutManager(layoutManager);
        res_list_edit.setAdapter(exhibitingResultAdapterForExhibitingResultScreen);

    }

}