package com.appnd.moviesapp;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Component;

@Component(modules = AppModule.class)
public interface AppComponent {

    Context providesContext();
}
