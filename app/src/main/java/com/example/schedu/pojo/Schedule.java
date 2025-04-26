package com.example.schedu.pojo;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Schedule {
    @SerializedName("schedule")
    List<StudyClass> classesList;

    public Schedule(List<StudyClass> classesList) {
        this.classesList = classesList;
    }

    public List<StudyClass> getClassesList() {
        return classesList;
    }

    @Override
    public String toString() {
        return "Schedule{" +
                "classesList=" + classesList +
                '}';
    }
}


