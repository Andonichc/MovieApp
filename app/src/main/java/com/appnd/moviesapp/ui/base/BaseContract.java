package com.appnd.moviesapp.ui.base;


import android.support.annotation.StringRes;

public interface BaseContract {
    /**
     * Interface that defines all the procedures/functions all presenters must implement
     *
     * @param <V> Type of view we want to attach to the presenter
     */
    interface Presenter<V extends View> {

        /**
         * Attaches a view to its instance
         * @param view view that will be attached to the presenter
         */
        void attachView(V view);

        /**
         * Detaches a view
         */
        void detachView();

        /**
         * Checks wether is a view attached or not
         * @return true if is attached / false if not
         */
        boolean isAttached();
    }

    /**
     * Interface that defines all the procedures/functions all views must implement
     */
    interface View {
        /**
         * Shows an error to the user given a String resource reference
         *
         * *Though this is a common method for all views to implement, it is highly
         * recommended to implement this method in each view instead of doing it in {@link BaseActivity}
         * because each view may show errors in a different way.
         *
         * @param error String resource id of the error that will be shown.
         */
        void showError(@StringRes int error);
    }
}
