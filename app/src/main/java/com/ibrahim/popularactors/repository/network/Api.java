package com.ibrahim.popularactors.repository.network;

import com.google.gson.JsonObject;
import com.ibrahim.popularactors.repository.model.PopularActors;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface Api {

    @GET("person/popular")
    Call<PopularActors> fetchPopularActors(@Query("page") int page);

    @GET("person/{person_id}")
    Call<JsonObject> getActorDetails(@Path("userid") int userId);
}
