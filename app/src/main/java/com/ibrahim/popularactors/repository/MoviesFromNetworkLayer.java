package com.ibrahim.popularactors.repository;

import android.arch.core.util.Function;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Transformations;
import android.arch.paging.LivePagedListBuilder;
import android.arch.paging.PagedList;

import com.ibrahim.popularactors.repository.model.Actor;
import com.ibrahim.popularactors.repository.paging.ActorDataSource;
import com.ibrahim.popularactors.repository.paging.ActorDataSourceFactory;

public class MoviesFromNetworkLayer {


    final private static String TAG = MoviesFromNetworkLayer.class.getSimpleName();
    private LiveData<PagedList<Actor>> moviesPaged;
    private LiveData<NetworkState> networkState;
    private static final int PAGE_SIZE = 10;


    public MoviesFromNetworkLayer(ActorDataSourceFactory dataSourceFactory) {

        PagedList.Config pagedListConfig = (new PagedList.Config.Builder())
                .setEnablePlaceholders(false)
                .setInitialLoadSizeHint(PAGE_SIZE)
                .setPageSize(PAGE_SIZE)
                .build();

        networkState = Transformations.switchMap(dataSourceFactory.getNetworkStatus(),
                (Function<ActorDataSource, LiveData<NetworkState>>)
                        ActorDataSource::getNetworkState);

        LivePagedListBuilder livePagedListBuilder =
                new LivePagedListBuilder(dataSourceFactory, pagedListConfig);

        moviesPaged = livePagedListBuilder
                .build();

    }

    public LiveData<PagedList<Actor>> getActorsPaged() {
        return moviesPaged;
    }

    public LiveData<NetworkState> getNetworkState() {
        return networkState;
    }

}