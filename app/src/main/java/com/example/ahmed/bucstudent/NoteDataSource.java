package com.example.ahmed.bucstudent;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by apple on 9/9/15.
 */
public class NoteDataSource {
    NoteSqlite mysql ;
    SQLiteDatabase db ;
    String[] AllClumns = new String[]{NoteSqlite.ID , NoteSqlite.NOTE_TITLE  , NoteSqlite.NOTE_TEXT};

    public NoteDataSource(Context context){
        mysql = new NoteSqlite(context);

    }

    public void open(){
        try{
            db=mysql.getWritableDatabase();

        }catch(Exception e){
            Log.d("Error : ", e.getMessage());
        }
    }

    public void close (){
        db.close();
    }

    public void createNote(String title  , String text){
        ContentValues list = new ContentValues();
        list.put(NoteSqlite.NOTE_TITLE , title);
        list.put(NoteSqlite.NOTE_TEXT , text);
        db.insert(NoteSqlite.TABLE_NAME , null , list);



    }

    public void updateNote(int id ,String title , String text){
        ContentValues list = new ContentValues();
        list.put(NoteSqlite.NOTE_TITLE , title);
        list.put(NoteSqlite.NOTE_TEXT , text);


        db.update(NoteSqlite.TABLE_NAME , list , NoteSqlite.ID+" = "+id , null);


    }

    public NoteData getNote(int id){
        NoteData note = new NoteData();
        Cursor cursor = db.rawQuery("SELECT * FROM "+ NoteSqlite.TABLE_NAME+" WHERE "+ NoteSqlite.ID+" = ?",new String[]{id+""});
        if(cursor.getCount()>0){
            cursor.moveToFirst();
            note.setId(cursor.getInt(0));
            note.setTitle(cursor.getString(1));
            note.setText(cursor.getString(2));
            cursor.close();
        }
/////////////////////----------->>>>>>>>

        return note;
    }
    public void deleteNote(int id){
        db.delete(NoteSqlite.TABLE_NAME , NoteSqlite.ID+" = "+id ,  null);
    }

    public List<NoteData> getAllNotes(){
        List<NoteData> noteList = new ArrayList<NoteData>();
        try {
            Cursor cursor = db.query(NoteSqlite.TABLE_NAME, AllClumns, null, null, null, null, null);

            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                NoteData note = new NoteData();
                note.setId(cursor.getInt(0));
                note.setTitle(cursor.getString(1));
                note.setText(cursor.getString(2));
                noteList.add(note);
                cursor.moveToNext();
            }
            cursor.close();
        }catch(Exception e){

        }
        return noteList ;
    }
}
