package com.cbc_app_poc.rokomari.rokomarians.Model;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ModelNominated {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("nominatedUserId")
    @Expose
    private Integer nominatedUserId;
    @SerializedName("reason")
    @Expose
    private String reason;
    @SerializedName("userImage")
    @Expose
    private String userImage;
    @SerializedName("userFirstName")
    @Expose
    private String userFirstName;
    @SerializedName("nominationDate")
    @Expose
    private String nominationDate;
    @SerializedName("nominatedUserFirstname")
    @Expose
    private String nominatedUserFirstname;
    @SerializedName("nominatedUserImage")
    @Expose
    private String nominatedUserImage;
    @SerializedName("numberOfLikes")
    @Expose
    private Integer numberOfLikes;
    @SerializedName("liked")
    @Expose
    private Boolean liked;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getNominatedUserId() {
        return nominatedUserId;
    }

    public void setNominatedUserId(Integer nominatedUserId) {
        this.nominatedUserId = nominatedUserId;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getUserImage() {
        return userImage;
    }

    public void setUserImage(String userImage) {
        this.userImage = userImage;
    }

    public String getUserFirstName() {
        return userFirstName;
    }

    public void setUserFirstName(String userFirstName) {
        this.userFirstName = userFirstName;
    }

    public String getNominationDate() {
        return nominationDate;
    }

    public void setNominationDate(String nominationDate) {
        this.nominationDate = nominationDate;
    }

    public String getNominatedUserFirstname() {
        return nominatedUserFirstname;
    }

    public void setNominatedUserFirstname(String nominatedUserFirstname) {
        this.nominatedUserFirstname = nominatedUserFirstname;
    }

    public String getNominatedUserImage() {
        return nominatedUserImage;
    }

    public void setNominatedUserImage(String nominatedUserImage) {
        this.nominatedUserImage = nominatedUserImage;
    }

    public Integer getNumberOfLikes() {
        return numberOfLikes;
    }

    public void setNumberOfLikes(Integer numberOfLikes) {
        this.numberOfLikes = numberOfLikes;
    }

    public Boolean getLiked() {
        return liked;
    }

    public void setLiked(Boolean liked) {
        this.liked = liked;
    }


}
