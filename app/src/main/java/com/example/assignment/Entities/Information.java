package com.example.assignment.Entities;

import android.media.Image;
import android.widget.ImageView;

public class Information {
    private int id;
    private String information;
    private ImageView picture;
    private int topicId;

    public Information(int id, String information, ImageView picture, int topicId) {
        this.id = id;
        this.information = information;
        this.picture = picture;
        this.topicId = topicId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getInformation() {
        return information;
    }

    public void setInformation(String information) {
        this.information = information;
    }

    public ImageView getPicture() {
        return picture;
    }

    public void setPicture(ImageView picture) {
        this.picture = picture;
    }

    public int getTopicId() {
        return topicId;
    }

    public void setTopicId(int topicId) {
        this.topicId = topicId;
    }
}
