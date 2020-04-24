package com.example.assignment;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.assignment.Entities.AccountAchievement;

import java.util.List;

@Dao
public interface AccountAchievementDao {

    @Query("SELECT * FROM AccountAchievement")
    List<AccountAchievement> getAccAchs();

    @Query("SELECT * FROM AccountAchievement WHERE email == :email")
    List<AccountAchievement>  getAccAch(String email);

    @Query("SELECT * FROM AccountAchievement WHERE email == :email AND achievementId == :achievementId")
    AccountAchievement getAch(String email, int achievementId);

    //i think i need more to be able to get the achievement ID and email?

    @Insert
    void insertAll(AccountAchievement... accountAchievements);

    @Query("DELETE FROM AccountAchievement")
    void delAll();

    @Update
    public void updateAccAch(AccountAchievement accountAchievement);

    @Delete
    public void deleteAccAch(AccountAchievement accountAchievement);

}
