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
import android.widget.TextView;

import com.example.assignment.Entities.Information;
import com.example.assignment.Entities.Topic;

import java.util.ArrayList;

public class TopicInformation extends AppCompatActivity {

    private Topic topic;
    private Information information;
    private ImageView image;
    private TextView title, info, position;
    private ImageButton cancel;
    private Button next, previous;
    private ArrayList<Information> mInfo = new ArrayList<>();
    private static int i = 0;
    ConstraintSet constraintSet;
    ConstraintLayout constraintLayout;
    public static final String EXTRA_MESSAGE = "topic_id";
    private String upperString;
    private static final String TAG = "TopicInformation";
    private int width;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_topic_information);

        Intent intent = getIntent();
        String topic = intent.getStringExtra("Topic");
        String id = intent.getStringExtra("id");
        upperString = intent.getStringExtra("upperString");

        image = findViewById(R.id.image);
        title = findViewById(R.id.title);
        info = findViewById(R.id.info);
        cancel = findViewById(R.id.cancel);
        next = findViewById(R.id.next);
        previous = findViewById(R.id.previous);
        position = findViewById(R.id.position);

        for (Information i : Information.getInfo()) {
            if (i.getTopicId() == Integer.parseInt(id)) {
                mInfo.add(i);
            }
        }

        //gets the width of the screen
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        width = displayMetrics.widthPixels;


        //preloads data with first information if the list of information is not null
        if (mInfo != null) {
            i = 0;
            information = mInfo.get(i);
            title.setText(topic.toUpperCase());
            info.setText(information.getInformation());
            image.setImageResource(information.getPicture());
            position.setText(i + 1 + "/" + mInfo.size());

            //changes button so that only the next button is visible when the user is on the first information object
            next.setVisibility(View.VISIBLE);
            previous.setVisibility(View.GONE);
            constraintLayout = findViewById(R.id.constraintLayout);
            constraintSet = new ConstraintSet();
            constraintSet.clone(constraintLayout);
            constraintSet.constrainWidth(R.id.next, ConstraintLayout.LayoutParams.MATCH_PARENT);
            constraintSet.applyTo(constraintLayout);
            Log.d(TAG, "onCreate line 85 displays element: " + i);
            i++;
            Log.d(TAG, "onCreate line 88 sets next element: " + i);
        } else {
            info.setText("Nothing has been added yet. Please come back when the app updates.");
        }

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //if the information is on the last element of the list (no more information to display)
                if (i >= mInfo.size()) {
                    //changes button so that only the previous button is visible when the user is on the last information object
                    next.setVisibility(View.GONE);
                    previous.setVisibility(View.VISIBLE);
                    constraintSet = new ConstraintSet();
                    constraintSet.clone(constraintLayout);
                    constraintSet.constrainWidth(R.id.previous, ConstraintLayout.LayoutParams.MATCH_PARENT);
                    constraintSet.applyTo(constraintLayout);
                    //sets components with data
                    info.setText("You have finished your learning");
                    image.setImageResource(R.drawable.tick);
                    position.setText("");
                    Log.d(TAG, "onClick line 109 displays element: " + i);
                    i = mInfo.size() - 1;
                    Log.d(TAG, "onClick line 111 sets next element: " + i);
                    //if the information is on the first element of the list (user can't go to previous)
                } else if (i == 0) {
                    //changes button so that only the next button is visible when the user is on the first information object
                    previous.setVisibility(View.GONE);
                    constraintSet = new ConstraintSet();
                    constraintSet.clone(constraintLayout);
                    constraintSet.constrainWidth(R.id.next, ConstraintLayout.LayoutParams.MATCH_PARENT);
                    constraintSet.applyTo(constraintLayout);
                    //sets components with data
                    information = mInfo.get(i);
                    info.setText(information.getInformation());
                    image.setImageResource(information.getPicture());
                    position.setText(i + 1 + "/" + mInfo.size());
                    Log.d(TAG, "onClick line 125 displays element: " + i);
                    i++;
                    Log.d(TAG, "onClick line 127 sets next element: " + i);
                    //if the user is not on the last or first element of the information list
                } else {
                    //changes buttons so that previous and next buttons are visible and will take up half the screen width each
                    previous.setVisibility(View.VISIBLE);
                    constraintSet = new ConstraintSet();
                    constraintSet.clone(constraintLayout);
                    constraintSet.constrainWidth(R.id.next, width / 2);
                    constraintSet.applyTo(constraintLayout);
                    //sets components with data
                    information = mInfo.get(i);
                    info.setText(information.getInformation());
                    image.setImageResource(information.getPicture());
                    position.setText(i + 1 + "/" + mInfo.size());
                    Log.d(TAG, "onClick line 141 displays element: " + i);
                    i++;
                    Log.d(TAG, "onClick line 143 sets next element: " + i);
                }
            }
        });

        previous.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //if the user is on the first element of the information list
                if (i == 0) {
                    //changes button so that only the next button is visible when the user is on the first information object
                    previous.setVisibility(View.GONE);
                    constraintSet = new ConstraintSet();
                    constraintSet.clone(constraintLayout);
                    constraintSet.constrainWidth(R.id.next, ConstraintLayout.LayoutParams.MATCH_PARENT);
                    constraintSet.applyTo(constraintLayout);
                    //sets components with data
                    information = mInfo.get(i);
                    info.setText(information.getInformation());
                    image.setImageResource(information.getPicture());
                    position.setText(i + 1 + "/" + mInfo.size());
                    Log.d(TAG, "onClick line 164 displays element: " + i);
                    i++;
                    Log.d(TAG, "onClick line 166 sets next element: " + i);
                    //if the user is not on the first element of the information list
                } else {
                    //changes buttons so that previous and next buttons are visible and will take up half the screen width each
                    previous.setVisibility(View.VISIBLE);
                    constraintSet = new ConstraintSet();
                    constraintSet.clone(constraintLayout);
                    constraintSet.constrainWidth(R.id.next, width / 2);
                    constraintSet.applyTo(constraintLayout);
                    next.setVisibility(View.VISIBLE);
                    constraintSet = new ConstraintSet();
                    constraintSet.clone(constraintLayout);
                    constraintSet.constrainWidth(R.id.previous, width / 2);
                    constraintSet.applyTo(constraintLayout);
                    //sets components with data
                    information = mInfo.get(i);
                    info.setText(information.getInformation());
                    image.setImageResource(information.getPicture());
                    position.setText(i + 1 + "/" + mInfo.size());
                    Log.d(TAG, "onClick line 185 dislpays element: " + i);
                    i--;
                    Log.d(TAG, "onClick line 187 sets next element: " + i);
                }

            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), TopicsActivity.class);
                intent.putExtra(EXTRA_MESSAGE, upperString.toLowerCase());
                startActivity(intent);

            }
        });

        setTitle(topic);

    }
}
