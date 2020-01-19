package com.example.popularmoviesstage2.list;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.popularmoviesstage2.data.source.DefaultRepository;

public class ViewModelListFactory extends ViewModelProvider.NewInstanceFactory {
    DefaultRepository repository;
    public  ViewModelListFactory(DefaultRepository repository){
        this.repository = repository;
    }
    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new ViewModelMovieList(repository);
    }
}
