package com.example.assignment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

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
    private static int i = 1;
    ConstraintSet constraintSet;
    ConstraintLayout constraintLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_topic_information);

        Intent intent = getIntent();
        String topic = intent.getStringExtra("Topic");
        String id = intent.getStringExtra("id");

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


        //preloads data with first information
        information = mInfo.get(0);
        title.setText(topic.toUpperCase());
        info.setText(information.getInformation());
        image.setImageResource(information.getPicture());
        next.setVisibility(View.VISIBLE);
        previous.setVisibility(View.GONE);
        position.setText(0 + 1 + "/" + mInfo.size());
        constraintLayout = findViewById(R.id.constraintLayout);
        constraintSet = new ConstraintSet();
        constraintSet.clone(constraintLayout);
        constraintSet.constrainWidth(R.id.next, ConstraintLayout.LayoutParams.MATCH_PARENT);
        constraintSet.applyTo(constraintLayout);


        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //if i is less than the array list size
                if (i >= mInfo.size()) {
                    //sets next button as invisible if there are no more pages to learn
                    next.setVisibility(View.GONE);
                    previous.setVisibility(View.VISIBLE);
                    constraintSet = new ConstraintSet();
                    constraintSet.clone(constraintLayout);
                    constraintSet.constrainWidth(R.id.previous, ConstraintLayout.LayoutParams.MATCH_PARENT);
                    constraintSet.applyTo(constraintLayout);

                    info.setText("You have finished your learning");
                    image.setImageResource(R.drawable.tick);
                    position.setText("");
                    i = mInfo.size() + 1;
                } else {
                    information = mInfo.get(i);
                    info.setText(information.getInformation());
                    image.setImageResource(information.getPicture());
                    position.setText(i + 1 + "/" + mInfo.size());

                    previous.setVisibility(View.VISIBLE);
                    constraintSet = new ConstraintSet();
                    constraintSet.clone(constraintLayout);
                    constraintSet.constrainWidth(R.id.next, 540);
                    constraintSet.applyTo(constraintLayout);

                    i++;
                }

                if (i == 1 || (i + 1) == 0) {
                    previous.setVisibility(View.GONE);
                    constraintSet = new ConstraintSet();
                    constraintSet.clone(constraintLayout);
                    constraintSet.constrainWidth(R.id.next, ConstraintLayout.LayoutParams.MATCH_PARENT);
                    constraintSet.applyTo(constraintLayout);

                    information = mInfo.get(0);
                    info.setText(information.getInformation());
                    image.setImageResource(information.getPicture());
                    position.setText(i + 1 + "/" + mInfo.size());
                }

            }
        });

        previous.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (i == 0 || (i - 2) == 0) {

                    previous.setVisibility(View.GONE);
                    constraintSet = new ConstraintSet();
                    constraintSet.clone(constraintLayout);
                    constraintSet.constrainWidth(R.id.next, ConstraintLayout.LayoutParams.MATCH_PARENT);
                    constraintSet.applyTo(constraintLayout);

                    information = mInfo.get(0);
                    info.setText(information.getInformation());
                    image.setImageResource(information.getPicture());
                    i = 1;
                    position.setText(i + "/" + mInfo.size());

                } else {
                    information = mInfo.get(i - 2);
                    info.setText(information.getInformation());
                    image.setImageResource(information.getPicture());

                    previous.setVisibility(View.VISIBLE);
                    constraintSet = new ConstraintSet();
                    constraintSet.clone(constraintLayout);
                    constraintSet.constrainWidth(R.id.next, 540);
                    constraintSet.applyTo(constraintLayout);

                    next.setVisibility(View.VISIBLE);
                    constraintSet = new ConstraintSet();
                    constraintSet.clone(constraintLayout);
                    constraintSet.constrainWidth(R.id.previous, 540);
                    constraintSet.applyTo(constraintLayout);

                    position.setText(i - 1 + "/" + mInfo.size());
                    i--;
                }


            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), TopicsActivity.class);
                startActivity(intent);

            }
        });


        setTitle(topic);

    }
}
