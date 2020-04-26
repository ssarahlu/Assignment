package com.example.assignment;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.assignment.Entities.Account;
import com.example.assignment.Entities.QuizResult;

import java.util.List;

@Dao
public interface QuizResultDao {

    @Query("SELECT * FROM QuizResult")
    List<QuizResult> getQuizResults();

    @Query("SELECT * FROM QuizResult WHERE email == :email AND difficulty == :difficulty AND quizType == :quizType")
    QuizResult getQuizResult(String email, String difficulty, String quizType);

    @Query("INSERT OR IGNORE INTO QuizResult VALUES (:email, :difficulty, :result, :quizType)")
    void insertSingleResult(String email, String difficulty, int result, String quizType);

    @Query("SELECT result FROM QUIZRESULT WHERE email == :email AND difficulty == :difficulty AND quizType == :quizType")
    int getResult(String email, String difficulty, String quizType);

    @Insert
    void insertAll(QuizResult... quizResults);

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(QuizResult quizResults);

    @Query("DELETE FROM QuizResult")
    void delAll();

    //will update if the primary keys already exist in the db
    @Update
    public void updateQuizResult(QuizResult quizResults);

    @Delete
    public void delQuizResult(QuizResult quizResults);

}
