package com.cbc_app_poc.rokomari.rokomarians.Model;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ModelHappySeeAll {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("details")
    @Expose
    private String details;
    @SerializedName("numberOfLikes")
    @Expose
    private Integer numberOfLikes;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("image")
    @Expose
    private String image;
    @SerializedName("dateTime")
    @Expose
    private String dateTime;
    @SerializedName("liked")
    @Expose
    private Boolean liked;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public Integer getNumberOfLikes() {
        return numberOfLikes;
    }

    public void setNumberOfLikes(Integer numberOfLikes) {
        this.numberOfLikes = numberOfLikes;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public Boolean getLiked() {
        return liked;
    }

    public void setLiked(Boolean liked) {
        this.liked = liked;
    }


}
