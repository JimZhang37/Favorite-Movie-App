package com.example.popularmoviesstage2;

import android.app.Application;

import com.example.popularmoviesstage2.data.source.DefaultRepository;
import com.example.popularmoviesstage2.data.source.local.MovieDatabase;

public class MovieApplication extends Application {
    private DefaultRepository repository;

    public DefaultRepository getRepository() {
        if (repository == null) {
            repository = new DefaultRepository(MovieDatabase.getInstance(getApplicationContext()));
        }
        return repository;
    }
}
