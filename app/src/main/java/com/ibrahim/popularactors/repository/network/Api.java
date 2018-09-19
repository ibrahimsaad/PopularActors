package com.ibrahim.popularactors.repository.network;

import com.ibrahim.popularactors.repository.model.ActorDetails;
import com.ibrahim.popularactors.repository.model.ActorImageResponse;
import com.ibrahim.popularactors.repository.model.PopularActors;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface Api {

    @GET("person/popular")
    Call<PopularActors> fetchPopularActors(@Query("page") int page);

    @GET("person/{person_id}")
    Call<ActorDetails> getActorDetails(@Path("person_id") int personId);


    @GET("person/{person_id}/images")
    Call<ActorImageResponse> getActorImages(@Path("person_id") int personId);
}
