package com.example.assignment;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.assignment.Entities.Account;
import com.example.assignment.Entities.QuizResult;

import java.util.List;

@Dao
public interface QuizResultDao {

    @Query("SELECT * FROM QuizResult")
    List<QuizResult> getQuizResults();

    @Query("SELECT * FROM QuizResult WHERE email == :email AND difficulty == :difficulty")
    QuizResult getQuizResult(String email, String difficulty);

    //do i need to get the individual difficulty/result for each ?

    @Insert
    void insertAll(QuizResult... quizResults);

    @Query("DELETE FROM QuizResult")
    void delAll();

    @Update
    public void updateQuizResult(QuizResult quizResults);

    @Delete
    public void delQuizResult(QuizResult quizResults);

}
