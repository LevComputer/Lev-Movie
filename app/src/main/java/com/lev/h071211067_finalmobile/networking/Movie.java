package com.lev.h071211067_finalmobile.networking;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

public class Movie implements Parcelable {
    @SerializedName("id") private int id;
    @SerializedName("popularity") private int popularity;
    @SerializedName("genre_ids") private int[] genreIds;
    @SerializedName("vote_average") private int voteAverage;
    @SerializedName("vote_count") private int voteCount;
    @SerializedName("title") private String title;
    @SerializedName("overview") private String overview;
    @SerializedName("poster_path") private String posterPath;
    @SerializedName("release_date") private String releaseDate;
    @SerializedName("backdrop_path") private String backdropPath;

//    Constructor
    public Movie(int id, int popularity, int[] genreIds, int voteAverage, int voteCount, String title, String overview, String posterPath, String releaseDate, String backdropPath) {
        this.id = id;
        this.popularity = popularity;
        this.genreIds = genreIds;
        this.voteAverage = voteAverage;
        this.voteCount = voteCount;
        this.title = title;
        this.overview = overview;
        this.posterPath = posterPath;
        this.releaseDate = releaseDate;
        this.backdropPath = backdropPath;
    }

    protected Movie(Parcel in) {
        id = in.readInt();
        popularity = in.readInt();
        genreIds = in.createIntArray();
        voteAverage = in.readInt();
        voteCount = in.readInt();
        title = in.readString();
        overview = in.readString();
        posterPath = in.readString();
        releaseDate = in.readString();
        backdropPath = in.readString();
    }

    public static final Creator<Movie> CREATOR = new Creator<Movie>() {
        @Override
        public Movie createFromParcel(Parcel in) {
            return new Movie(in);
        }

        @Override
        public Movie[] newArray(int size) {
            return new Movie[size];
        }
    };

    //    Getter
    public int getId() {
        return id;
    }
    public int getPopularity() {
        return popularity;
    }
    public int[] getGenreIds() {
        return genreIds;
    }
    public int getVoteAverage() {
        return voteAverage;
    }
    public int getVoteCount() {
        return voteCount;
    }
    public String getTitle() {
        return title;
    }
    public String getOverview() {
        return overview;
    }
    public String getPosterPath() {
        return posterPath;
    }
    public String getReleaseDate() {
        return releaseDate;
    }
    public String getBackdropPath() {
        return backdropPath;
    }

//    Setter
    public void setId(int id) {
        this.id = id;
    }
    public void setPopularity(int popularity) {
        this.popularity = popularity;
    }
    public void setGenreIds(int[] genreIds) {
        this.genreIds = genreIds;
    }
    public void setVoteAverage(int voteAverage) {
        this.voteAverage = voteAverage;
    }
    public void setVoteCount(int voteCount) {
        this.voteCount = voteCount;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public void setOverview(String overview) {
        this.overview = overview;
    }
    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
    }
    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }
    public void setBackdropPath(String backdropPath) {
        this.backdropPath = backdropPath;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeInt(popularity);
        dest.writeIntArray(genreIds);
        dest.writeInt(voteAverage);
        dest.writeInt(voteCount);
        dest.writeString(title);
        dest.writeString(overview);
        dest.writeString(posterPath);
        dest.writeString(releaseDate);
        dest.writeString(backdropPath);
    }
}
