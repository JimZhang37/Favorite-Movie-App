package com.example.popularmoviesstage2.data;


import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "movie_popular")
public class Movie_Popular {
    @PrimaryKey
    @NonNull
    String mMovieID;

    public Movie_Popular(String mMovieID) {
        this.mMovieID = mMovieID;
    }

    @NonNull
    public String getMovieID() {
        return mMovieID;
    }
}
