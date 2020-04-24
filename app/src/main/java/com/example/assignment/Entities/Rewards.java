package com.example.assignment.Entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Rewards {
    @PrimaryKey
    private String id;
    private String name;
    private int condition;



}

