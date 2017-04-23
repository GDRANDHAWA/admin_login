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

public class Editdriverdetails extends AppCompatActivity {
    EditText dname,dcontact,demail,dage,adrivinglicense,dcarassigned;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editdriverdetails);
        dname=(EditText)findViewById(R.id.id_drname);
        dcontact=(EditText)findViewById(R.id.id_contc);
        demail=(EditText)findViewById(R.id.id_email);
        dage=(EditText)findViewById(R.id.id_age);
        adrivinglicense=(EditText)findViewById(R.id.id_veh);
        dcarassigned=(EditText)findViewById(R.id.id_dlno);

        dname.setText(getIntent().getStringExtra("name_key"));
        dcontact.setText(getIntent().getStringExtra("contact_key"));
        demail.setText(getIntent().getStringExtra("age_key"));
        dage.setText(getIntent().getStringExtra("email_key"));
        adrivinglicense.setText(getIntent().getStringExtra("vehicleno_key"));
        dcarassigned.setText(getIntent().getStringExtra("drivinglicense_key"));


    }

    public void updatedriverprofile(View view) {
        String name = dname.getText().toString();
        String contact = dcontact.getText().toString();
        String email = demail.getText().toString();
        String age = dage.getText().toString();
        String drvlicencenum = adrivinglicense.getText().toString();
        String carassigned = dcarassigned.getText().toString();

        if (name.equals("")) {
            Toast.makeText(Editdriverdetails.this,"Please enter name",Toast.LENGTH_SHORT).show();

            return ;
        }

        if (contact.equals("")) {
            Toast.makeText(Editdriverdetails.this,"Please enter Contact no",Toast.LENGTH_SHORT).show();

            return ;
        }


        if (email.equals("")) {
            Toast.makeText(Editdriverdetails.this,"Please enter emailid",Toast.LENGTH_SHORT).show();

            return ;
        }

        if (age.equals("")) {
            Toast.makeText(Editdriverdetails.this,"Please enter Age",Toast.LENGTH_SHORT).show();

            return ;
        }
        if (drvlicencenum.equals("")) {
            Toast.makeText(Editdriverdetails.this,"Please enter Driving license No",Toast.LENGTH_SHORT).show();

            return ;
        }
        if (carassigned.equals("")) {
            Toast.makeText(Editdriverdetails.this,"Please enter car assigned",Toast.LENGTH_SHORT).show();

            return ;
        }


        JSONObject jobj = new JSONObject();
        try {
            jobj.put("namekey",name);
            jobj.put("contactkey",contact);
            jobj.put("emailkey",email);
            jobj.put("agekey",age);
            jobj.put("useridkey",getIntent().getStringExtra("user_id"));
            jobj.put("driving_license",drvlicencenum);
            jobj.put("car_assign",carassigned);


        } catch (JSONException e) {
            e.printStackTrace();
        }


        System.out.println(jobj);

        JsonObjectRequest jobjreq = new JsonObjectRequest("http://"+Ipaddress.ip+"/Cartracking/updatedriverdetail.php", jobj, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    if (response.getString("result").equals("done")) {
                        Toast.makeText(Editdriverdetails.this, "user registered sucessfully", Toast.LENGTH_SHORT).show();
                        finish();
                    }


                    else if (response.getString("result").equals("not done")) {
                        Toast.makeText(Editdriverdetails.this, "UserId already exist", Toast.LENGTH_SHORT).show();
                    }


                    else{
                        Toast.makeText(Editdriverdetails.this,"error try again",Toast.LENGTH_SHORT).show();
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
        AppController ap = new AppController(Editdriverdetails.this);
        ap.addToRequestQueue(jobjreq);

    }

}
