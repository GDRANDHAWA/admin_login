package com.example.dell.car_login;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.dell.car_login.Adapter.adapter_viewdistance;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Distanceview extends AppCompatActivity {
    RecyclerView r2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_distanceview);
        r2 = (RecyclerView)findViewById(R.id.recycler_iddistance) ;
        get_distancedata();
    }
    public void get_distancedata()
    {

        JSONObject jobj = new JSONObject();
        try {
            jobj.put("vehicleno_key",getIntent().getStringExtra("vehiclenum"));
            jobj.put("dtodate_key",getIntent().getStringExtra("todate"));
            jobj.put("dfromdate_key",getIntent().getStringExtra("fromdate"));


        } catch (JSONException e) {
            e.printStackTrace();
        }


        System.out.println(jobj);
        // making json array request to get json array data
        JsonObjectRequest jsonreq = new JsonObjectRequest("http://"+Ipaddress.ip+"/Cartracking/recycler_viewdistance.php", jobj,new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                System.out.println(response);

                try {
                    JSONArray jarr =   response.getJSONArray("key");
                    adapter_viewdistance ad = new adapter_viewdistance(jarr , Distanceview.this);
                    r2.setLayoutManager(new LinearLayoutManager(Distanceview.this , LinearLayoutManager.VERTICAL,false));

                    // setting adapter ad to recycler view r
                    r2.setAdapter(ad);

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


        jsonreq.setRetryPolicy(new DefaultRetryPolicy(20000 , 2 ,2));

        AppController app = new AppController(Distanceview.this);

        app.addToRequestQueue(jsonreq);
    }
}

