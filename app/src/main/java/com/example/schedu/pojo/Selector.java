package com.example.schedu.pojo;

import com.google.gson.annotations.SerializedName;

public class Selector {

    @SerializedName("group")
    private String group;
    @SerializedName("subgroup")
    private int subgroup;
    @SerializedName("teacher")
    private String teacher;

    public Selector(String group, int subgroup, String teacher) {
        this.group = group;
        this.subgroup = subgroup;
        this.teacher = teacher;
    }

    public Selector(String group, int subgroup) {
        this.group = group;
        this.subgroup = subgroup;
    }

    public String getGroup() {
        return group;
    }

    public int getSubgroup() {
        return subgroup;
    }

    public String getTeacher() {
        return teacher;
    }

    @Override
    public String toString() {
        return "Selector{" +
                "group='" + group + '\'' +
                ", subgroup=" + subgroup +
                ", teacher='" + teacher + '\'' +
                '}';
    }
}
