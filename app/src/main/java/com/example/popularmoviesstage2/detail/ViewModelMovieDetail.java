package com.example.popularmoviesstage2.detail;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.popularmoviesstage2.data.Movie;
import com.example.popularmoviesstage2.data.Review;
import com.example.popularmoviesstage2.data.Trailer;
import com.example.popularmoviesstage2.data.source.DefaultRepository;

import java.util.List;

public class ViewModelMovieDetail extends ViewModel {
    private String mMovieID;
    private LiveData<Movie> mMovie;
    private LiveData<List<Review>> mReviews;
    private LiveData<List<Trailer>> mTrailers;

    public ViewModelMovieDetail(String movieID, DefaultRepository repository){
        mMovieID = movieID;
        mMovie = repository.loadMovieByID(mMovieID);
        mReviews = repository.loadAllReviewsByMovieID(mMovieID);
        mTrailers = repository.loadAllTrailersByMovieID(mMovieID);
        repository.loadMovieReviewAndTrailerByID(mMovieID);

    }

    public LiveData<Movie> getMovie(){
        return mMovie;
    }

    public LiveData<List<Review>> getReviews(){
        return mReviews;
    }

    public LiveData<List<Trailer>> getTrailers(){
        return mTrailers;
    }
}
