package com.example.watchlater;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.List;

public class Saved_WatchedDetails extends AppCompatActivity implements DbManager.DbListener{
    Shows showDetails;
    Shows showGrabbed;
    TextView title;
    TextView rating;
    TextView summary;
    TextView genres;
    TextView premiered;
    TextView ended;
    TextView network;
    TextView country;
    ImageView image;
    DbManager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saved_watched_details);
        showDetails = getIntent().getParcelableExtra("show");
        title = findViewById(R.id.title_details);
        rating = findViewById(R.id.rating_details);
        summary = findViewById(R.id.summary_details);
        genres = findViewById(R.id.genre_details);
        premiered = findViewById(R.id.premiered_details);
        ended = findViewById(R.id.ended_details);
        network = findViewById(R.id.network_details);
        country = findViewById(R.id.country_details);
        image = findViewById(R.id.showCover_details);

        manager = ((AppManager)getApplication()).dbManager;
        manager.dbListener = this;
        manager.getDatabase(this);
        manager.getSingleShow(showDetails.name);

        Picasso.get().load(showDetails.image).resize(325,400).into(image);
        title.setText(showDetails.name);
        rating.setText("Rating: " + showDetails.rating + "/10");
        summary.setText("Summary: " + showDetails.summary);
        genres.setText("Genres: " + showDetails.genres);
        premiered.setText("Start Date: " + showDetails.premiered);

        if(showDetails.status == "Ended")
            ended.setText("End Date: " + showDetails.ended);
        else
            ended.setText("Status: " + showDetails.status);
        network.setText("Network: " + showDetails.network);
        country.setText("Country: " + showDetails.country);
        setTitle(showDetails.name);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.delete_change_watchedbutton, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        boolean check = false;

        if(id == R.id.deleteFromDbWatched) {
            manager.deleteShow(showGrabbed);
            return true;
        }
        else if(id == R.id.changedWatched){
            showGrabbed.watched = false;
            manager.updateShow(showGrabbed);
            return true;
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

    }

    @Override
    public void deleted(String name) {
        Intent intent = new Intent(this, SavedWatchedShows.class);
        Toast.makeText(this, showDetails.name + " has been removed", Toast.LENGTH_SHORT).show();
        manager.getAllWatchLaterShows();
        startActivity(intent);
    }

    @Override
    public void updated(String name) {
        Intent intent = new Intent(this, SavedWatchedShows.class);
        Toast.makeText(this, showGrabbed.name + " has been updated", Toast.LENGTH_SHORT).show();
        manager.getAllWatchLaterShows();
        System.out.print(showGrabbed.watched);
        startActivity(intent);
    }

    @Override
    public void singleShowReady(Shows show) {
        showGrabbed = show;
    }
}