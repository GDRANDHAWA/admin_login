package com.example.dell.car_login;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONException;
import org.json.JSONObject;

public class Fuelreportsecond extends AppCompatActivity {
    TextView tfuel,tamount,fueltdate,fuelfromdate,maxfuel,mfdate,drivernm;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_fuelreportsecond);
        get_fuelreport();
        tfuel=(TextView)findViewById (R.id.totalfuel2);
        tamount=(TextView)findViewById (R.id.fuelamt2);
        fuelfromdate=(TextView)findViewById (R.id.ffdate2);
       fueltdate=(TextView)findViewById (R.id.tfdate2);
        maxfuel=(TextView)findViewById (R.id.maxfuel2);
        mfdate=(TextView)findViewById (R.id.maxfueldate2);
        drivernm =(TextView)findViewById (R.id.drname2);

    }
    public void get_fuelreport()
    {

        JSONObject jobj = new JSONObject();
        try {
            jobj.put("vehiclenum_key",getIntent().getStringExtra("vehiclenumber"));
            jobj.put("fueltodate_key",getIntent().getStringExtra("fueltodate"));
            jobj.put("fuelfromdate_key",getIntent().getStringExtra("fuelfromdate"));


        } catch (JSONException e) {
            e.printStackTrace();
        }


        System.out.println(jobj);
        // making json array request to get json array data
        JsonObjectRequest jsonreq = new JsonObjectRequest("http://"+Ipaddress.ip+"/Cartracking/viewfuelreport.php", jobj,new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                System.out.println(response);



                try {
                    String s = response.getString ("amount");
                    String s2 = response.getString ("fuel");
                    tamount.setText(s);
                    tfuel.setText(s2);



                } catch (JSONException e) {
                    e.printStackTrace();


                    // making object of adapter class and passing json array and Activity to adapter constructer



                    // setting properties for recycler view like how it scroll vertically , horizontally

                }
            }}, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                System.out.println(error);

            }
        });


        jsonreq.setRetryPolicy(new DefaultRetryPolicy (20000 , 2 ,2));

        AppController app = new AppController(Fuelreportsecond.this);

        app.addToRequestQueue(jsonreq);
    }
}
