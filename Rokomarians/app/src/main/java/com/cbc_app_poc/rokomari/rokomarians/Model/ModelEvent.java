package com.cbc_app_poc.rokomari.rokomarians.Model;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ModelEvent {


    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("event")
    @Expose
    private String event;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }

}
