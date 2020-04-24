package com.example.assignment;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.assignment.Entities.Topic;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    private ArrayList<Topic> mTopics;
    private RecyclerViewClickListener mListener;
    private Context context;
    private Topic mTopic;

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
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.topic_list_row, parent, false);
        context = parent.getContext();
        return new MyViewHolder(v, mListener);

    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        mTopic = mTopics.get(position);
        holder.topic.setText(mTopic.getTopic());
        holder.tick.setImageResource(R.drawable.tick);

    }

    @Override
    public int getItemCount() {
        return mTopics.size();
    }

}

