package com.example.schedu.pojo;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Filters {

    @SerializedName("teachers")
    private List<String> teachers;
    @SerializedName("groups")
    private List<Group> groups;
    @SerializedName("current_week")
    private int currentWeek;

    public Filters(List<String> teachers, List<Group> groups, int currentWeek) {
        this.teachers = teachers;
        this.groups = groups;
        this.currentWeek = currentWeek;
    }

    public List<String> getTeachers() {
        return teachers;
    }

    public List<Group> getGroups() {
        return groups;
    }

    public int getCurrentWeek() {
        return currentWeek;
    }

    @Override
    public String toString() {
        return "Filters{" +
                "teachers=" + teachers +
                ", groups=" + groups +
                ", currentWeek=" + currentWeek +
                '}';
    }
}
