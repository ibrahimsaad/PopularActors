package com.ibrahim.popularactors.viewModel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;
import android.arch.paging.LivePagedListBuilder;
import android.arch.paging.PageKeyedDataSource;
import android.arch.paging.PagedList;

import com.ibrahim.popularactors.repository.model.Actor;
import com.ibrahim.popularactors.repository.paging.ActorDataSource;
import com.ibrahim.popularactors.repository.paging.ActorDataSourceFactory;

public class ActorListViewModel extends ViewModel {

    public LiveData<PagedList<Actor>> itemPagedList;

    public ActorListViewModel() {
        //getting our data source factory
        ActorDataSourceFactory itemDataSourceFactory = new ActorDataSourceFactory();
//        LiveData<PageKeyedDataSource<Integer, Actor>> liveDataSource =
//                itemDataSourceFactory.getItemLiveDataSource();
        PagedList.Config pagedListConfig =
                (new PagedList.Config.Builder())
                        .setEnablePlaceholders(false)
                        .setPageSize(ActorDataSource.PAGE_SIZE).build();
        itemPagedList = (new LivePagedListBuilder(itemDataSourceFactory, pagedListConfig))
                .build();
    }
    public void updateData (){

    }
}
