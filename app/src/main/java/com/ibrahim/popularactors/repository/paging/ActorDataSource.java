package com.ibrahim.popularactors.repository.paging;

import android.arch.lifecycle.MutableLiveData;
import android.arch.paging.PageKeyedDataSource;
import android.support.annotation.NonNull;

import com.ibrahim.popularactors.repository.NetworkState;
import com.ibrahim.popularactors.repository.model.Actor;
import com.ibrahim.popularactors.repository.model.PopularActors;
import com.ibrahim.popularactors.repository.network.RetrofitClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ActorDataSource extends PageKeyedDataSource<Integer, Actor> {

    private static final int FIRST_PAGE = 1;

    private static final String TAG = ActorDataSource.class.getSimpleName();
    private final MutableLiveData networkState;

    public ActorDataSource() {
        networkState = new MutableLiveData();

    }

    public MutableLiveData getNetworkState() {

        return networkState;
    }

    @Override
    public void loadInitial(@NonNull LoadInitialParams<Integer> params,
                            @NonNull final LoadInitialCallback<Integer, Actor> callback) {
        networkState.postValue(NetworkState.LOADING);
        RetrofitClient.getClient().fetchPopularActors(FIRST_PAGE)
                .enqueue(new Callback<PopularActors>() {
                    @Override
                    public void onResponse(Call<PopularActors> call,
                                           Response<PopularActors> response) {
                        if (response.body() != null) {
                            callback.onResult(response.body().getActors(),
                                    null, FIRST_PAGE + 1);
                            networkState.postValue(NetworkState.LOADED);

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
    }

    @Override
    public void loadAfter(@NonNull final LoadParams<Integer> params,
                          @NonNull final LoadCallback<Integer, Actor> callback) {
        networkState.postValue(NetworkState.LOADING);

        RetrofitClient.getClient().fetchPopularActors(params.key)
                .enqueue(new Callback<PopularActors>() {
                    @Override
                    public void onResponse(Call<PopularActors> call,
                                           Response<PopularActors> response) {
                        if (response.body() != null) {
                            Integer key = response.body().getPage() >=
                                    params.key ? params.key + 1 : null;
                            callback.onResult(response.body().getActors(), key);
                            networkState.postValue(NetworkState.LOADED);
                        }
                    }

                    @Override
                    public void onFailure(Call<PopularActors> call, Throwable t) {

                    }
                });
    }
}
