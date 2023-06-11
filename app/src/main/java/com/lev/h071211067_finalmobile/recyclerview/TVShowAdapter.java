package com.lev.h071211067_finalmobile.recyclerview;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.lev.h071211067_finalmobile.DetailActivity;
import com.lev.h071211067_finalmobile.R;
import com.lev.h071211067_finalmobile.networking.TVShow;

import java.util.ArrayList;
import java.util.List;

public class TVShowAdapter extends RecyclerView.Adapter<TVShowAdapter.TVShowViewHolder> {
    private List<TVShow> tvShows;
    public TVShowAdapter() {
        this.tvShows = new ArrayList<>();
    }
    public void setTVShows(List<TVShow> tvShows) {
        this.tvShows = tvShows;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public TVShowViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_movie, parent, false);
        return new TVShowViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TVShowViewHolder holder, int position) {
        Context context = holder.itemView.getContext();
        TVShow tvShow = tvShows.get(position);
        holder.setData(tvShow, context);
    }

    @Override
    public int getItemCount() {
        return tvShows.size();
    }

    class TVShowViewHolder extends RecyclerView.ViewHolder {
        private ImageView posterImageView;
        private TextView titleTextView;
        private TextView yearTextView;
        private TextView ratingTextView;

        public TVShowViewHolder(@NonNull View itemView) {
            super(itemView);
            posterImageView = itemView.findViewById(R.id.iv_poster);
            titleTextView = itemView.findViewById(R.id.tv_title);
            yearTextView = itemView.findViewById(R.id.tv_year);
            ratingTextView = itemView.findViewById(R.id.tv_rating);
        }

        public void setData(TVShow tvShow, Context context) {
            String title = tvShow.getName();
            String year = tvShow.getFirstAirDate().substring(0,4);
            String poster = "https://image.tmdb.org/t/p/w300_and_h450_bestv2/" + tvShow.getPosterPath();
            String rating = String.valueOf(tvShow.getVoteAverage());
            titleTextView.setText(title);
            yearTextView.setText(year);
            ratingTextView.setText(rating);
            Glide.with(context)
                    .load(poster)
                    .into(posterImageView);
            itemView.setOnClickListener(view ->  {
                Intent intent = new Intent(itemView.getContext(), DetailActivity.class);
                intent.putExtra("tvShow", tvShow);
                itemView.getContext().startActivity(intent);
            });
        }
    }
}

