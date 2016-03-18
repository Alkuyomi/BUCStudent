package com.example.ahmed.bucstudent;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class TaskSqlite extends SQLiteOpenHelper{

    public static final String DB_NAME = "buc_tasks_db";
    public static final int DB_ID = 1 ;

    public static final String TABLE_NAME = "tasks_table";

    public static final String ID = "id";
    public static final String TASK_TYPE = "type";
    public static final String TASK_YEAR = "year";
    public static final String TASK_MONTH = "month";
    public static final String TASK_DAY = "day";
    public static final String TASK_SUBJECT = "subject";
    public static final String TASK_DETAILS = "details";

    public static final String query = "CREATE TABLE "+TABLE_NAME+" ( "+
            ID+" INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, "+
            TASK_TYPE+" TEXT NOT NULL , "+
            TASK_YEAR+" INTEGER NOT NULL , "+
            TASK_MONTH+" INTEGER NOT NULL , "+
            TASK_DAY+" INTEGER NOT NULL , "+
            TASK_SUBJECT+" TEXT NOT NULL , "+
            TASK_DETAILS+" TEXT NOT NULL );";
    public TaskSqlite(Context context){
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
