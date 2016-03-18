package com.example.ahmed.bucstudent;


import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;

import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.afollestad.materialdialogs.AlertDialogWrapper;


import java.util.List;
import java.util.Locale;


public class ScheduleAdapter  extends ArrayAdapter{
    public static int pos ;
    private Context context ;
    private String[] weekDays ;
    int x = 0 ;

    RowDataSource rds ;

    public ScheduleAdapter(Context context , String[] weekDays   ) {
        super(context, R.layout.activity_schedule_adapter , weekDays);

        this.weekDays = weekDays ;
        this.context = context ;
        rds = new RowDataSource(context);


    }


    @Override
    public View getView(final int position, final View convertView, final ViewGroup parent) {
        LayoutInflater layoutInflater = (LayoutInflater)context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.activity_schedule_adapter , parent , false) ;


        final ListView adapterList = (ListView)view.findViewById(R.id.sAdapterList);
        TextView day = (TextView)view.findViewById(R.id.day);
        final ImageView showBtn = (ImageView)view.findViewById(R.id.showBtn);

        Typeface custom_font = Typeface.createFromAsset(context.getAssets(), "c.ttf");

        day.setTypeface(custom_font);
        day.setText(weekDays[position]);



        //"Sunday" , "Monday" , "Tuesday" , "Wednesday" , "Thursday"
        String[] mDay ={"Sunday" , "Monday" , "Tuesday" , "Wednesday" , "Thursday"};





        rds.open();
        final List<Subject> today = rds.findRows(mDay[position]);
        rds.close();



        for(int i = 0 ; i < today.size() ; i++){
            for(int y = 0 ; y < today.size()-1 ; y++){
                if (today.get(y).getStartTimeH() > today.get(y + 1).getStartTimeH()) {
                    Subject s = new Subject();
                    s = today.get(y) ;
                    today.set(y, today.get(y + 1));
                    today.set(y + 1, s);

                }
            }
        }





        String[] mySubjectName = new String[today.size()];

        for(int i = 0 ; i < today.size() ; i++){
            mySubjectName[i] = today.get(i).getSubjectName() ;
        }

        String[] myDays = new String[today.size()];

        for(int i = 0 ; i < today.size() ; i++){
            myDays[i] =  today.get(i).getDay() ;
        }

        int[] myStartH = new int[today.size()];

        for(int i = 0 ; i < today.size() ; i++){
            myStartH[i] =  today.get(i).getStartTimeH() ;
        }

        int[] myStartM = new int[today.size()];

        for(int i = 0 ; i < today.size() ; i++){
            myStartM[i] = today.get(i).getStartTimeM() ;
        }

        String[] myStartAP = new String[today.size()];

        for(int i = 0 ; i < today.size() ; i++){
            myStartAP[i] = today.get(i).getStartTimeAP() ;
        }

        int[] myEndH = new int[today.size()];

        for(int i = 0 ; i < today.size() ; i++){
            myEndH[i] = today.get(i).getEndTimeH() ;
        }

        int[] myEndM = new int[today.size()];

        for(int i = 0 ; i < today.size() ; i++){
            myEndM[i] = today.get(i).getEndTimeM() ;
        }

        String[] myEndAP = new String[today.size()];

        for(int i = 0 ; i < today.size() ; i++){
            myEndAP[i] = today.get(i).getEndTimeAP() ;
        }

        String[] myRooms = new String[today.size()];

        for(int i = 0 ; i < today.size() ; i++){
            myRooms[i] = today.get(i).getRoom() ;
        }

        String[] myInsNames = new String[today.size()];

        for(int i = 0 ; i < today.size() ; i++){
            myInsNames[i] =today.get(i).getInstructorName() ;
        }



        AdapterListAdapter mAdapter = new AdapterListAdapter(context , mySubjectName , myStartH , myStartM ,myStartAP , myEndH , myEndM , myEndAP , myInsNames , myRooms);

        adapterList.setAdapter(mAdapter);

        adapterList.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(final AdapterView<?> parent, View view, final int position, long id) {
                ScheduleAdapter.pos = position;
                new AlertDialogWrapper.Builder(context)
                        .setTitle(R.string.deleteNoteMsgTitle)
                        .setMessage(R.string.deleteNoteMsg)
                        .setPositiveButton(R.string.deleteMsgBtn, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                rds.open();
                                rds.deleteRow(today.get(ScheduleAdapter.pos).getId());
                                rds.close();
                                cancelAlarm(position+200);
                                Toast.makeText(context,R.string.successDeleteSubject, Toast.LENGTH_SHORT).show();

                            }
                        })
                        .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {


                            }
                        })
                        .show();

                return false;
            }
        });
        int totalHeight = 0;

        for (int i = 0; i < mAdapter.getCount(); i++) {
            View mView = mAdapter.getView(i, null, adapterList);

            mView.measure(
                    View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED),

                    View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED));

            totalHeight += mView.getMeasuredHeight();


        }

        ViewGroup.LayoutParams params = adapterList.getLayoutParams();
        params.height = totalHeight
                + (adapterList.getDividerHeight() * (mAdapter.getCount() - 1));
        adapterList.setLayoutParams(params);
        adapterList.requestLayout();

        adapterList.setVisibility(View.GONE);
        showBtn.setBackgroundResource(R.drawable.down_icon);


        showBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if(x == 0){
                    adapterList.setVisibility(View.VISIBLE);
                    showBtn.setBackgroundResource(R.drawable.up_icon);
                    x = 1 ;
                }else{
                    adapterList.setVisibility(View.GONE);
                    showBtn.setBackgroundResource(R.drawable.down_icon);
                    x = 0;
                }
            }
        });



       return view ;
    }
    private void cancelAlarm(int alarmRQS){



        Intent intent = new Intent(context.getApplicationContext(), AlarmReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context.getApplicationContext(), alarmRQS, intent, 0);
        AlarmManager alarmManager = (AlarmManager)context.getSystemService(Context.ALARM_SERVICE);
        alarmManager.cancel(pendingIntent);

    }
}
