package com.example.assignment;

import android.content.Intent;
import android.os.AsyncTask;
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
import androidx.room.Room;

import com.example.assignment.Entities.Quiz;
import com.example.assignment.Entities.QuizResult;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;

import java.util.ArrayList;
import java.util.Collections;

import static com.example.assignment.SelectQuizDifficulty.EXTRA_MESSAGE;

//this class displays the theory quizzes
//it will mark them based on how fast they answer (base score of 5000) and also how many they get correct
public class TheoryQuizActivity extends AppCompatActivity {

    private static final String TAG = "TheoryQuizActivity";
    private ArrayList<Quiz> mQuiz = new ArrayList<>();
    private RadioGroup options;
    private RadioButton optionA, optionB, optionC, optionD;
    private Button next, submit;
    private String difficulty, answer, email, selectedAnswer;
    private TextView question, position, results, score, highscore;
    private ImageButton cancel;
    private ImageView imgquest;
    private int id, i;
    private int correctAnswers = 0;
    private int baseScore = 5000;
    private Quiz q;
    private boolean correct = false;
    long timeStarted, timeEnded, timeTaken;
    MyDatabase myDb;
    private int myDbresult;
    private QuizResult qr;


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
        highscore = findViewById(R.id.highscore);
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

        setTitle("Theory Quiz");

        GoogleSignInAccount acct = GoogleSignIn.getLastSignedInAccount(getApplicationContext());
        if (acct != null) {
            email = acct.getEmail();
            System.out.println(email);
        }

        if (mQuiz != null) {
            Collections.shuffle(mQuiz);
            i = 0;
            q = mQuiz.get(i);
            question.setText(q.getQuestion());
            optionA.setText(q.getOp1());
            optionB.setText(q.getOp2());
            optionC.setText(q.getOp3());
            optionD.setText(q.getOp4());
            highscore.setText("");
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
                baseScore = (baseScore - (int) timeTaken) + (correctAnswers * 100);
                options.setVisibility(View.GONE);
                results.setVisibility(View.VISIBLE);
                score.setVisibility(View.VISIBLE);
                submit.setVisibility(View.GONE);
                new GetStoredScore().execute();
                new InsertScoresTask().execute();
                new UpdateScoresTask().execute();

            }
        });

    }

    private void next() {

        if (i >= 10) {
            next.setVisibility(View.GONE);
            submit.setVisibility(View.VISIBLE);
            timeEnded = System.currentTimeMillis();
            timeTaken = (timeEnded - timeStarted) / 1000;
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

    private class InsertScoresTask extends AsyncTask<Void, Void, Void> {
        @Override
        protected Void doInBackground(Void... voids) {
            myDb = Room.databaseBuilder(getApplicationContext(), MyDatabase.class, "my-db.db")
                    .build();

            myDb.quizResultDao().insertSingleResult(email, difficulty, baseScore, "theory");

            Log.d(TAG, "doInBackground:  ");
            return null;
        }

        @Override
        protected void onPostExecute(Void v) {
            super.onPostExecute(v);
            Log.d(TAG, "onPostExecute: FINISHED");

        }

    }

    private class UpdateScoresTask extends AsyncTask<Void, Void, Void> {
        @Override
        protected Void doInBackground(Void... voids) {
            myDb = Room.databaseBuilder(getApplicationContext(), MyDatabase.class, "my-db.db")
                    .build();

            if (baseScore > myDbresult) {
                qr = new QuizResult(email, difficulty, baseScore, "theory");
                myDb.quizResultDao().updateQuizResult(qr);
                Log.d(TAG, "doInBackground: New result updated! ");
            }
            Log.d(TAG, "doInBackground:  ");
            return null;
        }

        @Override
        protected void onPostExecute(Void v) {
            super.onPostExecute(v);
            updateUi();
            Log.d(TAG, "onPostExecute: FINISHED");

        }

    }

    private class GetStoredScore extends AsyncTask<Void, Void, Void> {
        @Override
        protected Void doInBackground(Void... voids) {
            myDb = Room.databaseBuilder(getApplicationContext(), MyDatabase.class, "my-db.db")
                    .build();

            myDbresult = myDb.quizResultDao().getResult(email, difficulty, "theory");
            Log.d(TAG, "doInBackground: get my saved result " + myDbresult);
            return null;
        }

        @Override
        protected void onPostExecute(Void v) {
            super.onPostExecute(v);
            Log.d(TAG, "onPostExecute: FINISHED");

        }

    }

    private void updateUi() {
        if (myDbresult > baseScore) {
            question.setText("Congratulations!");
            results.setText("You answered " + correctAnswers + " of 10 questions correctly");
            score.setText("Score: " + baseScore);
            imgquest.setImageResource(R.drawable.no_star);
            highscore.setText("You did not beat your previous high score of " + myDbresult);
        } else if (myDbresult == baseScore) {
            question.setText("Congratulations!");
            results.setText("You answered " + correctAnswers + " of 10 questions correctly");
            score.setText("Score: " + baseScore);
            imgquest.setImageResource(R.drawable.neutral);
            highscore.setText("You got the same score as your current high score " + baseScore);
        } else if (myDbresult == 0) {
            question.setText("Congratulations!");
            results.setText("You answered " + correctAnswers + " of 10 questions correctly");
            score.setText("Score: " + baseScore);
            imgquest.setImageResource(R.drawable.happy);
            highscore.setText("Your recorded high score is " + baseScore);
        } else {
            question.setText("Congratulations!");
            results.setText("You answered " + correctAnswers + " of 10 questions correctly");
            score.setText("Score: " + baseScore);
            imgquest.setImageResource(R.drawable.happy);
            highscore.setText("Congratulations! You beat your previous high score of " + myDbresult + ". Your new high score is " + baseScore);
        }


    }

}

