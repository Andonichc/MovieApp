package com.appnd.moviesapp.ui.main;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.appnd.moviesapp.R;
import com.appnd.moviesapp.api.dto.Movie;
import com.appnd.moviesapp.ui.base.BaseActivity;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity implements MainContract.View {

    @BindView(R.id.swipe_refresh_main)
    SwipeRefreshLayout mSwipeRefresh;

    @BindView(R.id.recycler_view_main)
    RecyclerView mRecyclerView;

    private MainContract.Presenter mPresenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //Initialize all the views having the @BindView annotation
        ButterKnife.bind(this);

        mPresenter = new MainPresenter(this);

    }

    @Override
    public void showLoading() {
        mSwipeRefresh.setRefreshing(true);
    }

    @Override
    public void hideLoading() {
        mSwipeRefresh.setRefreshing(false);
    }

    @Override
    public void addItems(ArrayList<Movie> items) {

    }

    @Override
    public void showItems(ArrayList<Movie> items) {

    }
}
