package com.example.ahmed.bucstudent;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by apple on 9/8/15.
 */
public class RowRecevier extends BroadcastReceiver {

    @Override
    public void onReceive(final Context context, Intent intent) {


        RowDataSource tds = new RowDataSource(context.getApplicationContext());
        Calendar calendar = Calendar.getInstance();

        int day = calendar.get(Calendar.DAY_OF_WEEK);
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute  = calendar.get(Calendar.MINUTE);

        tds.open();
        List<Subject> rows = tds.getAllRows();
        tds.close();

        ArrayList<String>  Subject   = new ArrayList<>();
        ArrayList<String>  Room   = new ArrayList<>();






        for(int i = 0 ; i < rows.size() ; i++){
            if(rows.get(i).getDayIndex() == day-1 && rows.get(i).getStartTimeH() == hour && rows.get(i).getStartTimeM() == minute){
                Subject.add(rows.get(i).getSubjectName()) ;
                Room.add(rows.get(i).getRoom()) ;

            }

        }





        for(int i = 0 ; i < Subject.size() ;i++){




            NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(context);
            mBuilder.setSmallIcon(R.drawable.buc);
            mBuilder.setContentTitle("BUCTask");


            mBuilder.setContentText("You have a "+Subject.get(i).toString()+" lecture in "+Room.get(i).toString());

            NotificationManager mNotificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

            mNotificationManager.notify(i+1, mBuilder.build());
        }



Toast.makeText(context.getApplicationContext() , "Hello" , Toast.LENGTH_LONG).show();




    }

}
