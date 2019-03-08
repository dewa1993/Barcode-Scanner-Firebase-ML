package com.upadhyde.android.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.upadhyde.android.db.dao.ScannerDao;


@Database(entities = {Input.class, Output.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {

    public abstract ScannerDao provideScannerDao();


}

