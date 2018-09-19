package com.ibrahim.popularactors.ui.details;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ibrahim.popularactors.R;

public class ActorDetailsFragment extends Fragment {

    public ActorDetailsFragment() {
        // Required empty public constructor
    }

    public static ActorDetailsFragment newInstance() {
        ActorDetailsFragment fragment = new ActorDetailsFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_actor_details, container, false);
    }


}
