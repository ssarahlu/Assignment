package com.example.assignment.Entities;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity (primaryKeys = {"email", "difficulty"})
public class QuizResult {
    @NonNull
    private String email;
    @NonNull
    private String difficulty;
    private int result;

    @Ignore
    public QuizResult() {
    }

    public QuizResult(String email, String difficulty, int result) {
        this.email = email;
        this.difficulty = difficulty;
        this.result = result;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }
}
