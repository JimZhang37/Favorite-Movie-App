package com.example.popularmoviesstage2.data.source.Remote;

import com.example.popularmoviesstage2.data.MovieList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;


public interface MovieApiService {
    String PATH_TOP_RATED = "top_rated";
    String PATH_POPULAR = "popular";
    String PARAM_APIKEY = "api_key";

    @GET(PATH_POPULAR)
    Call<MovieList> getPopularMoviesAsync(@Query(PARAM_APIKEY)
                                                  String key);
    @GET(PATH_TOP_RATED)
    Call<MovieList> getTopRatedMoviesAsync(@Query(PARAM_APIKEY)
                                                  String key);
}
