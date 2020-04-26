package com.example.assignment;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.assignment.Entities.AccountAchievement;
import com.example.assignment.Entities.Rewards;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

public class RewardDetailActivity extends AppCompatActivity {
    BottomNavigationView bottomNavigation;
    private int rewardId = 0;
    private Rewards reward;
    private String email, id;
    MyDatabase myDb;
    private boolean redeemed, achieved;
    private List<AccountAchievement> mAccAchs = new ArrayList<>();
    private ArrayList<Rewards> mRewards = new ArrayList<>();
    private static final String TAG = "RewardDetailActivity";
    private TextView name, expiry;
    private Button redeem;
    private ImageView image;
    private ImageButton cancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTitle("My Rewards");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reward_detail);
        name = findViewById(R.id.name);
        redeem = findViewById(R.id.redeem);
        image = findViewById(R.id.image);
        cancel = findViewById(R.id.cancel);
        expiry = findViewById(R.id.expiry);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Intent intent = getIntent();
        id = intent.getStringExtra("id");
        System.out.println(id);
        rewardId = Integer.valueOf(id);
        System.out.println(rewardId);
        email = intent.getStringExtra("email");
        mRewards = Rewards.getRewards();
        for (Rewards r : mRewards) {
            if (r.getId() == rewardId) {
                reward = r;
            }
        }
        redeem.setVisibility(View.GONE);
        new GetAccountAchievements().execute();

        bottomNavigation = findViewById(R.id.navigation);
        bottomNavigation.setItemIconTintList(null);
        bottomNavigation.setItemTextColor(null);
        bottomNavigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Intent intent;
                switch (item.getItemId()) {
                    case R.id.learning:
                        intent = new Intent(getApplicationContext(), SelectDifficulty.class);
                        startActivity(intent);
                        return true;
                    case R.id.quiz:
                        intent = new Intent(getApplicationContext(), QuizActivity.class);
                        startActivity(intent);
                        return true;
                    case R.id.leaderboard:
                        intent = new Intent(getApplicationContext(), LeaderboardActivity.class);
                        startActivity(intent);
                        return true;
                    case R.id.profile:
                        intent = new Intent(getApplicationContext(), ProfileActivity.class);
                        startActivity(intent);
                        return true;
                }
                return false;
            }
        });


        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), RewardActivity.class);
                startActivity(intent);
            }
        });

        redeem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new UpdateRedemption().execute();
                Toast.makeText(getApplicationContext(), "Your voucher has been redeemed!", Toast.LENGTH_SHORT).show();
                redeem.setVisibility(View.GONE);
                image.setImageResource(R.drawable.redeem_success);
                expiry.setVisibility(View.GONE);

            }
        });


    }

    private class GetAccountAchievements extends AsyncTask<Void, Void, Void> {
        @Override
        protected Void doInBackground(Void... voids) {
            myDb = Room.databaseBuilder(getApplicationContext(), MyDatabase.class, "my-db.db")
                    .build();
            mAccAchs = myDb.accountAchievementDao().getAccAch(email);
            redeemed = myDb.accountAchievementDao().getRedeemed(email, rewardId);
            Log.d(TAG, "doInBackground: redeemed " + redeemed);
            achieved = myDb.accountAchievementDao().getAchieved(email, rewardId);
            Log.d(TAG, "doInBackground: achieved " + achieved);

            Log.d(TAG, "doInBackground: ASYNC TASK  ");
            return null;
        }

        @Override
        protected void onPostExecute(Void v) {
            super.onPostExecute(v);
            Log.d(TAG, "onPostExecute: FINISHED");
            updateUi();
            myDb.close();

        }

    }


    private void updateUi() {
        if (achieved == false) {
            name.setText(reward.getName());
            redeem.setVisibility(View.GONE);
            expiry.setVisibility(View.GONE);
            image.setImageResource(R.drawable.reward_unavailable);

        } else if ((achieved == true) && (redeemed == false)) {
            name.setText(reward.getName());
            redeem.setVisibility(View.VISIBLE);
            expiry.setVisibility(View.VISIBLE);
            image.setImageResource(reward.getPhoto());

        } else if ((achieved == true) && (redeemed == true)) {
            name.setText(reward.getName());
            expiry.setVisibility(View.GONE);
            redeem.setVisibility(View.GONE);
            image.setImageResource(R.drawable.reward_redeemed);

        }


    }

    private class UpdateRedemption extends AsyncTask<Void, Void, Void> {
        @Override
        protected Void doInBackground(Void... voids) {
            myDb = Room.databaseBuilder(getApplicationContext(), MyDatabase.class, "my-db.db")
                    .build();
            myDb.accountAchievementDao().updateRedeemed(true, email, rewardId);
            Log.d(TAG, "doInBackground: ASYNC TASK  ");
            return null;
        }

        @Override
        protected void onPostExecute(Void v) {
            super.onPostExecute(v);
            Log.d(TAG, "onPostExecute: FINISHED");
            myDb.close();

        }

    }

    //added back button in the toolbar
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent myIntent = new Intent(getApplicationContext(), RewardActivity.class);
        startActivityForResult(myIntent, 0);
        return true;
    }


}
