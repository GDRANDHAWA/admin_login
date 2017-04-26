package com.example.dell.car_login;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONException;
import org.json.JSONObject;

public class Maintenancereportsecond extends AppCompatActivity {
    TextView mntdate,mnfdate,mnamtt,costlyworkdn,cwdate,drvrname;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_maintenancereportsecond);
        mnfdate=(TextView)findViewById (R.id.mfdate2);
        mntdate=(TextView)findViewById (R.id.tmdate2);
        mnamtt=(TextView)findViewById (R.id.mamt2);
        costlyworkdn=(TextView)findViewById (R.id.costlywork2);
        cwdate =(TextView)findViewById (R.id.costlyworkdate2);
        drvrname =(TextView)findViewById (R.id.dname2);
        get_mn();
    }
    public void get_mn()
    {

        JSONObject jobj = new JSONObject();
        try {
            jobj.put("vehiclenumber_key",getIntent().getStringExtra("vehiclenumber"));
            jobj.put("mntodate_key",getIntent().getStringExtra("maintenancetodate"));
            jobj.put("mnfromdate_key",getIntent().getStringExtra("maintenancefromdate"));


        } catch (JSONException e) {
            e.printStackTrace();
        }


        System.out.println(jobj);
        // making json array request to get json array data
        JsonObjectRequest jsonreq = new JsonObjectRequest("http://"+Ipaddress.ip+"/Cartracking/viewmnreport.php", jobj,new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                System.out.println(response);



                try {
                    String amt = response.getString ("amount");
                    mnamtt.setText (amt);





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

        AppController app = new AppController(Maintenancereportsecond.this);

        app.addToRequestQueue(jsonreq);
    }
}
