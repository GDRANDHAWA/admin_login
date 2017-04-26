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

public class MainActivity extends AppCompatActivity {
    EditText email_et , password_et;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        email_et = (EditText) findViewById(R.id.email_et);
        password_et = (EditText) findViewById(R.id.pass_et);
    }
    public void opensignup(View v ){
        Intent i = new Intent(MainActivity.this,Admin_signup.class);
        startActivity(i);



    }
    public void admnlogin(View v){
        String email = email_et.getText().toString();

        String password = password_et.getText().toString();

        if(email.equals(""))
        {
            Toast.makeText(MainActivity.this , "please enter your email" , Toast.LENGTH_SHORT).show();
            return;
        }
        if (password.equals(""))
        {
            Toast.makeText(MainActivity.this , "please enter your password" , Toast.LENGTH_SHORT).show();
            return;
        }

        JSONObject job = new JSONObject();

        try {
            job.put("email_key" , email);
            job.put("pass_key" , password);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        JsonObjectRequest jobjreq = new JsonObjectRequest("http://"+Ipaddress.ip+"/Cartracking/login.php", job,

                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        try {
                            if(response.getString("key").equals("done"))
                            {
                                SharedPreferences.Editor sp =getSharedPreferences("admin_info",MODE_PRIVATE).edit();
                                sp.putString("admin_id",response.getString("Aid"));
                                sp.putString("name_id",response.getString("Name"));



                                sp.commit();
                                Intent i = new Intent(MainActivity.this,admin_menu.class);
                                startActivity(i);
                            }
                            else {
                                Toast.makeText(MainActivity.this , "error" , Toast.LENGTH_SHORT).show();
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

        AppController app = new AppController(MainActivity.this);
        app.addToRequestQueue(jobjreq);
    }
}
