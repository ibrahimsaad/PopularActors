package com.ibrahim.popularactors.ui.details;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.ibrahim.popularactors.R;
import com.ibrahim.popularactors.repository.model.ActorDetails;
import com.ibrahim.popularactors.repository.model.ActorImageResponse;
import com.ibrahim.popularactors.ui.adapter.ImagesRecyclerAdapter;
import com.ibrahim.popularactors.viewModel.ActorDetailsViewModel;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class ActorDetailsFragment extends Fragment implements ImagesRecyclerAdapter.OnItemClickListener {

    @BindView(R.id.actorBanner)
    ImageView actorBanner;
    @BindView(R.id.birthDateLabel)
    TextView birthDateLabel;
    @BindView(R.id.birthDateValue)
    TextView birthDateValue;
    @BindView(R.id.biographyLabel)
    TextView biographyLabel;
    @BindView(R.id.biographyValue)
    TextView biographyValue;
    @BindView(R.id.imagesRecyclerView)
    RecyclerView imagesRecyclerView;
    @BindView(R.id.progress_bar)
    ProgressBar progressBar;
    Unbinder unbinder;
    @BindView(R.id.progressLayoutContainer)
    ConstraintLayout progressLayoutContainer;
    private ActorDetailsViewModel actorDetailsViewModel;
    private static final String BASE_IMAGE_URL = "https://image.tmdb.org/t/p/original";
    private static final String ACTOR_ID_RECIVED = "actorIdRecived";
    private int actorId;
    private ImagesRecyclerAdapter imagesRecyclerAdapter;
    private OnFragmentInteractionListener mListener;


    public ActorDetailsFragment() {
        // Required empty public constructor
    }

    public static ActorDetailsFragment newInstance(int actorId) {
        ActorDetailsFragment fragment = new ActorDetailsFragment();
        Bundle args = new Bundle();
        args.putInt(ACTOR_ID_RECIVED, actorId);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            actorId = getArguments().getInt(ACTOR_ID_RECIVED);
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_actor_details, container, false);
        unbinder = ButterKnife.bind(this, view);
        actorDetailsViewModel = ViewModelProviders.of(this).get(ActorDetailsViewModel.class);
        progressLayoutContainer.setVisibility(View.VISIBLE);
        observeViewModel(actorDetailsViewModel, actorId);


        imagesRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 3));
        imagesRecyclerView.setHasFixedSize(true);

        return view;
    }


    private void observeViewModel(ActorDetailsViewModel viewModel, int actorId) {
        // Update the list when the data changes
        viewModel.getActorDetails(actorId).observe(this, new Observer<ActorDetails>() {
            @Override
            public void onChanged(@Nullable ActorDetails actorDetails) {
                if (actorDetails != null) {
                    Glide.with(getActivity())
                            .load(BASE_IMAGE_URL + actorDetails.getProfilePath())
                            .into(actorBanner);
                    birthDateValue.setText(actorDetails.getBirthday());
                    biographyValue.setText(actorDetails.getBiography());
                }
            }
        });


        viewModel.getActorImages(actorId).observe(this, new Observer<ActorImageResponse>() {
            @Override
            public void onChanged(@Nullable ActorImageResponse actorImageResponse) {
                if (actorImageResponse != null) {

                    initRecyclerView(actorImageResponse);
                    progressLayoutContainer.setVisibility(View.GONE);

                }
            }
        });
    }

    private void initRecyclerView(@NonNull ActorImageResponse actorImageResponse) {
            imagesRecyclerAdapter = new ImagesRecyclerAdapter(
                    actorImageResponse.getActorImageList(), getActivity(), this);
            imagesRecyclerView.setAdapter(imagesRecyclerAdapter);
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onImageItemClick(String url, int position) {

        onButtonPressed(url);
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(String imageUrl);
    }


    public void onButtonPressed(String imageUrl) {
        if (mListener != null) {
            mListener.onFragmentInteraction(imageUrl);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
