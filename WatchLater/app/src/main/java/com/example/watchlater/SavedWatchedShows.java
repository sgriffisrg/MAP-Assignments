package com.example.watchlater;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.ClipData;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;

public class SavedWatchedShows extends AppCompatActivity implements DbManager.DbListener, ShowRecyclerAdapter.ShowListener {
    RecyclerView watchedShows;
    List<Shows> watchedSavedShows;
    DbManager dbManager;
    ShowRecyclerAdapter showAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saved_watched_shows);
        dbManager = ((AppManager)getApplication()).dbManager;
        dbManager.getDatabase(this);
        dbManager.dbListener = this;
        showAdapter = new ShowRecyclerAdapter(((AppManager)getApplication()).watchedShows, this);
        showAdapter.listener =this;
        watchedShows = findViewById(R.id.savedWatchedShows);
        watchedShows.setLayoutManager(new LinearLayoutManager(this));
        watchedSavedShows = ((AppManager)getApplication()).watchedShows;
        watchedShows.setAdapter(showAdapter);
        dbManager.getAllWatchedShows();
        setTitle("Finished Shows");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.dropdown_watched, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        boolean check = false;
        Intent intent = new Intent(this, MainActivity.class);
        if(id == R.id.goToUnwatchedList) {
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void added(String name) {

    }

    @Override
    public void listReady(List<Shows> showList) {

    }

    @Override
    public void watchedListReady(List<Shows> showList) {
        watchedSavedShows = showList;
        ((AppManager)getApplication()).watchedShows = (ArrayList<Shows>)watchedSavedShows;
        showAdapter.showlist = new ArrayList<>(showList);
        showAdapter.notifyDataSetChanged();
    }

    @Override
    public void unwatchedListReady(List<Shows> showList) {

    }

    @Override
    public void deleted(String name) {

    }

    @Override
    public void updated(String name) {

    }

    @Override
    public void singleShowReady(Shows show) {

    }

    @Override
    public void showClicked(Shows show) {
        Intent intent = new Intent(this, Saved_WatchedDetails.class);
        intent.putExtra("show", show);

        startActivity(intent);
    }
}