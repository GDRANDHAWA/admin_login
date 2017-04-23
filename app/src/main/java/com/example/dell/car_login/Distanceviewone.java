package com.example.dell.car_login;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;

import java.util.Calendar;

public class Distanceviewone extends AppCompatActivity {
    EditText dveh,dtodate,dfromdate;

    String sdveh,sdtodate,sdfromdate,date2,date3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_distanceviewone);
        dveh=(EditText)findViewById(R.id.vehnumbr);
        dtodate=(EditText)findViewById(R.id.todate);
        dfromdate=(EditText)findViewById(R.id.fdate);

    }

    public void viewdist(View view) {
        sdveh = dveh.getText().toString();
        sdtodate = dtodate.getText().toString();
        sdfromdate = dfromdate.getText().toString();
        Intent i = new Intent(Distanceviewone.this,Distanceview.class);

        i.putExtra("vehiclenum",sdveh);
        i.putExtra("todate",sdtodate);
        i.putExtra("fromdate",sdfromdate);
        startActivity(i);
    }

    public void dvcancel(View view) {
        finish();
    }

    public void opencal1(View view) {
        Calendar mcurrentDate = Calendar.getInstance();
        int year = mcurrentDate.get(Calendar.YEAR);
        int  month = mcurrentDate.get(Calendar.MONTH);
        int  day = mcurrentDate.get(Calendar.DAY_OF_MONTH);

        final DatePickerDialog mDatePicker = new DatePickerDialog(Distanceviewone.this , new DatePickerDialog.OnDateSetListener()
        {
            @Override
            public void onDateSet(DatePicker datepicker, int year, int month, int day) {

                date2 = String.valueOf(day)+"/"+String.valueOf(month + 1)+"/"+String.valueOf(year);

                dfromdate.setText(date2);


            }
        }, year, month, day);
        mDatePicker.setTitle("Please Select Date");

        // mDatePicker.getDatePicker().setMinDate(System.currentTimeMillis());

        mDatePicker.show();
    }

    public void opencal2(View view) {
        Calendar mcurrentDate = Calendar.getInstance();
        int year = mcurrentDate.get(Calendar.YEAR);
        int  month = mcurrentDate.get(Calendar.MONTH);
        int  day = mcurrentDate.get(Calendar.DAY_OF_MONTH);

        final DatePickerDialog mDatePicker = new DatePickerDialog(Distanceviewone.this , new DatePickerDialog.OnDateSetListener()
        {
            @Override
            public void onDateSet(DatePicker datepicker, int year, int month, int day) {

                date3 = String.valueOf(day)+"/"+String.valueOf(month + 1)+"/"+String.valueOf(year);

                dtodate.setText(date3);


            }
        }, year, month, day);
        mDatePicker.setTitle("Please Select Date");

        // mDatePicker.getDatePicker().setMinDate(System.currentTimeMillis());

        mDatePicker.show();
    }
}
