package com.example.watchlater;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.util.AndroidRuntimeException;

import androidx.room.Room;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class DbManager {
    interface DbListener {
         void added(String name);
         void listReady(List<Shows> showList);
         void watchedListReady(List<Shows> showList);
         void unwatchedListReady(List<Shows> showList);
         void deleted(String name);
         void updated(String name);
         void singleShowReady(Shows show);
    }

    DbListener dbListener;
    static WatchlaterDb db = null;

    ExecutorService databaseExecutor = Executors.newFixedThreadPool(4);
    Handler mainThread_Handler = new Handler(Looper.getMainLooper());

    private static void buildInstanceDb(Context context){
        db = Room.databaseBuilder(context, WatchlaterDb.class,"watch_later_db").fallbackToDestructiveMigration().build();
    }

    public WatchlaterDb getDatabase(Context context){
        if(db == null)
            buildInstanceDb(context);
        return db;
    }

    public void addNewShow(Shows newShow) {
        databaseExecutor.execute(new Runnable() {
            @Override
            public void run() {
                db.showsDao().addNewShow(newShow);
                mainThread_Handler.post(new Runnable() {
                    @Override
                    public void run() {
                        dbListener.added(newShow.name);
                    }
                });
            }
        });

    }
    public void getAllShows(){
        databaseExecutor.execute(new Runnable() {
            @Override
            public void run() {
                List<Shows> showList = db.showsDao().getAllShows();
                mainThread_Handler.post(new Runnable() {
                    @Override
                    public void run() {
                        dbListener.listReady(showList);
                    }
                });
            }
        });
    }
    public void getAllWatchLaterShows(){
        databaseExecutor.execute(new Runnable() {
            @Override
            public void run() {
                List<Shows> showList = db.showsDao().getAllUnwatchedShows();
                mainThread_Handler.post(new Runnable() {
                    @Override
                    public void run() {
                        dbListener.unwatchedListReady(showList);
                    }
                });
            }
        });
    }

    public void getAllWatchedShows(){
        databaseExecutor.execute(new Runnable() {
            @Override
            public void run() {
                List<Shows> showList = db.showsDao().getAllWatchedShows();
                mainThread_Handler.post(new Runnable() {
                    @Override
                    public void run() {
                        dbListener.watchedListReady(showList);
                    }
                });
            }
        });
    }

    public void deleteShow(Shows showToBeDeleted){
        databaseExecutor.execute(new Runnable() {
            @Override
            public void run() {
                db.showsDao().deleteShow(showToBeDeleted);
                mainThread_Handler.post(new Runnable() {
                    @Override
                    public void run() {
                        dbListener.deleted(showToBeDeleted.name);
                    }
                });
            }
        });
    }

    public void updateShow(Shows updatedShow){
        databaseExecutor.execute(new Runnable() {
            @Override
            public void run() {
                db.showsDao().updateShow(updatedShow);
                mainThread_Handler.post(new Runnable() {
                    @Override
                    public void run() {
                        dbListener.updated(updatedShow.name);
                    }
                });
            }
        });
    }

    public void getSingleShow(String name){
        databaseExecutor.execute(new Runnable() {
            @Override
            public void run() {
                Shows showGrabbed = db.showsDao().getShowsByName(name);
                mainThread_Handler.post(new Runnable() {
                    @Override
                    public void run() {
                        dbListener.singleShowReady(showGrabbed);
                    }
                });
            }
        });
    }
}
