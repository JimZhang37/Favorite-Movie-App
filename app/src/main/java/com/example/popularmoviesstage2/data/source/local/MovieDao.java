package com.example.popularmoviesstage2.data.source.local;


import androidx.lifecycle.LiveData;
import androidx.paging.DataSource;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Transaction;

import com.example.popularmoviesstage2.data.Movie;
import com.example.popularmoviesstage2.data.Movie_Popular;
import com.example.popularmoviesstage2.data.Movie_TopRated;

import java.util.ArrayList;
import java.util.List;


@Dao
public abstract class MovieDao {
    @Query("SELECT * FROM movies")
    public abstract LiveData<List<Movie>> loadAllMovies();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public abstract void insertMovie(Movie movie);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public abstract void insertMovieToprated(Movie_TopRated movie_topRated);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public abstract void insertMoviePopular(Movie_Popular movie_popular);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public abstract void insertAllMovies(List<Movie> movies);

    @Query("SELECT * FROM movies WHERE mMovieID = :movieId")
    public abstract LiveData<Movie> loadMovieByID(String movieId);


    @Transaction
    public void insertMoviesTopRatedTransaction(Movie movie, Movie_TopRated movieTopRated) {
        insertMovieToprated(movieTopRated);
        insertMovie(movie);
    }

    @Transaction
    public void insertMoviesPopularTransaction(Movie movie, Movie_Popular movie_popular) {
        insertMoviePopular(movie_popular);
        insertMovie(movie);
    }

    @Query("SELECT * FROM movies INNER JOIN movie_popular ON movies.mMovieID = movie_popular.mMovieID")
    public abstract LiveData<List<Movie>> observeMoviesPopular();


    @Query("SELECT * FROM movies INNER JOIN movie_toprated ON movies.mMovieID = movie_toprated.mMovieID")
    public abstract LiveData<List<Movie>> observeMoviesTopRated();
}

