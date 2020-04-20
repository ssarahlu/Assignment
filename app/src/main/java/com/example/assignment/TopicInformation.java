package com.example.assignment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.assignment.Entities.Information;
import com.example.assignment.Entities.Topic;

public class TopicInformation extends AppCompatActivity {

    private Topic topic;
    private Information information;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_topic_information);
        Intent intent = getIntent();
        String topic = intent.getStringExtra("Topic");

        setTitle(topic);
    }
}
