package com.example.ahmed.bucstudent;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class RowDataSource {
    RowsSqlite mysql ;
    SQLiteDatabase db ;
    String[] AllClumns = new String[]{RowsSqlite.ID , RowsSqlite.SUBJECT_NAME , RowsSqlite.DAY , RowsSqlite.START_TIME_H , RowsSqlite.START_TIME_M , RowsSqlite.START_TIME_AP , RowsSqlite.END_TIME_H , RowsSqlite.END_TIME_M , RowsSqlite.END_TIME_AP, RowsSqlite.ROOM,RowsSqlite.DAY_INDEX, RowsSqlite.INSTRUCTOR_NAME};

    public RowDataSource(Context context){
        mysql = new RowsSqlite(context);

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

    public void createRow(String subjectName , String day,int startTimeH , int startTimeM , String startTimeAP , int endTimeH , int endTimeM ,String endTimeAP ,  String Room ,int dayIndex, String instructorName){
        ContentValues list = new ContentValues();
        list.put(RowsSqlite.SUBJECT_NAME , subjectName);
        list.put(RowsSqlite.DAY , day);
        list.put(RowsSqlite.START_TIME_H , startTimeH);
        list.put(RowsSqlite.START_TIME_M, startTimeM);
        list.put(RowsSqlite.START_TIME_AP , startTimeAP);
        list.put(RowsSqlite.END_TIME_H , endTimeH);
        list.put(RowsSqlite.END_TIME_M , endTimeM);
        list.put(RowsSqlite.END_TIME_AP , endTimeAP);
        list.put(RowsSqlite.ROOM , Room);
        list.put(RowsSqlite.DAY_INDEX , dayIndex);
        list.put(RowsSqlite.INSTRUCTOR_NAME , instructorName);
        try {
            db.insert(RowsSqlite.TABLE_NAME, null, list);
        }catch(Exception e) {
         Log.d("DB_Error : " , e.getMessage());
        }



    }
 /*   unused functions

    public void updateRow(int id , String subjectName , String day,int startTimeH , int startTimeM , String startTimeAP , int endTimeH , int endTimeM ,String endTimeAP ,  String Room ,int dayIndex, String instructorName){
        ContentValues list = new ContentValues();
        list.put(RowsSqlite.SUBJECT_NAME , subjectName);
        list.put(RowsSqlite.DAY , day);
        list.put(RowsSqlite.START_TIME_H , startTimeH);
        list.put(RowsSqlite.START_TIME_M, startTimeM);
        list.put(RowsSqlite.START_TIME_AP , startTimeAP);
        list.put(RowsSqlite.END_TIME_H , endTimeH);
        list.put(RowsSqlite.END_TIME_M , endTimeM);
        list.put(RowsSqlite.END_TIME_AP , endTimeAP);
        list.put(RowsSqlite.ROOM , Room);
        list.put(RowsSqlite.DAY_INDEX , dayIndex);

        list.put(RowsSqlite.INSTRUCTOR_NAME , instructorName);

        db.insert(RowsSqlite.TABLE_NAME, null, list);
        db.update(RowsSqlite.TABLE_NAME, list, RowsSqlite.ID + " = " + id, null);


    }

    public Subject getRow(int id){
        Subject subject = new Subject();
        Cursor cursor = db.rawQuery("SELECT * FROM "+ RowsSqlite.TABLE_NAME+" WHERE "+ RowsSqlite.ID+" = ?",new String[]{id+""});
        if(cursor.getCount()>0){
            cursor.moveToFirst();
            subject.setId(cursor.getInt(0));
            subject.setSubjectName(cursor.getString(1));
            subject.setDay(cursor.getString(2));
            subject.setStartTimeH(cursor.getInt(3));
            subject.setStartTimeM(cursor.getInt(4));
            subject.setStartTimeAP(cursor.getString(5));
            subject.setEndTimeH(cursor.getInt(6));
            subject.setEndTimeM(cursor.getInt(7));
            subject.setEndTimeAP(cursor.getString(8));
            subject.setRoom(cursor.getString(9));
            subject.setDayIndex(cursor.getInt(10));
            subject.setInstructorName(cursor.getString(11));
            cursor.close();
        }


        return subject;
    } */

    public List<Subject> findRows(String day){
        List<Subject> rowList = new ArrayList<Subject>();
        Cursor cursor = db.rawQuery("SELECT * FROM "+ RowsSqlite.TABLE_NAME+" WHERE "+ RowsSqlite.DAY+" = ?",new String[]{day+""});
        cursor.moveToFirst();
        while (!cursor.isAfterLast()){
            Subject subject = new Subject();
            subject.setId(cursor.getInt(0));
            subject.setSubjectName(cursor.getString(1));
            subject.setDay(cursor.getString(2));
            subject.setStartTimeH(cursor.getInt(3));
            subject.setStartTimeM(cursor.getInt(4));
            subject.setStartTimeAP(cursor.getString(5));
            subject.setEndTimeH(cursor.getInt(6));
            subject.setEndTimeM(cursor.getInt(7));
            subject.setEndTimeAP(cursor.getString(8));
            subject.setRoom(cursor.getString(9));
            subject.setDayIndex(cursor.getInt(10));
            subject.setInstructorName(cursor.getString(11));
            rowList.add(subject);
            cursor.moveToNext();
        }
        cursor.close()     ;
        return rowList ;
    }
    public void deleteRow(int id){
        db.delete(RowsSqlite.TABLE_NAME, RowsSqlite.ID + " = " + id, null);
    }




    public List<Subject> getAllRows(){
        List<Subject> rowList = new ArrayList<Subject>();
        Cursor cursor = db.query(RowsSqlite.TABLE_NAME , AllClumns , null , null ,null ,null ,null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()){
            Subject subject = new Subject();
            subject.setId(cursor.getInt(0));
            subject.setSubjectName(cursor.getString(1));
            subject.setDay(cursor.getString(2));
            subject.setStartTimeH(cursor.getInt(3));
            subject.setStartTimeM(cursor.getInt(4));
            subject.setStartTimeAP(cursor.getString(5));
            subject.setEndTimeH(cursor.getInt(6));
            subject.setEndTimeM(cursor.getInt(7));
            subject.setEndTimeAP(cursor.getString(8));
            subject.setRoom(cursor.getString(9));
            subject.setDayIndex(cursor.getInt(10));
            subject.setInstructorName(cursor.getString(11));
            rowList.add(subject);
            cursor.moveToNext();
        }
        cursor.close()     ;
        return rowList ;
    }
}
