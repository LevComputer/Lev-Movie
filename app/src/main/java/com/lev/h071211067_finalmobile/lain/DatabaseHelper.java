package com.lev.h071211067_finalmobile.lain;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "favorites.db";
    private static final int DATABASE_VERSION = 2;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Create the table to store favorites
        String createTableQuery = "CREATE TABLE favorites (" +
                "_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "is_movie INTEGER," +
                "id INTEGER," +
                "title TEXT," +
                "popularity REAL," +
                "genre_ids TEXT," +
                "vote_average REAL," +
                "vote_count INTEGER," +
                "overview TEXT," +
                "poster_path TEXT," +
                "release_date TEXT," +
                "backdrop_path TEXT)";
        db.execSQL(createTableQuery);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop the table if it exists and recreate it
        String dropTableQuery = "DROP TABLE IF EXISTS favorites";
        db.execSQL(dropTableQuery);
        onCreate(db);
    }
}
