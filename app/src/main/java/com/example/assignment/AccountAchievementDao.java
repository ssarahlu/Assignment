package com.example.assignment;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.assignment.Entities.AccountAchievement;

import java.util.List;

@Dao
public interface AccountAchievementDao {

    @Query("SELECT * FROM AccountAchievement")
    List<AccountAchievement> getAccAchs();

    @Query("SELECT * FROM AccountAchievement WHERE email == :email")
    List<AccountAchievement> getAccAch(String email);

    @Query("SELECT * FROM AccountAchievement WHERE email == :email AND achievementId == :achievementId")
    AccountAchievement getAch(String email, int achievementId);

    @Query("INSERT OR IGNORE INTO AccountAchievement VALUES (:email, :achievementId, :achieved, :redeemed)")
    void insertSingle(String email, int achievementId, boolean achieved, boolean redeemed);

    @Query("UPDATE AccountAchievement SET achieved = :achieved WHERE email == :email AND achievementid == :achievementId ")
    void updateAchieved(boolean achieved, String email, int achievementId);

    @Query("UPDATE AccountAchievement SET redeemed = :redeemed WHERE email == :email AND achievementid == :achievementId ")
    void updateRedeemed(boolean redeemed, String email, int achievementId);

    @Query("SELECT achieved FROM AccountAchievement WHERE email == :email AND achievementId == :achievementId")
    boolean getAchieved(String email, int achievementId);

    @Query("SELECT redeemed FROM AccountAchievement WHERE email == :email AND achievementId == :achievementId")
    boolean getRedeemed(String email, int achievementId);

    @Insert
    void insertAll(AccountAchievement... accountAchievements);

    @Insert
    void insert(AccountAchievement accountAchievements);

    @Query("DELETE FROM AccountAchievement")
    void delAll();

    @Update
    public void updateAccAch(AccountAchievement accountAchievement);

    @Delete
    public void deleteAccAch(AccountAchievement accountAchievement);

}
