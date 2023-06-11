package com.lev.h071211067_finalmobile.lain;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.lev.h071211067_finalmobile.networking.Favorite;
import com.lev.h071211067_finalmobile.networking.Movie;
import com.lev.h071211067_finalmobile.networking.TVShow;

import java.util.ArrayList;
import java.util.List;

public class Database {
    private DatabaseHelper dbHelper;
    private SQLiteDatabase database;

    public Database(Context context) {
        dbHelper = new DatabaseHelper(context);
    }

    public void open() {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    public void addFavorite(Favorite favorite) {
        ContentValues values = new ContentValues();
        if (favorite.isMovie()) {
            values.put("is_movie", 1);
            values.put("movie_title", favorite.getMovie().getTitle());
        } else if (favorite.isTVShow()) {
            values.put("is_movie", 0);
            values.put("tvshow_title", favorite.getTvShow().getName());
        }
        database.insert("favorites", null, values);
    }

    public void removeFavorite(Favorite favorite) {
        if (favorite.isMovie()) {
            database.delete("favorites", "is_movie = 1 AND movie_title = ?", new String[]{favorite.getMovie().getTitle()});
        } else if (favorite.isTVShow()) {
            database.delete("favorites", "is_movie = 0 AND tvshow_title = ?", new String[]{favorite.getTvShow().getName()});
        }
    }

    public boolean isFavorite(Favorite favorite) {
        Cursor cursor = null;
        if (favorite.isMovie()) {
            cursor = database.query("favorites", null, "is_movie = 1 AND movie_title = ?", new String[]{favorite.getMovie().getTitle()}, null, null, null);
        } else if (favorite.isTVShow()) {
            cursor = database.query("favorites", null, "is_movie = 0 AND tvshow_title = ?", new String[]{favorite.getTvShow().getName()}, null, null, null);
        }
        boolean isFavorite = cursor != null && cursor.getCount() > 0;
        if (cursor != null) {
            cursor.close();
        }
        return isFavorite;
    }

    public List<Favorite> getAllFavorites() {
        List<Favorite> favorites = new ArrayList<>();

        Cursor cursor = database.query("favorites", null, null, null, null, null, null);
        if (cursor != null && cursor.moveToFirst()) {
            int isMovieIndex = cursor.getColumnIndex("is_movie");
            int movieTitleIndex = cursor.getColumnIndex("movie_title");
            int tvShowTitleIndex = cursor.getColumnIndex("tvshow_title");

            do {
                int isMovie = cursor.getInt(isMovieIndex);

                if (isMovie == 1) {
                    String movieTitle = cursor.getString(movieTitleIndex);
                    Movie movie = fetchMovieByTitle(movieTitle);

                    if (movie != null) {
                        Favorite favorite = new Favorite(movie);
                        favorites.add(favorite);
                    }
                } else {
                    String tvShowTitle = cursor.getString(tvShowTitleIndex);
                    TVShow tvShow = fetchTVShowByTitle(tvShowTitle);

                    if (tvShow != null) {
                        Favorite favorite = new Favorite(tvShow);
                        favorites.add(favorite);
                    }
                }
            } while (cursor.moveToNext());
        }

        if (cursor != null) {
            cursor.close();
        }

        return favorites;
    }

    public Movie fetchMovieByTitle(String title) {
        Cursor cursor = database.query(
                "favorites",
                null,
                "is_movie = 1 AND movie_title = ?",
                new String[]{title},
                null,
                null,
                null
        );

        if (cursor != null && cursor.moveToFirst()) {
            Movie movie = createMovieFromCursor(cursor);
            cursor.close();
            return movie;
        }

        return null;
    }

    public TVShow fetchTVShowByTitle(String title) {
        Cursor cursor = database.query(
                "favorites",
                null,
                "is_movie = 0 AND tv_show_title = ?",
                new String[]{title},
                null,
                null,
                null
        );

        if (cursor != null && cursor.moveToFirst()) {
            TVShow tvShow = createTVShowFromCursor(cursor);
            cursor.close();
            return tvShow;
        }

        return null;
    }

    private Movie createMovieFromCursor(Cursor cursor) {
        int idIndex = cursor.getColumnIndex("_id");
        int popularityIndex = cursor.getColumnIndex("movie_popularity");
        int genreIdsIndex = cursor.getColumnIndex("movie_genre_ids");
        int voteAverageIndex = cursor.getColumnIndex("movie_vote_average");
        int voteCountIndex = cursor.getColumnIndex("movie_vote_count");
        int movieTitleIndex = cursor.getColumnIndex("movie_title");
        int overviewIndex = cursor.getColumnIndex("movie_overview");
        int posterPathIndex = cursor.getColumnIndex("movie_poster_path");
        int releaseDateIndex = cursor.getColumnIndex("movie_release_date");
        int backdropPathIndex = cursor.getColumnIndex("movie_backdrop_path");

        int id = cursor.getInt(idIndex);
        double popularity = cursor.getDouble(popularityIndex);
        int[] genreIds = parseGenreIds(cursor.getString(genreIdsIndex));
        double voteAverage = cursor.getDouble(voteAverageIndex);
        int voteCount = cursor.getInt(voteCountIndex);
        String movieTitle = cursor.getString(movieTitleIndex);
        String overview = cursor.getString(overviewIndex);
        String posterPath = cursor.getString(posterPathIndex);
        String releaseDate = cursor.getString(releaseDateIndex);
        String backdropPath = cursor.getString(backdropPathIndex);

        return new Movie(id, popularity, genreIds, voteAverage, voteCount, movieTitle, overview, posterPath, releaseDate, backdropPath);
    }

    private TVShow createTVShowFromCursor(Cursor cursor) {
        int idIndex = cursor.getColumnIndex("_id");
        int popularityIndex = cursor.getColumnIndex("tvshow_popularity");
        int genreIdsIndex = cursor.getColumnIndex("tvshow_genre_ids");
        int voteAverageIndex = cursor.getColumnIndex("tvshow_vote_average");
        int voteCountIndex = cursor.getColumnIndex("tvshow_vote_count");
        int tvShowTitleIndex = cursor.getColumnIndex("tv_show_title");
        int overviewIndex = cursor.getColumnIndex("tvshow_overview");
        int posterPathIndex = cursor.getColumnIndex("tvshow_poster_path");
        int firstAirDateIndex = cursor.getColumnIndex("tvshow_first_air_date");
        int backdropPathIndex = cursor.getColumnIndex("tvshow_backdrop_path");
        int originCountryIndex = cursor.getColumnIndex("tvshow_origin_country");

        int id = cursor.getInt(idIndex);
        double popularity = cursor.getDouble(popularityIndex);
        int[] genreIds = parseGenreIds(cursor.getString(genreIdsIndex));
        double voteAverage = cursor.getDouble(voteAverageIndex);
        int voteCount = cursor.getInt(voteCountIndex);
        String tvShowTitle = cursor.getString(tvShowTitleIndex);
        String overview = cursor.getString(overviewIndex);
        String posterPath = cursor.getString(posterPathIndex);
        String firstAirDate = cursor.getString(firstAirDateIndex);
        String backdropPath = cursor.getString(backdropPathIndex);
        String[] originCountry = parseOriginCountry(cursor.getString(originCountryIndex));

        return new TVShow(id, popularity, genreIds, voteAverage, voteCount, tvShowTitle, overview, posterPath, firstAirDate, backdropPath, originCountry);
    }


    private String[] parseOriginCountry(String originCountryString) {
        if (originCountryString != null && !originCountryString.isEmpty()) {
            return originCountryString.split(",");
        }
        return new String[0]; // Return an empty array if the origin country string is null or empty
    }

    private int[] parseGenreIds(String genreIdsString) {
        if (genreIdsString != null && !genreIdsString.isEmpty()) {
            String[] genreIdsArray = genreIdsString.split(",");
            int[] genreIds = new int[genreIdsArray.length];
            for (int i = 0; i < genreIdsArray.length; i++) {
                genreIds[i] = Integer.parseInt(genreIdsArray[i]);
            }
            return genreIds;
        }
        return new int[0]; // Return an empty array if the genre ids string is null or empty
    }
}
