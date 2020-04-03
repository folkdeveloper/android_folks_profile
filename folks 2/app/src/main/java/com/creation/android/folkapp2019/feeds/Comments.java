package com.creation.android.folkapp2019.feeds;

import java.sql.Timestamp;

public class Comments {

    private String commentator;
    private String comment;
    //private Timestamp commentTimeStamp;

    public Comments() {
    }

    public Comments(String commentorImageUrl, String commentText, Timestamp commentTimeStamp) {
        this.commentator = commentorImageUrl;
        this.comment = commentText;
        //this.commentTimeStamp = commentTimeStamp;
    }

    public String getCommentator() {
        return commentator;
    }

    public void setCommentator(String commentator) {
        this.commentator = commentator;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

   /* public Timestamp getCommentTimeStamp() {
        return commentTimeStamp;
    }

    public void setCommentTimeStamp(Timestamp commentTimeStamp) {
        this.commentTimeStamp = commentTimeStamp;
    }*/
}
