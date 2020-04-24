package com.example.assignment;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.assignment.Entities.QuizResult;
import com.example.assignment.Entities.TopicResult;

import java.util.List;

@Dao
public interface TopicResultDao {
    @Query("SELECT * FROM TopicResult")
    List<TopicResult> getTopicResults();

    @Query("SELECT * FROM TopicResult WHERE email == :email AND topicId == :topicId")
    TopicResult getTopicResult(String email, int topicId);

    @Insert
    void insertAll(TopicResult... rewards);

    @Query("DELETE FROM TopicResult")
    void delAll();

    @Update
    public void updateTopicResult(TopicResult topicResults);

    @Delete
    public void delTopicResult(TopicResult topicResults);
}
