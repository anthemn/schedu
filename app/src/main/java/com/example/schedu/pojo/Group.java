package com.example.schedu.pojo;

import com.google.gson.annotations.SerializedName;

public class Group {
    @SerializedName("group")
    private String group;
    @SerializedName("subgroup")
    private String subgroup;

    public Group(String group, String subgroup) {
        this.group = group;
        this.subgroup = subgroup;
    }

    public String getGroup() {
        return group;
    }

    public String getSubgroup() {
        return subgroup;
    }

    @Override
    public String toString() {
        return "Group{" +
                "group='" + group + '\'' +
                ", subgroup='" + subgroup + '\'' +
                '}';
    }
}
