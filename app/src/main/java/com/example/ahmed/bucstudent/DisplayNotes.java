package com.example.ahmed.bucstudent;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.afollestad.materialdialogs.AlertDialogWrapper;
import com.gc.materialdesign.views.ButtonFloat;

import java.util.List;

public class DisplayNotes extends ActionBarActivity {
   NoteDataSource nds ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_notes);

        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        nds = new NoteDataSource(this);



        ButtonFloat addNoteBtn = (ButtonFloat)findViewById(R.id.addNoteBtn);

        addNoteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(DisplayNotes.this, Note.class));
            }
        });


    }

    @Override
    protected void onResume() {
        super.onResume();
        nds.open();
        final List<NoteData> notes = nds.getAllNotes();
        nds.close();

        String[] title = new String[notes.size()] ;
        //String[] text = new String[notes.size()] ;

        for(int i = 0 ; i < notes.size() ; i++){
            title[i] = notes.get(i).getTitle();
            // text[i] = notes.get(i).getText();
        }

        NoteListAdapter adapter = new NoteListAdapter(this , title );
         ListView noteListView = (ListView)findViewById(R.id.display_notes_list);
        noteListView.setDivider(null);

        noteListView.setAdapter(adapter);

        noteListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                DisplaySubjects.pos = position;
                new AlertDialogWrapper.Builder(DisplayNotes.this)
                        .setTitle(getString(R.string.deleteNoteMsgTitle))
                        .setMessage(getString(R.string.deleteNoteMsg))
                        .setPositiveButton(getString(R.string.delete), new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                nds.open();
                                nds.deleteNote(notes.get(DisplaySubjects.pos).getId());
                                nds.close();
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

        noteListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                ///// here u arrived

                nds.open();
                NoteData note = nds.getNote(notes.get(i).getId());
                nds.close();

                Intent intent = new Intent(DisplayNotes.this , ShowNote.class);
                intent.putExtra("id" , note.getId());

                startActivity(intent);
            }
        });



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_display_notes, menu);
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
