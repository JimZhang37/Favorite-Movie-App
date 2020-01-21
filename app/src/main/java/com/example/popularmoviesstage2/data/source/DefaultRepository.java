package com.example.popularmoviesstage2.data.source;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.popularmoviesstage2.data.Movie;
import com.example.popularmoviesstage2.data.MovieList;
import com.example.popularmoviesstage2.data.source.Remote.MovieApiService;
import com.example.popularmoviesstage2.data.source.local.MovieDatabase;
import com.squareup.moshi.Moshi;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.moshi.MoshiConverterFactory;

public class DefaultRepository {
    private LiveData<List<Movie>> topRated;
    private MovieDatabase database;
    public DefaultRepository(MovieDatabase db){
        this.database = db;

        topRated = database.movieDao().loadAllMovies();
    }

    public LiveData<List<Movie>> getTopRated() {
        return this.topRated;
    }

    public Call<MovieList> getMovieListCall() {
        String MOVIE_BASE_URL = "https://api.themoviedb.org/3/movie/";
        String API_KEY = "aaa";


        Moshi moshi = new Moshi.Builder()
                .build();

        Retrofit retrofit = new Retrofit
                .Builder()
                .baseUrl(MOVIE_BASE_URL)
                .addConverterFactory
                        (MoshiConverterFactory.create(moshi))
                .build();

        MovieApiService api = retrofit.create(MovieApiService.class);

        Call<MovieList> movieList = api.getTopRatedMoviesAsync(API_KEY);

        return movieList;
    }

    public void getValue() {
        getMovieListCall()
                .enqueue(new Callback<MovieList>() {

                             @Override
                             public void onResponse(Call<MovieList> call, Response<MovieList> response) {
                                 MovieList movieList = response.body();
                                 List<Movie> list = movieList.getMovies();
                                 list.forEach(movie -> database.movieDao().insertMovie(movie));
//                                 topRated.setValue(movieList.getMovies());
                             }

                             @Override
                             public void onFailure(Call<MovieList> call, Throwable t) {

                             }
                         }
                );

    }

}
