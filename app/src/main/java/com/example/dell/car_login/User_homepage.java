package com.example.dell.car_login;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class User_homepage extends AppCompatActivity {

    TextView userid,vehiclenum,dname ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_homepage);
        userid = (TextView) findViewById(R.id.tt3);
        vehiclenum=(TextView)findViewById(R.id.vehiclassigned) ;
        dname=(TextView)findViewById(R.id.drivernamr) ;

        SharedPreferences sp = getSharedPreferences("driver_info",MODE_PRIVATE);
        String driver_uid = sp.getString("did" , "");
        String driver_name = sp.getString("name" , "");
        String driver_vehiclenum = sp.getString("vehicle_no" , "");

        userid.setText(driver_uid);
        vehiclenum.setText(driver_vehiclenum);
        dname.setText(driver_name);
    }

    public void fuelentry(View v) {
        Intent i = new Intent(User_homepage.this, Fuel_entry.class);
        startActivity(i);
    }
    public void distrvlentry(View v) {
        Intent i = new Intent(User_homepage.this,Distance_travel.class);
        startActivity(i);
    }
    public void usrservicentry(View v) {
        Intent i = new Intent(User_homepage.this,Maintenance_entry.class);
        startActivity(i);
    }

    public void viewdprofile(View view) {
        Intent i = new Intent(User_homepage.this,driver_profile.class);
        startActivity(i);
    }

}
