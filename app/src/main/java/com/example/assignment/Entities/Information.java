package com.example.assignment.Entities;

import android.media.Image;

import com.example.assignment.R;

import java.util.ArrayList;

public class Information {
    private int id;
    private String information;
    private int picture;
    private int topicId;

    public Information(int id, String information, int picture, int topicId) {
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

    public int getPicture() {
        return picture;
    }

    public void setPicture(int picture) {
        this.picture = picture;
    }

    public int getTopicId() {
        return topicId;
    }

    public void setTopicId(int topicId) {
        this.topicId = topicId;
    }

    public static ArrayList<Information> getInfo() {
        ArrayList<Information> info = new ArrayList<>();
        info.add(new Information(1, "This is a crochet. Notice how crochets are filled in the middle and have a stem. Crochets are held for 1 beat.", R.drawable.crochet, 1));
        info.add(new Information(2, "This is a minim. Minims look very similar to crochets but are not filled in the middle. Minims are held for 2 beats. ", R.drawable.minim, 1));
        info.add(new Information(3, "This is a dotted minim. Dotted minims look very similar to minims but have a black dot on their right. Dotted minims are held for 3 beats. ", R.drawable.dotted_minim, 1));
        info.add(new Information(4, "This is a semibreve. Semibreves are held for 4 beats.", R.drawable.semibreve, 1));

        return info;
    }


}
