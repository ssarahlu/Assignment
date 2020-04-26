package com.example.assignment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.assignment.Entities.Quiz;

import java.util.ArrayList;
import java.util.Collections;

import static com.example.assignment.SelectQuizDifficulty.EXTRA_MESSAGE;

public class TheoryQuizActivity extends AppCompatActivity {

    private static final String TAG = "TheoryQuizActivity";
    private ArrayList<Quiz> mQuiz = new ArrayList<>();
    private RadioGroup options;
    private RadioButton optionA, optionB, optionC, optionD;
    private Button next, submit;
    private String difficulty, answer, email, selectedAnswer;
    private TextView question, position, results, score;
    private ImageButton cancel;
    private ImageView imgquest;
    private int id, i;
    private int correctAnswers = 0;
    private int baseScore = 5000;
    private Quiz q;
    private boolean correct = false;
    long timeStarted, timeEnded, timeTaken;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_theory_quiz);
        Intent intent = getIntent();
        difficulty = intent.getStringExtra(EXTRA_MESSAGE).toLowerCase();
        if (difficulty.equals("medium")) {
            difficulty = "intermediate";
        } else if (difficulty.equals("hard")) {
            difficulty = "advanced";
        }
        System.out.println(difficulty);

        optionA = findViewById(R.id.optionA);
        optionB = findViewById(R.id.optionB);
        optionC = findViewById(R.id.optionC);
        optionD = findViewById(R.id.optionD);
        question = findViewById(R.id.question);
        next = findViewById(R.id.next);
        submit = findViewById(R.id.submit);
        cancel = findViewById(R.id.cancel);
        imgquest = findViewById(R.id.imgquest);
        position = findViewById(R.id.position);
        options = findViewById(R.id.options);
        results = findViewById(R.id.results);
        score = findViewById(R.id.score);
        next.setVisibility(View.VISIBLE);
        submit.setVisibility(View.GONE);
        results.setVisibility(View.GONE);
        score.setVisibility(View.GONE);

        timeStarted = System.currentTimeMillis();


        for (Quiz q : Quiz.getQuiz()) {
            if (q.getDifficulty().equals(difficulty)) {
                mQuiz.add(q);
            }
        }

        setTitle("Quiz");

//        GoogleSignInAccount acct = GoogleSignIn.getLastSignedInAccount(getApplicationContext());
//        if (acct != null) {
//            email = acct.getEmail();
//            System.out.println(email);
//        }

        if (mQuiz != null) {
            Collections.shuffle(mQuiz);
            i = 0;
            q = mQuiz.get(i);
            question.setText(q.getQuestion());
            optionA.setText(q.getOp1());
            optionB.setText(q.getOp2());
            optionC.setText(q.getOp3());
            optionD.setText(q.getOp4());
            imgquest.setImageResource(q.getPhoto());
            answer = q.getAnswer();
            id = q.getId();
            position.setText(i + 1 + "/" + 10);
        } else {
            question.setText("Nothing has been added yet. Please come back when the app updates.");
        }

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkResult();
                next();
            }

        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), SelectLearningActivity.class);
//                intent.putExtra(EXTRA_MESSAGE, difficulty);
                startActivity(intent);

            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                question.setText("Congratulations!");
                imgquest.setVisibility(View.GONE);
                options.setVisibility(View.GONE);
                results.setVisibility(View.VISIBLE);
                score.setVisibility(View.VISIBLE);
                results.setText("You answered " + correctAnswers + " of 10 questions correctly");
                baseScore = (baseScore - (int) timeTaken) + (correctAnswers * 100);
                score.setText("Score: " + baseScore);
                submit.setVisibility(View.GONE);

            }
        });

    }

    private void next() {

        if (i >= 10) {
            next.setVisibility(View.GONE);
            submit.setVisibility(View.VISIBLE);
            timeEnded = System.currentTimeMillis();
            timeTaken = (timeEnded - timeStarted)/1000;
            i = 10;

        } else if (i <= 0) {
            options.clearCheck();
            i = 1;
            Log.d(TAG, "onClick: line 170 displays index at " + i);
            submit.setVisibility(View.GONE);
            next.setVisibility(View.VISIBLE);
            q = mQuiz.get(i);
            question.setText(q.getQuestion());
            optionA.setText(q.getOp1());
            optionB.setText(q.getOp2());
            optionC.setText(q.getOp3());
            optionD.setText(q.getOp4());
            imgquest.setImageResource(q.getPhoto());
            answer = q.getAnswer();
            id = q.getId();
            position.setText(i + 1 + "/10");
        } else {
            options.clearCheck();
            i++;
            Log.d(TAG, "onClick: line 187 displays index at " + i);
            submit.setVisibility(View.GONE);
            next.setVisibility(View.VISIBLE);
            q = mQuiz.get(i);
            question.setText(q.getQuestion());
            optionA.setText(q.getOp1());
            optionB.setText(q.getOp2());
            optionC.setText(q.getOp3());
            optionD.setText(q.getOp4());
            imgquest.setImageResource(q.getPhoto());
            answer = q.getAnswer();
            id = q.getId();
            position.setText(i + 1 + "/10");
            if (i == 9) {
                next.setVisibility(View.GONE);
                submit.setVisibility(View.VISIBLE);
                i++;
            }
        }
    }

    private boolean checkResult() {
        if (optionA.isChecked()) {
            selectedAnswer = optionA.getText().toString();
            if (selectedAnswer.equals(answer)) {
                correct = true;
                correctAnswers++;
            } else {
                correct = false;
            }
            return true;
        } else if (optionB.isChecked()) {
            selectedAnswer = optionB.getText().toString();
            if (selectedAnswer.equals(answer)) {
                correct = true;
                correctAnswers++;
            } else {
                correct = false;
            }
            return true;
        } else if (optionC.isChecked()) {
            selectedAnswer = optionC.getText().toString();
            if (selectedAnswer.equals(answer)) {
                correct = true;
                correctAnswers++;
            } else {
                correct = false;
            }
            return true;
        } else if (optionD.isChecked()) {
            selectedAnswer = optionD.getText().toString();
            if (selectedAnswer.equals(answer)) {
                correct = true;
                correctAnswers++;
            } else {
                correct = false;
            }
            return true;
        } else {
            return false;
        }
    }

    }

