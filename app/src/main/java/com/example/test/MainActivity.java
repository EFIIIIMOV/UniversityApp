package com.example.test;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.test.BD.Descipline;
import com.example.test.BD.Group;
import com.example.test.BD.ResWork;
import com.example.test.BD.User;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private String userEmailAddrSTR, userPasswordSTR;
    private EditText userEmailAddr, userPassword;
    FirebaseDatabase database;
    DatabaseReference getDatavalue;
    DatabaseReference myRef;
    int now_user = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void createDataBaseAccTeacher() {
        myRef = database.getReference("dataBase");

        ArrayList<ArrayList<String>> dateLessonAll = new ArrayList<>();
        ArrayList<String> dateLessonGroup_1 = new ArrayList<>();
        ArrayList<String> dateLessonGroup_2 = new ArrayList<>();
        ArrayList<String> dateLessonGroup_3 = new ArrayList<>();

        dateLessonGroup_1.add("01.01.2022");
        dateLessonGroup_1.add("02.01.2022");
        dateLessonGroup_1.add("03.01.2022");

        dateLessonGroup_2.add("10.01.2022");
        dateLessonGroup_2.add("20.01.2022");
        dateLessonGroup_2.add("30.01.2022");

        dateLessonGroup_3.add("11.01.2022");
        dateLessonGroup_3.add("22.01.2022");
        dateLessonGroup_3.add("31.01.2022");

        dateLessonAll.add(dateLessonGroup_1);
        dateLessonAll.add(dateLessonGroup_2);
        dateLessonAll.add(dateLessonGroup_3);

        ArrayList<String> groupsStudent = new ArrayList<>();

        groupsStudent.add("-N3iNjA2AdCOLD1TYPqq");
        groupsStudent.add("-N3iPA_lGJ_OtrWOoOwp");
        groupsStudent.add("-N3iPcn60PuiCPdcoPbp");

        Descipline descipline = new Descipline("МПС", groupsStudent, dateLessonAll);


        User user = new User("1", "1", "Преподаватель", " ", " ", "1", "TEACHER");

        user.userDesscipline.add(descipline);

        Group groups = new Group("TEACHER");

        groups.groupList.add(user);

        myRef.push().setValue(groups);
    }

    public void createDataBaseAccStudent() {

        ArrayList<String> visits = new ArrayList<>();
        visits.add(" ");
        visits.add(" ");
        visits.add(" ");
        myRef = database.getReference("dataBase");

        ResWork resWork = new ResWork("КР №1", " ", " ");
        ResWork resWork2 = new ResWork("Практика №1", " ", " ");
        ResWork resWork3 = new ResWork("Лаб. Работа №1", " ", " ");

        Descipline descipline = new Descipline("АиКМС", visits, true);
        Descipline descipline2 = new Descipline("МПС", visits, true);
        Descipline descipline3 = new Descipline("РМП", visits, true);

        descipline.resWorks.add(resWork);
        descipline.resWorks.add(resWork2);
        descipline.resWorks.add(resWork3);

        descipline2.resWorks.add(resWork);
        descipline2.resWorks.add(resWork2);
        descipline2.resWorks.add(resWork3);

        descipline3.resWorks.add(resWork);
        descipline3.resWorks.add(resWork2);
        descipline3.resWorks.add(resWork3);

        User user1 = new User("8", "8", "Даниил", "Долганов", "Викторович", "0", "ИКБО-09-20");
        User user2 = new User("9", "9", "Павел", "Плотников", "Дмитриевич", "0", "ИКБО-09-20");
        User user3 = new User("10", "10", "Журавлев", "Андрей", "Сергеевич", "0", "ИКБО-09-20");

        user1.userDesscipline.add(descipline);
        user1.userDesscipline.add(descipline2);
        user1.userDesscipline.add(descipline3);

        user2.userDesscipline.add(descipline);
        user2.userDesscipline.add(descipline2);
        user2.userDesscipline.add(descipline3);

        user3.userDesscipline.add(descipline);
        user3.userDesscipline.add(descipline2);
        user3.userDesscipline.add(descipline3);

        Group groups = new Group("ИКБО-09-20");

        groups.groupList.add(user1);
        groups.groupList.add(user2);
        groups.groupList.add(user3);

        myRef.push().setValue(groups);
    }

    public void init() {
        EditText userEmailAddr = findViewById(R.id.editTextEmailAddress);
        EditText userPassword = findViewById(R.id.editTextTextPassword);
        userEmailAddrSTR = userEmailAddr.getText().toString();
        userPasswordSTR = userPassword.getText().toString();
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("user");
    }

    public void onClickLogButton(View view) {
        System.out.println("Нажал кнопку входа");
        init();
        //createDataBaseAccStudent();
        //createDataBaseAccTeacher();
        LogDataCheck(userEmailAddrSTR, userPasswordSTR);
        //Intent intent = new Intent("com.example.test.TeacherMainScreen");
        //startActivity(intent);
    }

    public void onClickInfoButton(View view) {
        System.out.println("Нажал кнопку информации");
        Intent intent = new Intent("com.example.test.LogInfoScreen");
        startActivity(intent);
    }

    public void LogDataCheck(String email, String password) {
        getDatavalue = FirebaseDatabase.getInstance().getReference("dataBase");
        Toast toast = Toast.makeText(MainActivity.this, "Неверные данные для входа", Toast.LENGTH_SHORT);
        ValueEventListener valueEventListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot ds : snapshot.getChildren()) {
                    Group group = ds.getValue(Group.class);
                    while (now_user < group.groupList.size()) {
                        if (group.groupList.get(now_user).email.equals(email) && group.groupList.get(now_user).password.equals(password) && group.groupList.get(now_user).accessRights.equals("0")) {
                            Intent intent = new Intent("com.example.test.StudentMainScreen");
                            intent.putExtra("groupKey", ds.getKey());
                            intent.putExtra("userNum", Integer.toString(now_user));
                            startActivity(intent);
                            return;
                        } else if (group.groupList.get(now_user).email.equals(email) && group.groupList.get(now_user).password.equals(password) && group.groupList.get(now_user).accessRights.equals("1")) {
                            Intent intent = new Intent("com.example.test.TeacherMainScreen");
                            intent.putExtra("groupKey", ds.getKey());
                            intent.putExtra("teacherNum", Integer.toString(now_user));
                            System.out.println("groupKey = " + ds.getKey());
                            startActivity(intent);
                            return;
                        }
                        now_user++;
                    }
                    now_user = 0;
                }
                toast.show();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        };
        getDatavalue.addValueEventListener(valueEventListener);
    }
}