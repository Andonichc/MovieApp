package com.appnd.moviesapp.ui.main;

import android.os.Bundle;
import android.support.annotation.StringRes;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.appnd.moviesapp.AppComponent;
import com.appnd.moviesapp.MovieApplication;
import com.appnd.moviesapp.R;
import com.appnd.moviesapp.api.ApiModule;
import com.appnd.moviesapp.api.DaggerApiComponent;
import com.appnd.moviesapp.api.dto.Movie;
import com.appnd.moviesapp.ui.base.BaseActivity;
import com.appnd.moviesapp.util.EndlessRecyclerViewScrollListener;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity
        implements MainContract.View, SwipeRefreshLayout.OnRefreshListener {

    @BindView(R.id.swipe_refresh_main)
    SwipeRefreshLayout mSwipeRefresh;

    @BindView(R.id.recycler_view_main)
    RecyclerView mRecyclerView;

    @Inject
    MainPresenter mPresenter;

    private MoviesAdapter mAdapter;
    private EndlessRecyclerViewScrollListener mScrollListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //Initialize all the views having the @BindView annotation
        ButterKnife.bind(this);

        inject();

        mAdapter = new MoviesAdapter();
        setUpRecyclerView();

        mPresenter.fetchMoreItems();
    }

    private void setUpRecyclerView() {
        LinearLayoutManager lManager = new LinearLayoutManager(this);

        mRecyclerView.setLayoutManager(lManager);
        mRecyclerView.setAdapter(mAdapter);

        mScrollListener = new EndlessRecyclerViewScrollListener(lManager) {
            @Override
            public void onLoadMore(int page, int totalItemsCount) {
                mPresenter.fetchMoreItems();
            }
        };

        mRecyclerView.addOnScrollListener(mScrollListener);

        mSwipeRefresh.setOnRefreshListener(this);
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
    public void showError(@StringRes int error) {
        Snackbar.make(mRecyclerView, error, Snackbar.LENGTH_SHORT);
    }

    @Override
    public void clearItems() {
        mAdapter.clear();
    }

    @Override
    public void addItems(List<Movie> items) {
        mAdapter.addItems(items);
    }

    @Override
    public void onRefresh() {
        mPresenter.refresh();
        mScrollListener.restart();
    }

    private void inject() {
        AppComponent appComponent = ((MovieApplication) getApplication()).getAppComponent();
        DaggerMainComponent.builder()
                .apiComponent(DaggerApiComponent.builder()
                        .appComponent(appComponent)
                        .apiModule(new ApiModule())
                        .build())
                .appComponent(appComponent)
                .mainModule(new MainModule(this))
                .build()
                .inject(this);
    }
}
