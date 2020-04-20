package com.example.assignment.Entities;

import java.util.ArrayList;

public class Topic {
    private int id;
    private String difficulty;
    private String topic;

    public Topic() {

    }

    public Topic(int id, String difficulty, String topic) {
        this.id = id;
        this.difficulty = difficulty;
        this.topic = topic;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }


    public static ArrayList<Topic> getTopics() {
        ArrayList<Topic> topics = new ArrayList<>();
        topics.add(new Topic(1, "easy", "Rhythm"));
        topics.add(new Topic(2, "easy", "Time signatures"));
        topics.add(new Topic(3, "easy", "Treble clef"));
        topics.add(new Topic(4, "easy", "Bass clef"));
        topics.add(new Topic(5, "easy", "Dynamics"));

        return topics;
    }

}
