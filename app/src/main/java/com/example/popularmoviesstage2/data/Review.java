package com.example.popularmoviesstage2.data;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "reviews")
public class Review {
    @ColumnInfo(name = "author")
    String author;
    @ColumnInfo(name = "content")
    String content;
    @PrimaryKey
    @ColumnInfo(name = "id")
    @NonNull
    String id;
    @ColumnInfo(name = "url")
    String url;
    @ColumnInfo(name = "movie_id")
    String movieId;

    public Review(ReviewRemote review, String mMovieID) {
        this.author = review.author;
        this.content = review.content;
        this.id = review.id;
        this.url = review.url;
        this.movieId = mMovieID;
    }

    public Review(String author, String content, String id, String url, String movieId) {
        this.author = author;
        this.content = content;
        this.id = id;
        this.url = url;
        this.movieId = movieId;
    }

    public String getAuthor() {
        return author;
    }

    public String getContent() {
        return content;
    }

    @NonNull
    public String getId() {
        return id;
    }

    public String getUrl() {
        return url;
    }

    public String getMovieId() {
        return movieId;
    }
}
