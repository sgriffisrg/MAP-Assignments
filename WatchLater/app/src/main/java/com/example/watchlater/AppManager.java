package com.example.watchlater;

import android.app.Application;

import java.util.ArrayList;


public class AppManager extends Application {
    private NetworkingService networkingService = new NetworkingService();
    private JSONService jsonService = new JSONService();
    DbManager dbManager = new DbManager();
    ArrayList<Shows> savedShows = new ArrayList<Shows>();
    ArrayList<Shows> unwatchedShows = new ArrayList<Shows>();
    ArrayList<Shows> watchedShows = new ArrayList<Shows>();

    public JSONService getJsonService(){ return jsonService; }
    public NetworkingService getNetworkingService(){ return networkingService; }
}
