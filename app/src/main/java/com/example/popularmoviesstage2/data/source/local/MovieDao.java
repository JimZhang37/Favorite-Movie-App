package com.example.popularmoviesstage2.data.source.local;


import androidx.lifecycle.LiveData;
import androidx.paging.DataSource;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import com.example.popularmoviesstage2.data.Movie;

import java.util.ArrayList;
import java.util.List;


@Dao
public interface MovieDao {
    @Query("SELECT * FROM movies")
    LiveData<List<Movie>> loadAllMovies();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertMovie(Movie movie);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAllMovies(List<Movie> movies);



}
