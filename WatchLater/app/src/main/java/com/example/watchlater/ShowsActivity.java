package com.example.watchlater;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import androidx.appcompat.widget.SearchView;

import java.util.ArrayList;

public class ShowsActivity extends AppCompatActivity implements NetworkingService.NetworkingListener, ShowRecyclerAdapter.ShowListener {
    ArrayList<Shows> showList = new ArrayList<Shows>();
    RecyclerView unsavedShowList;
    ShowRecyclerAdapter recyclerAdapter;
    NetworkingService networkingService;
    JSONService jsonService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shows);
        jsonService = ((AppManager)getApplication()).getJsonService();
        networkingService = ((AppManager)getApplication()).getNetworkingService();
        unsavedShowList = findViewById(R.id.UnsavedShowList);
        unsavedShowList.setLayoutManager(new LinearLayoutManager(this));
        recyclerAdapter = new ShowRecyclerAdapter(showList, this);
        recyclerAdapter.listener = this;
        networkingService.listener = this;
        networkingService.getAllShows();
        setTitle("Find new shows!");

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.search_menu, menu);

        MenuItem searchViewMenuItem = menu.findItem(R.id.search);

        SearchView searchView = (SearchView) searchViewMenuItem.getActionView();
        String searchFor = searchView.getQuery().toString();
        if (!searchFor.isEmpty()) {
            searchView.setIconified(false);
            searchView.setQuery(searchFor, false);
        }

        searchView.setQueryHint("Look up shows");
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                Log.d("query", query);

                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                networkingService.SearchShows(newText);
                return true;
            }
        });
        return true;
    }

    @Override
    public void JSONListener(String json, int type) {
        if(type == 1)
            showList = jsonService.getShows(json);
        else
            showList = jsonService.getSearchedShows(json);
        System.out.println(showList.size());
        recyclerAdapter = new ShowRecyclerAdapter(showList, this);
        recyclerAdapter.listener = this;
        unsavedShowList.setAdapter(recyclerAdapter);
        recyclerAdapter.notifyDataSetChanged();
    }

    @Override
    public void showClicked(Shows show) {

        Intent intent = new Intent(this, Unsaved_Show_Details.class);
        intent.putExtra("show", show);

        startActivity(intent);
    }
}