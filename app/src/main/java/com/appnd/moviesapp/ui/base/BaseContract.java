package com.appnd.moviesapp.ui.base;


import android.support.annotation.StringRes;

public interface BaseContract {
    /**
     * Interface that defines all the procedures/functions all presenters must implement
     *
     * @param <V> Type of view we want to attach to the presenter
     */
    interface Presenter<V extends View> {

        void attachView(V view);

        void detachView();

        boolean isAttached();
    }

    /**
     * Interface that defines all the procedures/functions all views must implement
     */
    interface View {
        void showError(@StringRes int error);
    }
}
