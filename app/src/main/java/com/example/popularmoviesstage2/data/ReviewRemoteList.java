package com.example.popularmoviesstage2.data;

import com.squareup.moshi.Json;

import java.util.List;

public class ReviewRemoteList {

    @Json(name = "id")
    String id;
    @Json(name = "page")
    String page;
    @Json(name = "results")
    List<ReviewRemote> results;
    @Json(name = "total_pages")
    String total_pages;
    @Json(name = "total_results")
    String total_results;

    public List<ReviewRemote> getReviewRemote() {
        return results;
    }
}
