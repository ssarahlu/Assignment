package com.example.assignment.Entities;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(primaryKeys = {"email", "topicId"})
public class TopicResult {
    @NonNull
    private int topicId;
    @NonNull
    private String email;
    private int stars;
    private boolean viewed;

    @Ignore
    public TopicResult() {
    }

    public TopicResult(int topicId, String email, int stars, boolean viewed) {
        this.topicId = topicId;
        this.email = email;
        this.stars = stars;
        this.viewed = viewed;
    }

    public int getTopicId() {
        return topicId;
    }

    public void setTopicId(int topicId) {
        this.topicId = topicId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getStars() {
        return stars;
    }

    public void setStars(int stars) {
        this.stars = stars;
    }

    public boolean isViewed() {
        return viewed;
    }

    public void setViewed(boolean viewed) {
        this.viewed = viewed;
    }
}
