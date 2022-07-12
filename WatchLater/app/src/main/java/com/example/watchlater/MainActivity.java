package com.example.watchlater;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements DbManager.DbListener, ShowRecyclerAdapter.ShowListener{
    RecyclerView unwatchedShows;
    List<Shows> unwatchedSavedShows;
    DbManager dbManager;
    ShowRecyclerAdapter showAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dbManager = ((AppManager)getApplication()).dbManager;
        dbManager.getDatabase(this);
        dbManager.dbListener = this;
        showAdapter = new ShowRecyclerAdapter(((AppManager)getApplication()).unwatchedShows, this);
        showAdapter.listener =this;
        unwatchedShows = findViewById(R.id.savedUnwatchedShows);
        unwatchedShows.setLayoutManager(new LinearLayoutManager(this));
        unwatchedSavedShows = ((AppManager)getApplication()).unwatchedShows;
        unwatchedShows.setAdapter(showAdapter);
        dbManager.getAllWatchLaterShows();
        setTitle("Watch Later");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.dropdown, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        boolean check = false;
        Intent intent = new Intent(this, ShowsActivity.class);
        if(id == R.id.GoToShowList) {
            startActivity(intent);
        }
        else if(id == R.id.goToWatchedList){
            Intent intent_watched = new Intent(this, SavedWatchedShows.class);
            startActivity(intent_watched);
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

    }

    @Override
    public void unwatchedListReady(List<Shows> showList) {
        unwatchedSavedShows = showList;
        ((AppManager)getApplication()).unwatchedShows = (ArrayList<Shows>)unwatchedSavedShows;
        showAdapter.showlist = new ArrayList<>(showList);
        showAdapter.notifyDataSetChanged();
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
        Intent intent = new Intent(this, Saved_ShowDetails.class);
        intent.putExtra("show", show);

        startActivity(intent);
    }
}