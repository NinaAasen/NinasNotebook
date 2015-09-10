package com.example.nina.hig.ninasnotebook;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MyDBHandler extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "noteDB.db";
    public static final String TABLE_NOTES = "notes";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_NOTENAME = "notename";

    //Superclass, and information about the database
    public MyDBHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }

    @Override //Create tables with autoincrements for every new note
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_NOTES + "(" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_NOTENAME + " TEXT " +
                ");";
        db.execSQL(query);
    }

    @Override //If new version of database exists, it will delete previous
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NOTES);
        onCreate(db);
    }

    //Add a new row to the database
    public void addNote(Notes note) {
        ContentValues values = new ContentValues();
        values.put(COLUMN_NOTENAME, note.get_notename());
        SQLiteDatabase db = getWritableDatabase();
        db.insert(TABLE_NOTES, null, values);
        db.close();
    }

    //Delete a note from the database
    public void deleteNote(String noteName) {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_NOTES + " WHERE " + COLUMN_NOTENAME + "=\"" + noteName + "\";");
    }

    //Retrieves from database
    public String databaseToString() {
        String dbString = "";
        SQLiteDatabase db = getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_NOTES + " WHERE 1";

        Cursor c = db.rawQuery(query, null);
        c.moveToFirst();

        while (!c.isAfterLast()) {
            if (c.getString(c.getColumnIndex("notename")) != null) {
                dbString += c.getString(c.getColumnIndex("notename"));
                dbString += "\n";
            }
            c.moveToNext();
        }
        db.close();
        return dbString;
    }
}

