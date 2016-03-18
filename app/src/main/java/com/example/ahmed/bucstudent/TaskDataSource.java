package com.example.ahmed.bucstudent;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class TaskDataSource {
    TaskSqlite mysql ;
    SQLiteDatabase db ;
    String[] AllClumns = new String[]{TaskSqlite.ID , TaskSqlite.TASK_TYPE  , TaskSqlite.TASK_YEAR,TaskSqlite.TASK_MONTH,TaskSqlite.TASK_DAY , TaskSqlite.TASK_SUBJECT , TaskSqlite.TASK_DETAILS};

    public TaskDataSource(Context context){
        mysql = new TaskSqlite(context);

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

    public void createTask(String taskType  , int taskYear ,  int taskMonth , int taskDay, String taskSubject , String taskDetails){
        ContentValues list = new ContentValues();
        list.put(TaskSqlite.TASK_TYPE , taskType);
        list.put(TaskSqlite.TASK_YEAR , taskYear);
        list.put(TaskSqlite.TASK_MONTH , taskMonth);
        list.put(TaskSqlite.TASK_DAY , taskDay);
        list.put(TaskSqlite.TASK_SUBJECT , taskSubject);
        list.put(TaskSqlite.TASK_DETAILS , taskDetails);

            db.insert(TaskSqlite.TABLE_NAME, null, list);


    }

    public void updateTask(int id ,String taskType  ,  String taskYear ,  String taskMonth , String taskDay , String taskSubject , String taskDetails){
        ContentValues list = new ContentValues();
        list.put(TaskSqlite.TASK_TYPE , taskType);
        list.put(TaskSqlite.TASK_YEAR , taskYear);
        list.put(TaskSqlite.TASK_MONTH , taskMonth);
        list.put(TaskSqlite.TASK_DAY , taskDay);
        list.put(TaskSqlite.TASK_SUBJECT , taskSubject);
        list.put(TaskSqlite.TASK_DETAILS , taskDetails);

        db.insert(TaskSqlite.TABLE_NAME, null, list);
        db.update(TaskSqlite.TABLE_NAME, list, TaskSqlite.ID + " = " + id, null);


    }

    public Task getTask(int id){
        Task task = new Task();
        Cursor cursor = db.rawQuery("SELECT * FROM "+ TaskSqlite.TABLE_NAME+" WHERE "+ TaskSqlite.ID+" = ?",new String[]{id+""});
        if(cursor.getCount()>0){
            cursor.moveToFirst();
            task.setId(cursor.getInt(0));
            task.setType(cursor.getString(1));
            task.setYear(cursor.getInt(2));
            task.setMonth(cursor.getInt(3));
            task.setDay(cursor.getInt(4));
            task.setSubject(cursor.getString(5));
            task.setDetails(cursor.getString(6));
            cursor.close();
        }




        return task;
    }

    public List<Task> findTask(int day , int month , int year){
        List<Task> taskList = new ArrayList<Task>();
        Cursor cursor = db.rawQuery("SELECT * FROM "+ TaskSqlite.TABLE_NAME+" WHERE "+ TaskSqlite.TASK_DAY+" = ? AND "+TaskSqlite.TASK_MONTH+" = "+month+" AND "+TaskSqlite.TASK_MONTH+" = "+year,new String[]{day+""} );
        cursor.moveToFirst();
        while (!cursor.isAfterLast()){
            Task task = new Task();
            task.setId(cursor.getInt(0));
            task.setType(cursor.getString(1));
            task.setYear(cursor.getInt(2));
            task.setMonth(cursor.getInt(3));
            task.setDay(cursor.getInt(4));
            task.setSubject(cursor.getString(5));
            task.setDetails(cursor.getString(6));
            taskList.add(task);
            cursor.moveToNext();
        }
        cursor.close()     ;
        return taskList ;
    }

    public Task getTaskByDate(int day){
        Task task = new Task();
        Cursor cursor = db.rawQuery("SELECT * FROM "+ TaskSqlite.TABLE_NAME+" WHERE "+ TaskSqlite.TASK_DAY+" = ?",new String[]{day+""});
        if(cursor.getCount()>0){
            cursor.moveToFirst();
            task.setId(cursor.getInt(0));
            task.setType(cursor.getString(1));
            task.setYear(cursor.getInt(2));
            task.setMonth(cursor.getInt(3));
            task.setDay(cursor.getInt(4));
            task.setSubject(cursor.getString(5));
            task.setDetails(cursor.getString(6));
            cursor.close();
        }




        return task;
    }
    public void deleteTask(int id){
        db.delete(TaskSqlite.TABLE_NAME , TaskSqlite.ID+" = "+id ,  null);
    }

    public List<Task> getAllTasks(){
        List<Task> taskList = new ArrayList<Task>();
        try {
            Cursor cursor = db.query(TaskSqlite.TABLE_NAME, AllClumns, null, null, null, null, null);

            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                Task task = new Task();
                task.setId(cursor.getInt(0));
                task.setType(cursor.getString(1));
                task.setYear(cursor.getInt(2));
                task.setMonth(cursor.getInt(3));
                task.setDay(cursor.getInt(4));
                task.setSubject(cursor.getString(5));
                task.setDetails(cursor.getString(6));
                taskList.add(task);
                cursor.moveToNext();
            }
            cursor.close();
        }catch(Exception e){

        }
        return taskList ;
    }
}
