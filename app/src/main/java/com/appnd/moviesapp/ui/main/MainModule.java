package com.appnd.moviesapp.ui.main;

import dagger.Module;
import dagger.Provides;

@Module
public class MainModule {

    private MainContract.View mView;

    MainModule(MainContract.View view){
        mView = view;
    }

    @Provides
    MainContract.View providesView(){
        return mView;
    }
}
