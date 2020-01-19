package com.example.popularmoviesstage2;

import android.app.Application;

import com.example.popularmoviesstage2.data.source.DefaultRepository;

public class MovieApplication extends Application {
    DefaultRepository repository;

    public DefaultRepository getRepository() {
        if (repository == null) {
            repository = new DefaultRepository();
        }
        return repository;
    }
}
