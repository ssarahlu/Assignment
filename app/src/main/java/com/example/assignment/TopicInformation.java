package com.example.assignment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.assignment.Entities.Information;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;

import java.util.ArrayList;

public class TopicInformation extends AppCompatActivity {

    public static final String EXTRA_MESSAGE = "topic_id";
    private static final String TAG = "TopicInformation";
    private String topic, email, id, upperString;
    private String url;
    private Uri url1;
    private static int i = 0;
    private int width;
    private Information information;
    private ArrayList<Information> mInfo = new ArrayList<>();
    private ImageView image;
    private TextView title, info, position;
    private ImageButton cancel;
    private Button next, previous, check, learnMore;
    private ImageButton vv;
    ConstraintSet constraintSet;
    ConstraintLayout constraintLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_topic_information);

        Intent intent = getIntent();
        email = intent.getStringExtra("email");
        topic = intent.getStringExtra("Topic");
        id = intent.getStringExtra("id");
        upperString = intent.getStringExtra("upperString");
        Log.d(TAG, "onCreate: " + id);
        GoogleSignInAccount acct = GoogleSignIn.getLastSignedInAccount(getApplicationContext());
        if (acct != null) {
            email = acct.getEmail();
        }


        image = findViewById(R.id.image);
        title = findViewById(R.id.title);
        info = findViewById(R.id.info);
        cancel = findViewById(R.id.cancel);
        next = findViewById(R.id.next);
        previous = findViewById(R.id.previous);
        position = findViewById(R.id.position);
        check = findViewById(R.id.check);
        learnMore = findViewById(R.id.learnMore);
        vv = findViewById(R.id.vv);
        vv.setVisibility(View.GONE);
        url = null;

        for (Information i : Information.getInfo()) {
            if (i.getTopicId() == Integer.parseInt(id)) {
                mInfo.add(i);
                if (i.getId() == 10) {
                    url = "7Bv-JiFnoJ4";
                    url1 = Uri.parse(url);
                } else if (i.getId() == 18) {
                    url = "Z40fcNNvu0E";
                    url1 = Uri.parse(url);

                }
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
            Log.d(TAG, "onCreate: line 97 displays index at " + i);

            //changes button so that only the next button is visible when the user is on the first information object
            next.setVisibility(View.VISIBLE);
            previous.setVisibility(View.GONE);
            check.setVisibility(View.GONE);
            learnMore.setVisibility(View.GONE);
            constraintLayout = findViewById(R.id.constraintLayout);
            constraintSet = new ConstraintSet();
            constraintSet.clone(constraintLayout);
            constraintSet.constrainWidth(R.id.next, ConstraintLayout.LayoutParams.MATCH_PARENT);
            constraintSet.applyTo(constraintLayout);

            if (information.getId() == 10 || information.getId() == 18) {
                image.setVisibility(View.GONE);
                vv.setVisibility(View.VISIBLE);
                vv.setImageResource(information.getPicture());
                info.setText(information.getInformation());
            }

            image.setVisibility(View.VISIBLE);
            vv.setVisibility(View.GONE);

        } else {
            info.setText("Nothing has been added yet. Please come back when the app updates.");
        }

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                next();
            }
        });

        previous.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                back();
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

        check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), TopicQuestionActivity.class);
                intent.putExtra("id", id);
                intent.putExtra("topic", topic);
                intent.putExtra(EXTRA_MESSAGE, upperString);
                startActivity(intent);
            }

        });

        learnMore.setOnClickListener(new View.OnClickListener() {
            Intent implicit = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.google.com/"));
            @Override
            public void onClick(View v) {
                int id = information.getTopicId();
                switch (id) {
                    case 1:
                        implicit = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.google.com/#q=rhythm%20music"));
                        startActivity(implicit);
                        break;
                    case 2:
                        implicit = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.google.com/#q=time%20signatures"));
                            startActivity(implicit);
                    break;
                    case 3:
                        implicit = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.google.com/#q=treble%20clef"));
                        startActivity(implicit);
                        break;
                    case 4:
                        implicit = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.google.com/#q=bass%20clef"));
                        startActivity(implicit);
                        break;
                    case 5:
                        implicit = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.google.com/#q=dynamics%20music"));
                        startActivity(implicit);
                        break;
                    case 6:
                        implicit = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.google.com/#q=sharps%20music"));
                        startActivity(implicit);
                        break;
                    case 7:
                        implicit = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.google.com/#q=flats%20music"));
                        startActivity(implicit);
                        break;
                    case 8:
                        implicit = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.google.com/#q=tonality%20music"));
                        startActivity(implicit);
                        break;
                    case 9:
                        implicit = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.google.com/#q=dominant%207ths"));
                        startActivity(implicit);
                        break;
                    case 10:
                        implicit = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.google.com/#q=diminished%207ths"));
                        startActivity(implicit);
                        break;
                    case 11:
                        implicit = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.google.com/#q=music%20trivia"));
                        startActivity(implicit);
                        break;
                }

            }

        });

        vv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("vnd.youtube:" + url));
                intent.putExtra("VIDEO_ID", url);
                startActivity(intent);
                Log.d(TAG, "onClick: playing video");
            }

        });

        setTitle(topic);

    }

    private void next() {
        if (i >= mInfo.size()) {
            next.setVisibility(View.GONE);
            previous.setVisibility(View.VISIBLE);
            constraintSet = new ConstraintSet();
            constraintSet.clone(constraintLayout);
            constraintSet.constrainWidth(R.id.previous, ConstraintLayout.LayoutParams.MATCH_PARENT);
            constraintSet.applyTo(constraintLayout);
            //sets components with data
            info.setText("You have finished your learning");
            check.setVisibility(View.VISIBLE);
            learnMore.setVisibility(View.VISIBLE);
            image.setImageResource(R.drawable.tick);
            position.setText("");
            i = mInfo.size();
            Log.d(TAG, "onClick: line 167 displays index at " + i);

        } else if (i <= 0) {
            i = 1;
            Log.d(TAG, "onClick: line 170 displays index at " + i);
            previous.setVisibility(View.VISIBLE);
            check.setVisibility(View.GONE);
            learnMore.setVisibility(View.GONE);
            constraintSet = new ConstraintSet();
            constraintSet.clone(constraintLayout);
            constraintSet.constrainWidth(R.id.previous, width / 2);
            constraintSet.applyTo(constraintLayout);
            constraintSet = new ConstraintSet();
            constraintSet.clone(constraintLayout);
            constraintSet.constrainWidth(R.id.next, width / 2);
            constraintSet.applyTo(constraintLayout);
            //sets components with data
            information = mInfo.get(i);
            info.setText(information.getInformation());
            image.setImageResource(information.getPicture());
            position.setText(i + 1 + "/" + mInfo.size());
            image.setVisibility(View.VISIBLE);
            vv.setVisibility(View.GONE);

            if (information.getId() == 10 || information.getId() == 18) {
                image.setVisibility(View.GONE);
                vv.setVisibility(View.VISIBLE);
                vv.setImageResource(information.getPicture());
                info.setText(information.getInformation());
            }


        } else {
            i++;
            Log.d(TAG, "onClick: line 187 displays index at " + i);
            previous.setVisibility(View.VISIBLE);
            check.setVisibility(View.GONE);
            learnMore.setVisibility(View.GONE);
            constraintSet = new ConstraintSet();
            constraintSet.clone(constraintLayout);
            constraintSet.constrainWidth(R.id.next, width / 2);
            constraintSet.applyTo(constraintLayout);
            information = mInfo.get(i);
            info.setText(information.getInformation());
            image.setImageResource(information.getPicture());
            position.setText(i + 1 + "/" + mInfo.size());
            image.setVisibility(View.VISIBLE);
            vv.setVisibility(View.GONE);

            if (information.getId() == 10 || information.getId() == 18) {
                image.setVisibility(View.GONE);
                vv.setVisibility(View.VISIBLE);
                vv.setImageResource(information.getPicture());
                info.setText(information.getInformation());
            }


            if (i == (mInfo.size() - 1)) {
                i++;
                Log.d(TAG, "onClick: line 201 sets index at " + i);
            }
        }

    }

    private void back() {
        if (i == mInfo.size()) {
            i--;
            Log.d(TAG, "onClick: line 210 displays index at " + i);
            previous.setVisibility(View.VISIBLE);
            check.setVisibility(View.GONE);
            learnMore.setVisibility(View.GONE);
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
            image.setVisibility(View.VISIBLE);
            vv.setVisibility(View.GONE);

            if (information.getId() == 10 || information.getId() == 18) {
                image.setVisibility(View.GONE);
                vv.setVisibility(View.VISIBLE);
                vv.setImageResource(information.getPicture());
                info.setText(information.getInformation());
            }
            image.setVisibility(View.VISIBLE);
            vv.setVisibility(View.GONE);

        } else {
            i--;
            Log.d(TAG, "onClick: line 230 sets index at " + i);
            if (i <= 0) {
                previous.setVisibility(View.GONE);
                check.setVisibility(View.GONE);
                learnMore.setVisibility(View.GONE);
                constraintSet = new ConstraintSet();
                constraintSet.clone(constraintLayout);
                constraintSet.constrainWidth(R.id.next, ConstraintLayout.LayoutParams.MATCH_PARENT);
                constraintSet.applyTo(constraintLayout);
                i = 0;
                Log.d(TAG, "onClick: line 230 displays index at " + i);
                information = mInfo.get(i);
                info.setText(information.getInformation());
                image.setImageResource(information.getPicture());
                position.setText(i + 1 + "/" + mInfo.size());
                image.setVisibility(View.VISIBLE);
                vv.setVisibility(View.GONE);

                if (information.getId() == 10 || information.getId() == 18) {
                    image.setVisibility(View.GONE);
                    vv.setVisibility(View.VISIBLE);
                    vv.setImageResource(information.getPicture());
                    info.setText(information.getInformation());
                }


            } else {
                Log.d(TAG, "onClick: line 245 displays index at " + i);
                previous.setVisibility(View.VISIBLE);
                check.setVisibility(View.GONE);
                learnMore.setVisibility(View.GONE);
                constraintSet = new ConstraintSet();
                constraintSet.clone(constraintLayout);
                constraintSet.constrainWidth(R.id.next, width / 2);
                constraintSet.applyTo(constraintLayout);
                next.setVisibility(View.VISIBLE);
                constraintSet = new ConstraintSet();
                constraintSet.clone(constraintLayout);
                constraintSet.constrainWidth(R.id.previous, width / 2);
                constraintSet.applyTo(constraintLayout);
                information = mInfo.get(i);
                info.setText(information.getInformation());
                image.setImageResource(information.getPicture());
                position.setText(i + 1 + "/" + mInfo.size());
                image.setVisibility(View.VISIBLE);
                vv.setVisibility(View.GONE);

                if (information.getId() == 10 || information.getId() == 18) {
                    image.setVisibility(View.GONE);
                    vv.setVisibility(View.VISIBLE);
                    vv.setImageResource(information.getPicture());
                    info.setText(information.getInformation());

                }


            }
        }
    }



}
