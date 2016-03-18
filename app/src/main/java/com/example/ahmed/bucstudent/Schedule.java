package com.example.ahmed.bucstudent;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import android.widget.ListView;

import com.gc.materialdesign.views.ButtonFloat;




public class Schedule extends ActionBarActivity {
    ListView daysList ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule);
         daysList = (ListView)findViewById(R.id.daysList);

        ButtonFloat newRow = (ButtonFloat)findViewById(R.id.addRowBtn);
        newRow.setBackgroundColor(Color.parseColor("#f44336"));

        newRow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Schedule.this , NewRow.class));
            }
        });

        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);




        String[] weekDays = new String[]{getString(R.string.sundayDaysList) , getString(R.string.mondayDaysList) , getString(R.string.tuesdayDaysList) , getString(R.string.wednesdayDaysList) , getString(R.string.thursdayDaysList)};
        ScheduleAdapter adapter = new ScheduleAdapter(this , weekDays );

        daysList.setAdapter(adapter);
        daysList.setDivider(null);


    }


    @Override
    protected void onResume() {
        super.onResume();
        String[] weekDays = new String[]{getString(R.string.sundayDaysList) , getString(R.string.mondayDaysList) , getString(R.string.tuesdayDaysList) , getString(R.string.wednesdayDaysList) , getString(R.string.thursdayDaysList)};
        ScheduleAdapter adapter = new ScheduleAdapter(this , weekDays );

        daysList.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_schedule, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
       if(id == android.R.id.home){
            this.finish();
        }

        return super.onOptionsItemSelected(item);
    }
}
