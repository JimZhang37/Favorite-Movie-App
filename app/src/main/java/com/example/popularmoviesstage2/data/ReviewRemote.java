package com.example.popularmoviesstage2.data;


import com.squareup.moshi.Json;


public class ReviewRemote {
    @Json(name = "author")
    String author;
    @Json(name = "content")
    String content;
    @Json(name = "id")
    String id;
    @Json(name = "url")
    String url;


}
