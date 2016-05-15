package com.example.pabji.applectorrss.persistence;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class RSSSQLiteHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "lectorRss.db";
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_TABLE = "itemTable";

    public static final String TITLE = "title";
    public static final String LINK = "link";
    public static final String DESCRIPTION = "description";
    public static final String CONTENT = "content";
    public static final String IMAGE_URL = "imageURL";
    public static final String PUB_DATE = "pubDate";

    public RSSSQLiteHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    String sqlCreate = "CREATE TABLE " + DATABASE_TABLE + "("
            + "_id " + "INTEGER PRIMARY KEY AUTOINCREMENT, "
            + TITLE + " TEXT, "
            + DESCRIPTION + " TEXT, "
            + LINK + "TEXT, "
            + CONTENT + "TEXT, "
            + IMAGE_URL + "TEXT, "
            + PUB_DATE + "TEXT)";

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(sqlCreate);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + DATABASE_TABLE);
        db.execSQL(sqlCreate);
    }
}
