package com.ibrahim.popularactors.repository.paging;

import android.arch.lifecycle.MutableLiveData;
import android.arch.paging.DataSource;
import android.arch.paging.PageKeyedDataSource;

import com.ibrahim.popularactors.repository.model.Actor;

public class ActorDataSourceFactory extends DataSource.Factory{
    private MutableLiveData<PageKeyedDataSource<Integer, Actor>> itemLiveDataSource
            = new MutableLiveData<>();

    @Override
    public DataSource create() {
        ActorDataSource actorDataSource = new ActorDataSource();
        itemLiveDataSource.postValue(actorDataSource);

        return actorDataSource;
    }

    public MutableLiveData<PageKeyedDataSource<Integer, Actor>> getItemLiveDataSource() {
        return itemLiveDataSource;
    }
}
