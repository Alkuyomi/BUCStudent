package com.example.ahmed.bucstudent;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;


public class SubjectsListAdapter extends ArrayAdapter<String> {
    Context context ;
   String[] subjects , instructors ;


    public SubjectsListAdapter(Context context, String[] subjects , String[] instructors) {
        super(context , R.layout.activity_subjects_list_adapter , subjects);
        this.context = context;
        this.subjects = subjects;
        this.instructors = instructors ;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater layoutInflater = (LayoutInflater)context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.activity_subjects_list_adapter , parent , false) ;


        TextView subject_name = (TextView)view.findViewById(R.id.subject_name);
        TextView instructor_name = (TextView)view.findViewById(R.id.ins_name);

        if(subjects[position].toString().length() > 21)
            subject_name.setText(subjects[position].substring(0 ,21)+"...");
        else
            subject_name.setText(subjects[position]);

        if(this.instructors[position].toString().length() > 21)
            instructor_name.setText(this.instructors[position].substring(0 ,19)+"...");
        else
            instructor_name.setText(this.instructors[position]);






        return view;
    }
}
