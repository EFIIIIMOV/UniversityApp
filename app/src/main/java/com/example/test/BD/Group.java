package com.example.test.BD;

import java.util.AbstractCollection;
import java.util.ArrayList;

public class Group {
    public String groupName;
    public ArrayList<User> groupList = new ArrayList<User>();

    public Group() {
    }

    public Group(String groupName) {
        this.groupName = groupName;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }


}
