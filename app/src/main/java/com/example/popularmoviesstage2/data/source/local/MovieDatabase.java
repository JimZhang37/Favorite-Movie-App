package com.example.popularmoviesstage2.data.source.local;

import android.content.Context;
import android.util.Log;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.popularmoviesstage2.data.Movie;
import com.example.popularmoviesstage2.data.Movie_Favorite;
import com.example.popularmoviesstage2.data.Movie_Popular;
import com.example.popularmoviesstage2.data.Movie_TopRated;
import com.example.popularmoviesstage2.data.Review;
import com.example.popularmoviesstage2.data.Trailer;

@Database(entities = {Movie.class, Review.class, Trailer.class, Movie_Popular.class, Movie_TopRated.class, Movie_Favorite.class}, version = 1, exportSchema = false)
public abstract class MovieDatabase extends RoomDatabase {

    private static final String LOG_TAG = MovieDatabase.class.getSimpleName();
    private static final Object LOCK = new Object();
    private static final String DATABASE_NAME = "movies_db";
    private static MovieDatabase sInstance;

    public static MovieDatabase getInstance(Context context) {
        if (sInstance == null) {
            synchronized (LOCK) {
                Log.d(LOG_TAG, "Creating new database instance");
                sInstance = Room.databaseBuilder(context.getApplicationContext(),
                        MovieDatabase.class, MovieDatabase.DATABASE_NAME)
                        .build();
            }
        }
        Log.d(LOG_TAG, "Getting the database instance");
        return sInstance;
    }

    public abstract MovieDao movieDao();

    public abstract ReviewDao reviewDao();

    public abstract TrailerDao trailerDao();

    public abstract FavoriteDao favoriteDao();

}
