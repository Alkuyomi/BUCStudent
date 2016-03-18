package com.example.ahmed.bucstudent;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ShowNote extends ActionBarActivity {
     NoteDataSource nds ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_note);

        nds = new NoteDataSource(this);

        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);




    }
    @Override
    protected void onResume() {
        super.onResume();


        final int id = getIntent().getIntExtra("id" , 0);

        nds.open();
        NoteData note = nds.getNote(id);
        nds.close();



        TextView aTitle = (TextView)findViewById(R.id.noteTitleTextView);
        TextView aText = (TextView)findViewById(R.id.NoteTextView);
        Button editNote = (Button)findViewById(R.id.editNoteButton);

        editNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ShowNote.this , EditNote.class);
                intent.putExtra("id" , id);
                startActivity(intent);
            }
        });



        aTitle.setText(note.getTitle());
        aText.setText(note.getText());


    }

        @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_show_note, menu);
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
