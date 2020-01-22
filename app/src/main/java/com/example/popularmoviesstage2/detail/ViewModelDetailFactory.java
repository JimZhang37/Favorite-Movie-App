package com.example.popularmoviesstage2.detail;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.popularmoviesstage2.data.source.DefaultRepository;

public class ViewModelDetailFactory extends ViewModelProvider.NewInstanceFactory {

    DefaultRepository repository;
    String mMovieID;

    public ViewModelDetailFactory(DefaultRepository repository, String movieID) {
        this.repository = repository;
        mMovieID = movieID;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new ViewModelMovieDetail(mMovieID,repository);
    }
}
