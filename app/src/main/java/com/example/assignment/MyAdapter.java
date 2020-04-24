package com.example.assignment;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import com.bumptech.glide.Glide;
import com.example.assignment.Entities.Account;
import com.example.assignment.Entities.Topic;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    private ArrayList<Topic> mTopics;
    private RecyclerViewClickListener mListener;
    private Context context;
    private Topic mTopic;
    MyDatabase myDb;
    private String email;
    private static final String TAG = "MyAdapter";
    private boolean viewed = false;

    public MyAdapter(ArrayList<Topic> topics, RecyclerViewClickListener listener) {
        mTopics = topics;
        mListener = listener;
    }

    public interface RecyclerViewClickListener {
        void onClick(View view, int position);
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView topic;
        public ImageView tick;
        private RecyclerViewClickListener mListener;

        public MyViewHolder(View v, RecyclerViewClickListener listener) {
            super(v);
            mListener = listener;
            v.setOnClickListener(this);
            topic = v.findViewById(R.id.topic);
            tick = v.findViewById(R.id.tick);

        }

        @Override
        public void onClick(View view) {
            mListener.onClick(view, getAdapterPosition());
        }
    }

    @Override
    public MyAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.topic_list_row, parent, false);
        return new MyViewHolder(v, mListener);

    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        GoogleSignInAccount acct = GoogleSignIn.getLastSignedInAccount(context);
        if (acct != null) {
            email = acct.getEmail();
        }
        mTopic = mTopics.get(position);
        holder.topic.setText(mTopic.getTopic());

        myDb = Room.databaseBuilder(context, MyDatabase.class, "my-db.db")
                .allowMainThreadQueries()
                .build();

        viewed = myDb.topicResultDao().getViewed(email, mTopic.getId());

        if (viewed == true) {
            holder.tick.setImageResource(R.drawable.tick);
        } else {
            holder.tick.setImageResource(R.drawable.cancel);
        }

    }

    @Override
    public int getItemCount() {
        return mTopics.size();
    }


}

