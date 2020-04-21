package com.example.assignment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
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
        for (int i = 0; i < mInfo.size(); i++) {
            information = mInfo.get(i);
            title.setText(topic.toUpperCase());
            info.setText(information.getInformation());
            image.setImageResource(information.getPicture());
            previous.setVisibility(View.GONE);
            position.setText(i + 1 + "/" + mInfo.size());
            break;
        }

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (i > mInfo.size()) {
                    next.setVisibility(View.GONE);
                    previous.setVisibility(View.GONE);
                    info.setText("You have finished your learning");
                    image.setImageResource(R.drawable.tick);
                    position.setText(i + 1 + "/" + mInfo.size());
                    i = 1;
                }

                if (i == 0 || (i + 1) == 0) {
                    previous.setVisibility(View.GONE);
                    information = mInfo.get(0);
                    info.setText(information.getInformation());
                    image.setImageResource(information.getPicture());
                    position.setText(i + 1 + "/" + mInfo.size());
                }

                System.out.println("next " + i);
                information = mInfo.get(i);
                info.setText(information.getInformation());
                image.setImageResource(information.getPicture());
                previous.setVisibility(View.VISIBLE);
                position.setText(i + 1 + "/" + mInfo.size());
                i++;

            }
        });

        previous.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (i > mInfo.size()) {
                    next.setVisibility(View.GONE);
                    previous.setVisibility(View.GONE);
                    info.setText("You have finished your learning");
                    image.setImageResource(R.drawable.tick);
                    position.setText(i + 1 + "/" + mInfo.size());
                    i = 1;
                }

                if (i == 0 || (i - 2) == 0) {
                    previous.setVisibility(View.GONE);
                    information = mInfo.get(0);
                    info.setText(information.getInformation());
                    image.setImageResource(information.getPicture());
                    position.setText(i + 1 + "/" + mInfo.size());
                }

                System.out.println("previous " + (i - 2));
                information = mInfo.get(i - 2);
                info.setText(information.getInformation());
                image.setImageResource(information.getPicture());
                previous.setVisibility(View.VISIBLE);
                position.setText(i - 1 + "/" + mInfo.size());
                i--;
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
