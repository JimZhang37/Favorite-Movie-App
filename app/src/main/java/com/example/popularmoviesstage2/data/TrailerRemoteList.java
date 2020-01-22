package com.example.popularmoviesstage2.data;

import com.squareup.moshi.Json;

import java.util.List;

public class TrailerRemoteList {
    @Json(name = "id")
    String id;
    @Json(name = "results")
    List<TrailerRemote> results;

    public List<TrailerRemote> getTrailerRemote(){
        return results;
    }
}
