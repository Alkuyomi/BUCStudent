package com.example.ahmed.bucstudent;

import android.annotation.TargetApi;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Color;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Build;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.Display;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.gc.materialdesign.views.ButtonFloat;

import java.util.List;


public class MainActivity extends ActionBarActivity implements Callback {

     DrawerLayout mDrawerLayout ;
     ActionBarDrawerToggle mDrawerToggle ;
     ListView mList ;
     TaskDataSource tds ;
     TextView status ;
     public static TaskListAdapter  taskListAdapter ;
     public static List<Task>  tasks;
     public static ListView taskList;


    @TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
         status = (TextView)findViewById(R.id.status);

        tds = new TaskDataSource(this);




        tds.open();
        tasks = tds.getAllTasks();
        tds.close();

        if(tasks.size() == 0){
            status.setText(R.string.noteStatus);
        }else if (tasks.size() == 1){
            status.setText("("+tasks.size()+")"+" "+getString(R.string.oneNote));
        }else{
            status.setText("("+tasks.size()+")"+" "+getString(R.string.Notes));
        }

        String[] types    = new String[tasks.size()];
        String[] subjects = new String[tasks.size()];
        String[] details  = new String[tasks.size()];

        int[] years = new int[tasks.size()];
        int[] months = new int[tasks.size()];
        int[] days = new int[tasks.size()];


        for(int i = 0 ; i < tasks.size() ; i++){
            types[i] = tasks.get(i).getType();
        }

        for(int i = 0 ; i < tasks.size() ; i++){
            subjects[i] = tasks.get(i).getSubject();
        }

        for(int i = 0 ; i < tasks.size() ; i++){
            details[i] = tasks.get(i).getDetails();
        }

        for(int i = 0 ; i < tasks.size() ; i++){
            years[i] = tasks.get(i).getYear();
        }

        for(int i = 0 ; i < tasks.size() ; i++){
            months[i] = tasks.get(i).getMonth();
        }

        for(int i = 0 ; i < tasks.size() ; i++){
            days[i] = tasks.get(i).getDay();
        }
        taskList = (ListView)findViewById(R.id.mytasksList);
        taskListAdapter = new TaskListAdapter(this ,tasks , types , years , months , days , subjects , details);



        taskList.setAdapter(taskListAdapter);
        taskList.setDivider(null);





        ButtonFloat newTask = (ButtonFloat)findViewById(R.id.addTaskBtn);
        newTask.setBackgroundColor(Color.parseColor("#f44336"));

        newTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this , NewTask.class));

            }
        });
        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        mDrawerLayout = (DrawerLayout)findViewById(R.id.drawer);
        mList = (ListView)findViewById(R.id.list);
        mList.setDivider(null);

        String[] listItems = new String[]{getString(R.string.site),getString(R.string.Map),getString(R.string.Schedule)  , getString(R.string.SubjectDrawerMenu)  , getString(R.string.notesDrawerMenu) };

        mList.setAdapter(new ArrayAdapter<String>(this , android.R.layout.simple_list_item_1 , listItems));
        mList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(position == 0){
                    startActivity(new Intent(MainActivity.this , WebSite.class));
                    mDrawerLayout.closeDrawer(mList);
                }else if(position == 1) {
                    Intent intent = new Intent(android.content.Intent.ACTION_VIEW,
                    Uri.parse("http://maps.google.com/maps?daddr=24.234342,55.896523"));
                    startActivity(intent);
                    mDrawerLayout.closeDrawer(mList);
                }else if (position == 2){
                    startActivity(new Intent(MainActivity.this , Schedule.class));
                    mDrawerLayout.closeDrawer(mList);
                }else if (position == 3){
                    startActivity(new Intent(MainActivity.this , DisplaySubjects.class));
                    mDrawerLayout.closeDrawer(mList);
                }else if (position == 4){
                    startActivity(new Intent(MainActivity.this ,DisplayNotes.class));
                    mDrawerLayout.closeDrawer(mList);
                }

            }
        });


            mDrawerToggle = new ActionBarDrawerToggle(this , mDrawerLayout, toolbar ,R.string.drawer_open ,R.string.drawer_close  ){
            public void onDrawerOpened(View v){
                super.onDrawerOpened(v);
                invalidateOptionsMenu();
                syncState();

            }

            public void onDrawerClosed(View v){
                super.onDrawerClosed(v);
                invalidateOptionsMenu();
                syncState();
            }
        };


        mDrawerLayout.setDrawerListener(mDrawerToggle);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        mDrawerToggle.syncState();

    }


    @Override
    public void onDeleteClick() {
     taskListAdapter.notifyDataSetChanged();

        tds.open();
        tasks = tds.getAllTasks();
        tds.close();

        if(tasks.size() == 0){
            status.setText(R.string.noteStatus);
        }else if (tasks.size() == 1){
            status.setText("("+tasks.size()+")"+" "+getString(R.string.oneNote));
        }else{
            status.setText("("+tasks.size()+")"+" "+getString(R.string.Notes));
        }


        String[] types    = new String[tasks.size()];
        String[] subjects = new String[tasks.size()];
        String[] details  = new String[tasks.size()];

        int[] years = new int[tasks.size()];
        int[] months = new int[tasks.size()];
        int[] days = new int[tasks.size()];


        for(int i = 0 ; i < tasks.size() ; i++){
            types[i] = tasks.get(i).getType();
        }

        for(int i = 0 ; i < tasks.size() ; i++){
            subjects[i] = tasks.get(i).getSubject();
        }

        for(int i = 0 ; i < tasks.size() ; i++){
            details[i] = tasks.get(i).getDetails();
        }

        for(int i = 0 ; i < tasks.size() ; i++){
            years[i] = tasks.get(i).getYear();
        }

        for(int i = 0 ; i < tasks.size() ; i++){
            months[i] = tasks.get(i).getMonth();
        }

        for(int i = 0 ; i < tasks.size() ; i++){
            days[i] = tasks.get(i).getDay();
        }

        taskListAdapter = new TaskListAdapter(this ,tasks , types , years , months , days , subjects , details);



        taskList.setAdapter(taskListAdapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        onDeleteClick();
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        mDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mDrawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.

        getMenuInflater().inflate(R.menu.menu_main, menu);
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
            if(mDrawerLayout.isDrawerOpen(mList)){
                mDrawerLayout.closeDrawer(mList);

            }else{
                mDrawerLayout.openDrawer(mList);
            }
            return true ;
        }

        return super.onOptionsItemSelected(item);
    }


}
