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

import com.fourmob.datetimepicker.date.DatePickerDialog;
import com.rengwuxian.materialedittext.MaterialEditText;


import java.util.Calendar;
import java.util.List;


public class NewTask extends ActionBarActivity  implements DatePickerDialog.OnDateSetListener {
    SubjectDataSource sds ;
    TaskDataSource tds ;
    public static List<Task>  tasks;
    public static final String DATEPICKER_TAG = "datepicker";
    public static  int year ;
    public static  int month ;
    public static  int day ;



    Button dateBtn ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_task);

        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        dateBtn = (Button)findViewById(R.id.dateBtn);
        final Button typeBtn = (Button)findViewById(R.id.typeBtn);
        final Button subjectBtn = (Button)findViewById(R.id.subjectBtn);
        final MaterialEditText otherDetails = (MaterialEditText)findViewById(R.id.other_details);


        sds = new SubjectDataSource(this);

        sds.open();
        final List<Subject> subjects = sds.getAllSubjects();
        sds.close();

        if(subjects.size() == 0){


            new AlertDialogWrapper.Builder(this)
                    .setTitle(getString(R.string.noSubjectsMsgTitle))
                    .setMessage(getString(R.string.noteNoSubjectMsg))
                    .setPositiveButton(getString(R.string.yes), new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            startActivity(new Intent(NewTask.this, NewSubject.class));
                        }
                    })
                    .setNegativeButton(getString(R.string.no), new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            NewTask.this.finish();

                        }
                    })
                    .show();
        }

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        //Database
        tds = new TaskDataSource(this);

        tds.open();
        tasks = tds.getAllTasks();
        tds.close();

        Button addTask = (Button)findViewById(R.id.addTaskBtn);

        typeBtn.setText(getString(R.string.setType));
        subjectBtn.setText(getString(R.string.setSubject));
        dateBtn.setText(getString(R.string.setDate));

        addTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(dateBtn.getText().toString() == getString(R.string.setDate) || typeBtn.getText().toString() == getString(R.string.setType) || subjectBtn.getText().toString() == getString(R.string.setSubject) || otherDetails.getText().toString().length() == 0){
                    Toast.makeText(NewTask.this, getString(R.string.allDataMsg), Toast.LENGTH_SHORT).show();
                } else {



                    Calendar current = Calendar.getInstance();
                    Calendar cal = Calendar.getInstance();

                    cal.set(NewTask.year, NewTask.month, NewTask.day);


                    if(cal.compareTo(current) <= 0){

                        Toast.makeText(getApplicationContext(),
                               "Invalid Date",
                               Toast.LENGTH_LONG).show();
                    }else{
                        tds.open();
                        tds.createTask(typeBtn.getText().toString(), NewTask.year, NewTask.month, NewTask.day, subjectBtn.getText().toString(), otherDetails.getText().toString());
                        tds.close();
                        Toast.makeText(NewTask.this , getString(R.string.successAddNote) , Toast.LENGTH_SHORT).show();

                        setAlarm(cal);
                        finish();

                    }


                }

            }
        });


        //Pickers
        dateBtn = (Button)findViewById(R.id.dateBtn);

        final Calendar calendar = Calendar.getInstance();

         final DatePickerDialog datePickerDialog = DatePickerDialog.newInstance(this, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH), true);


        dateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                datePickerDialog.setVibrate(true);
                datePickerDialog.setYearRange(2015, 2028);
                datePickerDialog.setCloseOnSingleTapDay(false);
                datePickerDialog.show(getSupportFragmentManager(), DATEPICKER_TAG);
            }
        });
        if (savedInstanceState != null) {
            DatePickerDialog dpd = (DatePickerDialog) getSupportFragmentManager().findFragmentByTag(DATEPICKER_TAG);
            if (dpd != null) {
                dpd.setOnDateSetListener(this);
            }

        }


       //Spinners


        final String[] snames = new String[subjects.size()];

        for(int i = 0 ; i < subjects.size() ; i++){
            snames[i] = subjects.get(i).getSubjectName();
        }




        ListView typeList = new ListView(this);
        ListView subjList = new ListView(this);

        typeList.setDivider(null);
        subjList.setDivider(null);


        // Type Spinner
        final String[] types = new String[]{"Exam" , "Quiz" , "Test" , "Presentation" , "Home Work" , "Report" , "Research" , "Interview" , "Event" ,"Other" };
        ArrayAdapter<String> typeAdapte = new ArrayAdapter<String>(this , android.R.layout.simple_list_item_1 , android.R.id.text1 , types);
        typeList.setAdapter(typeAdapte);

        subjList.setAdapter(new ArrayAdapter<String>(this , android.R.layout.simple_list_item_1 , android.R.id.text1 , snames));

        AlertDialog.Builder tBuilder = new AlertDialog.Builder(this);
        tBuilder.setView(typeList);
        final Dialog tDialog = tBuilder.create();



        AlertDialog.Builder sBuilder = new AlertDialog.Builder(this);
        sBuilder.setView(subjList);
        final Dialog sDialog = sBuilder.create();



        typeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tDialog.show();
            }
        });

        subjectBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sDialog.show();
            }
        });
        typeList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                typeBtn.setText(types[position]);
                typeBtn.setTextColor(Color.parseColor("#f44336"));
                tDialog.cancel();
            }
        });
        subjList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                subjectBtn.setText(snames[position]);
                subjectBtn.setTextColor(Color.parseColor("#f44336"));
                sDialog.cancel();
            }
        });
    }

    public void setAlarm(Calendar targetCal){



        Intent intent = new Intent(getBaseContext(), AlarmReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(getBaseContext(), tasks.size(), intent, 0);
        AlarmManager alarmManager = (AlarmManager)getSystemService(Context.ALARM_SERVICE);
        alarmManager.set(AlarmManager.RTC_WAKEUP, targetCal.getTimeInMillis(), pendingIntent);



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_new_task, menu);
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
    public void onDateSet(DatePickerDialog datePickerDialog, int year, int month, int day) {

           dateBtn.setText(year + "-" + month+1 + "-" + day);
           dateBtn.setTextColor(Color.parseColor("#f44336"));

        this.year = year ;
        this.month = month ;
        this.day = day ;

    }


}
