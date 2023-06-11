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
import com.lev.h071211067_finalmobile.networking.TVShow;
import com.lev.h071211067_finalmobile.recyclerview.TVShowAdapter;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TVShowFragment extends Fragment {
    private ShimmerFrameLayout progressBar;
    private RecyclerView rvTVShow;
    private TVShowAdapter tvShowAdapter;
    private static final String API_KEY = "dad1cd55d3f6d09536f1c6bde1fe8d07";

    public TVShowFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_t_v_show, container, false);

        progressBar = view.findViewById(R.id.progress_bar);
        rvTVShow = view.findViewById(R.id.rv_movie);

        rvTVShow.setLayoutManager(new LinearLayoutManager(getContext()));
        tvShowAdapter = new TVShowAdapter();
        rvTVShow.setAdapter(tvShowAdapter);

        APIService apiService = APIConfig.getService();
        Call<DataResponse<List<TVShow>>> call = apiService.getTVShows(API_KEY);
        call.enqueue(new Callback<DataResponse<List<TVShow>>>() {
            @Override
            public void onResponse(Call<DataResponse<List<TVShow>>> call, Response<DataResponse<List<TVShow>>> response) {
                if (response.isSuccessful()) {
                    List<TVShow> tvShowList = response.body().getData();
                    tvShowAdapter.setTVShows(tvShowList);
                } else {
                    Toast.makeText(getActivity(), "Error: Berhasil dengan internet tetapi gagal karna " + response.code(), Toast.LENGTH_SHORT).show();
                }
                progressBar.setVisibility(View.GONE);
            }

            @Override
            public void onFailure(Call<DataResponse<List<TVShow>>> call, Throwable t) {
                Toast.makeText(getActivity(), "Failure: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                progressBar.setVisibility(View.GONE);
            }
        });

        return view;
    }
    private void showLoading() {
        progressBar.setVisibility(View.VISIBLE);
    }

    private void hideLoading() {
        progressBar.setVisibility(View.INVISIBLE);
    }
}
