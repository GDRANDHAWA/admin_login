package com.example.dell.car_login;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
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

public class Vehicleadd extends AppCompatActivity {
     EditText Vnum,Vtype,Minyr,Vcolor,Vcompany,Vname,fuelt;
    Button addveh,canclveh;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vehicleadd);
        Vnum = (EditText)findViewById(R.id.e1);
        Vtype=(EditText)findViewById(R.id.e2);
        Minyr =(EditText)findViewById(R.id.e3);
        Vcolor=(EditText)findViewById(R.id.e4);
        Vcompany=(EditText)findViewById(R.id.e5);
        Vname=(EditText)findViewById(R.id.e6);
        addveh= (Button)findViewById(R.id.btn_1);
        canclveh= (Button)findViewById(R.id.cnclveh);
        fuelt= (EditText) findViewById(R.id.fuel2);

    }


    public void save_veh(View v){

        String vnumber = Vnum.getText().toString();
        String vtype = Vtype.getText().toString();
        String minyr = Minyr.getText().toString();
        String vcolor = Vcolor.getText().toString();
        String vcompany = Vcompany.getText().toString();
        String vname = Vname.getText().toString();
        String fuel;
        fuel = fuelt.getText ().toString ();

        if (vnumber.equals("")) {
            Toast.makeText(Vehicleadd.this,"Please enter Vehicle number",Toast.LENGTH_SHORT).show();

            return ;
        }

        if (vtype.equals("")) {
            Toast.makeText(Vehicleadd.this,"Please enter Vehicle Type",Toast.LENGTH_SHORT).show();

            return ;
        }


        if (minyr.equals("")) {
            Toast.makeText(Vehicleadd.this,"Please enter Make in Year",Toast.LENGTH_SHORT).show();

            return ;
        }

        if (vcolor.equals("")) {
            Toast.makeText(Vehicleadd.this,"Please enter Vehicle Color",Toast.LENGTH_SHORT).show();

            return ;


        }
        if (vcompany.equals("")) {
            Toast.makeText(Vehicleadd.this,"Please enter Vehicle Company",Toast.LENGTH_SHORT).show();

            return ;
        }
        if (vname.equals("")) {
            Toast.makeText(Vehicleadd.this,"Please enter Vehicle Name",Toast.LENGTH_SHORT).show();

            return ;
        }



        JSONObject jobj = new JSONObject();
        try {
            SharedPreferences sp =getSharedPreferences("admin_info",MODE_PRIVATE);
            String admin_id =  sp.getString("admin_id","");
            jobj.put("num_key",vnumber);
            jobj.put("vtype_key",vtype);
            jobj.put("makein_key",minyr);
            jobj.put("color_key",vcolor);
            jobj.put("company_key",vcompany);
            jobj.put("vname_key",vname);
            jobj.put("admn_id",admin_id);
            jobj.put("fuel_key",fuel);



        } catch (JSONException e) {
            e.printStackTrace();
        }


        System.out.println(jobj);

        JsonObjectRequest jobjreq = new JsonObjectRequest("http://"+Ipaddress.ip+"/Cartracking/addvehicle.php", jobj, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    if (response.getString("result").equals("done")) {
                        Toast.makeText(Vehicleadd.this, "Vehicle data added", Toast.LENGTH_SHORT).show();
                        finish();
                    }




                    else{
                        Toast.makeText(Vehicleadd.this,"error try again",Toast.LENGTH_SHORT).show();
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
        AppController ap = new AppController(Vehicleadd.this);
        ap.addToRequestQueue(jobjreq);
    }

    public void cancel_veh(View v){
        finish();
    }

}
