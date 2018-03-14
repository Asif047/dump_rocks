package com.cbc_app_poc.rokomari.rokomarians.ImageUpload;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class FileInfo {

    @SerializedName("image-title")
    @Expose
    private String imageTitle;

    public String getImageTitle() {
        return imageTitle;
    }

    public void setImageTitle(String imageTitle) {
        this.imageTitle = imageTitle;
    }

}