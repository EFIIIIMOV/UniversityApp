package com.example.test;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.TextView;

import com.example.test.Adapter.DesciplineAdapterForMainStudentScreen;
import com.example.test.BD.Descipline;
import com.example.test.BD.Group;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class StudentMainScreen extends AppCompatActivity {

    FirebaseDatabase database;
    DatabaseReference getDatavalue;
    RecyclerView Descipline_list;
    DesciplineAdapterForMainStudentScreen desciplineAdapterForMainStudentScreen;
    int now_descipline;
    ArrayList<Descipline> DesciplineList = new ArrayList<>();
    TextView userNameSurname;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_main_screen);
        findDesciplineList();
    }

    private void setDesciplineRecycler(ArrayList<Descipline> desciplineList) {
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);

        Descipline_list = findViewById(R.id.Descipline_list);
        Descipline_list.setLayoutManager(layoutManager);
        desciplineAdapterForMainStudentScreen = new DesciplineAdapterForMainStudentScreen(this, desciplineList);
        Descipline_list.setAdapter(desciplineAdapterForMainStudentScreen);
    }

    public void findDesciplineList() {
        database = FirebaseDatabase.getInstance();
        getDatavalue = FirebaseDatabase.getInstance().getReference("dataBase");
        ValueEventListener valueEventListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String groupKey = getIntent().getStringExtra("groupKey");
                String userNum = getIntent().getStringExtra("userNum");
                Group group = snapshot.child(groupKey).getValue(Group.class);
                while (now_descipline < group.groupList.get(Integer.parseInt(userNum)).userDesscipline.size()) {
                    DesciplineList.add(group.groupList.get(Integer.parseInt(userNum)).userDesscipline.get(now_descipline));
                    now_descipline++;
                }
                userNameSurname = findViewById(R.id.userNameSurname);
                userNameSurname.setText(group.groupList.get(Integer.parseInt(userNum)).name + " " +
                        group.groupList.get(Integer.parseInt(userNum)).surname);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        };
        getDatavalue.addValueEventListener(valueEventListener);
        setDesciplineRecycler(DesciplineList);
    }
}