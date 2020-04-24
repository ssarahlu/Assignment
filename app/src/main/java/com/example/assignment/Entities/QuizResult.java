package com.example.assignment.Entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity (primaryKeys = {"email", "difficulty"})
public class QuizResult {

    private String email;
    private String difficulty;
    private int result;

}
