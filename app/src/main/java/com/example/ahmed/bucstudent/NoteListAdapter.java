package com.example.ahmed.bucstudent;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

/**
 * Created by apple on 9/9/15.
 */
public class NoteListAdapter extends ArrayAdapter<String> {

    Context context ;
    String[] title  ;


    public NoteListAdapter(Context context, String[] title) {
        super(context , R.layout.note_list_adapter , title);
        this.context = context;
        this.title = title;

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater layoutInflater = (LayoutInflater)context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.note_list_adapter , parent , false) ;


        TextView titleT = (TextView)view.findViewById(R.id.title);


        if(title[position].toString().length() > 21)
            titleT.setText(title[position].substring(0 ,21)+"...");
        else
            titleT.setText(title[position]);


        return view;
    }
}
