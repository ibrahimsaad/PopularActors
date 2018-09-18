package com.ibrahim.popularactors.ui.actors;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ibrahim.popularactors.R;


public class SavedImagesFragment extends Fragment {


    public SavedImagesFragment() {
        // Required empty public constructor
    }


    public static SavedImagesFragment newInstance() {
        SavedImagesFragment fragment = new SavedImagesFragment();

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
        return inflater.inflate(R.layout.fragment_saved_images, container, false);
    }

}
