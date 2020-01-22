package com.example.popularmoviesstage2.data.source;

import android.util.Log;

import androidx.lifecycle.LiveData;

import com.example.popularmoviesstage2.data.Movie;
import com.example.popularmoviesstage2.data.MovieList;
import com.example.popularmoviesstage2.data.Review;
import com.example.popularmoviesstage2.data.ReviewRemote;
import com.example.popularmoviesstage2.data.ReviewRemoteList;
import com.example.popularmoviesstage2.data.source.Remote.MovieApiService;
import com.example.popularmoviesstage2.data.source.async.SaveMovieAsyncTask;
import com.example.popularmoviesstage2.data.source.async.SaveReviewsAsyncTask;
import com.example.popularmoviesstage2.data.source.local.MovieDatabase;
import com.squareup.moshi.Moshi;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.moshi.MoshiConverterFactory;

public class DefaultRepository {
    private LiveData<List<Movie>> topRated;
    private MovieDatabase database;
    private MovieApiService api;
    private String API_KEY = "758f975f610e3d276c8f2364e5052672";

    public DefaultRepository(MovieDatabase db) {
        this.database = db;

        topRated = database.movieDao().loadAllMovies();
        String MOVIE_BASE_URL = "https://api.themoviedb.org/3/movie/";


        Moshi moshi = new Moshi.Builder()
                .build();

        Retrofit retrofit = new Retrofit
                .Builder()
                .baseUrl(MOVIE_BASE_URL)
                .addConverterFactory
                        (MoshiConverterFactory.create(moshi))
                .build();

        api = retrofit.create(MovieApiService.class);
    }

    public LiveData<List<Movie>> getTopRated() {
        return this.topRated;
    }


    public void loadMovies() {
        Call<MovieList> movieList = api.getTopRatedMoviesAsync(API_KEY);
        movieList
                .enqueue(new Callback<MovieList>() {

                             @Override
                             public void onResponse(Call<MovieList> call, Response<MovieList> response) {
                                 MovieList movieList = response.body();
                                 List<Movie> list = movieList.getMovies();
//                                 list.forEach(movie -> database.movieDao().insertMovie(movie));
//                                 topRated.setValue(movieList.getMovies());
                                 new SaveMovieAsyncTask(movieList.getMovies(), database.movieDao()).execute();
                             }

                             @Override
                             public void onFailure(Call<MovieList> call, Throwable t) {

                             }
                         }
                );

    }

    public LiveData<Movie> loadMovieByID(String id) {
        return database.movieDao().loadMovieByID(id);
    }

    public LiveData<List<Review>> loadAllReviewsByMovieID(String id) {
        return database.reviewDao().loadAllReviewsByMovieID(id);

    }
//    public Trailer getTrailersByID(String id){
//
//    }

    public void loadMovieReviewAndTrailerByID(String mMovieID) {
        api.getMovieReviewsAsync(mMovieID,API_KEY).enqueue(new Callback<ReviewRemoteList>() {
            @Override
            public void onResponse(Call<ReviewRemoteList> call, Response<ReviewRemoteList> response) {
                Log.d("123456", "review");
                ReviewRemoteList reviewRemoteList = response.body();
                List<ReviewRemote> reviewRemotes = reviewRemoteList.getReviewRemote();
                new SaveReviewsAsyncTask(reviewRemotes, database.reviewDao(),mMovieID).execute();
            }

            @Override
            public void onFailure(Call<ReviewRemoteList> call, Throwable t) {

            }
        });
    }


}
