package com.example.assignment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;

import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.assignment.Entities.Information;
import com.example.assignment.Entities.Question;

import java.util.ArrayList;

public class TopicQuestionActivity extends AppCompatActivity {
    private int topicId, stars, i, questionId;
    private String topic, answer, selectedAnswer, difficulty;
    private RadioButton opt1, opt2, opt3, opt4;
    private RadioGroup grp;
    private TextView question, position;
    private Button next, check, again;
    //    private Button back;
    private ImageButton cancel;
    private ImageView img1, img2, img3, img4, imgquest;
    public static final String EXTRA_MESSAGE = "topic_id";
    private ArrayList<Question> mQuestions = new ArrayList<>();
    private Question q;
    private boolean correct = false;
    ConstraintSet constraintSet;
    ConstraintLayout constraintLayout;
    private int width;
    private static final String TAG = "TopicQuestionActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_topic_question);
        Intent intent = getIntent();
        String id = intent.getStringExtra("id");
        topicId = Integer.valueOf(id);
        topic = intent.getStringExtra("topic");
        difficulty = intent.getStringExtra(EXTRA_MESSAGE);

        opt1 = findViewById(R.id.opt1);
        opt2 = findViewById(R.id.opt2);
        opt3 = findViewById(R.id.opt3);
        opt4 = findViewById(R.id.opt4);
        question = findViewById(R.id.question);
        again = findViewById(R.id.again);
        next = findViewById(R.id.next);
//        back = findViewById(R.id.back);
        check = findViewById(R.id.check);
        cancel = findViewById(R.id.cancel);
        img1 = findViewById(R.id.img1);
        img2 = findViewById(R.id.img2);
        img3 = findViewById(R.id.img3);
        img4 = findViewById(R.id.img4);
        imgquest = findViewById(R.id.imgquest);
        position = findViewById(R.id.position);
        grp = findViewById(R.id.grp);
//        constraintLayout = findViewById(R.id.constraintLayout);
        stars = 0;

        for (Question q : Question.getQuestions()) {
            if (q.getTopicId() == topicId) {
                mQuestions.add(q);
            }
        }
//        DisplayMetrics displayMetrics = new DisplayMetrics();
//        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
//        width = displayMetrics.widthPixels;
        setTitle(topic);

        //stars / question.size() will display total stars at end
        //also code it in so it stores in the DB
        //on click next display the data - say please make selection if null
        //questions displayed: check answer
        //answer displayed: next
        //code sound for wrong
        //change the viewed course green tick to only happen when they have finished knowledge check

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
//            back.setVisibility(View.GONE);
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
                //TODO - disappear buttons depending on array
                //TODO - check if null
                //TODO - clear radiogroup after selected
                img1.setImageResource(android.R.color.transparent);
                img2.setImageResource(android.R.color.transparent);
                img3.setImageResource(android.R.color.transparent);
                img4.setImageResource(android.R.color.transparent);
                checkResult();
                again.setVisibility(View.GONE);
                check.setVisibility(View.GONE);
                next.setVisibility(View.VISIBLE);
//                back.setVisibility(View.VISIBLE);
            }
        });

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                next();
                next.setVisibility(View.GONE);
//                back.setVisibility(View.GONE);
//                again.setVisibility(View.GONE);

            }

        });
