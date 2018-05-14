package com.example.user.test0416_1;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.CalendarView;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
    EditText text;
    EditText text2;
    DatePicker dp;
    TimePicker tp;
    CalendarView clv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        text = (EditText) findViewById(R.id.editText);
        text2 = (EditText) findViewById(R.id.editText2);
        tp = (TimePicker) findViewById(R.id.timePicker2);
        clv = (CalendarView) findViewById(R.id.calendarView2);

        clv.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                text.setText(String.format("%4d년 %2d월 %2d일", year, (month+1), dayOfMonth));
            }
        });

        tp.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
            @Override
            public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
                text2.setText(String.format("%2d시 %2d분", hourOfDay, minute));
            }
        });

    }
}
