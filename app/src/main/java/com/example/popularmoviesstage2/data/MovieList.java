package com.example.popularmoviesstage2.data;

import com.squareup.moshi.Json;

import java.util.ArrayList;
import java.util.List;

public class MovieList {
    @Json(name = "page")
    String page;
    @Json(name = "results")
    List<Movie> results;

    public List<Movie> getMovies(){
        return results;
    }
}
