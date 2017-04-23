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

public class Fuel_entry extends AppCompatActivity {
    int year;
    int month;
    int day;
   EditText amtt,fdate,billn,fuell;
    Button bbtnn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fuel_entry);
        amtt = (EditText)findViewById(R.id.amt);
        fdate=(EditText)findViewById(R.id.black);
        billn =(EditText)findViewById(R.id.billi);
        fuell=(EditText)findViewById(R.id.fueldata);
        Calendar c = Calendar.getInstance();


        fdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar mcurrentDate = Calendar.getInstance();
                year = mcurrentDate.get(Calendar.YEAR);
                month = mcurrentDate.get(Calendar.MONTH);
                day = mcurrentDate.get(Calendar.DAY_OF_MONTH);

                final DatePickerDialog mDatePicker = new DatePickerDialog(Fuel_entry.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datepicker, int selectedyear, int selectedmonth, int selectedday) {
                        fdate.setText(new StringBuilder().append(day).append("/").append(month + 1).append("/").append(year));
                        int month_k = selectedmonth + 1;

                    }
                }, year, month, day);
                mDatePicker.setTitle("Please select date");
                // TODO Hide Future Date Here
                //mDatePicker.getDatePicker().setMaxDate(System.currentTimeMillis());

                // TODO Hide Past Date Here
                 //mDatePicker.getDatePicker().setMinDate(System.currentTimeMillis());
                mDatePicker.show();
            }
        });
    }
    public void addfueld(View v){
        final Button Fsave_btn = (Button) v;

        Fsave_btn.setEnabled(false);
        Fsave_btn.setText("saving...");
        String amountt = amtt.getText().toString();
        String date = fdate.getText().toString();
        String billno = billn.getText().toString();
        String fuel = fuell.getText().toString();

        if (amountt.equals("")) {
            Toast.makeText(Fuel_entry.this,"Please enter Amount",Toast.LENGTH_SHORT).show();

            return ;
        }

        if (date.equals("")) {
            Toast.makeText(Fuel_entry.this,"Please enter Date",Toast.LENGTH_SHORT).show();

            return ;
        }


        if (billno.equals("")) {
            Toast.makeText(Fuel_entry.this,"Please enter BillNo.",Toast.LENGTH_SHORT).show();

            return ;
        }

        if (fuel.equals("")) {
            Toast.makeText(Fuel_entry.this,"Please enter Fuel(in L)",Toast.LENGTH_SHORT).show();

            return ;
        }

        SharedPreferences sp = getSharedPreferences("driver_info",MODE_PRIVATE);


        String driver_vehiclenum = sp.getString("vehicle_no" , "");


        JSONObject jobj = new JSONObject();
        try {
            jobj.put("amtkey",amountt);
            jobj.put("datekey",date);
            jobj.put("billkey",billno);
            jobj.put("fuelkey",fuel);
            jobj.put("did" , sp.getString("did",""));
            jobj.put("veh_numkey" ,driver_vehiclenum );


        } catch (JSONException e) {
            e.printStackTrace();
        }


        System.out.println(jobj);

        JsonObjectRequest jobjreq = new JsonObjectRequest("http://"+Ipaddress.ip+"/fuelentry.php", jobj, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Fsave_btn.setEnabled(true);
                Fsave_btn.setText("save");

                try {
                    if (response.getString("result").equals("done")) {
                        Toast.makeText(Fuel_entry.this, "fuel data added", Toast.LENGTH_SHORT).show();
                        finish();
                    }




                    else{
                        Toast.makeText(Fuel_entry.this,"error try again",Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println(error);
            }
        });

        jobjreq.setRetryPolicy(new DefaultRetryPolicy(2000,3,2));
        AppController ap = new AppController(Fuel_entry.this);
        ap.addToRequestQueue(jobjreq);
    }
    public void cancelb(View v){
        finish();
    }

}
