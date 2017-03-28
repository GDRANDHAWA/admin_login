package com.example.dell.car_login;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONException;
import org.json.JSONObject;

public class Fuel_entry extends AppCompatActivity {
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
    }
    public void addfueld(View v){
        String amountt = amtt.getText().toString();
        String date = fdate.getText().toString();
        String billno = billn.getText().toString();
        String fuel = fuell.getText().toString();

        if (amountt.equals("")) {
            Toast.makeText(Fuel_entry.this,"Please enter name",Toast.LENGTH_SHORT).show();

            return ;
        }

        if (date.equals("")) {
            Toast.makeText(Fuel_entry.this,"Please enter Contact no",Toast.LENGTH_SHORT).show();

            return ;
        }


        if (billno.equals("")) {
            Toast.makeText(Fuel_entry.this,"Please enter emailid",Toast.LENGTH_SHORT).show();

            return ;
        }

        if (fuel.equals("")) {
            Toast.makeText(Fuel_entry.this,"Please enter Age",Toast.LENGTH_SHORT).show();

            return ;
        }

        JSONObject jobj = new JSONObject();
        try {
            jobj.put("amtkey",amountt);
            jobj.put("datekey",date);
            jobj.put("billkey",billno);
            jobj.put("fuelkey",fuell);

        } catch (JSONException e) {
            e.printStackTrace();
        }


        System.out.println(jobj);

        JsonObjectRequest jobjreq = new JsonObjectRequest("http://192.168.0.42/fuelentry.php", jobj, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    if (response.getString("result").equals("done")) {
                        Toast.makeText(Fuel_entry.this, "fuel data added", Toast.LENGTH_SHORT).show();
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

}
