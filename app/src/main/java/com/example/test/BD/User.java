package com.example.test.BD;

import java.util.ArrayList;

public class User {
    public String email, password, name, surname, patronymic, id, accessRights, groups;
    public ArrayList<Descipline> userDesscipline = new ArrayList<Descipline>();
    public ArrayList<String> groupsAvalibleUser = new ArrayList<>();

    public User() {
    }

    public User(String email, String password, String name, String surname, String patronymic, String accessRights, String groups) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.surname = surname;
        this.patronymic = patronymic;
        this.accessRights = accessRights;
        this.groups = groups;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAccessRights() {
        return accessRights;
    }

    public void setAccessRights(String accessRights) {
        this.accessRights = accessRights;
    }

    public String getGroups() {
        return groups;
    }

    public void setGroups(String groups) {
        this.groups = groups;
    }

}