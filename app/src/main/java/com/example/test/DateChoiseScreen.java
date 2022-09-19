package com.example.test;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.test.Adapter.DateAdapterForDateChoiseScreen;
import com.example.test.BD.Group;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class DateChoiseScreen extends AppCompatActivity {

    RecyclerView dateList;
    DateAdapterForDateChoiseScreen dateAdapterForDateChoiseScreen;
    FirebaseDatabase database;
    DatabaseReference getDataValue;
    String teacherNum;
    String numGroup;
    String groupKey;
    public ArrayList<String> date;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_date_choise_screen);
        teacherNum = getIntent().getStringExtra("teacherNum");
        numGroup = getIntent().getStringExtra("numGroup");
        groupKey = getIntent().getStringExtra("groupKey");
        findDate();
    }

    private void setDateRecycler(ArrayList<String> date, String groupKey, String teacherNum, String numGroup) {
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        dateList = findViewById(R.id.dateList);
        dateList.setLayoutManager(layoutManager);
        dateAdapterForDateChoiseScreen = new DateAdapterForDateChoiseScreen(this, date, groupKey, teacherNum, numGroup);
        dateList.setAdapter(dateAdapterForDateChoiseScreen);
    }

    public void findDate() {
        database = FirebaseDatabase.getInstance();
        getDataValue = FirebaseDatabase.getInstance().getReference("dataBase");
        ValueEventListener valueEventListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Group group = snapshot.child(groupKey).getValue(Group.class);
                setDateRecycler(group.groupList.get(Integer.parseInt(teacherNum)).userDesscipline.get(0).dateLesson.get(Integer.parseInt(numGroup)), groupKey, teacherNum, numGroup);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        };
        getDataValue.addValueEventListener(valueEventListener);
    }
}