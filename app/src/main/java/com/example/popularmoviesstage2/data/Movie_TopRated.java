package com.example.popularmoviesstage2.data;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "movie_toprated")
public class Movie_TopRated {
    @PrimaryKey
    @NonNull
    String mMovieID;

    public Movie_TopRated(String mMovieID) {
        this.mMovieID = mMovieID;
    }

    @NonNull
    public String getMovieID() {
        return mMovieID;
    }
}
