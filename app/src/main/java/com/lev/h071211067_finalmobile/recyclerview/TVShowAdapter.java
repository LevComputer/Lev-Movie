package com.lev.h071211067_finalmobile.recyclerview;

import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class TVShowAdapter extends RecyclerView.Adapter<TVShowAdapter.TVShowViewHolder> {
    @NonNull
    @Override
    public TVShowAdapter.TVShowViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull TVShowAdapter.TVShowViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class TVShowViewHolder extends RecyclerView.ViewHolder {
        public TVShowViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}

