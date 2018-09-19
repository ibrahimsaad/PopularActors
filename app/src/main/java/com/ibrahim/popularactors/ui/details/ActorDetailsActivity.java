package com.ibrahim.popularactors.ui.details;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.FrameLayout;

import com.ibrahim.popularactors.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ActorDetailsActivity extends AppCompatActivity {

    @BindView(R.id.main_layout_container)
    FrameLayout mainLayoutContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actor_details);
        ButterKnife.bind(this);

    }
}
