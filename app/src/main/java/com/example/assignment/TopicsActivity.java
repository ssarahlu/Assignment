package com.example.assignment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.assignment.Entities.Topic;

import java.util.ArrayList;

public class TopicsActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private static final String TAG = "Topic";
    public static final String EXTRA_MESSAGE = "topic_id";
    private ArrayList<Topic> mTopics = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_topics);
        mRecyclerView = findViewById(R.id.rvList);
        mRecyclerView.setHasFixedSize(true);
        Intent intent = getIntent();
        String difficulty = intent.getStringExtra(SelectDifficulty.EXTRA_MESSAGE);
        String upperString = difficulty.substring(0, 1).toUpperCase() + difficulty.substring(1).toLowerCase();
        setTitle(upperString + " Courses");

        for (Topic t : Topic.getTopics()) {
            if (t.getDifficulty().equals(difficulty)) {
                mTopics.add(t);
            }
        }

        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        MyAdapter.RecyclerViewClickListener listener = new MyAdapter.RecyclerViewClickListener() {
            public void onClick(View view, int position) {
                launchTopics(position);
            }
        };
        mAdapter = new MyAdapter(mTopics, listener);
        mRecyclerView.setAdapter(mAdapter);
    }


    private void launchTopics(int position) {
        Intent intent = new Intent(this, TopicInformation.class);
        intent.putExtra(EXTRA_MESSAGE, position);
        Topic topic = mTopics.get(position);
        intent.putExtra("Topic", topic.getTopic());
        intent.putExtra("id", String.valueOf(topic.getId()));
        startActivity(intent);

    }
}
