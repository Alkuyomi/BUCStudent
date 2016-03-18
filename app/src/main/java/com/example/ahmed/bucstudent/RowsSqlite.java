package com.example.ahmed.bucstudent;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class RowsSqlite extends SQLiteOpenHelper{

    public static final String DB_NAME = "buc_student_db2";
    public static final int DB_ID = 1 ;

    public static final String TABLE_NAME = "rows_table";

    public static final String ID = "id";
    public static final String SUBJECT_NAME = "name";
    public static final String DAY = "day";
    public static final String START_TIME_H = "start_time_h";
    public static final String START_TIME_M = "start_time_m";
    public static final String START_TIME_AP ="start_time_ap";
    public static final String END_TIME_H ="end_time_h";
    public static final String END_TIME_M ="end_time_m";
    public static final String END_TIME_AP ="end_time_ap";
    public static final String ROOM ="room";
    public static final String DAY_INDEX = "day_index";

    public static final String INSTRUCTOR_NAME = "instructor_name";



    public RowsSqlite(Context context){
        super(context ,DB_NAME , null , DB_ID );
    }







    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table "+RowsSqlite.TABLE_NAME+" ( "+ ID+" INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, "+
                this.SUBJECT_NAME+" TEXT NOT NULL    , "+
                this.DAY+" TEXT NOT NULL             , "+
                this.START_TIME_H+" INTEGER NOT NULL , "+
                this.START_TIME_M+" INTEGER NOT NULL , "+
                this.START_TIME_AP+" TEXT NOT NULL   , "+
                this.END_TIME_H +" INTEGER NOT NULL  , "+
                this.END_TIME_M +" INTEGER NOT NULL  , "+
                this.END_TIME_AP+" TEXT NOT NULL     , "+
                this.ROOM       +" TEXT NOT NULL     , "+
                this.DAY_INDEX       +" INTEGER NOT NULL     , "+
                this.INSTRUCTOR_NAME+" TEXT NOT NULL );");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXIST "+TABLE_NAME);
        onCreate(db);

    }
}
