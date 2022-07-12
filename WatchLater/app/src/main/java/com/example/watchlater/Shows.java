package com.example.watchlater;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import java.util.ArrayList;

@Entity(indices = {@Index(value = {"id"}, unique = true)})
public class Shows implements Parcelable {

    public Shows(int id, String name, String genres, String status, int runtime, String premiered, String ended, String network, String image, String summary, Double rating, String country){
        this.id = id;
        this.name = name;
        this.genres = genres;
        this.status = status;
        this.runtime = runtime;
        this.premiered = premiered;
        this.ended = ended;
        this.network = network;
        this.image = image;
        this.summary = summary;
        this.rating = rating;
        this.country = country;
    }

    @PrimaryKey(autoGenerate = true)
    public int showId;

    public int id;

    public String name;

    public String genres;

    public String status;

    public int runtime;

    public String premiered;

    public String ended;

    public String network;

    public String country;

    public String image;

    public String summary;

    public Double rating;

    public Boolean watched = false;

    protected Shows(Parcel in) {
        id = in.readInt();
        name = in.readString();
        genres = in.readString();
        status = in.readString();
        runtime = in.readInt();
        premiered = in.readString();
        ended = in.readString();
        network = in.readString();
        country = in.readString();
        image = in.readString();
        summary = in.readString();
        if (in.readByte() == 0) {
            rating = null;
        } else {
            rating = in.readDouble();
        }
        byte tmpWatched = in.readByte();
        watched = tmpWatched == 0 ? null : tmpWatched == 1;
    }

    public static final Creator<Shows> CREATOR = new Creator<Shows>() {
        @Override
        public Shows createFromParcel(Parcel in) {
            return new Shows(in);
        }

        @Override
        public Shows[] newArray(int size) {
            return new Shows[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeString(name);
        parcel.writeString(genres);
        parcel.writeString(status);
        parcel.writeInt(runtime);
        parcel.writeString(premiered);
        parcel.writeString(ended);
        parcel.writeString(network);
        parcel.writeString(country);
        parcel.writeString(image);
        parcel.writeString(summary);
        if (rating == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeDouble(rating);
        }
        parcel.writeByte((byte) (watched == null ? 0 : watched ? 1 : 2));
    }
}
