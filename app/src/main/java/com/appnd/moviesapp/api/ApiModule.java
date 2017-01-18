package com.appnd.moviesapp.api;

import android.content.Context;

import com.appnd.moviesapp.BuildConfig;
import com.appnd.moviesapp.api.service.MovieService;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


@Module
public class ApiModule {

    private Context mContext;

    public ApiModule(Context context) {
        mContext = context;
    }

    @Provides
    Retrofit providesRetrofit(Context context) {
        return new Retrofit.Builder()
                .baseUrl(BuildConfig.HOST)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

    }

    @Provides
    MovieService providesMovieService(Retrofit retrofit) {
        return retrofit.create(MovieService.class);
    }
}
