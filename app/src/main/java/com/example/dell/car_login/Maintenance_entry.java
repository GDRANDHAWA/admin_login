package com.example.dell.car_login;

import android.app.DatePickerDialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Calendar;

public class Maintenance_entry extends AppCompatActivity {
    int year;
    int month;
    int day;

    EditText ammtt, mdatee, mbill, workdn;
    Button msave, mcancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maintenance_entry);
        ammtt = (EditText) findViewById(R.id.amu);
        mdatee = (EditText) findViewById(R.id.mdate);
        mbill = (EditText) findViewById(R.id.mbillno);
        workdn = (EditText) findViewById(R.id.detail);
        mcancel= (Button) findViewById(R.id.cancel_man);
        Calendar c = Calendar.getInstance();


        mdatee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar mcurrentDate = Calendar.getInstance();
                year = mcurrentDate.get(Calendar.YEAR);
                month = mcurrentDate.get(Calendar.MONTH);
                day = mcurrentDate.get(Calendar.DAY_OF_MONTH);

                final DatePickerDialog mDatePicker = new DatePickerDialog(Maintenance_entry.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datepicker, int selectedyear, int selectedmonth, int selectedday) {
                        mdatee.setText(new StringBuilder().append(day).append("/").append(month + 1).append("/").append(year));
                        int month_k = selectedmonth + 1;

                    }
                }, year, month, day);
                mDatePicker.setTitle("Please select date");
                // TODO Hide Future Date Here
                mDatePicker.getDatePicker().setMaxDate(System.currentTimeMillis());

                // TODO Hide Past Date Here
                //  mDatePicker.getDatePicker().setMinDate(System.currentTimeMillis());
                mDatePicker.show();
            }
        });

    }

    public void addmaintenance(View v) {

        final Button save_btn = (Button) v;

        save_btn.setEnabled(false);
        save_btn.setText("saving...");

        String mamount = ammtt.getText().toString();
        String mdate = mdatee.getText().toString();
        String mbillno = mbill.getText().toString();
        String mworkdone = workdn.getText().toString();

        if (mamount.equals("")) {
            Toast.makeText(Maintenance_entry.this, "Please enter Amount", Toast.LENGTH_SHORT).show();

            return;
        }

        if (mdatee.equals("")) {
            Toast.makeText(Maintenance_entry.this, "Please enter DATE", Toast.LENGTH_SHORT).show();

            return;
        }


        if (mbill.equals("")) {
            Toast.makeText(Maintenance_entry.this, "Please enter Bill No.", Toast.LENGTH_SHORT).show();

            return;
        }

        if (mworkdone.equals("")) {
            Toast.makeText(Maintenance_entry.this, "Please enter Maintenance/service Details", Toast.LENGTH_SHORT).show();

            return;
        }
        SharedPreferences sp = getSharedPreferences("driver_info",MODE_PRIVATE);


        String driver_vehiclenum = sp.getString("vehicle_no" , "");

        JSONObject jobj = new JSONObject();
        try {
            jobj.put("mamtkey", mamount);
            jobj.put("mdatekey", mdate);
            jobj.put("mbillkey", mbillno);
            jobj.put("workkey", mworkdone);
            jobj.put("mveh_numkey" ,driver_vehiclenum );

        } catch (JSONException e) {
            e.printStackTrace();
        }


        System.out.println(jobj);

        JsonObjectRequest jobjreq = new JsonObjectRequest("http://"+Ipaddress.ip+"/Cartracking/maintenancetry.php", jobj, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                save_btn.setEnabled(true);
                save_btn.setText("save");

                System.out.println(response);
                try {
                    if (response.getString("result").equals("done")) {
                        Toast.makeText(Maintenance_entry.this, "Maintenance data added", Toast.LENGTH_SHORT).show();
                        finish();
                    } else {
                        Toast.makeText(Maintenance_entry.this, "error try again", Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                save_btn.setEnabled(true);
                save_btn.setText("save");

                System.out.println(error);
            }
        });

        jobjreq.setRetryPolicy(new DefaultRetryPolicy(2000, 3, 2));
        AppController ap = new AppController(Maintenance_entry.this);
        ap.addToRequestQueue(jobjreq);
    }
    public void mncncl(View v){
        finish();
    }


}