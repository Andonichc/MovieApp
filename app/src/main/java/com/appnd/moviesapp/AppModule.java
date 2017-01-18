package com.appnd.moviesapp;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class AppModule {
    private Context mContext;

    AppModule(MovieApplication movieApplication){
        mContext = movieApplication;
    }

    @Provides
    public Context providesContext(){
        return mContext;
    }
}
