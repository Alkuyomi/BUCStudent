package com.example.ahmed.bucstudent;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.PowerManager;
import android.support.v4.app.NotificationCompat;


import java.util.ArrayList;
import java.util.Calendar;

import java.util.List;

/**
 * Created by apple on 8/1/15.
 */
public class AlarmReceiver extends BroadcastReceiver {

    private PowerManager.WakeLock wl;
    @Override
    public void onReceive(final Context context, Intent intent) {
        TaskDataSource tds = new TaskDataSource(context.getApplicationContext());
        Calendar calendar = Calendar.getInstance();

        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int month = calendar.get(Calendar.MONTH);
        int year  = calendar.get(Calendar.YEAR);

        tds.open();
        List<Task> tasks = tds.getAllTasks();
        tds.close();

        ArrayList<String> types    = new ArrayList<>();
        ArrayList<String> subjects = new ArrayList<>();
        ArrayList<String> Des = new ArrayList<>();
        ArrayList<Integer> Day = new ArrayList<>();
        ArrayList<Integer> Month = new ArrayList<>();
        ArrayList<Integer> Year = new ArrayList<>();




        for(int i = 0 ; i < tasks.size() ; i++){
            if(tasks.get(i).getDay() == day && tasks.get(i).getMonth() == month && tasks.get(i).getYear() == year){
                types.add(tasks.get(i).getType()) ;
                subjects.add(tasks.get(i).getSubject()) ;
                Des.add(tasks.get(i).getDetails()) ;
                Day.add(tasks.get(i).getDay()) ;
                Month.add(tasks.get(i).getMonth()) ;
                Year.add(tasks.get(i).getYear()) ;
            }

        }





        for(int i = 0 ; i < types.size() ;i++){
            Intent myIntent = new Intent(context.getApplicationContext(), ShowTask.class);
            myIntent.putExtra("Subject" ,subjects.get(i));
            myIntent.putExtra("Type" ,types.get(i));
            myIntent.putExtra("Day" ,Day.get(i));
            myIntent.putExtra("Month" ,Month.get(i));
            myIntent.putExtra("Year" ,Year.get(i));
            myIntent.putExtra("Des" ,Des.get(i));
            myIntent.putExtra("n" ,i+1);

            PendingIntent pendingIntent = PendingIntent.getActivity(context.getApplicationContext(), 0, myIntent , Intent.FLAG_ACTIVITY_NEW_TASK);


            NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(context);
            mBuilder.setSmallIcon(R.drawable.buc);
            mBuilder.setContentTitle("BUCTask");
            mBuilder.setContentIntent(pendingIntent);

               mBuilder.setContentText("Click here to show the task ! ");

            NotificationManager mNotificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

            mNotificationManager.notify(i+1, mBuilder.build());
        }




    }



}
