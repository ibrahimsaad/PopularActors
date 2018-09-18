package com.ibrahim.popularactors.ui.actors;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.FrameLayout;

import com.ibrahim.popularactors.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ActorsListActivity extends AppCompatActivity {

    @BindView(R.id.main_layout_container)
    FrameLayout mainLayoutContainer;
    private SearchView searchView;
    private SavedImagesFragment savedImagesFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actors_list);
        ButterKnife.bind(this);
        PopularActorsFragment popularActorsFragment = PopularActorsFragment.newInstance();

        getSupportFragmentManager().beginTransaction()
                .add(mainLayoutContainer.getId(), popularActorsFragment)
                .commit();

    }

    //<editor-fold desc="Option menu items creation and click">
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_popular_actors, menu);
        MenuItem search = menu.findItem(R.id.action_search);
        searchView = (SearchView) search.getActionView();
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.action_saved_images:
                if (savedImagesFragment == null) {
                    savedImagesFragment = SavedImagesFragment.newInstance();
                }
                addFragmentOnTop(savedImagesFragment);

                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
    //</editor-fold>


    @Override
    public void onBackPressed() {
        super.onBackPressed();

    }


    public void addFragmentOnTop(Fragment fragment) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(mainLayoutContainer.getId(), fragment)
                .addToBackStack(null)
                .commit();
    }




}
