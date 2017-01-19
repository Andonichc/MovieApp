package com.appnd.moviesapp.ui.main;

import android.support.annotation.StringRes;

import com.appnd.moviesapp.BuildConfig;
import com.appnd.moviesapp.R;
import com.appnd.moviesapp.api.dto.MovieList;
import com.appnd.moviesapp.api.service.MovieService;
import com.appnd.moviesapp.ui.base.BasePresenter;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class MainPresenter extends BasePresenter<MainContract.View> implements MainContract.Presenter {

    private MovieService mMovieService; //Api MovieService endpoint.

    private int pagesLoaded; //Keeps count of the number of pages loaded on the view.
    private String mQuery; //Query that we are showing (in case there's any).

    private Subscription mSearchSubscriber;

    @Inject
    public MainPresenter(MainContract.View view, MovieService movieService) {
        super(view);

        mMovieService = movieService;
        pagesLoaded = 1;
    }

    /**
     * Implementation of {@link MainContract.Presenter#fetchMoreItems()}
     */
    @Override
    public void fetchMoreItems() {
        if (mQuery != null && !"".equals(mQuery))
            //if query is not empty means we are searching for a movie
            fetchMoreSearchItems();
        else
            //else we retrieve the most trending movies
            fetchMoreTrendingItems();
    }

    /**
     * Implementation of {@link MainContract.Presenter#refresh()}
     */
    @Override
    public void refresh() {
        mView.showLoading();
        mView.clearItems();
        pagesLoaded = 1;

        fetchMoreItems();
    }

    /**
     * Implementation of {@link MainContract.Presenter#search(String)}
     */
    @Override
    public void search(String query) {
        if (mSearchSubscriber != null && !mSearchSubscriber.isUnsubscribed()) {
            mSearchSubscriber.unsubscribe();
        }

        mSearchSubscriber = Observable.just(query)
                .delay(500, TimeUnit.MILLISECONDS)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<String>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        mView.showError(getErrorMessage(e));
                    }

                    @Override
                    public void onNext(String query) {

                        mView.clearItems();
                        pagesLoaded = 1;
                        mQuery = query;

                        fetchMoreItems();
                    }
                });
    }

    /**
     * Checks wether is a network error (IOException) or some other kind of exception
     * and returns the proper message.
     *
     * @param e exception we want to determine its kind.
     * @return String resource reference that holds a message related to the error.
     */
    @StringRes
    private int getErrorMessage(Throwable e) {
        if (e instanceof IOException)
            return R.string.error_network;
        else
            return R.string.error_unknown;
    }

    /**
     * Retrieves one page of the most trending movies from the Api.
     */
    private void fetchMoreTrendingItems() {
        mMovieService.findMovieList(pagesLoaded, BuildConfig.API_KEY)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<MovieList>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        mView.showError(getErrorMessage(e));
                    }

                    @Override
                    public void onNext(MovieList movieList) {
                        //hides loading in case it was loading and it adds all the requested items
                        //to the view
                        pagesLoaded++;
                        mView.addItems(movieList.getResults());
                        mView.hideLoading();
                    }
                });
    }

    /**
     * Retrieves one page of the movies related to the {@link #mQuery} from the Api.
     */
    private void fetchMoreSearchItems() {
        mMovieService.searchMovies(mQuery, pagesLoaded, BuildConfig.API_KEY)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<MovieList>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        mView.showError(getErrorMessage(e));
                    }

                    @Override
                    public void onNext(MovieList movieList) {
                        //hides loading in case it was loading and it adds all the requested items
                        //to the view
                        pagesLoaded++;
                        mView.addItems(movieList.getResults());
                        mView.hideLoading();
                    }
                });
    }
}
