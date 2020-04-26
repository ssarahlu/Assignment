package com.example.assignment;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class QuizActivity extends AppCompatActivity {
    BottomNavigationView bottomNavigation;
    Button start;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
        setTitle("Quiz");
        bottomNavigation = findViewById(R.id.navigation);
        start = findViewById(R.id.start);

        bottomNavigation.setItemIconTintList(null);
        bottomNavigation.setItemTextColor(null);

        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), SelectQuizDifficulty.class);
                startActivity(intent);
            }
        });

        bottomNavigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Intent intent;
                switch (item.getItemId()) {
                    case R.id.learning:
                        intent = new Intent(getApplicationContext(), SelectDifficulty.class);
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

    }
}
