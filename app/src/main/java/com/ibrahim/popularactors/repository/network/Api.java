package com.ibrahim.popularactors.repository.network;

import com.ibrahim.popularactors.repository.model.PopularActors;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface Api {

    @GET("person/popular")
    Call<PopularActors> fetchPopularActors(@Query("page") int page);
}
