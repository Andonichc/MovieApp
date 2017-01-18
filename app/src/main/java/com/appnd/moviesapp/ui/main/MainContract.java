package com.appnd.moviesapp.ui.main;

import com.appnd.moviesapp.api.dto.Movie;
import com.appnd.moviesapp.ui.base.BaseContract;

import java.util.List;

/**
 * Main contract which contains all methods between MainView and its presenter and viceversa
 */
public interface MainContract {
    interface View extends BaseContract.View {
        void showLoading();

        void hideLoading();

        void addItems(List<Movie> items);

        void clearItems();
    }

    interface Presenter extends BaseContract.Presenter<View> {
        void fetchMoreItems();

        void refresh();
    }
}
