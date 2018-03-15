package com.cbc_app_poc.rokomari.rokomarians.Model;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ModelCommentsHappyPost {

    @SerializedName("commentId")
    @Expose
    private Integer commentId;
    @SerializedName("postId")
    @Expose
    private Integer postId;
    @SerializedName("comment")
    @Expose
    private String comment;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("imageUrl")
    @Expose
    private String imageUrl;

    public Integer getCommentId() {
        return commentId;
    }

    public void setCommentId(Integer commentId) {
        this.commentId = commentId;
    }

    public Integer getPostId() {
        return postId;
    }

    public void setPostId(Integer postId) {
        this.postId = postId;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

}
