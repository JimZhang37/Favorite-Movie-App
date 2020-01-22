package com.example.popularmoviesstage2.data;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "movie_favorite")
public class Movie_Favorite {
    @PrimaryKey

    @NonNull
    String mMovieID;

    public Movie_Favorite(@NonNull String mMovieID) {
        this.mMovieID = mMovieID;
    }

    @NonNull
    public String getMovieID() {
        return mMovieID;
    }
}
