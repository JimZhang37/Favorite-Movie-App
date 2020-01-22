package com.example.popularmoviesstage2.data.source.local;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;


import com.example.popularmoviesstage2.data.Trailer;

import java.util.List;

@Dao
public interface TrailerDao {
    @Query("SELECT * FROM trailers WHERE movie_id =:movieID")
    LiveData<List<Trailer>> loadAllTrailersByMovieID(String movieID);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertTrailer(Trailer trailer);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAllTrailers(List<Trailer> trailers);



}
