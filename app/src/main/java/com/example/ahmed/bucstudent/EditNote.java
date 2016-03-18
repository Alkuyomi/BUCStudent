package com.example.ahmed.bucstudent;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.rengwuxian.materialedittext.MaterialEditText;

public class EditNote extends ActionBarActivity {
    int id ;
    NoteDataSource nds ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_note);


        id = getIntent().getIntExtra("id" , 0);
        nds = new NoteDataSource(this);

        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        nds.open();
        NoteData cNote = nds.getNote(id);
        nds.close();

        Button editNoteBtn = (Button)findViewById(R.id.editNoteButton);
        final MaterialEditText title = (MaterialEditText)findViewById(R.id.noteTitle);
        final EditText text = (EditText)findViewById(R.id.NoteText);


        title.setText(cNote.getTitle());
        text.setText(cNote.getText());

        editNoteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(title.getText().toString().length() == 0 || text.getText().toString().length() == 0){
                    Toast.makeText(EditNote.this, getString(R.string.allDataMsg), Toast.LENGTH_SHORT).show();
                }else{
                    nds.open();
                    nds.updateNote(id,title.getText().toString(), text.getText().toString());
                    nds.close();
                    Toast.makeText(EditNote.this , getString(R.string.successEditNoteMsg) , Toast.LENGTH_SHORT).show();
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
