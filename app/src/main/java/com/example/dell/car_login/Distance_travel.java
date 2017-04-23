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

public class Distance_travel extends AppCompatActivity {
    int year;
    int month;
    int day;

    EditText ddate,dist;
    Button dissav,candist;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_distance_travel);
        ddate = (EditText)findViewById(R.id.ddate);
        dist=(EditText)findViewById(R.id.ddist);
        dissav = (Button)findViewById(R.id.dsave_btn);
        candist = (Button)findViewById(R.id.dcnclbtn);
        Calendar c = Calendar.getInstance();


        ddate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar mcurrentDate = Calendar.getInstance();
                year = mcurrentDate.get(Calendar.YEAR);
                month = mcurrentDate.get(Calendar.MONTH);
                day = mcurrentDate.get(Calendar.DAY_OF_MONTH);

                final DatePickerDialog mDatePicker = new DatePickerDialog(Distance_travel.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datepicker, int selectedyear, int selectedmonth, int selectedday) {
                        ddate.setText(new StringBuilder().append(day).append("/").append(month + 1).append("/").append(year));
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
    public void adddis(View v){
        final Button dsave_btn = (Button) v;

        dsave_btn.setEnabled(false);
        dsave_btn.setText("saving...");
        String distdate = ddate.getText().toString();
        String tdist = dist.getText().toString();


        if (distdate.equals("")) {
            Toast.makeText(Distance_travel.this,"Please enter Date",Toast.LENGTH_SHORT).show();

            return ;
        }

        if (tdist.equals("")) {
            Toast.makeText(Distance_travel.this,"Please enter Travel Distance in Km",Toast.LENGTH_SHORT).show();

            return ;
        }
        SharedPreferences sp = getSharedPreferences("driver_info",MODE_PRIVATE);


        String driver_vehiclenum = sp.getString("vehicle_no" , "");





        JSONObject jobj = new JSONObject();
        try {
            jobj.put("datedistt_key",distdate);
            jobj.put("travldist",tdist);
            jobj.put("fveh_numkey" ,driver_vehiclenum );
            jobj.put("did" , sp.getString("did",""));


        } catch (JSONException e) {
            e.printStackTrace();
        }


        System.out.println(jobj);

        JsonObjectRequest jobjreq = new JsonObjectRequest("http://"+Ipaddress.ip+"/Cartracking/distance.php", jobj, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                dsave_btn.setEnabled(true);
                dsave_btn.setText("save");
                try {
                    if (response.getString("result").equals("done")) {
                        Toast.makeText(Distance_travel.this, "Travel Distance Added", Toast.LENGTH_SHORT).show();
                        finish();
                    }




                    else{
                        Toast.makeText(Distance_travel.this,"error try again",Toast.LENGTH_SHORT).show();
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
        AppController ap = new AppController(Distance_travel.this);
        ap.addToRequestQueue(jobjreq);
    }
    public void dcancel(View v){
        finish();
    }
}
