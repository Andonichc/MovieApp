package com.appnd.moviesapp.ui.main;

import com.appnd.moviesapp.ui.base.BasePresenter;

public class MainPresenter extends BasePresenter<MainContract.View> implements MainContract.Presenter {

    public MainPresenter(MainContract.View mView) {
        super(mView);
    }

    @Override
    public void fetchMoreItems(int offset) {

    }

    @Override
    public void refresh() {

    }
}
