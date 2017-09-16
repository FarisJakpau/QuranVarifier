package com.example.farisjakpau.quranvarifier;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by FarisJakpau on 9/17/2017.
 */

public class feedAdapter extends RecyclerView.Adapter<feedAdapter.feedViewHolder>{

    List<FeedHolder> data;
    LayoutInflater inflater;
    FeedHolder current;
    Context context;

    public feedAdapter(List<FeedHolder> data , Context context) {
        this.data = data;
        this.context = context;
    }

    @Override
    public feedViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.from(parent.getContext()).inflate(R.layout.feed_view,parent,false);
        return new feedViewHolder(view);
    }

    @Override
    public void onBindViewHolder(feedViewHolder holder, int position) {

        current = data.get(position);

        holder.description.setText(current.getDescription());
        holder.message.setText(current.getMessage());
        Picasso.with(context).load(current.getDate()).into(holder.imageView);
//        holder.date.setText(current.getDate());
        holder.position = position;
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class feedViewHolder extends RecyclerView.ViewHolder{

        TextView message , description , date;
        int position;
        ImageView imageView;

        public feedViewHolder(View itemView) {
            super(itemView);
            message = (TextView)itemView.findViewById(R.id.message_feed);
            description = (TextView)itemView.findViewById(R.id.description_feed);
            imageView = (ImageView)itemView.findViewById(R.id.image_feed);
//            date = (TextView)itemView.findViewById(R.id.date_feed);
        }
    }
}
