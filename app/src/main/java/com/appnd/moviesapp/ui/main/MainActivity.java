package com.appnd.moviesapp.ui.main;

import android.os.Bundle;
import android.support.annotation.StringRes;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.appnd.moviesapp.AppComponent;
import com.appnd.moviesapp.MovieApplication;
import com.appnd.moviesapp.R;
import com.appnd.moviesapp.api.ApiModule;
import com.appnd.moviesapp.api.DaggerApiComponent;
import com.appnd.moviesapp.api.dto.Movie;
import com.appnd.moviesapp.ui.base.BaseActivity;
import com.appnd.moviesapp.util.EndlessRecyclerViewScrollListener;
import com.miguelcatalan.materialsearchview.MaterialSearchView;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity
        implements MainContract.View,
        SwipeRefreshLayout.OnRefreshListener,
        MaterialSearchView.OnQueryTextListener {

    @BindView(R.id.swipe_refresh_main)
    SwipeRefreshLayout mSwipeRefresh;

    @BindView(R.id.recycler_view_main)
    RecyclerView mRecyclerView;

    @BindView(R.id.search_view_main)
    MaterialSearchView mSearchView;

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

        //inject all dependencies
        inject();

        mSearchView.setOnQueryTextListener(this);

        mAdapter = new MoviesAdapter();
        setUpRecyclerView();

        mPresenter.fetchMoreItems();
    }

    @Override
    public void onBackPressed() {
        if (mSearchView.isSearchOpen())
            mSearchView.closeSearch();
        else
            super.onBackPressed();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);

        //attach the search menu icon with the SearchView
        MenuItem searchItem = menu.findItem(R.id.search_action);
        mSearchView.setMenuItem(searchItem);

        return true;
    }

    /**
     * Implementation of {@link MainContract.Presenter} methods.
     */
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

    /**
     * Implementation of {@link SwipeRefreshLayout} methods.
     */
    @Override
    public void onRefresh() {
        mPresenter.refresh();
        mScrollListener.restart();
    }

    /**
     * Implementation of {@link MaterialSearchView.OnQueryTextListener} methods.
     */
    @Override
    public boolean onQueryTextSubmit(String query) {
        mPresenter.search(query);
        return true;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        mPresenter.search(newText);
        return true;
    }

    /**
     * Activity's own private methods
     */

    /**
     * Sets the recyclerView and all its related objects to be used.
     */
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

    /**
     * Injects all the fields of MainActivity with Dagger building the {@link MainComponent}
     * and its dependencies.
     */
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
