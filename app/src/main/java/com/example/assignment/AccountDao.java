package com.example.assignment;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.assignment.Entities.Account;
import com.example.assignment.Entities.AccountAchievement;

import java.util.List;

@Dao
public interface AccountDao {

    @Query("SELECT * FROM Account")
    List<Account> getAccs();

    @Query("SELECT * FROM Account WHERE email == :email")
    Account getAcc(String email);

    @Query("SELECT fName FROM Account WHERE email == :email")
    List<Account> getFname(String email);

    @Query("SELECT lName FROM Account WHERE email == :email")
    List<Account> lName(String email);

    @Insert
    void insertAll(Account... accounts);

    @Query("DELETE FROM Account")
    void delAll();

    @Update
    public void updateAccount(Account account);

    @Delete
    public void deleteAccount(Account account);

}
