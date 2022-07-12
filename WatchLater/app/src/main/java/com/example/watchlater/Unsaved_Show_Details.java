package com.example.watchlater;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class Unsaved_Show_Details extends AppCompatActivity implements DbManager.DbListener {
    Shows showDetails;
    List<Shows> allSavedShows;
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
        setContentView(R.layout.activity_unsaved_show_details);
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
        manager.getDatabase(this);
        manager.dbListener = this;

        manager.getAllShows();
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
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.add_button, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        boolean check = false;
        Intent intent = new Intent(this, ShowsActivity.class);
        if(id == R.id.addButton) {
            if(((AppManager)getApplication()).savedShows != null) {
                for(int i = 0; i < ((AppManager)getApplication()).savedShows.size(); i++){
                    if(((AppManager)getApplication()).savedShows.get(i).id == showDetails.id){
                        check = true;
                        break;
                    }
                }
                if(check) {
                    Toast.makeText(this, showDetails.name + " has already been added to your Watch Later list", Toast.LENGTH_SHORT).show();
                    return true;
                }
                else{
                    manager.addNewShow(showDetails);
                    startActivity(intent);
                    return true;
                }
            }
            else{
                manager.addNewShow(showDetails);
                startActivity(intent);
                return true;
            }
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void added(String name) {
        Toast.makeText(this, name + " has been added to your Watch Later list", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void listReady(List<Shows> showList) {
        this.allSavedShows = showList;
        ((AppManager)getApplication()).savedShows = (ArrayList<Shows>) allSavedShows;
    }

    @Override
    public void watchedListReady(List<Shows> showList) {

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
}