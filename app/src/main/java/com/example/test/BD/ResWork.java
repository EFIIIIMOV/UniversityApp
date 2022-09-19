package com.example.test.BD;

import java.io.Serializable;

public class ResWork implements Serializable {

    public String workType, point, grade;

    public ResWork() {
    }

    public ResWork(String workType, String point, String grade) {
        this.workType = workType;
        this.point = point;
        this.grade = grade;
    }

    public ResWork(int num_workType, String workType, String point, String grade) {
        //this.num_workType = num_workType;
        this.workType = workType;
        this.point = point;
        this.grade = grade;
    }

    public String getWorkType() {
        return workType;
    }

    public void setWorkType(String workType) {
        this.workType = workType;
    }

    public String getPoint() {
        return point;
    }

    public void setPoint() {
        this.point = point;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade() {
        this.grade = grade;
    }
}
