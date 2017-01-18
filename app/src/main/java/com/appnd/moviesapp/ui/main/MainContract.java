package com.appnd.moviesapp.ui.main;

import com.appnd.moviesapp.api.dto.Movie;
import com.appnd.moviesapp.ui.base.BaseContract;

import java.util.ArrayList;

/**
 * Main contract which contains all methods between MainView and its presenter and viceversa
 */
public interface MainContract {
    interface View extends BaseContract.View{
        void showLoading();
        void hideLoading();
        void addItems(ArrayList<Movie> items);
        void showItems(ArrayList<Movie> items);
    }

    interface Presenter extends BaseContract.Presenter<View>{
        void fetchMoreItems(int offset);
        void refresh();
    }
}
