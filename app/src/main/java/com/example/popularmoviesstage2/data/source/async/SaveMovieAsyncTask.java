package com.example.popularmoviesstage2.data.source.async;

import android.os.AsyncTask;

import com.example.popularmoviesstage2.data.Movie;
import com.example.popularmoviesstage2.data.source.local.MovieDao;

import java.util.List;

public class SaveMovieAsyncTask extends AsyncTask<Void, Void, Void> {
    private List<Movie> movieList;
    private MovieDao movieDao;

    public SaveMovieAsyncTask(List<Movie> list, MovieDao dao) {
        movieList = list;
        movieDao = dao;
    }

    @Override
    protected Void doInBackground(Void... voids) {
        movieDao.insertAllMovies(movieList);
        return null;
    }
}
