package com.example.assignment;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;

import com.example.assignment.Entities.Topic;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

//this displays a list of all topics
public class TopicsActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private static final String TAG = "Topic";
    public static final String EXTRA_MESSAGE = "topic_id";
    private ArrayList<Topic> mTopics = new ArrayList<>();
    private String upperString;
    private String difficulty = "";
    private String email;
    BottomNavigationView bottomNavigation;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_topics);
        mRecyclerView = findViewById(R.id.rvList);
        mRecyclerView.setHasFixedSize(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Intent intent = getIntent();
        email = intent.getStringExtra("email");

        GoogleSignInAccount acct = GoogleSignIn.getLastSignedInAccount(getApplicationContext());
        if (acct != null) {
            email = acct.getEmail();
        }

        bottomNavigation = findViewById(R.id.navigation);
        bottomNavigation.setItemIconTintList(null);
        bottomNavigation.setItemTextColor(null);
        bottomNavigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Intent intent;
                switch (item.getItemId()) {
                    case R.id.learning:
                        intent = new Intent(getApplicationContext(), SelectDifficulty.class);
                        startActivity(intent);
                        return true;
                    case R.id.quiz:
                        intent = new Intent(getApplicationContext(), QuizActivity.class);
                        startActivity(intent);
                        return true;
                    case R.id.reward:
                        intent = new Intent(getApplicationContext(), RewardActivity.class);
                        startActivity(intent);
                        return true;
                    case R.id.leaderboard:
                        intent = new Intent(getApplicationContext(), LeaderboardActivity.class);
                        startActivity(intent);
                        return true;
                    case R.id.profile:
                        intent = new Intent(getApplicationContext(), ProfileActivity.class);
                        startActivity(intent);
                        return true;
                }
                return false;
            }
        });

        if (intent.getStringExtra(SelectDifficulty.EXTRA_MESSAGE) != null) {
            difficulty = intent.getStringExtra(SelectDifficulty.EXTRA_MESSAGE);
            upperString = difficulty.substring(0, 1).toUpperCase() + difficulty.substring(1).toLowerCase();
            Log.d(TAG, "onCreate SELECT DIFFICULTY : " + difficulty + " " + upperString);
            setTitle(upperString + " Courses");

        } else if (intent.getStringExtra(TopicInformation.EXTRA_MESSAGE) != null) {
            difficulty = intent.getStringExtra(TopicInformation.EXTRA_MESSAGE).toLowerCase();
            upperString = difficulty.substring(0, 1).toUpperCase() + difficulty.substring(1).toLowerCase();
            Log.d(TAG, "onCreate TOPIC INFORMATION  : " + difficulty + " " + upperString);
            setTitle(upperString + " Courses");

        } else if (intent.getStringExtra(TopicQuestionActivity.EXTRA_MESSAGE) != null) {
            difficulty = intent.getStringExtra(TopicQuestionActivity.EXTRA_MESSAGE).toLowerCase();
            upperString = difficulty.substring(0, 1).toUpperCase() + difficulty.substring(1).toLowerCase();
            Log.d(TAG, "onCreate TOPIC INFORMATION  : " + difficulty + " " + upperString);
            setTitle(upperString + " Courses");

        } else {
            difficulty = "Not courses found. Please reload the app.";
            setTitle(difficulty);
        }

        for (Topic t : Topic.getTopics()) {
            if (t.getDifficulty().equals(difficulty)) {
                mTopics.add(t);
                System.out.println("added");
            }
        }

        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        MyAdapter.RecyclerViewClickListener listener = new MyAdapter.RecyclerViewClickListener() {
            public void onClick(View view, int position) {
                launchTopics(position);
                Topic topic = mTopics.get(position);
            }
        };
        mAdapter = new MyAdapter(mTopics, listener);
        mRecyclerView.setAdapter(mAdapter);
    }

    private void launchTopics(int position) {
        Topic topic = mTopics.get(position);
        Intent intent = new Intent(this, TopicInformation.class);
        intent.putExtra(EXTRA_MESSAGE, position);
        intent.putExtra("Topic", topic.getTopic());
        intent.putExtra("id", String.valueOf(topic.getId()));
        intent.putExtra("upperString", upperString);
        intent.putExtra("email", email);
        startActivity(intent);

    }

    //added back button in the toolbar
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent myIntent = new Intent(getApplicationContext(), SelectDifficulty.class);
        startActivityForResult(myIntent, 0);
        return true;
    }
}
