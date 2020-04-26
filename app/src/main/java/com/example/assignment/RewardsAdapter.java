package com.example.assignment;

import android.content.Context;
import android.graphics.Color;
import android.os.AsyncTask;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import com.example.assignment.Entities.AccountAchievement;
import com.example.assignment.Entities.Rewards;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;

import java.util.ArrayList;
import java.util.List;

//adapter class to display all the rewards in a recycler view
public class RewardsAdapter extends RecyclerView.Adapter<RewardsAdapter.MyViewHolder> {
    private ArrayList<Rewards> mRewards;
    private List<AccountAchievement> mAccountAchievements = new ArrayList<>();
    private Rewards mReward;
    private AccountAchievement mAccAch;
    private int mStars, mTotal;
    private RecyclerViewClickListener mListener;
    private Context context;
    MyDatabase myDb;
    private String email;
    private static final String TAG = "MyAdapter";
    private boolean achieved;
    private boolean redeemed;

    public RewardsAdapter(ArrayList<Rewards> rewards, List<AccountAchievement> accountAchievements, RecyclerViewClickListener listener) {
        mRewards = rewards;
        mAccountAchievements = accountAchievements;
        mListener = listener;
    }

    public interface RecyclerViewClickListener {
        void onClick(View view, int position);
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView name, achieved, redeemed, star;
        private RecyclerViewClickListener mListener;

        public MyViewHolder(View v, RecyclerViewClickListener listener) {
            super(v);
            mListener = listener;
            v.setOnClickListener(this);
            name = v.findViewById(R.id.name);
            achieved = v.findViewById(R.id.achieved);
            star = v.findViewById(R.id.star);
            redeemed = v.findViewById(R.id.redeemed);

        }

        @Override
        public void onClick(View view) {
            mListener.onClick(view, getAdapterPosition());
        }
    }

    @Override
    public RewardsAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.rewards_list_row, parent, false);
        return new MyViewHolder(v, mListener);

    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        GoogleSignInAccount acct = GoogleSignIn.getLastSignedInAccount(context);
        if (acct != null) {
            email = acct.getEmail();
        }

        mReward = mRewards.get(position);
        holder.name.setText(mReward.getName());
        holder.star.setText(String.valueOf(mReward.getCondition()));

        myDb = Room.databaseBuilder(context, MyDatabase.class, "my-db.db")
                .allowMainThreadQueries()
                .build();
        achieved = myDb.accountAchievementDao().getAchieved(email, mRewards.get(position).getId());
        redeemed = myDb.accountAchievementDao().getRedeemed(email, mRewards.get(position).getId());

        //sets text as green if the reward is achieved
        if (achieved == true) {
            holder.achieved.setText("Reward achieved");
            holder.achieved.setTextColor(Color.parseColor("#8BBC64"));
            if (redeemed == true) {
                holder.redeemed.setText("Reward redeemed");
            } else {
                holder.redeemed.setText("Reward not redeemed yet");
            }
            //sets text as blue if the reward isn't achieved yet
        } else if (achieved == false) {
            holder.achieved.setText("Reward not achieved yet");
            holder.achieved.setTextColor(Color.parseColor("#2886E2"));
            holder.redeemed.setText("");
        }

        myDb.close();

    }


    @Override
    public int getItemCount() {
        return mRewards.size();
    }


}

