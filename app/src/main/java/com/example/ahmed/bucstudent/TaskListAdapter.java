package com.example.ahmed.bucstudent;


import android.annotation.TargetApi;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;

import android.os.Build;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;

import android.widget.TextView;


import com.afollestad.materialdialogs.AlertDialogWrapper;

import java.util.List;

public class TaskListAdapter extends ArrayAdapter<String>{
    Context context ;
    String[] types , subjects , details ;
    int[] years , months , days ;
    TaskDataSource tds ;
    List<Task> taskList ;
    Callback mCallback ;




    public TaskListAdapter(Context context,List<Task> tasks , String[] types ,int[] years , int[] months , int[] days ,String[] subjects , String[] details) {
        super(context , R.layout.task_list_adapter , types);

        this.context = context;
        this.types = types ;
        this.subjects = subjects ;
        this.years = years ;
        this.months = months ;
        this.days  = days ;
        this.subjects = subjects;
        this.details = details ;
        this.taskList = tasks ;
        mCallback =(Callback)context ;


    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        LayoutInflater layoutInflater = (LayoutInflater)context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.task_list_adapter , parent , false) ;

        TextView subject_textView = (TextView)view.findViewById(R.id.subject_textView);
        TextView type_textView = (TextView)view.findViewById(R.id.type_textView);
        TextView date_textView = (TextView)view.findViewById(R.id.date_textView) ;
        final TextView moreInfo_textView = (TextView)view.findViewById(R.id.moreInfo) ;
        int m = this.months[position]+1 ;
        try {

            subject_textView.setText(subjects[position]);
            type_textView.setText(this.types[position]);
            date_textView.setText(this.years[position] + "/" + m + "/" + this.days[position]);

            if(details[position].length() <= 50)
                moreInfo_textView.setText(details[position]);
            else {
                moreInfo_textView.setText(this.details[position].substring(0, 50) + "...");
                moreInfo_textView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        moreInfo_textView.setText(details[position]);
                    }
                });
            }



            ImageView logo = (ImageView)view.findViewById(R.id.logo);
            logo.setImageResource(R.drawable.note_icon);
        }catch(Exception e){

        }
        tds = new TaskDataSource(context);

       Button deleteTask = (Button)view.findViewById(R.id.deleteTask);



        deleteTask.setOnClickListener(new View.OnClickListener() {
            @TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH)
            @Override
            public void onClick(View v) {
                new AlertDialogWrapper.Builder(context)
                        .setTitle(R.string.deleteTaskMsgTitle)
                        .setMessage(R.string.deleteTaskMsg)
                        .setPositiveButton(R.string.deleteMsgBtn, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                tds.open();
                                tds.deleteTask(taskList.get(position).getId());
                                tds.close();
                                mCallback.onDeleteClick();
                                cancelAlarm(position);
                            }
                        })
                        .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {


                            }
                        })
                        .show();

                }


            });








        return view;
    }

    private void cancelAlarm(int alarmRQS){



        Intent intent = new Intent(context.getApplicationContext(), AlarmReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context.getApplicationContext(), alarmRQS, intent, 0);
        AlarmManager alarmManager = (AlarmManager)context.getSystemService(Context.ALARM_SERVICE);
        alarmManager.cancel(pendingIntent);

    }

}
