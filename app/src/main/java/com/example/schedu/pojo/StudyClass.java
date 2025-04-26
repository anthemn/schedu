package com.example.schedu.pojo;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class StudyClass {
    @SerializedName("type")
    private String type;
    @SerializedName("day")
    private int day;
    @SerializedName("order")
    private int order;
    @SerializedName("week")
    private int week;
    @SerializedName("title")
    private String title;
    @SerializedName("teacher")
    private List<String> teacher;
    @SerializedName("location")
    private List<String> location;
    @SerializedName("begins")
    private String begins;
    @SerializedName("ends")
    private String ends;

    public StudyClass(String type, int day, int order, int week, String title, List<String> teacher, List<String> location, String begins, String ends) {
        this.type = type;
        this.day = day;
        this.order = order;
        this.week = week;
        this.title = title;
        this.teacher = teacher;
        this.location = location;
        this.begins = begins;
        this.ends = ends;
    }

    public String getType() {
        return type;
    }

    public int getDay() {
        return day;
    }

    public int getOrder() {
        return order;
    }

    public int getWeek() {
        return week;
    }

    public String getTitle() {
        return title;
    }

    public List<String> getTeacher() {
        return teacher;
    }

    public List<String> getLocation() {
        return location;
    }

    public String getBegins() {
        return begins;
    }

    public String getEnds() {
        return ends;
    }

    @Override
    public String toString() {
        return "StudyClass{" +
                "type='" + type + '\'' +
                ", day=" + day +
                ", order=" + order +
                ", week=" + week +
                ", title='" + title + '\'' +
                ", teacher=" + teacher +
                ", location=" + location +
                ", begins='" + begins + '\'' +
                ", ends='" + ends + '\'' +
                '}';
    }
}
