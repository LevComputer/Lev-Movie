package com.lev.h071211067_finalmobile.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.facebook.shimmer.ShimmerFrameLayout;
import com.lev.h071211067_finalmobile.R;
import com.lev.h071211067_finalmobile.networking.APIConfig;
import com.lev.h071211067_finalmobile.networking.APIService;
import com.lev.h071211067_finalmobile.networking.DataResponse;
import com.lev.h071211067_finalmobile.networking.Movie;
import com.lev.h071211067_finalmobile.recyclerview.MovieAdapter;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieFragment extends Fragment {
    RecyclerView rvMovie;
    private MovieAdapter movieAdapter;
    private static final String API_KEY = "dad1cd55d3f6d09536f1c6bde1fe8d07";

    public MovieFragment() {
        // Required empty public constructor
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_movie, container, false);

        rvMovie = view.findViewById(R.id.rv_movie);
        rvMovie.setLayoutManager(new LinearLayoutManager(getContext()));
        movieAdapter = new MovieAdapter();
        rvMovie.setAdapter(movieAdapter);
        APIService apiService = APIConfig.getService();
        Call<DataResponse<List<Movie>>> call = apiService.getMovies(API_KEY);
        call.enqueue(new Callback<DataResponse<List<Movie>>>() {
            @Override
            public void onResponse(Call<DataResponse<List<Movie>>> call, Response<DataResponse<List<Movie>>> response) {
                if (response.isSuccessful()) {
                    List<Movie> movieList = response.body().getData();
                    movieAdapter.setMovies(movieList);
                } else {
                    Toast.makeText(getActivity(), "Error: Berhasil dengan internet tetapi gagal karna " + response.code(), Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<DataResponse<List<Movie>>> call, Throwable t) {
                Toast.makeText(getActivity(), "Failure:" + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }
}