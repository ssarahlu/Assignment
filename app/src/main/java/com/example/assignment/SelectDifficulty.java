package com.example.assignment;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

//users can select difficulty from this screen
public class SelectDifficulty extends AppCompatActivity {

    private Button beginner, intermediate, advanced;
    private String email;
    public static final String EXTRA_MESSAGE = "difficulty";
    BottomNavigationView bottomNavigation;
    private static final String TAG = "SelectDifficulty";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.select_difficulty);

        Intent intent = getIntent();
        email = intent.getStringExtra("email");

        beginner = findViewById(R.id.beginner);
        intermediate = findViewById(R.id.intermediate);
        advanced = findViewById(R.id.advanced);
        setTitle("Select Course Difficulty");
        bottomNavigation = findViewById(R.id.navigation);
        bottomNavigation.setItemIconTintList(null);
        bottomNavigation.setItemTextColor(null);
        bottomNavigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Intent intent;
                switch (item.getItemId()) {
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


        beginner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), TopicsActivity.class);
                intent.putExtra(EXTRA_MESSAGE, "easy");
                intent.putExtra("email", email);
                startActivity(intent);
                Toast.makeText(getApplicationContext(), "Beginner selected", Toast.LENGTH_SHORT).show();

            }
        });

        intermediate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), TopicsActivity.class);
                intent.putExtra(EXTRA_MESSAGE, "intermediate");
                intent.putExtra("email", email);
                Toast.makeText(getApplicationContext(), "Intermediate selected", Toast.LENGTH_SHORT).show();
                Log.d(TAG, "onClick: starting");
                startActivity(intent);

            }
        });


        advanced.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), TopicsActivity.class);
                intent.putExtra(EXTRA_MESSAGE, "advanced");
                intent.putExtra("email", email);
                Toast.makeText(getApplicationContext(), "Advanced selected", Toast.LENGTH_SHORT).show();
                Log.d(TAG, "onClick: starting");
                startActivity(intent);

            }
        });

    }
}
