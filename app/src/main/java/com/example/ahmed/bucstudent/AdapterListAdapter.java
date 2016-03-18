package com.example.ahmed.bucstudent;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class AdapterListAdapter extends ArrayAdapter<String>{

    private Context context ;
    private String[] subjNames ;
    private int[] startH ;
    private int[] startM ;
    private String[] startAP ;
    private int[] endH ;
    private int[] endM ;
    private String[] endAP ;
    private String[] insNames ;
    private String[] rooms ;


    public AdapterListAdapter(Context context ,String[] subjNames ,int[] startH , int[] startM ,String[] startAP ,int[] endH , int[] endM ,String[] endAP ,  String[] insNames , String[] rooms ) {
        super(context , R.layout.adapter_list_adapter ,subjNames);

        this.context = context ;
        this.subjNames = subjNames ;
        this.startH = startH ;
        this.startM = startM ;
        this.startAP = startAP ;
        this.endH = endH ;
        this.endM = endM ;
        this.endAP = endAP ;
        this.insNames = insNames ;
        this.rooms = rooms ;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater layoutInflater = (LayoutInflater)context.getSystemService(context.LAYOUT_INFLATER_SERVICE);

        View view = layoutInflater.inflate(R.layout.adapter_list_adapter, parent , false) ;

        TextView subjectName = (TextView)view.findViewById(R.id.subjectName);
        TextView instructorName = (TextView)view.findViewById(R.id.InstructorName);
        TextView Time = (TextView)view.findViewById(R.id.Time);
        TextView Room = (TextView)view.findViewById(R.id.Room);

       // subjectName.setText(subjNames[position]);

        if(subjNames[position].toString().length() > 15)
            subjectName.setText(subjNames[position].substring(0 ,15)+"...");
        else
            subjectName.setText(subjNames[position]);

        instructorName.setText ("("+insNames[position]+")");

        if(insNames[position].toString().length() > 10)
            instructorName.setText("("+insNames[position].substring(0 ,10)+"..."+")");
        else
            instructorName.setText("("+insNames[position]+")");


       //+":"+startM[position]+" "+startAP+" - "+endH[position]+":"+endM[position]+" "+endAP+""
        Time.setText(""+startH[position]+":"+String.format("%02d", startM[position])+" "+startAP[position]+" - "+endH[position]+":"+String.format("%02d" , endM[position])+" "+endAP[position]);
        Room.setText(rooms[position]);

        return view;
    }
}
