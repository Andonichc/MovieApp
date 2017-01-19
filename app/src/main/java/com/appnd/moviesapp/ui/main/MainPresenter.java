package com.appnd.moviesapp.ui.main;

import com.appnd.moviesapp.BuildConfig;
import com.appnd.moviesapp.R;
import com.appnd.moviesapp.api.dto.MovieList;
import com.appnd.moviesapp.api.service.MovieService;
import com.appnd.moviesapp.ui.base.BasePresenter;
import com.appnd.moviesapp.util.Constants;

import java.io.IOException;

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
        pagesLoaded = 1;
    }

    @Override
    public void fetchMoreItems() {
        mMovieService.findMovieList(pagesLoaded, BuildConfig.API_KEY)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<MovieList>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        if (e instanceof IOException)
                            mView.showError(R.string.error_network);
                        else
                            mView.showError(R.string.error_unknown);
                    }

                    @Override
                    public void onNext(MovieList movieList) {
                        pagesLoaded++;
                        mView.addItems(movieList.getResults());
                        mView.hideLoading();
                    }
                });
    }

    @Override
    public void refresh() {
        mView.showLoading();
        mView.clearItems();
        pagesLoaded = 1;

        fetchMoreItems();
    }
}
