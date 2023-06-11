package com.lev.h071211067_finalmobile.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.lev.h071211067_finalmobile.R;
import com.lev.h071211067_finalmobile.lain.Database;
import com.lev.h071211067_finalmobile.networking.Favorite;
import com.lev.h071211067_finalmobile.recyclerview.FavoriteAdapter;

import java.util.ArrayList;
import java.util.List;

public class FavoriteFragment extends Fragment {
    private RecyclerView rvFavorites;
    private FavoriteAdapter favoriteAdapter;
    private Database database;

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

        database = new Database(getContext());
        loadFavorites();

        return view;
    }

    private void loadFavorites() {
        database.open();
        List<Favorite> favorites = database.getAllFavorites();
        database.close();

        if (favorites.isEmpty()) {
            Toast.makeText(getContext(), "No favorites found", Toast.LENGTH_SHORT).show();
        }

        favoriteAdapter.setFavorites(favorites);
    }


}
