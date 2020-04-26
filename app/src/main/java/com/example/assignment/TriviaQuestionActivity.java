package com.example.assignment;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import com.example.assignment.Entities.QuizResult;
import com.example.assignment.Fragments.FragmentTrivia;

// Used the following repository for reference: https://github.com/EklavyaM/Trivia

public class TriviaQuestionActivity extends AppCompatActivity {

    private int mPosInCurrentList = 0;
    MusicTriviaResult g;
    private TextView score;
    ProgressBar progressBar;
    private CountDownTimer stopWatch;
    MyDatabase myDb;
    private String email, difficulty, scoreString;
    private int myDbresult;
    private QuizResult qr;

    int maxTime = 45000;
    int countDownInterval = 5;
    int remainingProgress;
    private static final String TAG = "TriviaQuestionActivity";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trivia_question);
        init();
        loadNextFragment();
        setTitle("Trivia Quiz");

        Intent intent = getIntent();
        difficulty = intent.getStringExtra("difficulty");


    }

    private void init() {
        g = MusicTriviaResult.getInstance(this);
        score = findViewById(R.id.score);
        progressBar = findViewById(R.id.progressBar);

        score.setText("0");

        progressBar.setMax(maxTime);
        progressBar.setProgress(maxTime);

        additionalTime(0);
    }

    public void onAnswerClicked(boolean isCorrect, String difficulty) {

        stopWatch.cancel();

        int marks = 0;
        float penalty = 0;

        switch (difficulty) {
            case "easy":
                marks = 5;
                penalty = 3f;
                break;
            case "medium":
                marks = 8;
                penalty = 5f;
                break;
            case "hard":
                marks = 12;
                penalty = 7.0f;
                break;
        }


        if (isCorrect) {
            g.questionsCorrectlyAnswered++;
            additionalTime(marks * 1000);
            g.correctAnswer(marks);
            scoreString = Integer.toString(g.getScore());
            score.setText(scoreString);

        } else {
            additionalTime((int) (-penalty * 1000));
        }

        nextQuestion();
    }

    public void nextQuestion() {

        mPosInCurrentList++;
        g.questionsEncountered++;

        if (mPosInCurrentList >= g.currentList.size()) {
            if (g.nextList.size() > 0) {
                g.currentList.clear();
                g.currentList.addAll(g.nextList);

                g.loadNextList();

                mPosInCurrentList = 0;
                loadNextFragment();
            } else {
                g.gameOver(this);
            }
        } else {
            loadNextFragment();
        }
    }

    private void loadNextFragment() {
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                FragmentTrivia fragment = FragmentTrivia.newInstance(mPosInCurrentList);
                FragmentManager fragmentManager = getSupportFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.frame_layout, fragment).commit();
            }
        }, 100);

    }

    private void additionalTime(int time) {
        remainingProgress = progressBar.getProgress();

        int amount = remainingProgress + time;

        if (amount <= 0) {
            progressBar.setProgress(0);
            g.gameOver(TriviaQuestionActivity.this);
        }

        if (amount >= maxTime) {
            stopWatch = new CountDownTimer(maxTime, countDownInterval) {
                @Override
                public void onTick(long millisUntilFinished) {
                    int progress = Math.round((millisUntilFinished));
                    progressBar.setProgress(progress);
                }

                @Override
                public void onFinish() {
                    g.gameOver(TriviaQuestionActivity.this);
                }
            }.start();
        } else {
            stopWatch = new CountDownTimer(amount, countDownInterval) {
                @Override
                public void onTick(long millisUntilFinished) {
                    int progress = Math.round((millisUntilFinished));
                    progressBar.setProgress(progress);
                }

                @Override
                public void onFinish() {
                    g.gameOver(TriviaQuestionActivity.this);
                }
            }.start();
        }
    }

    @Override
    public void onBackPressed() {
        stopWatch.cancel();
        Intent intent = new Intent(this, StartTriviaActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        finish();
    }


}