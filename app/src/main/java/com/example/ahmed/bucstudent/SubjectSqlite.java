package com.example.ahmed.bucstudent;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Ahmed on 12/04/2015.
 */
public class SubjectSqlite extends SQLiteOpenHelper {


    public static final String DB_NAME = "buc_student_db";
    public static final int DB_ID = 1 ;

    public static final String TABLE_NAME = "subjects_table";

    public static final String ID = "id";
    public static final String SUBJECT_NAME = "name";
    public static final String INSTRUCTOR_NAME = "instructor_name";

    public static final String query = "CREATE TABLE "+TABLE_NAME+" ( "+
            ID+" INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, "+
            SUBJECT_NAME+" TEXT NOT NULL , "+
            INSTRUCTOR_NAME+" TEXT NOT NULL );";

    public SubjectSqlite(Context context){
        super(context ,DB_NAME , null , DB_ID );
    }







    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(query);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXIST "+TABLE_NAME);
        onCreate(db);

    }
}
