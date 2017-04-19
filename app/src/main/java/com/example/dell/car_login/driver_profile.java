package com.example.dell.car_login;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class driver_profile extends AppCompatActivity {
TextView drname,drage,ddlnum,demail,dmobile;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver_profile);
        drname=(TextView)findViewById(R.id.drname2);
        drage=(TextView)findViewById(R.id.ag2);
        ddlnum=(TextView)findViewById(R.id.dl2);
        demail=(TextView)findViewById(R.id.dreid2);
        dmobile=(TextView)findViewById(R.id.contact2);
        get_values();
    }
    public  void get_values()
    {
        JSONObject jobj = new JSONObject();
        SharedPreferences sp = getSharedPreferences("driver_info",MODE_PRIVATE);
        String driver_uid = sp.getString("did" , "");

        try {
            jobj.put("duserid_key" , driver_uid);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        JsonObjectRequest jobreq = new JsonObjectRequest("http://"+Ipaddress.ip+"/Cartracking/get_data.php", jobj, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                try {
                    JSONArray jarr =  response.getJSONArray("result");

                    JSONObject job_box = (JSONObject) jarr.get(0);
                    drname.setText(  job_box.getString("Name") );
                    dmobile.setText(  job_box.getString("Contactno") );
                    demail.setText( job_box.getString("Emailid"));
                    drage.setText( job_box.getString("Age"));
                    ddlnum.setText( job_box.getString("DrivingLicensenumber"));


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


        jobreq.setRetryPolicy(new DefaultRetryPolicy(20000 ,  2 , 2));
        AppController app = new AppController(driver_profile.this);
        app.addToRequestQueue(jobreq);
    }


}