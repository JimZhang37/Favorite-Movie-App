package com.example.popularmoviesstage2.data.source;

import android.util.Log;

import androidx.lifecycle.LiveData;

import com.example.popularmoviesstage2.data.Movie;
import com.example.popularmoviesstage2.data.MovieList;
import com.example.popularmoviesstage2.data.Movie_Favorite;
import com.example.popularmoviesstage2.data.Review;
import com.example.popularmoviesstage2.data.ReviewRemote;
import com.example.popularmoviesstage2.data.ReviewRemoteList;
import com.example.popularmoviesstage2.data.Trailer;
import com.example.popularmoviesstage2.data.TrailerRemote;
import com.example.popularmoviesstage2.data.TrailerRemoteList;
import com.example.popularmoviesstage2.data.source.Remote.MovieApiService;
import com.example.popularmoviesstage2.data.source.async.DeleteFavoriteAsyncTask;
import com.example.popularmoviesstage2.data.source.async.SaveFavoriteAsyncTask;
import com.example.popularmoviesstage2.data.source.async.SaveMovieAsyncTask;
import com.example.popularmoviesstage2.data.source.async.SaveReviewsAsyncTask;
import com.example.popularmoviesstage2.data.source.async.SaveTrailersAsyncTask;
import com.example.popularmoviesstage2.data.source.local.MovieDatabase;
import com.squareup.moshi.Moshi;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.moshi.MoshiConverterFactory;

public class DefaultRepository {
    //    private LiveData<List<Movie>> topRated;
    private MovieDatabase database;
    private MovieApiService api;
    private String API_KEY = "758f975f610e3d276c8f2364e5052672";

    private LiveData<List<Movie>> topRated;
    private LiveData<List<Movie>> popular;
    private LiveData<List<Movie>> favorite;
    public DefaultRepository(MovieDatabase db) {
        this.database = db;

        topRated = database.movieDao().observeMoviesTopRated();
        popular = database.movieDao().observeMoviesPopular();
        favorite = database.movieDao().observeMoviesFavorite();

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
        return database.movieDao().observeMoviesTopRated();
    }

    public LiveData<List<Movie>> getPopular() {
        return popular;
    }

    public LiveData<List<Movie>> getFavorite() {
        return favorite;
    }

    public void loadMovies() {
        Call<MovieList> movieTopRatedList = api.getTopRatedMoviesAsync(API_KEY);
        movieTopRatedList
                .enqueue(new Callback<MovieList>() {

                             @Override
                             public void onResponse(Call<MovieList> call, Response<MovieList> response) {
                                 MovieList movieList = response.body();
                                 List<Movie> list = movieList.getMovies();
//                                 list.forEach(movie -> database.movieDao().insertMovie(movie));
//                                 topRated.setValue(movieList.getMovies());
                                 new SaveMovieAsyncTask(movieList.getMovies(), database.movieDao(), 2).execute();
                             }

                             @Override
                             public void onFailure(Call<MovieList> call, Throwable t) {

                             }
                         }
                );
        Call<MovieList> moviePopularList = api.getPopularMoviesAsync(API_KEY);
        moviePopularList
                .enqueue(new Callback<MovieList>() {

                             @Override
                             public void onResponse(Call<MovieList> call, Response<MovieList> response) {
                                 MovieList movieList = response.body();
                                 List<Movie> list = movieList.getMovies();
//                                 list.forEach(movie -> database.movieDao().insertMovie(movie));
//                                 topRated.setValue(movieList.getMovies());
                                 new SaveMovieAsyncTask(movieList.getMovies(), database.movieDao(), 1).execute();
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

    public LiveData<List<Trailer>> loadAllTrailersByMovieID(String id) {
        return database.trailerDao().loadAllTrailersByMovieID(id);
    }
//    public Trailer getTrailersByID(String id){
//
//    }

    public void loadMovieReviewAndTrailerByID(String mMovieID) {
        api.getMovieReviewsAsync(mMovieID, API_KEY).enqueue(new Callback<ReviewRemoteList>() {
            @Override
            public void onResponse(Call<ReviewRemoteList> call, Response<ReviewRemoteList> response) {
                Log.d("123456", "review");
                ReviewRemoteList reviewRemoteList = response.body();
                List<ReviewRemote> reviewRemotes = reviewRemoteList.getReviewRemote();
                new SaveReviewsAsyncTask(reviewRemotes, database.reviewDao(), mMovieID).execute();
            }

            @Override
            public void onFailure(Call<ReviewRemoteList> call, Throwable t) {

            }
        });

        api.getMovieTrailersAsync(mMovieID, API_KEY).enqueue(new Callback<TrailerRemoteList>() {
            @Override
            public void onResponse(Call<TrailerRemoteList> call, Response<TrailerRemoteList> response) {
                TrailerRemoteList trailerRemoteList = response.body();
                List<TrailerRemote> trailerRemotes = trailerRemoteList.getTrailerRemote();
                new SaveTrailersAsyncTask(trailerRemotes, database.trailerDao(), mMovieID).execute();
            }

            @Override
            public void onFailure(Call<TrailerRemoteList> call, Throwable t) {

            }
        });
    }

    public void saveFavorite(Movie_Favorite movie) {
        new SaveFavoriteAsyncTask(database.favoriteDao(), movie).execute();
    }

    public void deleteFavorite(Movie_Favorite movie) {
        new DeleteFavoriteAsyncTask(database.favoriteDao(),movie).execute();
    }

    public LiveData<Movie_Favorite> queryFavorite(String movieId) {
        return database.favoriteDao().queryFavorite(movieId);
    }


}
