package com.example.assignment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.assignment.Entities.Information;
import com.example.assignment.Entities.Question;

import java.util.ArrayList;

public class TopicQuestionActivity extends AppCompatActivity {
    private int topicId, stars;
    private String topic;
    private ArrayList<Question> questions = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_topic_question);
        Intent intent = getIntent();
        String id = intent.getStringExtra("id");
        topicId = Integer.valueOf(id);
        topic = intent.getStringExtra("topic");

        //stars / question.size() will display total stars
        //also code it in so it stores in the DB
        //code the correct/wrong function
        //on click next display the data - say please make selection if null
        //questions displayed: check answer
        //answer displayed: next

        for (Question q : Question.getQuestions()) {
            if (q.getTopicId() == topicId) {
                questions.add(q);
                System.out.println(q.getQuestion() + " " + q.getAnswer());
            }
        }
        setTitle(topic);

    }
}
