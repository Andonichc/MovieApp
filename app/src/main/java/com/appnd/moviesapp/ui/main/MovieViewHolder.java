package com.appnd.moviesapp.ui.main;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.appnd.moviesapp.BuildConfig;
import com.appnd.moviesapp.R;
import com.appnd.moviesapp.api.dto.Movie;
import com.bumptech.glide.Glide;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MovieViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.title_item_movie)
    TextView mTitle;

    @BindView(R.id.overview_item_movie)
    TextView mOverview;

    @BindView(R.id.image_item_movie)
    ImageView mImage;

    @BindView(R.id.year_item_movie)
    TextView mYear;

    public MovieViewHolder(View itemView) {
        super(itemView);

        ButterKnife.bind(this, itemView);
    }

    public void bind(Movie item) {
        mTitle.setText(item.getTitle());

        mOverview.setText(item.getOverview());

        mYear.setText(item.getYear());

        Glide.with(itemView.getContext())
                .load(BuildConfig.IMAGE_BASE_URL + item.getImage())
                .placeholder(R.drawable.placeholder)
                .into(mImage);
    }
}
