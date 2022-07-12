package com.example.watchlater;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ShowRecyclerAdapter extends RecyclerView.Adapter<ShowRecyclerAdapter.ShowViewHolder> {

    ArrayList<Shows> showlist;
    Context context;
    interface ShowListener{
        public void showClicked(Shows show);
    }

    ShowListener listener;
    public ShowRecyclerAdapter(ArrayList<Shows> shows, Context con){
        showlist = shows;
        context = con;
    }

    @NonNull
    @Override
    public ShowViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.show_row_details, parent, false);
        return new ShowViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ShowViewHolder holder, int position) {
        String genres = "Genres: ";
        String rating = "Rating: " + showlist.get(position).rating.toString() + "/10";
        genres += showlist.get(position).genres;

        Picasso.get().load(showlist.get(position).image).resize(300,350).into(holder.imageView);
        holder.genreText.setText(genres);
        holder.titleText.setText(showlist.get(position).name);
        holder.ratingText.setText(rating);
    }

    @Override
    public int getItemCount() { return showlist.size(); }

    class ShowViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView titleText;
        TextView ratingText;
        TextView genreText;
        ImageView imageView;
        public ShowViewHolder(@NonNull View itemView) {
            super(itemView);
            titleText = itemView.findViewById(R.id.title);
            ratingText = itemView.findViewById(R.id.rating);
            genreText = itemView.findViewById(R.id.genres);
            imageView = itemView.findViewById(R.id.showCover);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
           listener.showClicked(showlist.get(getAdapterPosition()));
        }
    }
}
