package com.example.popularmoviesstage2.data;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "trailers")
public class Trailer {
    @NonNull
    @PrimaryKey
    @ColumnInfo(name = "id")
    String id;
    @ColumnInfo(name = "key")
    String key;
    @ColumnInfo(name = "name")
    String name;
    @ColumnInfo(name = "site")
    String site;
    @ColumnInfo(name = "type")
    String type;
    @ColumnInfo(name = "movie_id")
    String movieId;

    public Trailer(String id, String key, String name, String site, String type, String movieId) {
        this.id = id;
        this.key = key;
        this.name = name;
        this.site = site;
        this.type = type;
        this.movieId = movieId;
    }

    public Trailer(TrailerRemote trailerRemote, String movieId) {
        this.id = trailerRemote.id;
        this.key = trailerRemote.key;
        this.name = trailerRemote.name;
        this.site = trailerRemote.site;
        this.type = trailerRemote.type;
        this.movieId = movieId;
    }

    public String getId() {
        return id;
    }

    public String getKey() {
        return key;
    }

    public String getName() {
        return name;
    }

    public String getSite() {
        return site;
    }

    public String getType() {
        return type;
    }

    public String getMovieId() {
        return movieId;
    }
}
