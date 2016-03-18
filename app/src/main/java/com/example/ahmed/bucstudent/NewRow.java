package com.example.ahmed.bucstudent;

import android.app.AlarmManager;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.afollestad.materialdialogs.AlertDialogWrapper;
import com.rengwuxian.materialedittext.MaterialEditText;
import com.sleepbot.datetimepicker.time.RadialPickerLayout;
import com.sleepbot.datetimepicker.time.TimePickerDialog;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;




public class NewRow extends ActionBarActivity implements TimePickerDialog.OnTimeSetListener{

    SubjectDataSource sds ;
    RowDataSource rowDataSource;


    public static Button setStartTime ;
    public static Button setEndTime ;

    public static int startTimeH ;
    public static int startTimeM ;
    public static String startAorP ;

    public static int startTimeH24 ;
    public static int startTimeM24 ;

    public static int endTimeH ;
    public static int endTimeM ;
    public static String endAorP ;

    public static String instructor ;

    public static int selectedDay ;

    public static List<Subject> rowLsit ;

    int id = 200;
    Intent myIntent;
    PendingIntent pendingIntent;
    AlarmManager alarmManager;

    public static final String TIMEPICKER_TAG = "timepicker";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_row);

        sds = new SubjectDataSource(this);
        sds.open();
        List<Subject> subjects = sds.getAllSubjects();
        sds.close();

        rowDataSource = new RowDataSource(this);
        rowDataSource.open();
        rowLsit = rowDataSource.getAllRows();
        rowDataSource.close();



        if(subjects.size() == 0){


            new AlertDialogWrapper.Builder(this)
                    .setTitle(getString(R.string.noSubjectsMsgTitle))
                    .setMessage(getString(R.string.noSubjectMsg))
                    .setPositiveButton(getString(R.string.yes), new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            startActivity(new Intent(NewRow.this, NewSubject.class));
                        }
                    })
                    .setNegativeButton(getString(R.string.no), new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            NewRow.this.finish();

                        }
                    })
                    .show();
        }



        rowDataSource = new RowDataSource(this);
        final Button setSubject = (Button)findViewById(R.id.setSubject);
        final Button setDay = (Button)findViewById(R.id.setDay);
        setStartTime = (Button)findViewById(R.id.setStartTime);
        setEndTime = (Button)findViewById(R.id.setEndTime);

        setSubject.setText(getString(R.string.setSubject));
        setDay.setText(getString(R.string.setDay));
        setStartTime.setText(getString(R.string.setStartTime));
        setEndTime.setText(getString(R.string.setEndTime));
        final MaterialEditText room = (MaterialEditText)findViewById(R.id.room);
        Button addRow = (Button)findViewById(R.id.addRowBtn);



        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);


        final String[] subNames = new String[subjects.size()];

        for(int i = 0 ; i < subjects.size() ; i++){
            subNames[i] = subjects.get(i).getSubjectName();
        }

        final String[] subIns = new String[subjects.size()];

        for(int i = 0 ; i < subjects.size() ; i++){
            subIns[i] = subjects.get(i).getInstructorName();
        }

        ArrayAdapter<String> subjAdapter = new ArrayAdapter<String>(this , android.R.layout.simple_list_item_1 , android.R.id.text1 , subNames);


        final ListView subjList = new ListView(this);
        subjList.setDivider(null);
        subjList.setAdapter(subjAdapter);

        AlertDialog.Builder sBuilder = new AlertDialog.Builder(this);
        sBuilder.setView(subjList);
        final Dialog sDialog = sBuilder.create();

        setSubject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sDialog.show();
            }
        });

        subjList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                NewRow.instructor = subIns[position];
                setSubject.setText(subNames[position]);
                setSubject.setTextColor(Color.parseColor("#f44336"));
                sDialog.cancel();
            }
        });

        final String[] days = new String[]{getString(R.string.sundayDaysList) , getString(R.string.mondayDaysList) , getString(R.string.tuesdayDaysList) , getString(R.string.wednesdayDaysList) , getString(R.string.thursdayDaysList)};
        ListView daysList = new ListView(this);
        daysList.setDivider(null);


        daysList.setAdapter(new ArrayAdapter<String>(this , android.R.layout.simple_list_item_1 , android.R.id.text1 , days));

        AlertDialog.Builder dBuilder = new AlertDialog.Builder(this);
        dBuilder.setView(daysList);
        final Dialog dDialog = dBuilder.create();


        //////////START




        /////////END

        setDay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dDialog.show();


            }
        });

        daysList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                selectedDay = position ;
                setDay.setText(days[position]);
                setDay.setTextColor(Color.parseColor("#f44336"));
                dDialog.cancel();
            }
        });



        addRow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String[] eDays = {"Sunday" , "Monday" , "Tuesday" , "Wednesday" , "Thursday"};
                final String mDay = eDays[selectedDay];



                if(setSubject.getText().toString() == getString(R.string.setSubject) || setDay.getText().toString() == getString(R.string.setDay) || setStartTime.getText().toString() == getString(R.string.setStartTime) || setEndTime.getText().toString() == getString(R.string.setEndTime)|| room.getText().toString().length() == 0){
                    Toast.makeText(NewRow.this, getString(R.string.allDataMsg), Toast.LENGTH_SHORT).show();
                }else{

                    rowDataSource.open();
                    List<Subject> rows = rowDataSource.getAllRows();
                    rowDataSource.close();

                    boolean check = true ;
                    for(int i = 0 ; i < rows.size() ; i++){
                        if(rows.get(i).getStartTimeH() == startTimeH24 && rows.get(i).getStartTimeM() == startTimeM24 && rows.get(i).getDayIndex() == selectedDay){
                            check = false ;
                        }
                    }
                    if(check == true){
                        myIntent = new Intent(getBaseContext(), RowRecevier.class);

                        pendingIntent = PendingIntent.getBroadcast(getBaseContext(), id+rows.size(), myIntent, 0);

                        alarmManager = (AlarmManager)getSystemService(ALARM_SERVICE);
                        Calendar calendar = Calendar.getInstance();


                        calendar.set(Calendar.DAY_OF_WEEK,selectedDay);
                        calendar.set(Calendar.HOUR,startTimeH24);
                        calendar.set(Calendar.MINUTE, startTimeM24);


                        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(),AlarmManager.INTERVAL_DAY * 7, pendingIntent);

                        rowDataSource.open();
                        rowDataSource.createRow(setSubject.getText().toString(), mDay, startTimeH24, startTimeM24, NewRow.startAorP, NewRow.endTimeH, NewRow.endTimeM, NewRow.endAorP, room.getText().toString(),selectedDay, NewRow.instructor);
                        rowDataSource.close();
                        Toast.makeText(NewRow.this, getString(R.string.addSubjectMsg), Toast.LENGTH_SHORT).show();
                        finish();
                    }else{
                        Toast.makeText(NewRow.this,"You have a subject at this time !", Toast.LENGTH_SHORT).show();
                    }




                }
            }
        });

        final Calendar calendar = Calendar.getInstance();

        final TimePickerDialog sTimePickerDialog = TimePickerDialog.newInstance(this , calendar.get(Calendar.HOUR_OF_DAY) ,calendar.get(Calendar.MINUTE), false, false);

        setStartTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sTimePickerDialog.setVibrate(true);
                sTimePickerDialog.show(getSupportFragmentManager(), TIMEPICKER_TAG);
            }
        });


        final TimePickerDialog eTimePickerDialog = TimePickerDialog.newInstance(new TimePicker2() , calendar.get(Calendar.HOUR_OF_DAY) ,calendar.get(Calendar.MINUTE), false, false);

        setEndTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                eTimePickerDialog.setVibrate(true);
                eTimePickerDialog.show(getSupportFragmentManager(), TIMEPICKER_TAG);
            }
        });

    }




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_new_line, menu);
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

    @Override
    public void onTimeSet(RadialPickerLayout radialPickerLayout, int h, int m) {
        startTimeH24 = h ;
        startTimeM24 = m ;

        if(radialPickerLayout.getIsCurrentlyAmOrPm() == 0)
            startAorP = "AM" ;
        else
           startAorP = "PM" ;
        if(h > 12){
            h = h-12 ;
        }



        if(h == 0)
            h = 12 ;
        startTimeH = h ;
        startTimeM = m ;

        setStartTime.setTextColor(Color.parseColor("#f44336"));
        NewRow.setStartTime.setText(h+":"+String.format("%02d" , m)+" "+startAorP);

    }
}
