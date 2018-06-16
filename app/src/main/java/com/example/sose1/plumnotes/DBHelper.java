package com.example.sose1.plumnotes;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper{

    private static final String DB_BASE = "maindb";
    private static final int DB_VERSION = 1;


    public DBHelper(Context context){
        super(context, DB_BASE, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database) {
        String sqlNotes = "CREATE TABLE notes(" +
                "ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "title VARCHAR, " +
                "content VARCHAR, " +
                "color INTEGER )";

                database.execSQL(sqlNotes);

        System.out.println("CREATE TABLE");
    }


    @Override
    public void onUpgrade(SQLiteDatabase database, int i, int i1) {
        String sqlNotes = "DROP TABLE IF EXISTS note";
        database.execSQL(sqlNotes);

        onCreate(database);

        System.out.println("UPGRADE TABLE");
    }

    public void addNote(String title, String content, int color){
        SQLiteDatabase database = getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put("title", title);
        contentValues.put("content", content);
        contentValues.put("color", color);

        database.insert("notes", null, contentValues);
        database.close();

        System.out.println("ADD NOTE");
    }

    public void delNote(int ID){
        SQLiteDatabase database = getWritableDatabase();
        String sqlNotes = "DELETE FROM notes WHERE ID = " + ID;
        database.execSQL(sqlNotes);
        database.close();

        System.out.println("DELETE NOTE");
    }

    public void updateNote( int ID, String title ,String content) {
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("title",title);
        contentValues.put("content",content);

        database.update("notes", contentValues, "ID =" + ID, null );
        database.close();
    }
    public Cursor getAllNotes(){
        SQLiteDatabase database = getWritableDatabase();
        return database.rawQuery("SELECT * from notes", null);
    }
}
