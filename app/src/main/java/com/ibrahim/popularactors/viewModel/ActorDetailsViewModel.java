package com.ibrahim.popularactors.viewModel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.ibrahim.popularactors.repository.ActorsRepository;
import com.ibrahim.popularactors.repository.model.ActorDetails;
import com.ibrahim.popularactors.repository.model.ActorImageResponse;

public class ActorDetailsViewModel extends AndroidViewModel {
    private ActorsRepository mRepository;
    private LiveData<ActorDetails> actorDetailsLiveData;
    private LiveData<ActorImageResponse> actorImagesLiveData;


    public ActorDetailsViewModel(@NonNull Application application) {
        super(application);
        mRepository = ActorsRepository.getInstance(application.getApplicationContext());

    }

    public LiveData<ActorDetails> getActorDetails(int actorId) {
        if (actorDetailsLiveData == null) {
            actorDetailsLiveData = mRepository.getActorDetails(actorId);
        }
        return actorDetailsLiveData;
    }

    public LiveData<ActorImageResponse> getActorImages(int actorId) {
        if (actorImagesLiveData == null) {
            actorImagesLiveData = mRepository.getActorImages(actorId);
        }
        return actorImagesLiveData;
    }


}


