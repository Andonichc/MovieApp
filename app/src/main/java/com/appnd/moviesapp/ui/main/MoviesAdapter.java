package com.appnd.moviesapp.ui.main;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.appnd.moviesapp.R;
import com.appnd.moviesapp.api.dto.Movie;

import java.util.ArrayList;
import java.util.List;

public class MoviesAdapter extends RecyclerView.Adapter<MovieViewHolder> {

    private List<Movie> mItems;

    public MoviesAdapter() {
        mItems = new ArrayList<>();
    }

    @Override
    public MovieViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_movie, parent, false);
        return new MovieViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MovieViewHolder holder, int position) {
        holder.bind(mItems.get(position));
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    public void addItems(List<Movie> movies) {
        int previousSize = mItems.size();
        mItems.addAll(movies);
        notifyItemRangeInserted(previousSize, movies.size());
    }

    public void clear() {
        int previousSize = mItems.size();
        mItems.clear();

        notifyItemRangeRemoved(0, previousSize);

    }

}
