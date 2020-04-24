package com.example.assignment.Entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(primaryKeys = {"email", "topicId"})
public class TopicResult {

    private int topicId;
    private String email;
    private int stars;


}
