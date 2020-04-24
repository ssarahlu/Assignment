package com.example.assignment.Entities;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity
public class Account {
    @PrimaryKey
    @NonNull
    private String email;
    private String fName;
    private String lName;

    @Ignore
    public Account() {
    }

    public Account(String email, String fName, String lName) {
        this.email = email;
        this.fName = fName;
        this.lName = lName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFName() {
        return fName;
    }

    public void setFName(String fName) {
        this.fName = fName;
    }

    public String getLName() {
        return lName;
    }

    public void setLName(String lName) {
        this.lName = lName;
    }
}
