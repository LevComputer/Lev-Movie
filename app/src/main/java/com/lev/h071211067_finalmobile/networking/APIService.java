package com.lev.h071211067_finalmobile.networking;

import java.util.List;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface APIService {
//  Movie
    @GET("movie/top_rated")
    Call<DataResponse<List<Movie>>> getMovies(
            @Query("api_key") String apiKey
    );

//    TV Show
    @GET("tv/top_rated")
    Call<DataResponse<List<TVShow>>> getTVShows(
            @Query("api_key") String apiKey
    );
}
