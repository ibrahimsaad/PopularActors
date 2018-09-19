package com.ibrahim.popularactors.repository;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MediatorLiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.paging.PagedList;
import android.content.Context;

import com.ibrahim.popularactors.repository.model.Actor;
import com.ibrahim.popularactors.repository.model.ActorDetails;
import com.ibrahim.popularactors.repository.model.ActorImageResponse;
import com.ibrahim.popularactors.repository.network.RetrofitClient;
import com.ibrahim.popularactors.repository.paging.ActorDataSourceFactory;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ActorsRepository {
    private static final String TAG = ActorsRepository.class.getSimpleName();
    private static ActorsRepository instance;
    private MoviesFromNetworkLayer moviesFromNetworkLayer;
    private MediatorLiveData liveDataMerger;

    private ActorsRepository(Context context) {

        ActorDataSourceFactory dataSourceFactory = new ActorDataSourceFactory();

        moviesFromNetworkLayer = new MoviesFromNetworkLayer(dataSourceFactory);
        liveDataMerger = new MediatorLiveData<>();
        liveDataMerger.addSource(moviesFromNetworkLayer.getActorsPaged(), value -> {
            liveDataMerger.postValue(value);
        });
    }

    public static ActorsRepository getInstance(Context context) {
        if (instance == null) {
            instance = new ActorsRepository(context);
        }
        return instance;
    }

    public LiveData<PagedList<Actor>> getMovies() {
        return liveDataMerger;
    }

    public LiveData<NetworkState> getNetworkState() {

        return moviesFromNetworkLayer.getNetworkState();
    }

    public LiveData<ActorDetails> getActorDetails(int actorId) {
        final MutableLiveData<ActorDetails> data = new MutableLiveData<>();

        RetrofitClient.getClient().getActorDetails(actorId)
                .enqueue(new Callback<ActorDetails>() {
                    @Override
                    public void onResponse(Call<ActorDetails> call,
                                           Response<ActorDetails> response) {
                        if (response.isSuccessful()) {
                            data.setValue(response.body());
                        }
                    }

                    @Override
                    public void onFailure(Call<ActorDetails> call, Throwable t) {
                        data.setValue(null);

                    }
                });
        return data;
    }

    public LiveData<ActorImageResponse> getActorImages(int actorId) {
        final MutableLiveData<ActorImageResponse> data = new MutableLiveData<>();

        RetrofitClient.getClient().getActorImages(actorId)
                .enqueue(new Callback<ActorImageResponse>() {
                    @Override
                    public void onResponse(Call<ActorImageResponse> call,
                                           Response<ActorImageResponse> response) {
                        if (response.isSuccessful()) {
                            data.setValue(response.body());
                        }
                    }

                    @Override
                    public void onFailure(Call<ActorImageResponse> call, Throwable t) {
                        data.setValue(null);

                    }
                });
        return data;
    }
}
