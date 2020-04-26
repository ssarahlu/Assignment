package com.example.assignment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class SelectLearningActivity extends AppCompatActivity {

    private Button musicTheory, musicTrivia;
    public static final String EXTRA_MESSAGE = "difficulty";
    private String email, difficulty;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_learning);

        musicTheory = findViewById(R.id.musicTheory);
        musicTrivia = findViewById(R.id.musicTrivia);

        Intent intent = getIntent();
        difficulty = intent.getStringExtra(EXTRA_MESSAGE).toLowerCase();

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
