package com.example.assignment;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.assignment.Entities.Account;
import com.example.assignment.Entities.AccountAchievement;
import com.example.assignment.Entities.QuizResult;
import com.example.assignment.Entities.TopicResult;

@Database(entities = {Account.class, AccountAchievement.class, QuizResult.class, TopicResult.class}, version = 1, exportSchema = false)
public abstract class MyDatabase extends RoomDatabase {

    public abstract AccountAchievementDao accountAchievementDao();

    public abstract QuizResultDao quizResultDao();

    public abstract TopicResultDao topicResultDao();

    public abstract AccountDao accountDao();


}
