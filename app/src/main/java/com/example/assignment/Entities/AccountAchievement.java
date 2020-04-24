package com.example.assignment.Entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(primaryKeys = {"email", "achievementId"})
public class AccountAchievement {
    private String email;
    private String achievementId;

    private boolean achieved;


}
