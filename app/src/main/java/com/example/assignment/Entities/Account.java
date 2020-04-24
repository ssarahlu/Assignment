package com.example.assignment.Entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Account {
    @PrimaryKey
    private String email;
    private String fName;
    private String lName;

}
