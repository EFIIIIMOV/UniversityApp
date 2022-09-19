package com.example.test.BD;

import java.util.ArrayList;
import java.io.Serializable;

public class Descipline {

    public String name_descipline;
    public ArrayList<ResWork> resWorks = new ArrayList<ResWork>();
    public ArrayList<String> visits = new ArrayList<>();
    public ArrayList<String> groupThisDescipline = new ArrayList<>();
    public ArrayList<ArrayList<String>> dateLesson = new ArrayList<>();
    boolean student;

    public Descipline(String name_descipline, ArrayList<String> visits, boolean student) {
        this.name_descipline = name_descipline;
        this.visits = visits;
        this.student = student;
    }

    public Descipline() {
    }

    public Descipline(String name_descipline, ArrayList<String> groupThisDescipline, ArrayList<ArrayList<String>> dateLesson) {
        this.name_descipline = name_descipline;
        this.groupThisDescipline = groupThisDescipline;
        this.dateLesson = dateLesson;
    }


    public Descipline(String name_descipline) {
        this.name_descipline = name_descipline;
    }

    public Descipline(int num_descipline, String name_descipline) {
        //this.num_descipline = num_descipline;
        this.name_descipline = name_descipline;
    }

    public ArrayList<ResWork> getResWorks() {
        return resWorks;
    }

    public String getName_descipline() {
        return name_descipline;
    }

    public void setName_descipline(String name_descipline) {
        this.name_descipline = name_descipline;
    }
}
