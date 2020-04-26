package com.example.assignment.Entities;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.Ignore;

@Entity(primaryKeys = {"email", "difficulty", "quizType"})
public class QuizResult {
    @NonNull
    private String email;
    @NonNull
    private String difficulty;

    private int result;

    @NonNull
    private String quizType;

    @Ignore
    public QuizResult() {
    }

    public QuizResult(String email, String difficulty, int result, String quizType) {
        this.email = email;
        this.difficulty = difficulty;
        this.result = result;
        this.quizType = quizType;
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

    public String getQuizType() {
        return quizType;
    }

    public void setQuizType(String quizType) {
        this.quizType = quizType;
    }
}
