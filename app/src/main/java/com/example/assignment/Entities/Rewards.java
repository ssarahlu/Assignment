package com.example.assignment.Entities;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity
public class Rewards {
    @PrimaryKey
    @NonNull
    private String id;
    private String name;
    private int condition;

    @Ignore
    public Rewards() {
    }

    public Rewards(String id, String name, int condition) {
        this.id = id;
        this.name = name;
        this.condition = condition;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCondition() {
        return condition;
    }

    public void setCondition(int condition) {
        this.condition = condition;
    }
}

