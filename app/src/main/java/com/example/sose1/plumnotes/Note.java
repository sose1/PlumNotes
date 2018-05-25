package com.example.sose1.plumnotes;

import android.content.Context;
import android.database.Cursor;

import java.util.ArrayList;

public class Note {

    private int ID;
    private String title;
    private String content;
    private static DBHelper dbHelper;

    public Note(int ID, String title, String content) {
        this.ID = ID;
        this.title = title;
        this.content = content;
    }

    public Note(String title, String content) {
    }


    public static ArrayList<Note> noteArrayList(Context context){
        dbHelper = new DBHelper(context);

        Cursor cursor = dbHelper.getAllNotes();
        ArrayList<Note> noteArrayList;

        if(cursor.getCount() == 0){
            cursor.close();
            return new ArrayList<Note>();
        }

        noteArrayList = new ArrayList<>();

        while (cursor.moveToNext()){

            Note note = new Note(
                    cursor.getInt(0),
                    cursor.getString(1),
                    cursor.getString(2));

            noteArrayList.add(note);
        }
        cursor.close();
        return noteArrayList;
    }
    public int getID() {
        return ID;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }
}
