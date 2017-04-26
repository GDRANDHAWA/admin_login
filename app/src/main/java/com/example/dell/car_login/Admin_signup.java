package com.example.dell.car_login;

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

public class Admin_signup extends AppCompatActivity {
    EditText Admname,Admcontact,Admemail,Admndob,Admpass,Admcpass;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_signup);
        Admname=(EditText)findViewById(R.id.adminname);
        Admcontact=(EditText)findViewById(R.id.cntct);
        Admemail=(EditText)findViewById(R.id.email);
        Admndob=(EditText)findViewById(R.id.dob);
        Admpass=(EditText)findViewById(R.id.pass);
        Admcpass=(EditText)findViewById(R.id.cpass);



    }

    public void admreg(View view) {
        String name = Admname.getText().toString();
        String contact = Admcontact.getText().toString();
        String email = Admemail.getText().toString();
        String dob = Admndob.getText().toString();

        String password = Admpass.getText().toString();
        String cpass = Admcpass.getText().toString();
        if (name.equals("")) {
            Toast.makeText(Admin_signup.this,"Please enter name",Toast.LENGTH_SHORT).show();

            return ;
        }

        if (contact.equals("")) {
            Toast.makeText(Admin_signup.this,"Please enter Contact no",Toast.LENGTH_SHORT).show();

            return ;
        }


        if (email.equals("")) {
            Toast.makeText(Admin_signup.this,"Please enter emailid",Toast.LENGTH_SHORT).show();

            return ;
        }

        if (dob.equals("")) {
            Toast.makeText(Admin_signup.this,"Please enter Date of Birth",Toast.LENGTH_SHORT).show();

            return ;
        }

        if (password.equals("")) {
            Toast.makeText(Admin_signup.this,"Please enter password",Toast.LENGTH_SHORT).show();

            return ;
        }
        if (cpass.equals("")) {
            Toast.makeText(Admin_signup.this,"Please enter confirm password",Toast.LENGTH_SHORT).show();

            return ;
        }
        if (!password.equals(cpass)){
            Toast.makeText(Admin_signup.this,"password do not match",Toast.LENGTH_SHORT).show();
            return;
        }

        JSONObject jobj = new JSONObject();
        try {
            jobj.put("name_key",name);
            jobj.put("contact_key",contact);
            jobj.put("email_key",email);
            jobj.put("dob_key",dob);


            jobj.put("pass_key",password);


        } catch (JSONException e) {
            e.printStackTrace();
        }


        System.out.println(jobj);

        JsonObjectRequest jobjreq = new JsonObjectRequest("http://"+Ipaddress.ip+"/Cartracking/adminsignup.php", jobj, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    if (response.getString("result").equals("done")) {
                        Toast.makeText(Admin_signup.this, "Admin registered sucessfully", Toast.LENGTH_SHORT).show();
                        finish();
                    }


                    else if (response.getString("result").equals("not done")) {
                        Toast.makeText(Admin_signup.this, "Emailid already exist", Toast.LENGTH_SHORT).show();
                    }


                    else{
                        Toast.makeText(Admin_signup.this,"error try again",Toast.LENGTH_SHORT).show();
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
        AppController ap = new AppController(Admin_signup.this);
        ap.addToRequestQueue(jobjreq);

    }

}

