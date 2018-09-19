package com.ibrahim.popularactors.ui.actors;


import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ibrahim.popularactors.repository.NetworkState;
import com.ibrahim.popularactors.R;
import com.ibrahim.popularactors.repository.model.Actor;
import com.ibrahim.popularactors.ui.adapter.ActorsRecyclerAdapter;
import com.ibrahim.popularactors.listeners.ItemClickListener;
import com.ibrahim.popularactors.ui.details.ActorDetailsActivity;
import com.ibrahim.popularactors.viewModel.ActorListViewModel;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;


public class PopularActorsFragment extends Fragment
        implements ItemClickListener {

    @BindView(R.id.popularActorsRecyclerView)
    RecyclerView popularActorsRecyclerView;
    Unbinder unbinder;
    @BindView(R.id.parentProgressView)
    ConstraintLayout parentProgressView;
    private ActorsRecyclerAdapter actorsRecyclerAdapter;
    private ActorListViewModel actorListViewModel;


    public PopularActorsFragment() {
    }

    public static PopularActorsFragment newInstance() {
        PopularActorsFragment fragment = new PopularActorsFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_popular_actors,
                container, false);
        unbinder = ButterKnife.bind(this, view);

        //<editor-fold desc="Init recyclerView and its adapter">
        popularActorsRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        popularActorsRecyclerView.setHasFixedSize(true);
        actorsRecyclerAdapter = new ActorsRecyclerAdapter(this);

        //</editor-fold>

        initViewModel();

        return view;
    }

    private void initViewModel() {
        actorListViewModel = ViewModelProviders.of(this)
                .get(ActorListViewModel.class);

        actorListViewModel.getActors().observe(this,
                actorsRecyclerAdapter::submitList);
        actorListViewModel.getNetworkState().observe(this,
                networkState -> {
                    if (networkState.getMsg() == NetworkState.SUCCESS_RUNNING) {
                        parentProgressView.setVisibility(View.VISIBLE);
                    } else {
                        parentProgressView.setVisibility(View.GONE);

                    }
                });
        popularActorsRecyclerView.setAdapter(actorsRecyclerAdapter);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void OnItemClick(Actor actor) {
        Intent mIntent = new Intent(getActivity(), ActorDetailsActivity.class);
        mIntent.putExtra("EXTRA", actor);
        startActivity(mIntent);

    }
}
