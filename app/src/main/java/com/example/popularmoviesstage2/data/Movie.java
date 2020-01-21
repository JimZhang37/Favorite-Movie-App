package com.example.popularmoviesstage2.data;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.squareup.moshi.Json;

@Entity(tableName = "movies")
public class Movie {
    @PrimaryKey
    @NonNull
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

    public String getMovieID(){
        return mMovieID;
    }

    public String getTitle() {
        return mTitle;
    }

    public String getSynopsis() {
        return mSynopsis;
    }

    public String getUserRating() {
        return mUserRating;
    }

    public String getReleaseDate() {
        return mReleaseDate;
    }

    public String getImage() {
        return mImage;
    }

    public Movie( String mMovieID, String mImage, String mTitle, String mSynopsis, String mUserRating, String mReleaseDate) {

        this.mMovieID = mMovieID;
        this.mImage = mImage;
        this.mTitle = mTitle;
        this.mSynopsis = mSynopsis;
        this.mUserRating = mUserRating;
        this.mReleaseDate = mReleaseDate;
    }

}