package com.example.popularmoviesstage2.list;


import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.popularmoviesstage2.data.Movie;
import com.example.popularmoviesstage2.data.source.DefaultRepository;

import java.util.List;

public class ViewModelMovieList extends ViewModel {

    ViewModelMovieList(DefaultRepository repository) {
        this.repository = repository;
        dataTopRated = this.repository.getTopRated();
        dataPopular = this.repository.getPopular();
        dataFavorite = this.repository.getFavorite();
    }

    private DefaultRepository repository;

    public LiveData<List<Movie>> dataTopRated ;
    public LiveData<List<Movie>> dataPopular ;
    public LiveData<List<Movie>> dataFavorite;

    //TODO(1) async call to repository's
    public void initValue(){
        repository.loadMovies();
    }
}
