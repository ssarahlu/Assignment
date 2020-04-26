package com.example.assignment;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.assignment.Entities.AccountAchievement;
import com.example.assignment.Entities.Rewards;
import com.example.assignment.Entities.Topic;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

//this class displays the rewards in a recylcerview
//if the user has earnt the reward (met the condition), it display text in green saying the reward has been achieved
public class RewardActivity extends AppCompatActivity {
    BottomNavigationView bottomNavigation;
    private RecyclerView mRecyclerView;
    private String email;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private ArrayList<Rewards> mRewards = new ArrayList<>();
    private List<AccountAchievement> mAccAchs = new ArrayList<>();
    MyDatabase myDb;
    private int mStars;
    private static final String TAG = "RewardActivity";
    private int achievementId;
    private TextView totalStars;
    RewardsAdapter.RecyclerViewClickListener listener;
    private int rewardId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reward);
        setTitle("My Rewards");
        GoogleSignInAccount acct = GoogleSignIn.getLastSignedInAccount(getApplicationContext());
        if (acct != null) {
            email = acct.getEmail();
        }
        new GetAccountAchievements().execute();
        new GetTotalStars().execute();
        totalStars = findViewById(R.id.stars);
        mRecyclerView = findViewById(R.id.rvList);
        mRecyclerView.setHasFixedSize(true);

        //bottom nav bar
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

        for (Rewards r : Rewards.getRewards()) {
            mRewards.add(r);
        }


        new InsertAccountAchievements().execute();

        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        listener = new RewardsAdapter.RecyclerViewClickListener() {
            public void onClick(View view, int position) {
                Rewards r = mRewards.get(position);
                rewardId = r.getId();
                launchRewardDetail();
                Toast.makeText(getApplicationContext(), r.getName() + " is selected!", Toast.LENGTH_SHORT).show();
            }
        };
        mAdapter = new RewardsAdapter(mRewards, mAccAchs, listener);
        mRecyclerView.setAdapter(mAdapter);
    }

    //on click, the user can see the reward details
    // E.g. it will show the QR code if they have achieved the reward (voucher)
    private void launchRewardDetail() {
        Intent intent = new Intent(this, RewardDetailActivity.class);
        intent.putExtra("id", String.valueOf(rewardId));
        System.out.println(rewardId);
        intent.putExtra("email", email);
        System.out.println(email);
        startActivity(intent);

    }

    //gets all the different account achievements of the user so that we can display the rewards accordingly
    //e.g. user will not be able to see the QR codes of rewards they haven't received yet
    private class GetAccountAchievements extends AsyncTask<Void, Void, Void> {
        @Override
        protected Void doInBackground(Void... voids) {
            myDb = Room.databaseBuilder(getApplicationContext(), MyDatabase.class, "my-db.db")
                    .build();
            mAccAchs = myDb.accountAchievementDao().getAccAch(email);
            Log.d(TAG, "doInBackground: ASYNC TASK  ");
            return null;
        }

        @Override
        protected void onPostExecute(Void v) {
            super.onPostExecute(v);
            Log.d(TAG, "onPostExecute: FINISHED");

        }

    }


    //gets total stars a user has earnt from knowledge checks
    private class GetTotalStars extends AsyncTask<Void, Void, Void> {
        @Override
        protected Void doInBackground(Void... voids) {
            myDb = Room.databaseBuilder(getApplicationContext(), MyDatabase.class, "my-db.db")
                    .build();
            mStars = myDb.topicResultDao().getTotalStars(email);
            Log.d(TAG, "doInBackground: Current stars:  " + mStars);
            return null;
        }

        @Override
        protected void onPostExecute(Void v) {
            super.onPostExecute(v);
            Log.d(TAG, "onPostExecute: FINISHED");

        }

    }

    //inserts a new achievement if the user has met the condition (e.g. over 20 stars earnt)
    private class InsertAccountAchievements extends AsyncTask<Void, Void, Void> {
        @Override
        protected Void doInBackground(Void... voids) {
            myDb = Room.databaseBuilder(getApplicationContext(), MyDatabase.class, "my-db.db")
                    .build();

            for (Rewards r : mRewards) {
                if (r.getCondition() < mStars) {
                    achievementId = r.getId();
                    mAccAchs.add(new AccountAchievement(email, achievementId, true, false));
                    myDb.accountAchievementDao().insertSingle(email, achievementId, true, false);
                }
            }
            for (AccountAchievement a : mAccAchs) {
                myDb.accountAchievementDao().insertSingle(a.getEmail(), a.getAchievementId(), a.isAchieved(), a.isRedeemed());
            }
            Log.d(TAG, "doInBackground: ASYNC TASK  ");
            return null;
        }

        @Override
        protected void onPostExecute(Void v) {
            super.onPostExecute(v);
            updateUi();
            Log.d(TAG, "onPostExecute: FINISHED");
            mAdapter = new RewardsAdapter(mRewards, mAccAchs, listener);
            mRecyclerView.setAdapter(mAdapter);
            mAdapter.notifyDataSetChanged();
            myDb.close();

        }

    }

    private void updateUi() {
        totalStars.setText("My total stars: " + String.valueOf(mStars));


    }

}
