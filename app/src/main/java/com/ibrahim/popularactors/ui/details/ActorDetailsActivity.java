package com.ibrahim.popularactors.ui.details;

import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.FrameLayout;

import com.ibrahim.popularactors.R;
import com.ibrahim.popularactors.repository.model.Actor;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ActorDetailsActivity extends AppCompatActivity implements
        ActorDetailsFragment.OnFragmentInteractionListener {

    @BindView(R.id.main_layout_container)
    FrameLayout mainLayoutContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actor_details);
        ButterKnife.bind(this);
        Actor actor = getIntent().getExtras().getParcelable("EXTRA");
        getSupportActionBar().setTitle(actor.getName());
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        ActorDetailsFragment actorDetailsFragment =
                ActorDetailsFragment.newInstance(actor.getId());

        getSupportFragmentManager().beginTransaction()
                .add(mainLayoutContainer.getId(), actorDetailsFragment)
                .commit();

    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void addFragmentOnTop(Fragment fragment) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(mainLayoutContainer.getId(), fragment)
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void onFragmentInteraction(String imageUrl) {

        FullScreenImageFragment fullScreenImageFragment =
                FullScreenImageFragment.newInstance(imageUrl);
        addFragmentOnTop(fullScreenImageFragment);

    }
}
