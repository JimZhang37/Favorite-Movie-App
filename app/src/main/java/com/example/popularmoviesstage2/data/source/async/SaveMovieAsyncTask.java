package com.example.popularmoviesstage2.data.source.async;

import android.os.AsyncTask;

import com.example.popularmoviesstage2.data.Movie;
import com.example.popularmoviesstage2.data.Movie_Popular;
import com.example.popularmoviesstage2.data.Movie_TopRated;
import com.example.popularmoviesstage2.data.source.local.MovieDao;

import java.util.List;

public class SaveMovieAsyncTask extends AsyncTask<Void, Void, Void> {
    private List<Movie> movieList;
    private MovieDao movieDao;
    private int mMovieType;

    public SaveMovieAsyncTask(List<Movie> list, MovieDao dao, int type) {
        movieList = list;
        movieDao = dao;
        mMovieType = type;
    }

    //TODO how to insert data ? I need to insert data once.
    @Override
    protected Void doInBackground(Void... voids) {
        switch (mMovieType){
            case 1:
                movieList.forEach(movie -> {
                    movieDao.insertMoviesPopularTransaction(movie,new Movie_Popular(movie.getMovieID()));
                });

                return null;
            case 2:

                movieList.forEach(movie -> {
                    movieDao.insertMoviesTopRatedTransaction(movie, new Movie_TopRated(movie.getMovieID()));
                });
                return null;

        }
//        movieDao.insertAllMovies(movieList);
        return null;
    }
}
