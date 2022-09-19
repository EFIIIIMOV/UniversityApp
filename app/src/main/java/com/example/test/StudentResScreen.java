package com.example.test;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.TextView;

import com.example.test.Adapter.ResAdapterForStudentResScreen;
import com.example.test.BD.ResWork;

import java.util.ArrayList;
import java.util.List;

public class StudentResScreen extends AppCompatActivity {

    RecyclerView resList;
    ResAdapterForStudentResScreen resAdapterForStudentResScreen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_res_screen);

        TextView studentResScrDisName = findViewById(R.id.desciplineNameResScreen);

        studentResScrDisName.setText(getIntent().getStringExtra("DesciplineNameClick"));

        List<ResWork> ResList = new ArrayList<>();
        ResList = (List<ResWork>) getIntent().getSerializableExtra("resWorkList");
        setResRecycler(ResList);

    }

    private void setResRecycler(List<ResWork> resWorks) {
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);

        resList = findViewById(R.id.resList);
        resList.setLayoutManager(layoutManager);

        resAdapterForStudentResScreen = new ResAdapterForStudentResScreen(this, resWorks);
        resList.setAdapter(resAdapterForStudentResScreen);
    }
}