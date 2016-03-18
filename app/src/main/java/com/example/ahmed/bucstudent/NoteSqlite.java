package com.example.ahmed.bucstudent;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by apple on 9/9/15.
 */
public class NoteSqlite extends SQLiteOpenHelper{

    public static final String DB_NAME = "buc_student_db3";
    public static final int DB_ID = 1 ;

    public static final String TABLE_NAME = "notes_table";

    public static final String ID = "id";
    public static final String NOTE_TITLE = "title";
    public static final String NOTE_TEXT = "text";




    public NoteSqlite(Context context){
        super(context ,DB_NAME , null , DB_ID );
    }







    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table "+NoteSqlite.TABLE_NAME+" ( "+
                this.ID+" INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, "+
                this.NOTE_TITLE+" TEXT NOT NULL    , "+
                this.NOTE_TEXT+" TEXT NOT NULL             ); ");


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXIST "+TABLE_NAME);
        onCreate(db);

    }
}

