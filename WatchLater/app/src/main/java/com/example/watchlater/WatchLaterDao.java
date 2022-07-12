package com.example.watchlater;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.ArrayList;
import java.util.List;

@Dao
public interface WatchLaterDao {
    @Insert
    void addNewShow(Shows newShow);

    @Delete()
    void deleteShow(Shows showToDelete);

    @Update
    void updateShow(Shows showUpdated);

    @Query("Select * FROM Shows")
    List<Shows> getAllShows();

    @Query("Select * From Shows Where name == :selectedName")
    Shows getShowsByName(String selectedName);

    @Query("Select * FROM Shows Where watched == 0")
    List<Shows> getAllUnwatchedShows();

    @Query("Select * FROM Shows Where watched == 1")
    List<Shows> getAllWatchedShows();
}
