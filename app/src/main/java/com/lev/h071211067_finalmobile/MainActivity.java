package com.lev.h071211067_finalmobile;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.lev.h071211067_finalmobile.fragment.FavoriteFragment;
import com.lev.h071211067_finalmobile.fragment.MovieFragment;
import com.lev.h071211067_finalmobile.fragment.TVShowFragment;

public class MainActivity extends AppCompatActivity {
    ProgressBar progressBar;
    TextView judul;
    FragmentManager fragmentManager;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        progressBar = findViewById(R.id.progress_bar);
        judul = findViewById(R.id.judul);
        fragmentManager = getSupportFragmentManager();
        BottomNavigationView bottomNav = findViewById(R.id.bottom_nav);
        showFragment(new MovieFragment());
        progressBar.setVisibility(View.INVISIBLE);
        bottomNav.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener(){
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getItemId() == R.id.nav_movie) {
                    judul.setText("Lev Movie");
                    progressBar.setVisibility(View.VISIBLE);
                    showFragment(new MovieFragment());
                    progressBar.setVisibility(View.INVISIBLE);
                    return true;
                } else if (item.getItemId() == R.id.nav_favorite) {
                    judul.setText("Lev Favorite");
                    progressBar.setVisibility(View.VISIBLE);
                    showFragment(new FavoriteFragment());
                    progressBar.setVisibility(View.INVISIBLE);
                    return true;
                } else if (item.getItemId() == R.id.nav_tv_show) {
                    judul.setText("Lev TV Show");
                    progressBar.setVisibility(View.VISIBLE);
                    showFragment(new TVShowFragment());
                    progressBar.setVisibility(View.INVISIBLE);
                    return true;
                }
                return false;
            }
        });
    }

    private void showFragment(Fragment fragment) {
        fragmentManager.beginTransaction()
                .replace(R.id.fl_main, fragment)
                .addToBackStack(null)
                .commit();
    }
}