package com.example.assignment;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class LeaderboardActivity extends AppCompatActivity {
    BottomNavigationView bottomNavigation;
    private Button beginner, intermediate, advanced;
    private ImageView image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leaderboard);
        setTitle("Leaderboard");
        beginner = findViewById(R.id.beginner);
        intermediate = findViewById(R.id.intermediate);
        advanced = findViewById(R.id.advanced);
        image = findViewById(R.id.image);


        beginner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                image.setImageResource(R.drawable.leaderboard_beginner);

            }
        });

        intermediate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                image.setImageResource(R.drawable.leaderboard_intermediate);

            }
        });

        advanced.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                image.setImageResource(R.drawable.leaderboard_advanced);

            }
        });


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
