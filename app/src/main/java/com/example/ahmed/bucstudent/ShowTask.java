package com.example.ahmed.bucstudent;

import android.app.NotificationManager;
import android.content.Context;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;


public class ShowTask extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_task);

        String subject = getIntent().getStringExtra("Subject");
        String type = getIntent().getStringExtra("Type");
        String des = getIntent().getStringExtra("Des");
        int day = getIntent().getIntExtra("Day", -1);
        int month = getIntent().getIntExtra("Month", -1);
        int year = getIntent().getIntExtra("Year", -1);
        int n = getIntent().getIntExtra("n", -1);

        Toast.makeText(this , subject ,Toast.LENGTH_LONG).show();

        TextView subject_textView = (TextView)findViewById(R.id.subject_textView);
        TextView type_textView = (TextView)findViewById(R.id.type_textView);
        TextView date_textView = (TextView)findViewById(R.id.date_textView) ;
        TextView moreInfo_textView = (TextView)findViewById(R.id.moreInfo) ;

        subject_textView.setText(subject);
        type_textView.setText(type);
        date_textView.setText(year + "/" + month+ "/" + day);
        moreInfo_textView.setText(des);

        NotificationManager mNotificationManager = (NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);

        mNotificationManager.cancel(n);

    }


}
