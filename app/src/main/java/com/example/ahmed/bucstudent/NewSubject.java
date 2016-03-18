package com.example.ahmed.bucstudent;


import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.rengwuxian.materialedittext.MaterialEditText;



public class NewSubject extends ActionBarActivity {
    SubjectDataSource sds ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_subject);


        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        sds = new SubjectDataSource(this);



        final MaterialEditText SubjectName = (MaterialEditText)findViewById(R.id.subject_name);
        final MaterialEditText InstructorName = (MaterialEditText)findViewById(R.id.instructor_name);
        Button addBtn = (Button)findViewById(R.id.addBtn);

        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(SubjectName.getText().toString().length() < 3 || InstructorName.getText().toString().length() < 3){
                    Toast.makeText(NewSubject.this , getString(R.string.allDataMsg) , Toast.LENGTH_SHORT).show();
                }else{
                    sds.open();
                    sds.createSubject(SubjectName.getText().toString() ,InstructorName.getText().toString() );
                    sds.close();
                    Toast.makeText(NewSubject.this , getString(R.string.successAddSubjectMsg) , Toast.LENGTH_SHORT).show();
                    finish();
                }
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_new_subject, menu);
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
