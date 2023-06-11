package com.lev.h071211067_finalmobile.networking;

import com.google.gson.annotations.SerializedName;

public class TVShow {
    @SerializedName("id") private int id;
    @SerializedName("popularity") private int popularity;
    @SerializedName("genre_ids") private int genreIds;
    @SerializedName("vote_average") private int voteAverage;
    @SerializedName("vote_count") private int voteCount;
    @SerializedName("name") private String name;
    @SerializedName("overview") private String overview;
    @SerializedName("poster_path") private String posterPath;
    @SerializedName("first_air_date") private String firstAirDate;
    @SerializedName("backdrop_path") private String backdropPath;
    @SerializedName("origin_country") private String originCountry;

//    Constructor
    public TVShow(int id, int popularity, int genreIds, int voteAverage, int voteCount, String name, String overview, String posterPath, String firstAirDate, String backdropPath, String originCountry) {
        this.id = id;
        this.popularity = popularity;
        this.genreIds = genreIds;
        this.voteAverage = voteAverage;
        this.voteCount = voteCount;
        this.name = name;
        this.overview = overview;
        this.posterPath = posterPath;
        this.firstAirDate = firstAirDate;
        this.backdropPath = backdropPath;
        this.originCountry = originCountry;
    }

//    Getter
    public int getId() {
        return id;
    }
    public int getPopularity() {
        return popularity;
    }
    public int getGenreIds() {
        return genreIds;
    }
    public int getVoteAverage() {
        return voteAverage;
    }
    public int getVoteCount() {
        return voteCount;
    }
    public String getName() {
        return name;
    }
    public String getOverview() {
        return overview;
    }
    public String getPosterPath() {
        return posterPath;
    }
    public String getFirstAirDate() {
        return firstAirDate;
    }
    public String getBackdropPath() {
        return backdropPath;
    }
    public String getOriginCountry() {
        return originCountry;
    }

//    Setter
    public void setId(int id) {
        this.id = id;
    }
    public void setPopularity(int popularity) {
        this.popularity = popularity;
    }
    public void setGenreIds(int genreIds) {
        this.genreIds = genreIds;
    }
    public void setVoteAverage(int voteAverage) {
        this.voteAverage = voteAverage;
    }
    public void setVoteCount(int voteCount) {
        this.voteCount = voteCount;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setOverview(String overview) {
        this.overview = overview;
    }
    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
    }
    public void setFirstAirDate(String firstAirDate) {
        this.firstAirDate = firstAirDate;
    }
    public void setBackdropPath(String backdropPath) {
        this.backdropPath = backdropPath;
    }
    public void setOriginCountry(String originCountry) {
        this.originCountry = originCountry;
    }
}