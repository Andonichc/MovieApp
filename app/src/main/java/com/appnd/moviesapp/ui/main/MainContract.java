package com.appnd.moviesapp.ui.main;

import com.appnd.moviesapp.api.dto.Movie;
import com.appnd.moviesapp.ui.base.BaseContract;

import java.util.List;

/**
 * Main contract which contains all methods between MainView and its presenter and vice versa.
 */
public interface MainContract {
    interface View extends BaseContract.View {
        /**
         * Shows to the user that the view is loading its content.
         */
        void showLoading();

        /**
         * Hides the loading indicator.
         */
        void hideLoading();

        /**
         * Adds and shows more items.
         * @param items items we want the view to add and show.
         */
        void addItems(List<Movie> items);

        void clearItems();
    }

    interface Presenter extends BaseContract.Presenter<View> {
        /**
         * Fetches more items and eventually gives them to the
         * view through {@link View#addItems(List)} method.
         */
        void fetchMoreItems();

        /**
         * Called when refreshing the view
         */
        void refresh();

        /**
         * Searches items given a certain Query and eventually gives them to
         * the view through {@link View#addItems(List)} method.
         * @param query
         */
        void search(String query);
    }
}
