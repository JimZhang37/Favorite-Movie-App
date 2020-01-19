package com.example.popularmoviesstage2.data;

import com.squareup.moshi.Json;

import java.util.ArrayList;

public class MovieList {
    @Json(name = "page")
    String page;
    @Json(name = "results")
    ArrayList<Movie> results;

    public ArrayList<Movie> getMovies(){
        return results;
    }
}
