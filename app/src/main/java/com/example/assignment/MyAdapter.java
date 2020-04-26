package com.example.assignment;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import com.example.assignment.Entities.Question;
import com.example.assignment.Entities.Topic;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;

import java.util.ArrayList;

//this adapter class displays a list of topics with the difficulty the user has chosen in the previous screen
//it will display a green tick if they have finished the whole course (after the knowledge check) and it will also display total stars earnt
//in that course
public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    private ArrayList<Topic> mTopics;
    private int mStars;
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
        public TextView topic, star;
        public ImageView tick, img;
        private RecyclerViewClickListener mListener;

        public MyViewHolder(View v, RecyclerViewClickListener listener) {
            super(v);
            mListener = listener;
            v.setOnClickListener(this);
            topic = v.findViewById(R.id.topic);
            tick = v.findViewById(R.id.tick);
            star = v.findViewById(R.id.star);
            img = v.findViewById(R.id.img);

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

        //gets whether the user has viewed (finished) the topic and displays a green tick
        viewed = myDb.topicResultDao().getViewed(email, mTopic.getId());
        //displays the total stars the user has earnt from each topic
        mStars = myDb.topicResultDao().getStars(email, mTopic.getId());

        int[] totalQuestions = new int[mTopics.get(mTopics.size() - 1).getId() + 1];
        Log.d(TAG, "onBindViewHolder: testing whether the array is correct size " + totalQuestions.length);

        for (Question q : Question.getQuestions()) {
            if (q.getTopicId() == mTopic.getId()) {
                totalQuestions[mTopic.getId() - 1]++;
            }
        }

        if (viewed == true) {
            holder.tick.setImageResource(R.drawable.tick);
            holder.img.setImageResource(R.drawable.star);
            holder.star.setText(mStars + "/" + totalQuestions[mTopic.getId() - 1] + " stars");
            System.out.println("Viewed method");
            System.out.println(totalQuestions[mTopics.get(position).getId()]);
        } else {
            holder.tick.setImageResource(android.R.color.transparent);
            holder.img.setImageResource(android.R.color.transparent);
            holder.star.setText("");
        }

        myDb.close();

    }


    @Override
    public int getItemCount() {
        return mTopics.size();
    }


}

