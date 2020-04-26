package com.example.assignment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class SelectQuizDifficulty extends AppCompatActivity {

    private Button beginner, intermediate, advanced;
    private String email;
    public static final String EXTRA_MESSAGE = "difficulty";
//    BottomNavigationView bottomNavigation;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.select_difficulty);

        Intent intent = getIntent();
        email = intent.getStringExtra("email");

        beginner = findViewById(R.id.beginner);
        intermediate = findViewById(R.id.intermediate);
        advanced = findViewById(R.id.advanced);
//        bottomNavigation = findViewById(R.id.navigation);

        setTitle("Select Difficulty");

//        bottomNavigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
//            @Override
//            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
//                Intent intent;
//                switch (item.getItemId()) {
//                    case R.id.learning:
////                                Intent intent = new Intent(this, SelectDifficulty.class);
////                                startActivity(intent);
//                        return true;
//                    case R.id.quiz:
//                        intent = new Intent(getApplicationContext(), QuizActivity.class);
//                        startActivity(intent);
//                        return true;
//                    case R.id.reward:
//                        intent = new Intent(getApplicationContext(), RewardActivity.class);
//                        startActivity(intent);
//                        return true;
//                    case R.id.leaderboard:
//                        intent = new Intent(getApplicationContext(), LeaderboardActivity.class);
//                        startActivity(intent);
//                        return true;
//                    case R.id.profile:
//                        intent = new Intent(getApplicationContext(), ProfileActivity.class);
//                        startActivity(intent);
//                        return true;
//                }
//                return false;
//            }
//        });


        beginner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), SelectLearningActivity.class);
                intent.putExtra(EXTRA_MESSAGE, "easy");
                intent.putExtra("email", email);
                startActivity(intent);
                Toast.makeText(getApplicationContext(), "Beginner selected", Toast.LENGTH_SHORT).show();

            }
        });

        intermediate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), SelectLearningActivity.class);
                intent.putExtra(EXTRA_MESSAGE, "medium");
                intent.putExtra("email", email);
                Toast.makeText(getApplicationContext(), "Intermediate selected", Toast.LENGTH_SHORT).show();

            }
        });


        advanced.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), SelectLearningActivity.class);
                intent.putExtra(EXTRA_MESSAGE, "hard");
                intent.putExtra("email", email);
                Toast.makeText(getApplicationContext(), "Advanced selected", Toast.LENGTH_SHORT).show();

            }
        });



    }
}