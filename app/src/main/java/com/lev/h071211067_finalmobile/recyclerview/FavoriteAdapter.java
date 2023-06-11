package com.lev.h071211067_finalmobile.recyclerview;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.lev.h071211067_finalmobile.DetailActivity;
import com.lev.h071211067_finalmobile.R;
import com.lev.h071211067_finalmobile.networking.Favorite;
import com.lev.h071211067_finalmobile.networking.Movie;
import com.lev.h071211067_finalmobile.networking.TVShow;

import java.util.ArrayList;
import java.util.List;

public class FavoriteAdapter extends RecyclerView.Adapter<FavoriteAdapter.FavoriteViewHolder> {
    private List<Favorite> favorites;

    public FavoriteAdapter() {
        this.favorites = new ArrayList<>();
    }

    public void setFavorites(List<Favorite> favorites) {
        this.favorites = favorites;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public FavoriteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_favorite, parent, false);
        return new FavoriteViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FavoriteViewHolder holder, int position) {
        Context context = holder.itemView.getContext();
        Favorite favorite = favorites.get(position);
        holder.setData(favorite, context);
    }

    @Override
    public int getItemCount() {
        return favorites.size();
    }

    static class FavoriteViewHolder extends RecyclerView.ViewHolder {
        private ImageView posterImageView;
        private ImageView typeImageView;
        private TextView titleTextView;
        private TextView tanggalTextView;

        public FavoriteViewHolder(@NonNull View itemView) {
            super(itemView);
            typeImageView = itemView.findViewById(R.id.iv_type);
            posterImageView = itemView.findViewById(R.id.iv_poster);
            titleTextView = itemView.findViewById(R.id.tv_title);
            tanggalTextView = itemView.findViewById(R.id.tvRilisFavorite);
        }

        public void setData(Favorite favorite, Context context) {
            String title;
            String poster;

            if (favorite.isMovie()) {
                Toast.makeText(context, "AAAA", Toast.LENGTH_SHORT).show();
                Movie movie = favorite.getMovie();
                title = movie.getTitle();
                poster = "https://image.tmdb.org/t/p/w300_and_h450_bestv2/" + movie.getPosterPath();
                typeImageView.setImageResource(R.drawable.round_movie_24);
            } else {
                Toast.makeText(context, "BBBBB", Toast.LENGTH_SHORT).show();
                typeImageView.setImageResource(R.drawable.round_tv_24);
                TVShow tvShow = favorite.getTvShow();
                title = tvShow.getName();
                poster = "https://image.tmdb.org/t/p/w300_and_h450_bestv2/" + tvShow.getPosterPath();
            }

            titleTextView.setText(title);

            Glide.with(context)
                    .load(poster)
                    .into(posterImageView);

            itemView.setOnClickListener(view -> {
                Intent intent = new Intent(itemView.getContext(), DetailActivity.class);
                intent.putExtra("favorite", favorite);
                itemView.getContext().startActivity(intent);
            });

        }
    }
}
