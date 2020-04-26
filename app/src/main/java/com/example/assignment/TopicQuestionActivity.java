package com.example.assignment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.assignment.Entities.Question;
import com.example.assignment.Entities.TopicResult;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;

import java.util.ArrayList;

//this class displays the "knowledge check" or mini-quiz after each course
//this is where users can earn stars
//it will play a sound if the user gets the answer right/wrong
public class TopicQuestionActivity extends AppCompatActivity {
    private int topicId, stars, i, questionId;
    private String topic, answer, selectedAnswer, difficulty, email;
    private RadioButton opt1, opt2, opt3, opt4;
    private RadioGroup grp;
    private TextView question, position, fin;
    private Button next, check, again;
    private ImageButton cancel;
    private ImageView img1, img2, img3, img4, imgquest;
    public static final String EXTRA_MESSAGE = "topic_id";
    private ArrayList<Question> mQuestions = new ArrayList<>();
    private Question q;
    private boolean correct = false;
    private static final String TAG = "TopicQuestionActivity";
    TopicResult tr;
    MyDatabase myDb;
    int addedStars, mStars;
    private String finished;
    MediaPlayer mpWrong;
    MediaPlayer mpRight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_topic_question);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Intent intent = getIntent();
        String id = intent.getStringExtra("id");
        topicId = Integer.valueOf(id);
        topic = intent.getStringExtra("topic");
        difficulty = intent.getStringExtra(EXTRA_MESSAGE);
        mpWrong = MediaPlayer.create(getApplicationContext(), R.raw.wrong);
        mpRight = MediaPlayer.create(getApplicationContext(), R.raw.right);

        opt1 = findViewById(R.id.opt1);
        opt2 = findViewById(R.id.opt2);
        opt3 = findViewById(R.id.opt3);
        opt4 = findViewById(R.id.opt4);
        question = findViewById(R.id.question);
        again = findViewById(R.id.again);
        next = findViewById(R.id.next);
        check = findViewById(R.id.check);
        cancel = findViewById(R.id.cancel);
        img1 = findViewById(R.id.img1);
        img2 = findViewById(R.id.img2);
        img3 = findViewById(R.id.img3);
        img4 = findViewById(R.id.img4);
        imgquest = findViewById(R.id.imgquest);
        position = findViewById(R.id.position);
        grp = findViewById(R.id.grp);
        fin = findViewById(R.id.finished);
        stars = 0;

        for (Question q : Question.getQuestions()) {
            if (q.getTopicId() == topicId) {
                mQuestions.add(q);
            }
        }
        setTitle(topic);
        GoogleSignInAccount acct = GoogleSignIn.getLastSignedInAccount(getApplicationContext());
        if (acct != null) {
            email = acct.getEmail();
        }

        //code sound for wrong

        if (mQuestions != null) {
            i = 0;
            q = mQuestions.get(i);
            question.setText(q.getQuestion());
            opt1.setText(q.getOp1());
            opt2.setText(q.getOp2());
            opt3.setText(q.getOp3());
            opt4.setText(q.getOp4());
            imgquest.setImageResource(q.getPhoto());
            answer = q.getAnswer();
            questionId = q.getId();
            position.setText(i + 1 + "/" + mQuestions.size());
            next.setVisibility(View.GONE);
            img1.setImageResource(android.R.color.transparent);
            img2.setImageResource(android.R.color.transparent);
            img3.setImageResource(android.R.color.transparent);
            img4.setImageResource(android.R.color.transparent);
            check.setVisibility(View.VISIBLE);
            again.setVisibility(View.GONE);

        } else {
            question.setText("Nothing has been added yet. Please come back when the app updates.");
        }

        check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO - check if null
                img1.setImageResource(android.R.color.transparent);
                img2.setImageResource(android.R.color.transparent);
                img3.setImageResource(android.R.color.transparent);
                img4.setImageResource(android.R.color.transparent);
                boolean answered = checkResult();
                if (answered == true) {
                    again.setVisibility(View.GONE);
                    check.setVisibility(View.GONE);
                    next.setVisibility(View.VISIBLE);
                } else {
                    again.setVisibility(View.GONE);
                    check.setVisibility(View.VISIBLE);
                    next.setVisibility(View.GONE);
                }
            }
        });

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                next();
                next.setVisibility(View.GONE);

            }

        });


        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), TopicsActivity.class);
                intent.putExtra(EXTRA_MESSAGE, difficulty);
                startActivity(intent);

            }
        });

        again.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fin.setText("");
                if (mQuestions != null) {
                    i = 0;
                    stars = 0;
                    q = mQuestions.get(i);
                    question.setText(q.getQuestion());
                    opt1.setText(q.getOp1());
                    opt2.setText(q.getOp2());
                    opt3.setText(q.getOp3());
                    opt4.setText(q.getOp4());
                    imgquest.setImageResource(q.getPhoto());
                    answer = q.getAnswer();
                    questionId = q.getId();
                    position.setText(i + 1 + "/" + mQuestions.size());
                    next.setVisibility(View.GONE);
                    img1.setImageResource(android.R.color.transparent);
                    img2.setImageResource(android.R.color.transparent);
                    img3.setImageResource(android.R.color.transparent);
                    img4.setImageResource(android.R.color.transparent);
                    check.setVisibility(View.VISIBLE);
                    again.setVisibility(View.GONE);
                    grp.setVisibility(View.VISIBLE);

                } else {
                    question.setText("Nothing has been added yet. Please come back when the app updates.");
                }

            }
        });
    }

    private boolean checkResult() {
        if (opt1.isChecked()) {
            selectedAnswer = opt1.getText().toString();
            if (selectedAnswer.equals(answer)) {
                img1.setImageResource(R.drawable.correct);
                correct = true;
                mpRight.start();
                stars++;
            } else {
                img1.setImageResource(R.drawable.wrong);
                displayTick();
                correct = false;
            }
            return true;
        } else if (opt2.isChecked()) {
            selectedAnswer = opt2.getText().toString();
            if (selectedAnswer.equals(answer)) {
                img2.setImageResource(R.drawable.correct);
                correct = true;
                mpRight.start();
                stars++;
            } else {
                img2.setImageResource(R.drawable.wrong);
                displayTick();
                correct = false;
                mpWrong.start();
            }
            return true;
        } else if (opt3.isChecked()) {
            selectedAnswer = opt3.getText().toString();
            if (selectedAnswer.equals(answer)) {
                img3.setImageResource(R.drawable.correct);
                correct = true;
                mpRight.start();
                stars++;
            } else {
                img3.setImageResource(R.drawable.wrong);
                displayTick();
                correct = false;
                mpWrong.start();
            }
            return true;
        } else if (opt4.isChecked()) {
            selectedAnswer = opt4.getText().toString();
            if (selectedAnswer.equals(answer)) {
                img4.setImageResource(R.drawable.correct);
                correct = true;
                mpRight.start();
                stars++;
            } else {
                img4.setImageResource(R.drawable.wrong);
                displayTick();
                correct = false;
                mpWrong.start();
            }
            return true;

        } else {
            next.setVisibility(View.GONE);
            Toast.makeText(getApplicationContext(), "Please select an answer", Toast.LENGTH_SHORT).show();
            mpWrong.start();
            return false;
        }
    }

    //displays the correct answer if the user is wrong
    public void displayTick() {
        if (answer.equals(opt1.getText().toString())) {
            img1.setImageResource(R.drawable.correct);
        } else if (answer.equals(opt2.getText().toString())) {
            img2.setImageResource(R.drawable.correct);
        } else if (answer.equals(opt3.getText().toString())) {
            img3.setImageResource(R.drawable.correct);
        } else if (answer.equals(opt4.getText().toString())) {
            img4.setImageResource(R.drawable.correct);
        } else {
            img1.setImageResource(android.R.color.transparent);
            img2.setImageResource(android.R.color.transparent);
            img3.setImageResource(android.R.color.transparent);
            img4.setImageResource(android.R.color.transparent);
        }
    }


    private void next() {
        img1.setImageResource(android.R.color.transparent);
        img2.setImageResource(android.R.color.transparent);
        img3.setImageResource(android.R.color.transparent);
        img4.setImageResource(android.R.color.transparent);
        grp.clearCheck();
        if (i >= mQuestions.size()) {
            new MyViewedTask().execute();
            new GetStarsTask().execute();
            tr = new TopicResult(topicId, email, stars, true);
            new UpdateStarsTask().execute();
            next.setVisibility(View.GONE);
            grp.setVisibility(View.GONE);
            check.setVisibility(View.GONE);
            again.setVisibility(View.VISIBLE);
            //displays stars earnt in the mini quiz

            i = mQuestions.size();


        } else if (i <= 0) {
            i = 1;
            Log.d(TAG, "onClick: line 170 displays index at " + i);
            next.setVisibility(View.VISIBLE);
            check.setVisibility(View.VISIBLE);
            again.setVisibility(View.GONE);
            q = mQuestions.get(i);
            question.setText(q.getQuestion());
            opt1.setText(q.getOp1());
            opt2.setText(q.getOp2());
            opt3.setText(q.getOp3());
            opt4.setText(q.getOp4());
            imgquest.setImageResource(q.getPhoto());
            answer = q.getAnswer();
            questionId = q.getId();
            position.setText(i + 1 + "/" + mQuestions.size());
        } else {
            i++;
            Log.d(TAG, "onClick: line 187 displays index at " + i);
            check.setVisibility(View.VISIBLE);
            again.setVisibility(View.GONE);
            next.setVisibility(View.VISIBLE);
            q = mQuestions.get(i);
            question.setText(q.getQuestion());
            opt1.setText(q.getOp1());
            opt2.setText(q.getOp2());
            opt3.setText(q.getOp3());
            opt4.setText(q.getOp4());
            imgquest.setImageResource(q.getPhoto());
            answer = q.getAnswer();
            questionId = q.getId();
            position.setText(i + 1 + "/" + mQuestions.size());

            if (i == (mQuestions.size() - 1)) {
                i++;
            }
        }

    }


    private class MyViewedTask extends AsyncTask<Void, Void, Void> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            Log.d(TAG, "onPreExecute: LOADING");
        }

        @Override
        protected Void doInBackground(Void... voids) {
            myDb = Room.databaseBuilder(getApplicationContext(), MyDatabase.class, "my-db.db")
                    .build();
            myDb.topicResultDao().insert(topicId, tr.getEmail(), 0, true);
            return null;
        }

        @Override
        protected void onPostExecute(Void v) {
            super.onPostExecute(v);
            Log.d(TAG, "onPostExecute: FINISHED");
        }

    }

    private class GetStarsTask extends AsyncTask<Void, Void, Void> {
        @Override
        protected Void doInBackground(Void... voids) {
            myDb = Room.databaseBuilder(getApplicationContext(), MyDatabase.class, "my-db.db")
                    .build();
            mStars = myDb.topicResultDao().getStars(tr.getEmail(), tr.getTopicId());
            Log.d(TAG, "doInBackground: Current stars for this topic are:  " + mStars);
            return null;
        }

        @Override
        protected void onPostExecute(Void v) {
            super.onPostExecute(v);
            Log.d(TAG, "onPostExecute: FINISHED");

        }

    }

    private void updateUi() {
        switch (stars) {
            case 1:
                imgquest.setImageResource(R.drawable.one_star);
                break;
            case 2:
                imgquest.setImageResource(R.drawable.two_star);
                break;
            case 3:
                imgquest.setImageResource(R.drawable.three_star);
                break;
            case 4:
                imgquest.setImageResource(R.drawable.four_star);
                break;
            case 5:
                imgquest.setImageResource(R.drawable.five_star);
                break;
            case 6:
                imgquest.setImageResource(R.drawable.six_star);
                break;
            case 0:
                imgquest.setImageResource(R.drawable.no_star);
                break;
            default:
                imgquest.setImageResource(R.drawable.tick);
        }
        question.setText("You have finished this topic's knowledge check. Your result is " + stars + "/" + mQuestions.size() + "");
        position.setText("");
        fin.setText(finished);
    }

    private class UpdateStarsTask extends AsyncTask<Void, Void, Void> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            Log.d(TAG, "onPreExecute: LOADING");
        }

        @Override
        protected Void doInBackground(Void... voids) {
            myDb = Room.databaseBuilder(getApplicationContext(), MyDatabase.class, "my-db.db")
                    .build();
            Log.d(TAG, "doInBackground: The stars before updating are " + mStars);

            //if my stars is maxed out
            if (mStars >= mQuestions.size()) {
                addedStars = 0;
                finished = "You have already achieved the maximum stars for this topic. You currently have a total of " + mStars + "/" + mQuestions.size() + " stars for this topic so you cannot earn any more stars.";
                Log.d(TAG, "doInBackground: " + finished);
                //if your currently earn stars are greater than the maximum stars
            } else if ((mStars + stars) > mQuestions.size()) {
                addedStars = (mStars + stars) - mQuestions.size();
                if (addedStars == 1) {
                    finished = "You will get an additional " + addedStars + " star. Your new total is " + (addedStars + mStars) + "/" + mQuestions.size() + " stars for this topic";
                    Log.d(TAG, "doInBackground: " + finished);
                } else {
                    finished = "You will get an additional " + addedStars + " star. Your new total is " + (addedStars + mStars) + "/" + mQuestions.size() + " stars for this topic";
                    Log.d(TAG, "doInBackground: " + finished);
                }
            } else if (mStars == 0) {
                addedStars = stars;
                if (addedStars == 1) {
                    finished = "You have earned " + addedStars + " star. Your new total is " + (addedStars + mStars) + "/" + mQuestions.size() + " stars for this topic";
                    Log.d(TAG, "doInBackground: " + finished);
                } else {
                    finished = "You have earned " + addedStars + " stars. Your new total is " + (addedStars + mStars) + "/" + mQuestions.size() + " stars for this topic";
                    Log.d(TAG, "doInBackground: " + finished);
                }
            } else {
                addedStars = stars;
                if (addedStars == 1) {
                    finished = "You have earned " + addedStars + " star. Your new total is " + (addedStars + mStars) + "/" + mQuestions.size() + " stars for this topic";
                    Log.d(TAG, "doInBackground: " + finished);
                } else {
                    finished = "You have earned " + addedStars + " stars. Your new total is " + (addedStars + mStars) + "/" + mQuestions.size() + " stars for this topic";
                    Log.d(TAG, "doInBackground: " + finished);
                }
            }
            myDb.topicResultDao().updateStars((mStars + addedStars), tr.getEmail(), tr.getTopicId());
            mStars = myDb.topicResultDao().getStars(tr.getEmail(), tr.getTopicId());
            Log.d(TAG, "onPostExecute: my new total stars " + mStars);
            return null;
        }

        @Override
        protected void onPostExecute(Void v) {
            super.onPostExecute(v);
            Log.d(TAG, "onPostExecute: FINISHED");

            myDb.close();
            updateUi();

        }

    }

    //added back button in the toolbar
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent myIntent = new Intent(getApplicationContext(), TopicsActivity.class);
        myIntent.putExtra(EXTRA_MESSAGE, difficulty);
        startActivityForResult(myIntent, 0);
        return true;
    }

}
