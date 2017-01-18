package com.appnd.moviesapp.ui.main;


import com.appnd.moviesapp.AppComponent;
import com.appnd.moviesapp.api.ApiComponent;

import dagger.Component;

@Component(dependencies = {AppComponent.class, ApiComponent.class}, modules = MainModule.class)
public interface MainComponent {
    void inject(MainActivity activity);
}
