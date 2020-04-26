package com.example.assignment;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class SelectLearningActivity extends AppCompatActivity {

    private Button musicTheory, musicTrivia;
    public static final String EXTRA_MESSAGE = "difficulty";
    private String email, difficulty;
    BottomNavigationView bottomNavigation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_learning);
        setTitle("Select Quiz Topic");

        musicTheory = findViewById(R.id.musicTheory);
        musicTrivia = findViewById(R.id.musicTrivia);

        Intent intent = getIntent();
        difficulty = intent.getStringExtra(EXTRA_MESSAGE).toLowerCase();

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

        musicTheory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), TheoryQuizActivity.class);
                intent.putExtra(EXTRA_MESSAGE, difficulty);
                intent.putExtra("email", email);
                startActivity(intent);
                Toast.makeText(getApplicationContext(), "Music theory selected", Toast.LENGTH_SHORT).show();

            }
        });

        musicTrivia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), StartTriviaActivity.class);
                intent.putExtra(EXTRA_MESSAGE, difficulty);
                intent.putExtra("email", email);
                startActivity(intent);
                Toast.makeText(getApplicationContext(), "Music trivia selected", Toast.LENGTH_SHORT).show();

            }
        });


    }
}
