package com.example.dell.car_login;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONException;
import org.json.JSONObject;

public class user_login extends AppCompatActivity {

    EditText user_id ,password_ett;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_login);

        user_id = (EditText) findViewById(R.id.userid);
        password_ett = (EditText) findViewById(R.id.userpassword);
    }
    public void usrhmpage(View v)
    {
        final String usrid = user_id.getText().toString();

        String password = password_ett.getText().toString();

        if(usrid.equals(""))
        {
            Toast.makeText(user_login.this , "please enter your User Id" , Toast.LENGTH_SHORT).show();
            return;
        }
        if (password.equals(""))
        {
            Toast.makeText(user_login.this , "please enter your password" , Toast.LENGTH_SHORT).show();
            return;
        }

        JSONObject job = new JSONObject();

        try {
            job.put("userid_key" , usrid);
            job.put("password_key" , password);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        JsonObjectRequest jobjreq = new JsonObjectRequest("http://"+Ipaddress.ip+"/Cartracking/userlogin.php", job,

                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        try {
                            if(response.getString("key").equals("done"))
                            {
                                SharedPreferences.Editor sp = getSharedPreferences("driver_info" , MODE_PRIVATE).edit();

                                sp.putString("did",usrid);
                                sp.putString("name",response.getString("name"));
                                sp.putString("vehicle_no",response.getString("vehicle_no"));


                                sp.commit();
                                Intent i = new Intent(user_login.this, User_homepage.class);
                                startActivity(i);
                            }
                            else {
                                Toast.makeText(user_login.this , "error" , Toast.LENGTH_SHORT).show();
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

        jobjreq.setRetryPolicy(new DefaultRetryPolicy(20000 , 2 ,2));

        AppController app = new AppController(user_login.this);
        app.addToRequestQueue(jobjreq);
    }
}