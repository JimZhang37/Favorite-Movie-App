package com.example.popularmoviesstage2.data.source;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.popularmoviesstage2.data.Movie;
import com.example.popularmoviesstage2.data.MovieList;
import com.example.popularmoviesstage2.data.source.Remote.MovieApiService;
import com.squareup.moshi.Moshi;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.moshi.MoshiConverterFactory;

public class DefaultRepository {
    private MutableLiveData<ArrayList<Movie>> topRated;
    public DefaultRepository(){
        topRated = new MutableLiveData<ArrayList<Movie>>();
    }

    public LiveData<ArrayList<Movie>> getTopRated() {
        return this.topRated;
    }

    public Call<MovieList> getMovieListCall() {
        String MOVIE_BASE_URL = "https://api.themoviedb.org/3/movie/";
        String API_KEY = "mmm";


        Moshi moshi = new Moshi.Builder()
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(MoshiConverterFactory.create(moshi))
                .baseUrl(MOVIE_BASE_URL)
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
                                 topRated.setValue(movieList.getMovies());
                             }

                             @Override
                             public void onFailure(Call<MovieList> call, Throwable t) {

                             }
                         }
                );

    }

}
