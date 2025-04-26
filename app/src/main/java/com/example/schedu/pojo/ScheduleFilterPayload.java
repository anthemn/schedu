package com.example.schedu.pojo;

import com.google.gson.annotations.SerializedName;

public class ScheduleFilterPayload {
    @SerializedName("type")
    private String type;
    @SerializedName("selector")
    private Selector selector;

    public ScheduleFilterPayload(String type, Selector selector) {
        this.type = type;
        this.selector = selector;
    }

    public String getType() {
        return type;
    }

    public Selector getSelector() {
        return selector;
    }
}
