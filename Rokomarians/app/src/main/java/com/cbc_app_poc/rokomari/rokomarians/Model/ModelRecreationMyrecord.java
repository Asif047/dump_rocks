package com.cbc_app_poc.rokomari.rokomarians.Model;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ModelRecreationMyrecord {


    @SerializedName("details")
    @Expose
    private String details;
    @SerializedName("eventCategories")
    @Expose
    private Object eventCategories;
    @SerializedName("selectedCategories")
    @Expose
    private String selectedCategories;

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public Object getEventCategories() {
        return eventCategories;
    }

    public void setEventCategories(Object eventCategories) {
        this.eventCategories = eventCategories;
    }

    public String getSelectedCategories() {
        return selectedCategories;
    }

    public void setSelectedCategories(String selectedCategories) {
        this.selectedCategories = selectedCategories;
    }


}
