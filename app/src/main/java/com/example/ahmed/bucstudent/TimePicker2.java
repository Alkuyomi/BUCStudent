package com.example.ahmed.bucstudent;


import android.graphics.Color;

import com.sleepbot.datetimepicker.time.RadialPickerLayout;
import com.sleepbot.datetimepicker.time.TimePickerDialog;

public class TimePicker2 implements TimePickerDialog.OnTimeSetListener{
    @Override
    public void onTimeSet(RadialPickerLayout radialPickerLayout, int h, int m) {
        if(radialPickerLayout.getIsCurrentlyAmOrPm() == 0)
           NewRow.endAorP = "AM" ;
        else
           NewRow.endAorP = "PM" ;

        if(h > 12){
            h = h-12 ;
        }
        if(h == 0)
            h = 12 ;

        NewRow.endTimeH = h ;
        NewRow.endTimeM = m ;
        NewRow.setEndTime.setTextColor(Color.parseColor("#f44336"));
        NewRow.setEndTime.setText(h+":"+String.format("%02d" , m)+" "+ NewRow.endAorP);
    }
}
