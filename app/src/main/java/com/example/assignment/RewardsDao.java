package com.example.assignment;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.assignment.Entities.Rewards;

import java.util.List;

@Dao
public interface RewardsDao {
    @Query("SELECT * FROM Rewards")
    List<Rewards> getRewards();

    @Query("SELECT * FROM Rewards WHERE id == :id")
    Rewards getReward(int id);

    //do i need to get the condition/name for each?

    @Insert
    void insertAll(Rewards... rewards);

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Rewards rewards);

    @Query("DELETE FROM Rewards")
    void delAll();

    @Update
    public void updateRewards(Rewards rewards);

    @Delete
    public void deleteRewards(Rewards rewards);

}
