package com.example.dell.car_login;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.dell.car_login.Adapter.adapter_viewmaintenance;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by DELL on 4/20/2017.
 */

public class viewmaintenance extends AppCompatActivity {
    RecyclerView r4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewmaintenance);
        r4 = (RecyclerView)findViewById(R.id.recycler_id4) ;
        get_data4();
    }
    public void get_data4()
    {

        JSONObject jobj = new JSONObject();


        System.out.println(jobj);
        // making json array request to get json array data
        JsonObjectRequest jsonreq = new JsonObjectRequest("http://"+Ipaddress.ip+"/Cartracking/recycler_viewmndata.php", jobj,new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                System.out.println(response);

                try {
                    JSONArray jarr =   response.getJSONArray("key");
                    adapter_viewmaintenance ad = new adapter_viewmaintenance(jarr , viewmaintenance.this);
                    r4.setLayoutManager(new LinearLayoutManager(viewmaintenance.this , LinearLayoutManager.VERTICAL,false));

                    // setting adapter ad to recycler view r
                    r4.setAdapter(ad);

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

        AppController app = new AppController(viewmaintenance.this);

        app.addToRequestQueue(jsonreq);
    }
}
