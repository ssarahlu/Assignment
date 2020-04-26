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
    private int achievementId;

    private boolean achieved;

    private boolean redeemed;


    public AccountAchievement(String email, int achievementId, boolean achieved, boolean redeemed) {
        this.email = email;
        this.achievementId = achievementId;
        this.achieved = achieved;
        this.redeemed = redeemed;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getAchievementId() {
        return achievementId;
    }

    public void setAchievementId(int achievementId) {
        this.achievementId = achievementId;
    }

    public boolean isAchieved() {
        return achieved;
    }

    public void setAchieved(boolean achieved) {
        this.achieved = achieved;
    }

    public boolean isRedeemed() {
        return redeemed;
    }

    public void setRedeemed(boolean redeemed) {
        this.redeemed = redeemed;
    }
}
