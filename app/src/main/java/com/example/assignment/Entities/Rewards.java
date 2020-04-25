package com.example.assignment.Entities;

import java.util.ArrayList;

import com.example.assignment.R;

public class Rewards {

    private int id;
    private String name;
    private int photo;
    private int condition;

    public Rewards() {
    }

    public Rewards(int id, String name, int photo, int condition) {
        this.id = id;
        this.name = name;
        this.photo = photo;
        this.condition = condition;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    public int getPhoto() {
        return photo;
    }

    public void setPhoto(int photo) {
        this.photo = photo;
    }

    public static ArrayList<Rewards> getRewards() {
        ArrayList<Rewards> rewards = new ArrayList<>();
        rewards.add(new Rewards(1, "$5 It's Time for Thai Voucher", R.drawable.its_time_for_thai_qr, 10));
        rewards.add(new Rewards(2, "$10 UNSW Bookshop Voucher", R.drawable.bookshop_qr, 20));
        rewards.add(new Rewards(3, "$20 Coles Voucher", R.drawable.coles_qr, 40));
        rewards.add(new Rewards(4, "$20 Woolworths Voucher", R.drawable.woolworths_qr, 40));
        rewards.add(new Rewards(5, "Digital Metronome", R.drawable.metronome_qr, 50));
        return rewards;
    }

}

