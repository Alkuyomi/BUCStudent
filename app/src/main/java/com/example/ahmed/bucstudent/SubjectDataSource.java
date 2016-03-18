package com.example.ahmed.bucstudent;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.view.accessibility.AccessibilityManager;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class SubjectDataSource {
    SubjectSqlite mysql ;
    SQLiteDatabase db ;
    String[] AllClumns = new String[]{SubjectSqlite.ID , SubjectSqlite.SUBJECT_NAME  , SubjectSqlite.INSTRUCTOR_NAME};

    public SubjectDataSource(Context context){
        mysql = new SubjectSqlite(context);

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

    public void createSubject(String subjectName  , String instructorName){
        ContentValues list = new ContentValues();
        list.put(SubjectSqlite.SUBJECT_NAME , subjectName);
        list.put(SubjectSqlite.INSTRUCTOR_NAME , instructorName);
        db.insert(SubjectSqlite.TABLE_NAME , null , list);



    }

    public void updateSubject(int id ,String subjectName , String instructorName){
        ContentValues list = new ContentValues();
        list.put(SubjectSqlite.SUBJECT_NAME , subjectName);
        list.put(SubjectSqlite.INSTRUCTOR_NAME , instructorName);

        db.insert(SubjectSqlite.TABLE_NAME , null , list);
        db.update(SubjectSqlite.TABLE_NAME , list , SubjectSqlite.ID+" = "+id , null);


    }

    public Subject getSubject(int id){
        Subject subject = new Subject();
        Cursor cursor = db.rawQuery("SELECT * FROM "+ SubjectSqlite.TABLE_NAME+" WHERE "+ SubjectSqlite.ID+" = ?",new String[]{id+""});
        if(cursor.getCount()>0){
            cursor.moveToFirst();
            subject.setId(cursor.getInt(0));
            subject.setSubjectName(cursor.getString(1));
            subject.setInstructorName(cursor.getString(2));
            cursor.close();
        }


        return subject;
    }
    public void deleteSubject(int id){
        db.delete(SubjectSqlite.TABLE_NAME , SubjectSqlite.ID+" = "+id ,  null);
    }

    public List<Subject> getAllSubjects(){
        List<Subject> subjectList = new ArrayList<Subject>();
           try {
               Cursor cursor = db.query(SubjectSqlite.TABLE_NAME, AllClumns, null, null, null, null, null);

               cursor.moveToFirst();
               while (!cursor.isAfterLast()) {
                   Subject subject = new Subject();
                   subject.setId(cursor.getInt(0));
                   subject.setSubjectName(cursor.getString(1));
                   subject.setInstructorName(cursor.getString(2));
                   subjectList.add(subject);
                   cursor.moveToNext();
               }
               cursor.close();
           }catch(Exception e){

           }
        return subjectList ;
    }
}
