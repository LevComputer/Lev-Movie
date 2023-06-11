package com.lev.h071211067_finalmobile.networking;

import java.util.List;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface APIService {

//    Movie
    @GET("movie/top_rated")
    Call<Response<List<Movie>>> getMovies(
            @Query("api_key") String apiKey,
            @Query("language") String language
    );
    @GET("movie/{id}")
    Call<Movie> getMediaById(
            @Path("id") int id,
            @Query("api_key") String apiKey,
            @Query("language") String language
    );

//    TV Show
    @GET("tv/top_rated")
    Call<Response<List<TVShow>>> getTVShows(
            @Query("api_key") String apiKey,
            @Query("language") String language
    );
    @GET("tv/{id}")
    Call<TVShow> getTVShowById(
            @Path("id") int id,
            @Query("api_key") String apiKey,
            @Query("language") String language
    );

}
