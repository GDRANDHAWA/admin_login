package com.example.dell.car_login;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONException;
import org.json.JSONObject;

public class User_signup extends AppCompatActivity {
    EditText nameet,contactet,emailet,ageet,vehiclenoet,useret,passet,cpasset,dlicense;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_signup);
        nameet=(EditText)findViewById(R.id.edt1);
        contactet=(EditText)findViewById(R.id.edt2);
        emailet=(EditText)findViewById(R.id.edt3);
        ageet=(EditText)findViewById(R.id.age2);
        vehiclenoet=(EditText)findViewById(R.id.vehicleno2);
        useret=(EditText)findViewById(R.id.userid2);
        passet=(EditText)findViewById(R.id.passwrd2);
        cpasset=(EditText)findViewById(R.id.cpass2);
        dlicense=(EditText)findViewById(R.id.drvrlicense);
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
        String drlicense = dlicense.getText().toString();
        if (name.equals("")) {
            Toast.makeText(User_signup.this,"Please enter name",Toast.LENGTH_SHORT).show();

            return ;
        }

        if (contact.equals("")) {
            Toast.makeText(User_signup.this,"Please enter Contact no",Toast.LENGTH_SHORT).show();

            return ;
        }


        if (email.equals("")) {
            Toast.makeText(User_signup.this,"Please enter emailid",Toast.LENGTH_SHORT).show();

            return ;
        }

        if (age.equals("")) {
            Toast.makeText(User_signup.this,"Please enter Age",Toast.LENGTH_SHORT).show();

            return ;
        }
        if (vehicleno.equals("")) {
            Toast.makeText(User_signup.this,"Please enter Vehicle No",Toast.LENGTH_SHORT).show();

            return ;
        }
        if (drlicense.equals("")) {
            Toast.makeText(User_signup.this,"Please enter Driving license number",Toast.LENGTH_SHORT).show();

            return ;
        }
        if (passwordd.equals("")) {
            Toast.makeText(User_signup.this,"Please enter password",Toast.LENGTH_SHORT).show();

            return ;
        }
        if (cpass.equals("")) {
            Toast.makeText(User_signup.this,"Please enter confirm password",Toast.LENGTH_SHORT).show();

            return ;
        }
        if (!passwordd.equals(cpass)){
            Toast.makeText(User_signup.this,"password do not match",Toast.LENGTH_SHORT).show();
            return;
        }
        SharedPreferences sp = getSharedPreferences("admin_info",MODE_PRIVATE);
        String admin_id2 =sp.getString("admin_id","");
        JSONObject jobj = new JSONObject();
        try {
            jobj.put("namekey",name);
            jobj.put("contactkey",contact);
            jobj.put("emailkey",email);
            jobj.put("agekey",age);
            jobj.put("useridkey",userid);
            jobj.put("vehiclenokey",vehicleno);
            jobj.put("passkey",passwordd);
            jobj.put("license_key",drlicense);
            jobj.put("admin_key",admin_id2);

        } catch (JSONException e) {
            e.printStackTrace();
        }


        System.out.println(jobj);

        JsonObjectRequest jobjreq = new JsonObjectRequest("http://"+Ipaddress.ip+"/signup.php", jobj, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    if (response.getString("result").equals("done")) {
                        Toast.makeText(User_signup.this, "user registered sucessfully", Toast.LENGTH_SHORT).show();
                        finish();
                    }


                    else if (response.getString("result").equals("not done")) {
                        Toast.makeText(User_signup.this, "UserId already exist", Toast.LENGTH_SHORT).show();
                    }


                    else{
                        Toast.makeText(User_signup.this,"error try again",Toast.LENGTH_SHORT).show();
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
        AppController ap = new AppController(User_signup.this);
        ap.addToRequestQueue(jobjreq);

    }

}

