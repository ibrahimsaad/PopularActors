package com.ibrahim.popularactors.ui.actors;


import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.arch.paging.PagedList;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ibrahim.popularactors.R;
import com.ibrahim.popularactors.repository.model.Actor;
import com.ibrahim.popularactors.repository.paging.ActorsRecyclerAdapter;
import com.ibrahim.popularactors.viewModel.ActorListViewModel;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;


public class PopularActorsFragment extends Fragment
        implements ActorsRecyclerAdapter.ItemClickListener {

    @BindView(R.id.popularActorsRecyclerView)
    RecyclerView popularActorsRecyclerView;
    Unbinder unbinder;
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

        //<editor-fold desc="Init recyclerView">
        popularActorsRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        popularActorsRecyclerView.setHasFixedSize(true);
        actorsRecyclerAdapter = new ActorsRecyclerAdapter(getActivity(), this);

        actorListViewModel = ViewModelProviders.of(this)
                .get(ActorListViewModel.class);

        actorListViewModel.itemPagedList.observe(this, new Observer<PagedList<Actor>>() {
            @Override
            public void onChanged(@Nullable PagedList<Actor> items) {
                actorsRecyclerAdapter.submitList(items);
            }
        });
        popularActorsRecyclerView.setAdapter(actorsRecyclerAdapter);
        //</editor-fold>

        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void OnItemClick(Actor actor) {

    }
}