//        back.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                back();
//                next.setVisibility(View.GONE);
//                back.setVisibility(View.GONE);
//                again.setVisibility(View.GONE);
//
//            }
//        });

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

    private void checkResult() {
        if (opt1.isChecked()) {
            selectedAnswer = opt1.getText().toString();
            if (selectedAnswer.equals(answer)) {
                img1.setImageResource(R.drawable.correct);
                correct = true;
                stars++;
            } else {
                img1.setImageResource(R.drawable.wrong);
                displayTick();
                correct = false;
            }
            Toast.makeText(getApplicationContext(), selectedAnswer + " is " + correct, Toast.LENGTH_SHORT).show();

        } else if (opt2.isChecked()) {
            selectedAnswer = opt2.getText().toString();
            if (selectedAnswer.equals(answer)) {
                img2.setImageResource(R.drawable.correct);
                correct = true;
                stars++;
            } else {
                img2.setImageResource(R.drawable.wrong);
                displayTick();
                correct = false;
            }
            Toast.makeText(getApplicationContext(), selectedAnswer + " is " + correct, Toast.LENGTH_SHORT).show();

        } else if (opt3.isChecked()) {
            selectedAnswer = opt3.getText().toString();
            if (selectedAnswer.equals(answer)) {
                img3.setImageResource(R.drawable.correct);
                correct = true;
                stars++;
            } else {
                img3.setImageResource(R.drawable.wrong);
                displayTick();
                correct = false;
            }
            Toast.makeText(getApplicationContext(), selectedAnswer + " is " + correct, Toast.LENGTH_SHORT).show();

        } else if (opt4.isChecked()) {
            selectedAnswer = opt4.getText().toString();
            if (selectedAnswer.equals(answer)) {
                img4.setImageResource(R.drawable.correct);
                correct = true;
                stars++;
            } else {
                img4.setImageResource(R.drawable.wrong);
                displayTick();
                correct = false;
            }
            Toast.makeText(getApplicationContext(), selectedAnswer + " is " + correct, Toast.LENGTH_SHORT).show();

        } else {
            next.setVisibility(View.GONE);
//            back.setVisibility(View.GONE);
            Toast.makeText(getApplicationContext(), "Please select an answer", Toast.LENGTH_SHORT).show();
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
            next.setVisibility(View.GONE);
            grp.setVisibility(View.GONE);
            check.setVisibility(View.GONE);
            again.setVisibility(View.VISIBLE);
//            back.setVisibility(View.GONE);
//            constraintSet = new ConstraintSet();
//            constraintSet.clone(constraintLayout);
//            constraintSet.constrainWidth(R.id.previous, ConstraintLayout.LayoutParams.MATCH_PARENT);
//            constraintSet.applyTo(constraintLayout);
            question.setText("You have finished your quiz. You have earned " + stars + "/" + mQuestions.size() + " stars");
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
            position.setText("");
            i = mQuestions.size();
        } else if (i <= 0) {
            i = 1;
            Log.d(TAG, "onClick: line 170 displays index at " + i);
//            back.setVisibility(View.VISIBLE);
            next.setVisibility(View.VISIBLE);
            check.setVisibility(View.VISIBLE);
            again.setVisibility(View.GONE);
//            constraintSet = new ConstraintSet();
//            constraintSet.clone(constraintLayout);
//            constraintSet.constrainWidth(R.id.previous, width / 2);
//            constraintSet.applyTo(constraintLayout);
//            constraintSet = new ConstraintSet();
//            constraintSet.clone(constraintLayout);
//            constraintSet.constrainWidth(R.id.next, width / 2);
//            constraintSet.constrainWidth(R.id.next, ConstraintLayout.LayoutParams.MATCH_PARENT);
//            constraintSet.applyTo(constraintLayout);
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
//            back.setVisibility(View.VISIBLE);
            check.setVisibility(View.VISIBLE);
            again.setVisibility(View.GONE);
            next.setVisibility(View.VISIBLE);

//            constraintSet = new ConstraintSet();
//            constraintSet.clone(constraintLayout);
//            constraintSet.constrainWidth(R.id.next, ConstraintLayout.LayoutParams.MATCH_PARENT);
//            constraintSet.applyTo(constraintLayout);
//            constraintSet.clone(constraintLayout);
//            constraintSet.constrainWidth(R.id.back, width / 2);
//            constraintSet.applyTo(constraintLayout);
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
//
//    private void back() {
//        grp.clearCheck();
//        img1.setImageResource(android.R.color.transparent);
//        img2.setImageResource(android.R.color.transparent);
//        img3.setImageResource(android.R.color.transparent);
//        img4.setImageResource(android.R.color.transparent);
//        if (i == mQuestions.size()) {
//            i--;
//            back.setVisibility(View.VISIBLE);
//            check.setVisibility(View.VISIBLE);
//            again.setVisibility(View.GONE);
//            constraintSet = new ConstraintSet();
//            constraintSet.clone(constraintLayout);
//            constraintSet.constrainWidth(R.id.next, width / 2);
//            constraintSet.applyTo(constraintLayout);
//            next.setVisibility(View.VISIBLE);
//            constraintSet = new ConstraintSet();
//            constraintSet.clone(constraintLayout);
//            constraintSet.constrainWidth(R.id.previous, width / 2);
//            constraintSet.applyTo(constraintLayout);
//            //sets components with data
//            q = mQuestions.get(i);
//            question.setText(q.getQuestion());
//            opt1.setText(q.getOp1());
//            opt2.setText(q.getOp2());
//            opt3.setText(q.getOp3());
//            opt4.setText(q.getOp4());
//            imgquest.setImageResource(q.getPhoto());
//            answer = q.getAnswer();
//            questionId = q.getId();
//            position.setText(i + 1 + "/" + mQuestions.size());
//
//        } else {
//            i--;
//            Log.d(TAG, "onClick: line 230 sets index at " + i);
//            if (i <= 0) {
//                back.setVisibility(View.GONE);
//                check.setVisibility(View.VISIBLE);
//                again.setVisibility(View.GONE);
//                constraintSet = new ConstraintSet();
//                constraintSet.clone(constraintLayout);
//                constraintSet.constrainWidth(R.id.next, ConstraintLayout.LayoutParams.MATCH_PARENT);
//                constraintSet.applyTo(constraintLayout);
//                i = 0;
//                q = mQuestions.get(i);
//                question.setText(q.getQuestion());
//                opt1.setText(q.getOp1());
//                opt2.setText(q.getOp2());
//                opt3.setText(q.getOp3());
//                opt4.setText(q.getOp4());
//                imgquest.setImageResource(q.getPhoto());
//                answer = q.getAnswer();
//                questionId = q.getId();
//                position.setText(i + 1 + "/" + mQuestions.size());
//
//            } else {
//                Log.d(TAG, "onClick: line 245 displays index at " + i);
//                back.setVisibility(View.VISIBLE);
//                check.setVisibility(View.VISIBLE);
//                again.setVisibility(View.GONE);
//                constraintSet = new ConstraintSet();
//                constraintSet.clone(constraintLayout);
//                constraintSet.constrainWidth(R.id.next, width / 2);
//                constraintSet.applyTo(constraintLayout);
//                next.setVisibility(View.VISIBLE);
//                constraintSet = new ConstraintSet();
//                constraintSet.clone(constraintLayout);
//                constraintSet.constrainWidth(R.id.previous, width / 2);
//                constraintSet.applyTo(constraintLayout);
//                q = mQuestions.get(i);
//                question.setText(q.getQuestion());
//                opt1.setText(q.getOp1());
//                opt2.setText(q.getOp2());
//                opt3.setText(q.getOp3());
//                opt4.setText(q.getOp4());
//                imgquest.setImageResource(q.getPhoto());
//                answer = q.getAnswer();
//                questionId = q.getId();
//                position.setText(i + 1 + "/" + mQuestions.size());
//            }
//        }
//    }


}
