package com.lev.h071211067_finalmobile.networking;

import android.os.Parcel;
import android.os.Parcelable;

public class Favorite implements Parcelable {
    private Movie movie;
    private TVShow tvShow;

    public Favorite(Movie movie) {
        this.movie = movie;
    }
    public Favorite(TVShow tvShow) {
        this.tvShow = tvShow;
    }
    protected Favorite(Parcel in) {
        movie = in.readParcelable(Movie.class.getClassLoader());
        tvShow = in.readParcelable(TVShow.class.getClassLoader());
    }
    public static final Creator<Favorite> CREATOR = new Creator<Favorite>() {
        @Override
        public Favorite createFromParcel(Parcel in) {
            return new Favorite(in);
        }

        @Override
        public Favorite[] newArray(int size) {
            return new Favorite[size];
        }
    };

    public Movie getMovie() {
        return movie;
    }

    public TVShow getTvShow() {
        return tvShow;
    }

    public boolean isMovie() {
        return movie != null;
    }

    public boolean isTVShow() {
        return tvShow != null;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(movie, flags);
        dest.writeParcelable(tvShow, flags);
    }

    @Override
    public int describeContents() {
        return 0;
    }
}

