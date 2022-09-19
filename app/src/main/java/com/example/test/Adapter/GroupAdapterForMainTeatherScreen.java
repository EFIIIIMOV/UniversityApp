package com.example.test.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.test.BD.Descipline;
import com.example.test.R;
import com.example.test.TeacherMainScreen;

import java.util.ArrayList;
import java.util.List;

public class GroupAdapterForMainTeatherScreen extends RecyclerView.Adapter<GroupAdapterForMainTeatherScreen.GroupViewHolder> {
    public Context context;
    public ArrayList<String> groups;
    public ArrayList<String> groupsAddr;
    public String teacherDescipline;
    public String teacherNum;
    public String groupKey;

    public GroupAdapterForMainTeatherScreen(Context context, ArrayList<String> groups, ArrayList<String> groupsAddr, String teacherDescipline, String teacherNum, String groupKey) {
        this.context = context;
        this.groups = groups;
        this.groupsAddr = groupsAddr;
        this.teacherDescipline = teacherDescipline;
        this.teacherNum = teacherNum;
        this.groupKey = groupKey;
    }

    public GroupAdapterForMainTeatherScreen(Context context, ArrayList<String> groups, ArrayList<String> groupsAddr, String teacherDescipline, String teacherNum) {
        this.context = context;
        this.groups = groups;
        this.groupsAddr = groupsAddr;
        this.teacherDescipline = teacherDescipline;
        this.teacherNum = teacherNum;
    }

    public GroupAdapterForMainTeatherScreen(Context context, ArrayList<String> groups, ArrayList<String> groupsAddr, String teacherDescipline) {
        this.context = context;
        this.groups = groups;
        this.groupsAddr = groupsAddr;
        this.teacherDescipline = teacherDescipline;
    }

    public GroupAdapterForMainTeatherScreen(Context context, ArrayList<String> groups, ArrayList<String> groupsAddr) {
        this.context = context;
        this.groups = groups;
        this.groupsAddr = groupsAddr;
    }

    public GroupAdapterForMainTeatherScreen(Context context, ArrayList<String> groups) {
        this.context = context;
        this.groups = groups;
    }

    public GroupAdapterForMainTeatherScreen() {
    }

    @NonNull
    @Override
    public GroupViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View groupItems = LayoutInflater.from(context).inflate(R.layout.descipline_list_item, parent, false);
        return new GroupViewHolder(groupItems);
    }

    @Override
    public void onBindViewHolder(@NonNull GroupViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.groupName.setText(groups.get(position));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent("com.example.test.TeacherStudentChoiseScreen");
                intent.putExtra("groupClick", Integer.toString(position));
                intent.putExtra("GroupsNameList", groups);
                intent.putExtra("GroupsAddrList", groupsAddr);
                intent.putExtra("teacherDescipline", teacherDescipline);
                intent.putExtra("teacherNum", teacherNum);
                intent.putExtra("groupKey", groupKey);
                System.out.println("+++" + teacherDescipline + "+++");
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return groups.size();
    }

    public static final class GroupViewHolder extends RecyclerView.ViewHolder { //с какими полями взаимодействуем
        TextView groupName;

        public GroupViewHolder(@NonNull View itemView) {
            super(itemView);
            groupName = itemView.findViewById(R.id.desciplineName);
        }
    }
}
