package com.vcu.ryanhayden.synonym;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseManager extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "synonym.db";
    private static final String TABLE_NAME = "synonyms";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_FIRST = "first";
    private static final String COLUMN_SECOND = "second";
    private SQLiteDatabase db;
    private static final String TABLE_CREATE = "create table synonyms (id integer primary key not null, " +
            "first text not null, second text not null)";

    public DatabaseManager(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TABLE_CREATE);
        this.db = db;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String query = "DROP TABLE IF EXISTS " + TABLE_NAME;
        db.execSQL(query);
        this.onCreate(db);
    }

    public void insertSynonymPair(SynonymPair pair){
        db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        String query = "select * from synonyms";
        Cursor cursor = db.rawQuery(query, null);
        int count = cursor.getCount();
        values.put(COLUMN_ID, count);
        values.put(COLUMN_FIRST, pair.getFirstWord());
        values.put(COLUMN_SECOND, pair.getSecondWord());
        db.insert(TABLE_NAME, null, values);
        db.close();
        cursor.close();
    }

    public String searchByFirst(String first){
        db = this.getReadableDatabase();
        String query = "select first, second from "+TABLE_NAME;
        Cursor cursor = db.rawQuery(query,null);
        String result = "Word not found";
        if(cursor.moveToFirst()){
            do{
                if(first.equals(cursor.getString(0))) {
                    result = cursor.getString(1);
                    break;
                }
            }while(cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return result;
    }
}
