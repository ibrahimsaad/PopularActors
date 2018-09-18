package com.ibrahim.popularactors.ui.actors;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuItem;

import com.ibrahim.popularactors.R;

public class ActorsListActivity extends AppCompatActivity {

    private SearchView searchView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actors_list);

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

        }
        return super.onOptionsItemSelected(item);
    }
    //</editor-fold>
}