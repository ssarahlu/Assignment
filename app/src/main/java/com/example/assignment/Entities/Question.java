package com.example.assignment.Entities;

import com.example.assignment.R;

import java.util.ArrayList;

public class Question {

    private int id;
    private String question;
    private String op1, op2, op3, op4, answer;
    private int photo;
    private int topicId;

    public Question() {
    }

    public Question(int id, String question, String op1, String op2, String op3, String op4, String answer, int photo, int topicId) {
        this.id = id;
        this.question = question;
        this.op1 = op1;
        this.op2 = op2;
        this.op3 = op3;
        this.op4 = op4;
        this.answer = answer;
        this.photo = photo;
        this.topicId = topicId;
    }

    public static ArrayList<Question> getQuestions() {
        ArrayList<Question> q = new ArrayList<>();
        q.add(new Question(1, "What is this note called?", "Semibreve", "Crochet", "Semibreathe", "Semi", "Semibreve", R.drawable.semibreve, 1));
        q.add(new Question(2, "How many beats are in a crochet?", "1", "2", "4", "3", "1", R.drawable.crochet, 1));
        q.add(new Question(3, "What is this note called?", "Menum", "Minam", "Minim", "Minimum", "Minim", R.drawable.minim, 1));
        q.add(new Question(4, "How many beats are in a minim?", "3", "1", "4", "2", "2", R.drawable.minim, 1));

        return q;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getOp1() {
        return op1;
    }

    public void setOp1(String op1) {
        this.op1 = op1;
    }

    public String getOp2() {
        return op2;
    }

    public void setOp2(String op2) {
        this.op2 = op2;
    }

    public String getOp3() {
        return op3;
    }

    public void setOp3(String op3) {
        this.op3 = op3;
    }

    public String getOp4() {
        return op4;
    }

    public void setOp4(String op4) {
        this.op4 = op4;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public int getPhoto() {
        return photo;
    }

    public void setPhoto(int photo) {
        this.photo = photo;
    }

    public int getTopicId() {
        return topicId;
    }

    public void setTopicId(int topicId) {
        this.topicId = topicId;
    }
}
