package com.ibrahim.popularactors.repository.paging;

import android.arch.paging.PageKeyedDataSource;
import android.support.annotation.NonNull;

import com.ibrahim.popularactors.repository.model.Actor;
import com.ibrahim.popularactors.repository.model.PopularActors;
import com.ibrahim.popularactors.repository.network.RetrofitClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ActorDataSource extends PageKeyedDataSource<Integer, Actor> {

    private static final int FIRST_PAGE = 1;
    public static final int PAGE_SIZE = 20;

    @Override
    public void loadInitial(@NonNull LoadInitialParams<Integer> params,
                            @NonNull final LoadInitialCallback<Integer, Actor> callback) {

        RetrofitClient.getClient().fetchPopularActors(FIRST_PAGE)
                .enqueue(new Callback<PopularActors>() {
                    @Override
                    public void onResponse(Call<PopularActors> call,
                                           Response<PopularActors> response) {
                        if (response.body() != null) {
                            callback.onResult(response.body().getActors(),
                                    null, FIRST_PAGE + 1);
                        }
                    }

                    @Override
                    public void onFailure(Call<PopularActors> call, Throwable t) {

                    }
                });
    }

    @Override
    public void loadBefore(@NonNull final LoadParams<Integer> params,
                           @NonNull final LoadCallback<Integer, Actor> callback) {

        RetrofitClient.getClient().fetchPopularActors(params.key)
                .enqueue(new Callback<PopularActors>() {
                    @Override
                    public void onResponse(Call<PopularActors> call,
                                           Response<PopularActors> response) {

                        Integer adjacentKey = (params.key > 1) ? params.key - 1 : null;
                        if (response.body() != null) {
                            callback.onResult(response.body().getActors(), adjacentKey);
                        }
                    }

                    @Override
                    public void onFailure(Call<PopularActors> call, Throwable t) {

                    }
                });
    }

    @Override
    public void loadAfter(@NonNull final LoadParams<Integer> params,
                          @NonNull final LoadCallback<Integer, Actor> callback) {

        RetrofitClient.getClient().fetchPopularActors(params.key)
                .enqueue(new Callback<PopularActors>() {
                    @Override
                    public void onResponse(Call<PopularActors> call,
                                           Response<PopularActors> response) {
                        if (response.body() != null) {
                            Integer key = response.body().getPage() >=
                                    params.key ? params.key + 1 : null;
                            callback.onResult(response.body().getActors(), key);
                        }
                    }

                    @Override
                    public void onFailure(Call<PopularActors> call, Throwable t) {

                    }
                });
    }
}
