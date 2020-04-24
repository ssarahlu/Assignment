package com.example.assignment;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.assignment.Entities.TopicResult;

import java.util.List;

@Dao
public interface TopicResultDao {
    @Query("SELECT * FROM TopicResult")
    List<TopicResult> getTopicResults();

    @Query("SELECT * FROM TopicResult WHERE email == :email AND topicId == :topicId")
    TopicResult getTopicResult(String email, int topicId);

    @Query("SELECT viewed FROM TopicResult WHERE email == :email AND topicId == :topicId")
    boolean getViewed(String email, int topicId);

    @Insert
    void insertAll(TopicResult... trs);

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void ins(TopicResult trs);

    @Query("INSERT OR IGNORE INTO TopicResult VALUES (:topicId, :email, :stars, :viewed)")
    void insert(int topicId, String email, int stars, boolean viewed);

    @Query("INSERT INTO TopicResult VALUES (:topicId, :email, :stars, :viewed)")
    void insert2(int topicId, String email, int stars, boolean viewed);


    @Query("DELETE FROM TopicResult")
    void delAll();

    @Update
    public void updateTopicResult(TopicResult topicResults);

    @Delete
    public void delTopicResult(TopicResult topicResults);
}
