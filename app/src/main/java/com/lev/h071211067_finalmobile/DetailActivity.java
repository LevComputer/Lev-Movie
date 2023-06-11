package com.lev.h071211067_finalmobile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.lev.h071211067_finalmobile.lain.Database;
import com.lev.h071211067_finalmobile.networking.Favorite;
import com.lev.h071211067_finalmobile.networking.Movie;
import com.lev.h071211067_finalmobile.networking.TVShow;

public class DetailActivity extends AppCompatActivity {
    private ImageView backdropImageView, backButton, favoriteButton, posterImageView, typeImageView;
    private TextView titleTextView, ratingTextView, synopsisTextView;
    boolean isFavorite = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Database database = new Database(this);
        typeImageView = findViewById(R.id.iv_type);
        backdropImageView = findViewById(R.id.iv_wallpaper);
        backButton = findViewById(R.id.btn_back);
        favoriteButton = findViewById(R.id.btn_favorite);
        posterImageView = findViewById(R.id.iv_poster);
        titleTextView = findViewById(R.id.tv_title);
        ratingTextView = findViewById(R.id.tv_rating);
        synopsisTextView = findViewById(R.id.tv_synopsis);
        database.open();

        Intent intent = getIntent();
        if (intent.getParcelableExtra("test") != null) {
            Movie movie = intent.getParcelableExtra("test");
            Favorite favorite = new Favorite(movie);
            String posterUrl = "https://image.tmdb.org/t/p/w300_and_h450_bestv2/" + movie.getPosterPath();
            String backdropUrl = "https://image.tmdb.org/t/p/w300_and_h450_bestv2/" + movie.getBackdropPath();
            titleTextView.setText(movie.getTitle());
            ratingTextView.setText(String.valueOf(movie.getVoteAverage()));
            synopsisTextView.setText(movie.getOverview());
            typeImageView.setImageResource(R.drawable.round_movie_24);
            Glide.with(this).load(posterUrl).into(posterImageView);
            Glide.with(this).load(backdropUrl).into(backdropImageView);

            if (database.isFavorite(favorite)) {
                isFavorite = true;
                favoriteButton.setImageResource(R.drawable.ic_favorite_true);
            } else {
                isFavorite = false;
                favoriteButton.setImageResource(R.drawable.ic_favorite_false);
            }

            favoriteButton.setOnClickListener(view -> {
                if (!isFavorite) {
                    database.addFavorite(favorite);
                    favoriteButton.setImageResource(R.drawable.ic_favorite_true);
                    isFavorite = !isFavorite;
                    Toast.makeText(this, "added to Favorite", Toast.LENGTH_SHORT).show();
                } else {
                    database.removeFavorite(favorite);
                    favoriteButton.setImageResource(R.drawable.ic_favorite_false);
                    isFavorite = !isFavorite;
                    Toast.makeText(this, "delete to Favorite", Toast.LENGTH_SHORT).show();
                }
            });
        }
        else if (intent.getParcelableExtra("tvShow") != null) {
            TVShow show = intent.getParcelableExtra("tvShow");
            Favorite favorite = new Favorite(show);
            String posterUrl = "https://image.tmdb.org/t/p/w300_and_h450_bestv2/" + favorite.getTvShow().getPosterPath();
            String backdropUrl = "https://image.tmdb.org/t/p/w300_and_h450_bestv2/" + favorite.getTvShow().getBackdropPath();
            titleTextView.setText(favorite.getTvShow().getName());
            ratingTextView.setText(String.valueOf(favorite.getTvShow().getVoteAverage()));
            synopsisTextView.setText(favorite.getTvShow().getOverview());
            typeImageView.setImageResource(R.drawable.round_tv_24);
            Glide.with(this).load(posterUrl).into(posterImageView);
            Glide.with(this).load(backdropUrl).into(backdropImageView);
            if (database.isFavorite(favorite)) {
                isFavorite = true;
                favoriteButton.setImageResource(R.drawable.ic_favorite_true);
            } else {
                isFavorite = false;
                favoriteButton.setImageResource(R.drawable.ic_favorite_false);
            }

            favoriteButton.setOnClickListener(view -> {
                if (!isFavorite) {
                    database.addFavorite(favorite);
                    favoriteButton.setImageResource(R.drawable.ic_favorite_true);
                    isFavorite = !isFavorite;
                    Toast.makeText(this, "added to Favorite", Toast.LENGTH_SHORT).show();
                } else {
                    database.removeFavorite(favorite);
                    favoriteButton.setImageResource(R.drawable.ic_favorite_false);
                    isFavorite = !isFavorite;
                    Toast.makeText(this, "delete to Favorite", Toast.LENGTH_SHORT).show();
                }
            });
        } else if (intent.getParcelableExtra("favorite") != null) {
            Favorite favorite = intent.getParcelableExtra("favorite");
            if (favorite.isMovie()) {
                String posterUrl = "https://image.tmdb.org/t/p/w300_and_h450_bestv2/" + favorite.getMovie().getPosterPath();
                String backdropUrl = "https://image.tmdb.org/t/p/w300_and_h450_bestv2/" + favorite.getMovie().getBackdropPath();
                titleTextView.setText(favorite.getMovie().getTitle());
                ratingTextView.setText(String.valueOf(favorite.getMovie().getVoteAverage()));
                synopsisTextView.setText(favorite.getMovie().getOverview());
                typeImageView.setImageResource(R.drawable.round_movie_24);
                Glide.with(this).load(posterUrl).into(posterImageView);
                Glide.with(this).load(backdropUrl).into(backdropImageView);
            } else {
                String posterUrl = "https://image.tmdb.org/t/p/w300_and_h450_bestv2/" + favorite.getTvShow().getPosterPath();
                String backdropUrl = "https://image.tmdb.org/t/p/w300_and_h450_bestv2/" + favorite.getTvShow().getBackdropPath();
                titleTextView.setText(favorite.getTvShow().getName());
                ratingTextView.setText(String.valueOf(favorite.getTvShow().getVoteAverage()));
                synopsisTextView.setText(favorite.getTvShow().getOverview());
                typeImageView.setImageResource(R.drawable.round_tv_24);
                Glide.with(this).load(posterUrl).into(posterImageView);
                Glide.with(this).load(backdropUrl).into(backdropImageView);
            }
            if (database.isFavorite(favorite)) {
                isFavorite = true;
                favoriteButton.setImageResource(R.drawable.ic_favorite_true);
            } else {
                isFavorite = false;
                favoriteButton.setImageResource(R.drawable.ic_favorite_false);
            }

            favoriteButton.setOnClickListener(view -> {
                if (!isFavorite) {
                    database.addFavorite(favorite);
                    favoriteButton.setImageResource(R.drawable.ic_favorite_true);
                    isFavorite = !isFavorite;
                    Toast.makeText(this, "added to Favorite", Toast.LENGTH_SHORT).show();
                } else {
                    database.removeFavorite(favorite);
                    favoriteButton.setImageResource(R.drawable.ic_favorite_false);
                    isFavorite = !isFavorite;
                    Toast.makeText(this, "delete to Favorite", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}