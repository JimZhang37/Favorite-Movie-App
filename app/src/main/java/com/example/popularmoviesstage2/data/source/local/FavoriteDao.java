package com.example.popularmoviesstage2.data.source.local;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;


import com.example.popularmoviesstage2.data.Movie_Favorite;
import com.example.popularmoviesstage2.data.Trailer;

import java.util.List;

@Dao
public interface FavoriteDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertFavorite(Movie_Favorite movie_favorite);

    @Delete
    void deleteFavorite(Movie_Favorite movie_favorite);

    @Query("SELECT * FROM movie_favorite WHERE mMovieID = :movieId")
    LiveData<Movie_Favorite> queryFavorite(String movieId);
}
