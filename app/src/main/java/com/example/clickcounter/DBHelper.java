package com.example.clickcounter;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "count.db";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_COUNT = "counts";
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_TITLE = "title";
    private static final String COLUMN_COUNTS = "counts";


    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createSongTableSql = "CREATE TABLE " + TABLE_COUNT + "("
                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COLUMN_TITLE + " TEXT,"
                + COLUMN_COUNTS + " INTEGER )";

        db.execSQL(createSongTableSql);

        Log.i("info", "created tables");

    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }


    //-----------------Insert--------------
    public long insertSong(String title,int counts) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_TITLE, title);
        values.put(String.valueOf(COLUMN_COUNTS), counts);

        long result = db.insert(TABLE_COUNT, null, values);

        db.close();
        Log.d("SQL Insert","ID:"+ result); //id returned, shouldn’t be -1
        return result;
    }
    //-----------------Insert--------------

    public ArrayList<Count> getAllCounts() {
        ArrayList<Count> counts = new ArrayList<Count>();

        String selectQuery = "SELECT " + COLUMN_ID + ","
                + COLUMN_TITLE +  "," +COLUMN_COUNTS  + " FROM " + TABLE_COUNT;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(0);
                String title = cursor.getString(1);
                int countA = cursor.getInt(2);

                Count newCount = new Count(id,title,countA);
                counts.add(newCount);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return counts;
    }


    public int updateCounts(Count data){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(COLUMN_TITLE, data.getTitle());
        values.put(COLUMN_COUNTS, data.getCounts());
        String condition = COLUMN_ID + "= ?";
        String[] args = {String.valueOf(data.get_id())};
        int result = db.update(TABLE_COUNT, values, condition, args);
        db.close();
        return result;
    }

    public int deleteCounts(int id){
        SQLiteDatabase db = this.getWritableDatabase();
        String condition = COLUMN_ID + "= ?";
        String[] args = {String.valueOf(id)};
        int result = db.delete(TABLE_COUNT,condition,args);
        db.close();
        return result;
    }
}
