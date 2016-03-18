package com.example.ahmed.bucstudent;

import android.graphics.Color;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.rengwuxian.materialedittext.MaterialEditText;

public class Note extends ActionBarActivity {
    NoteDataSource nds ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note);

        nds = new NoteDataSource(this);

        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);


        Button addNoteBtn = (Button)findViewById(R.id.saveNoteButton);
        final MaterialEditText title = (MaterialEditText)findViewById(R.id.noteTitle);
        final EditText text = (EditText)findViewById(R.id.NoteText);

        addNoteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(title.getText().toString().length() == 0 || text.getText().toString().length() == 0){
                    Toast.makeText(Note.this, getString(R.string.allDataMsg), Toast.LENGTH_SHORT).show();
                }else{
                    nds.open();
                    nds.createNote(title.getText().toString(), text.getText().toString());
                    nds.close();
                    Toast.makeText(Note.this , getString(R.string.successAddNoteMsg) , Toast.LENGTH_SHORT).show();
                    finish();
                }
            }
        });



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_note, menu);
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
