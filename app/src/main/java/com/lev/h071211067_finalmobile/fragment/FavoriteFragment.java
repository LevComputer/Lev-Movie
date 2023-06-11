package com.lev.h071211067_finalmobile.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.lev.h071211067_finalmobile.R;
import com.lev.h071211067_finalmobile.recyclerview.FavoriteAdapter;

public class FavoriteFragment extends Fragment {
    private RecyclerView rvFavorites;
    private FavoriteAdapter favoriteAdapter;

    public FavoriteFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_favorite, container, false);

        rvFavorites = view.findViewById(R.id.rvFavorite);
        rvFavorites.setLayoutManager(new LinearLayoutManager(getContext()));
        favoriteAdapter = new FavoriteAdapter();
        rvFavorites.setAdapter(favoriteAdapter);
        return view;
    }
}
