package com.example.popularmoviesstage2.data;

import com.squareup.moshi.Json;

public class TrailerRemote {

    @Json(name = "id")
    String id;
    @Json(name = "key")
    String key;
    @Json(name = "name")
    String name;
    @Json(name = "site")
    String site;
    @Json(name = "type")
    String type;
}
