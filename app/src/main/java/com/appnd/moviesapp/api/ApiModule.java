package com.appnd.moviesapp.api;

import com.appnd.moviesapp.BuildConfig;
import com.appnd.moviesapp.api.service.MovieService;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import static okhttp3.logging.HttpLoggingInterceptor.Level.BODY;
import static okhttp3.logging.HttpLoggingInterceptor.Level.NONE;


@Module
public class ApiModule {

    @Provides
    MovieService providesMovieService(Retrofit retrofit) {
        return retrofit.create(MovieService.class);
    }

    @Provides
    Retrofit providesRetrofit(OkHttpClient client) {
        return new Retrofit.Builder()
                .baseUrl(BuildConfig.HOST)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .client(client)
                .build();

    }

    @Provides
    OkHttpClient providesOkHttpClient(HttpLoggingInterceptor interceptor) {
        return new OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .build();
    }

    @Provides
    HttpLoggingInterceptor providesHttpLoggingInterceptor() {
        return new HttpLoggingInterceptor()
                .setLevel(BuildConfig.DEBUG ?
                        BODY : NONE);
    }
}
