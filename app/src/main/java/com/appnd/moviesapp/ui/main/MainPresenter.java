package com.appnd.moviesapp.ui.main;

import android.os.Build;

import com.appnd.moviesapp.BuildConfig;
import com.appnd.moviesapp.api.dto.MovieList;
import com.appnd.moviesapp.api.service.MovieService;
import com.appnd.moviesapp.ui.base.BasePresenter;
import com.appnd.moviesapp.util.Constants;

import javax.inject.Inject;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class MainPresenter extends BasePresenter<MainContract.View> implements MainContract.Presenter {

    private MovieService mMovieService;

    private int pagesLoaded;

    @Inject
    public MainPresenter(MainContract.View mView, MovieService movieService) {
        super(mView);

        mMovieService = movieService;
        pagesLoaded = 0;
    }

    @Override
    public void fetchMoreItems() {
        mMovieService.findMovieList(Constants.LIST_ID, pagesLoaded, BuildConfig.API_KEY)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<MovieList>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(MovieList movieList) {
                        mView.showItems(movieList.getResults());
                    }
                });
    }

    @Override
    public void refresh() {
        pagesLoaded = 0;
        fetchMoreItems();
    }
}
