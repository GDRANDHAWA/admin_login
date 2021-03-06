package com.example.dell.car_login;

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

public class main_page extends AppCompatActivity {
     EditText nameet,contactet,emailet,ageet,vehiclenoet,useret,passet,cpasset;
    Button btn ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);
        nameet=(EditText)findViewById(R.id.edt1);
        contactet=(EditText)findViewById(R.id.edt2);
        emailet=(EditText)findViewById(R.id.edt3);
        ageet=(EditText)findViewById(R.id.age2);
        vehiclenoet=(EditText)findViewById(R.id.vehicleno2);
        useret=(EditText)findViewById(R.id.userid2);
        passet=(EditText)findViewById(R.id.passwrd2);
        cpasset=(EditText)findViewById(R.id.cpass2);

    }
    public void datasubmit(View b) {
        String name = nameet.getText().toString();
        String contact = contactet.getText().toString();
        String email = emailet.getText().toString();
        String age = ageet.getText().toString();
        String vehicleno = vehiclenoet.getText().toString();
        String userid = useret.getText().toString();
        String passwordd = passet.getText().toString();
        String cpass = cpasset.getText().toString();
        if (name.equals("")) {
            Toast.makeText(main_page.this,"Please enter name",Toast.LENGTH_SHORT).show();

            return ;
        }

        if (contact.equals("")) {
            Toast.makeText(main_page.this,"Please enter Contact no",Toast.LENGTH_SHORT).show();

            return ;
        }


        if (email.equals("")) {
            Toast.makeText(main_page.this,"Please enter emailid",Toast.LENGTH_SHORT).show();

            return ;
        }

        if (age.equals("")) {
            Toast.makeText(main_page.this,"Please enter Age",Toast.LENGTH_SHORT).show();

            return ;
        }
        if (vehicleno.equals("")) {
            Toast.makeText(main_page.this,"Please enter Vehicle No",Toast.LENGTH_SHORT).show();

            return ;
        }
        if (passwordd.equals("")) {
            Toast.makeText(main_page.this,"Please enter password",Toast.LENGTH_SHORT).show();

            return ;
        }
        if (cpass.equals("")) {
            Toast.makeText(main_page.this,"Please enter confirm password",Toast.LENGTH_SHORT).show();

            return ;
        }
        if (!passwordd.equals(cpass)){
            Toast.makeText(main_page.this,"password do not match",Toast.LENGTH_SHORT).show();
            return;
        }
        JSONObject jobj = new JSONObject();
        try {
            jobj.put("namekey",name);
            jobj.put("contactkey",contact);
            jobj.put("emailkey",email);
            jobj.put("agekey",age);
            jobj.put("useridkey",userid);
            jobj.put("vehiclenokey",vehicleno);
            jobj.put("passkey",passwordd);
        } catch (JSONException e) {
            e.printStackTrace();
        }


        System.out.println(jobj);

        JsonObjectRequest jobjreq = new JsonObjectRequest("http://"+Ipaddress.ip+"/signup.php", jobj, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    if (response.getString("result").equals("done")) {
                        Toast.makeText(main_page.this, "user registered sucessfully", Toast.LENGTH_SHORT).show();
                    }
                    else{
                        Toast.makeText(main_page.this,"error try again",Toast.LENGTH_SHORT).show();
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
        AppController ap = new AppController(main_page.this);
        ap.addToRequestQueue(jobjreq);

    }

}

