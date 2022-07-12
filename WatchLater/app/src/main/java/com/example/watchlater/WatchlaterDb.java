package com.example.watchlater;

import androidx.room.AutoMigration;
import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.migration.AutoMigrationSpec;

@Database(entities = {Shows.class, Country.class}, version = 6)
    public abstract class WatchlaterDb extends RoomDatabase {
        public abstract WatchLaterDao showsDao();
    }

/*
    @Database(version = 2, entities = {Shows.class},  autoMigrations = {@AutoMigration(from = 1, to = 2)})
    public abstract class WatchlaterDb extends RoomDatabase {
        public abstract WatchLaterDao showsDao();
    }
*/
