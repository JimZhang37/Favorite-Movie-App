package com.example.popularmoviesstage2.data;

import com.squareup.moshi.Json;

public class Movie {
    @Json(name = "id")
    String mMovieID;
    @Json(name = "poster_path")
    String mImage;
    @Json(name = "title")
    String mTitle;
    @Json(name = "overview")
    String mSynopsis;
    @Json(name = "vote_average")
    String mUserRating;
    @Json(name = "release_date")
    String mReleaseDate;

    public String getImage() {
        return mImage;
    }
}