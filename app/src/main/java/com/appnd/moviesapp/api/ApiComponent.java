package com.appnd.moviesapp.api;

import com.appnd.moviesapp.AppComponent;
import com.appnd.moviesapp.api.service.MovieService;

import dagger.Component;

@Component(dependencies = AppComponent.class, modules = ApiModule.class)
public interface ApiComponent {

    MovieService providesMovieService();
}
