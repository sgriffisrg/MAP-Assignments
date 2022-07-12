package com.example.watchlater;

import android.app.Application;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class NetworkingService {
    private String searchUrl = "https://api.tvmaze.com/search/shows?q=";

    public static ExecutorService networkExecutorService = Executors.newFixedThreadPool(4);
    public static Handler networkingHandler = new Handler(Looper.getMainLooper());

    interface NetworkingListener{
        void JSONListener(String json, int type);
    }
    NetworkingListener listener;

    public void SearchShows(String name){
        String updatedUrl = searchUrl + name;
        connect(updatedUrl, 0);
    }
    public void getAllShows(){
        connect("https://api.tvmaze.com/shows", 1);
    }

    public void connect(String url, int type){
        networkExecutorService.execute(new Runnable() {
            @Override
            public void run() {
                HttpURLConnection httpConnection = null;

                try{
                    String json = "";
                    URL urlObj = new URL(url);

                    httpConnection = (HttpURLConnection)urlObj.openConnection();
                    httpConnection.setRequestMethod("GET");
                    httpConnection.setRequestProperty("Content-Type", "application/json");

                    InputStream in = httpConnection.getInputStream();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(in));

                    StringBuilder out = new StringBuilder();
                    String line;
                    while ((line = reader.readLine()) != null) {
                        out.append(line);
                    }

                    json = out.toString();
                    reader.close();
                    final String finalJson = json;

                    networkingHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            listener.JSONListener(finalJson, type);
                        }
                    });

                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    httpConnection.disconnect();
                }
            }
        });
    }
}
