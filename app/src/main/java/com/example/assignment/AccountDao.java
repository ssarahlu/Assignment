package com.example.assignment;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.assignment.Entities.Account;

import java.util.List;

@Dao
public interface AccountDao {

    @Query("SELECT * FROM Account")
    List<Account> getAccs();

    @Query("SELECT * FROM Account WHERE email == :email")
    Account getAcc(String email);

    @Insert
    void insertAll(Account... accounts);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Account accounts);

    @Query("DELETE FROM Account")
    void delAll();

    @Update
    public void updateAccount(Account account);

    @Delete
    public void deleteAccount(Account account);

}
