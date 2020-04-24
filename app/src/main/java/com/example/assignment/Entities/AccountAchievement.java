package com.example.assignment.Entities;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(primaryKeys = {"email", "achievementId"})
public class AccountAchievement {
    @NonNull
    private String email;
    @NonNull
    private String achievementId;

    private boolean achieved;

    @Ignore
    public AccountAchievement() {
    }

    public AccountAchievement(String email, String achievementId, boolean achieved) {
        this.email = email;
        this.achievementId = achievementId;
        this.achieved = achieved;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAchievementId() {
        return achievementId;
    }

    public void setAchievementId(String achievementId) {
        this.achievementId = achievementId;
    }

    public boolean isAchieved() {
        return achieved;
    }

    public void setAchieved(boolean achieved) {
        this.achieved = achieved;
    }
}
