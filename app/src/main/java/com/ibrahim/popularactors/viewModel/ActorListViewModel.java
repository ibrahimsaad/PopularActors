package com.ibrahim.popularactors.viewModel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.paging.PagedList;
import android.support.annotation.NonNull;

import com.ibrahim.popularactors.repository.NetworkState;
import com.ibrahim.popularactors.repository.ActorsRepository;
import com.ibrahim.popularactors.repository.model.Actor;

public class ActorListViewModel extends AndroidViewModel {


private ActorsRepository repository;

    public ActorListViewModel(@NonNull Application application) {
        super(application);
        repository = ActorsRepository.getInstance(application);
    }
    public LiveData<PagedList<Actor>> getActors() {
        return repository.getMovies();
    }

    public LiveData<NetworkState> getNetworkState() {
        return repository.getNetworkState();
    }

}
