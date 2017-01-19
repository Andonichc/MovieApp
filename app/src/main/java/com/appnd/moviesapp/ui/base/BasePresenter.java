package com.appnd.moviesapp.ui.base;

public class BasePresenter<T extends BaseContract.View> implements BaseContract.Presenter<T> {

    protected T mView;

    public BasePresenter(T mView) {
        attachView(mView);
    }

    @Override
    public void attachView(T view) {
        mView = view;
    }

    @Override
    public void detachView() {
        mView = null;
    }

    @Override
    public boolean isAttached(){
        return mView != null;
    }
}
