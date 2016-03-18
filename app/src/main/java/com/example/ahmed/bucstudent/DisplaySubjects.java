package com.example.ahmed.bucstudent;

import android.content.DialogInterface;
import android.content.Intent;

import android.graphics.Color;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;

import android.widget.ListView;
import android.support.v7.widget.Toolbar;

import com.afollestad.materialdialogs.AlertDialogWrapper;
import com.gc.materialdesign.views.ButtonFloat;

import java.util.List;


public class DisplaySubjects extends ActionBarActivity {


    Toolbar toolbar ;
    ListView dsl ;
    SubjectDataSource sds ;

    public static int pos ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_subjects);
        dsl = (ListView)findViewById(R.id.display_subjects_list);

        sds = new SubjectDataSource(this);

        sds.open();
        final List<Subject> subjects = sds.getAllSubjects();
        sds.close();

        String[] snames = new String[subjects.size()];

        for(int i = 0 ; i < subjects.size() ; i++){
            snames[i] = subjects.get(i).getSubjectName();
        }

        String[] inames = new String[subjects.size()];

        for(int i = 0 ; i < subjects.size() ; i++){
            inames[i] = subjects.get(i).getInstructorName();
        }

        SubjectsListAdapter adapter = new SubjectsListAdapter(this , snames , inames);

        dsl.setAdapter(adapter);

        ButtonFloat btn = (ButtonFloat)findViewById(R.id.buttonFloat);
        btn.setBackgroundColor(Color.parseColor("#f44336"));
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DisplaySubjects.this, NewSubject.class));
            }
        });


        toolbar = (Toolbar)findViewById(R.id.toolbar);


        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);






    }

    @Override
    protected void onResume() {
        super.onResume();

        sds = new SubjectDataSource(this);

        sds.open();
        final List<Subject> subjects = sds.getAllSubjects();
        sds.close();

        String[] snames = new String[subjects.size()];

        for(int i = 0 ; i < subjects.size() ; i++){
            snames[i] = subjects.get(i).getSubjectName();
        }

        String[] inames = new String[subjects.size()];

        for(int i = 0 ; i < subjects.size() ; i++){
            inames[i] = subjects.get(i).getInstructorName();
        }

        SubjectsListAdapter adapter = new SubjectsListAdapter(this , snames , inames);

        dsl.setAdapter(adapter);

        dsl.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                DisplaySubjects.pos = position ;
                new AlertDialogWrapper.Builder(DisplaySubjects.this)
                        .setTitle(getString(R.string.deleteSubjectTitle))
                        .setMessage(getString(R.string.deleteSubjectMsg))
                        .setPositiveButton(getString(R.string.delete), new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                sds.open();
                                sds.deleteSubject(subjects.get(DisplaySubjects.pos).getId());
                                sds.close();


                                onResume();

                            }
                        })
                        .setNegativeButton(getString(R.string.cancel), new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {


                            }
                        })
                        .show();

                return false;
            }
        });

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
            this.finish();
        }

        return super.onOptionsItemSelected(item);
    }
}



