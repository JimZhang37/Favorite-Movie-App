package com.example.popularmoviesstage2.data.source.local;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.popularmoviesstage2.data.Movie;
import com.example.popularmoviesstage2.data.Review;

import java.util.List;

@Dao
public interface ReviewDao {
    @Query("SELECT * FROM reviews WHERE movie_id =:movieID")
    LiveData<List<Review>> loadAllReviewsByMovieID(String movieID);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertReveiw(Review review);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAllReviews(List<Review> reviews);



}
