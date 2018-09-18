package com.ibrahim.popularactors.repository.network;

import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface Api {
    @GET("person/popular")
    Call<JsonObject> fetchPopularPersons(@Query("page") int page);
}
