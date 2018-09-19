package com.ibrahim.popularactors.repository.paging;

import android.arch.lifecycle.MutableLiveData;
import android.arch.paging.DataSource;

public class ActorDataSourceFactory extends DataSource.Factory{

    private static final String TAG = ActorDataSourceFactory.class.getSimpleName();
    private MutableLiveData<ActorDataSource> networkStatus;
    private ActorDataSource moviesPageKeyedDataSource;


    public ActorDataSourceFactory() {
        this.networkStatus = new MutableLiveData<>();
        moviesPageKeyedDataSource = new ActorDataSource();
    }

    @Override
    public DataSource create() {
        networkStatus.postValue(moviesPageKeyedDataSource);
        return moviesPageKeyedDataSource;
    }


    public MutableLiveData<ActorDataSource> getNetworkStatus() {
        return networkStatus;
    }

}
