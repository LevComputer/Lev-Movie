package com.lev.h071211067_finalmobile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.lev.h071211067_finalmobile.lain.DatabaseContract;
import com.lev.h071211067_finalmobile.lain.DatabaseHelper;
import com.lev.h071211067_finalmobile.networking.Favorite;
import com.lev.h071211067_finalmobile.networking.Movie;
import com.lev.h071211067_finalmobile.networking.TVShow;

public class DetailActivity extends AppCompatActivity {
    private DatabaseHelper dbHelper;
    private ImageView backdropImageView, backButton, favoriteButton, posterImageView, typeImageView;
    private TextView titleTextView, ratingTextView, synopsisTextView, releaseTextView;
    boolean favorite = false;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        typeImageView = findViewById(R.id.iv_type);
        backdropImageView = findViewById(R.id.iv_wallpaper);
        backButton = findViewById(R.id.btn_back);
        favoriteButton = findViewById(R.id.btn_favorite);
        posterImageView = findViewById(R.id.iv_poster);
        titleTextView = findViewById(R.id.tv_title);
        ratingTextView = findViewById(R.id.tv_rating);
        synopsisTextView = findViewById(R.id.tv_synopsis);
        releaseTextView = findViewById(R.id.tv_release_date);
        dbHelper = new DatabaseHelper(this);

        Intent intent = getIntent();
        if (intent.getParcelableExtra("movie") != null) {
            Movie movie = intent.getParcelableExtra("movie");
            handleFilmDetails(movie.getTitle(), String.valueOf(movie.getVoteAverage()), movie.getOverview(), movie.getPosterPath(), movie.getBackdropUrl(), movie.getId(), movie.getReleaseDate(), R.drawable.ic_movie);
        } else if (intent.getParcelableExtra("tvShow") != null) {
            TVShow show = intent.getParcelableExtra("tvShow");
            handleFilmDetails(show.getName(), String.valueOf(show.getVoteAverage()), show.getOverview(), show.getPosterPath(), show.getBackdropPath(), show.getId(), show.getName(), R.drawable.ic_tv);
        } else if (intent.getParcelableExtra("favorite") != null) {
            Favorite favorite = intent.getParcelableExtra("favorite");

            handleFilmDetails(favorite.getTitle(), String.valueOf(favorite.getVoteAverage()), favorite.getOverview(), favorite.getPosterPath(), favorite.getBackdropPath(), favorite.getId(), favorite.getReleaseDate(), R.drawable.ic_favorite_true);
        }

        backButton.setOnClickListener(view -> {
            onBackPressed();
        });
    }

    private void handleFilmDetails(String title, String voteAverage, String overview, String posterPath, String backdropPath, int id, String releaseDate, int type) {

//        String[] tanggalArray = releaseDate.split("-");
//        String tahun = tanggalArray[0];
//        String bulan = takeMonth(tanggalArray[1]);
//        String tanggal = tanggalArray[2];

        String posterUrl = "https://image.tmdb.org/t/p/w300_and_h450_bestv2/" + posterPath;
        String backdropUrl = "https://image.tmdb.org/t/p/w300_and_h450_bestv2/" + backdropPath;
        titleTextView.setText(title);
        ratingTextView.setText(voteAverage);
        releaseTextView.setText(releaseDate);
        Glide.with(this).load(posterUrl).into(posterImageView);
        Glide.with(this).load(backdropUrl).into(backdropImageView);
        synopsisTextView.setText(overview);
        typeImageView.setImageResource(type);

        favoriteButton.setOnClickListener(view -> {
            if (!dbHelper.isMovieInFavorites(title)) {
                favoriteButton.setImageResource(R.drawable.ic_favorite_true);
                favorite = true;
                addMovieToFavorites(id, overview, posterUrl, releaseDate, title, Double.parseDouble(voteAverage), backdropUrl);
            } else {
                favoriteButton.setImageResource(R.drawable.ic_favorite_false);
                favorite = false;
                deleteMovieFromFavorites(title);
            }
        });

        if (dbHelper.isMovieInFavorites(title)) {
            favoriteButton.setImageResource(R.drawable.ic_favorite_true);
        } else {
            favoriteButton.setImageResource(R.drawable.ic_favorite_false);
        }

    }

//    private String takeMonth(String monthDate) {
//        String formattedMonth = "";
//        switch (monthDate) {
//            case "01":
//                formattedMonth = "January";
//                break;
//            case "02":
//                formattedMonth = "February";
//                break;
//            case "03":
//                formattedMonth = "Maret";
//                break;
//            case "04":
//                formattedMonth = "April";
//                break;
//            case "05":
//                formattedMonth = "May";
//                break;
//            case "06":
//                formattedMonth = "June";
//                break;
//            case "07":
//                formattedMonth = "July";
//                break;
//            case "08":
//                formattedMonth = "August";
//                break;
//            case "09":
//                formattedMonth = "September";
//                break;
//            case "10":
//                formattedMonth = "October";
//                break;
//            case "11":
//                formattedMonth = "November";
//                break;
//            case "12":
//                formattedMonth = "December";
//                break;
//
//        }
//        return formattedMonth;
//    }

    private void addMovieToFavorites(int id, String overview, String posterUrl, String releaseDate, String title, double voteAverage, String backdropUrl) {
        Movie movie = new Movie(id, overview, posterUrl, releaseDate, title, voteAverage, backdropUrl);
        long result = dbHelper.insertMovie(movie);
        if (result != -1) {
            Toast.makeText(this, "Success added to favorites", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Failed to add to favorites", Toast.LENGTH_SHORT).show();
        }
    }

    private void deleteMovieFromFavorites(String nama) {
        long result = dbHelper.deleteMovie(nama);
        if (result != -1) {
            Toast.makeText(this, "Success deleted from favorites", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Failed to delete from favorites", Toast.LENGTH_SHORT).show();
        }
    }
}