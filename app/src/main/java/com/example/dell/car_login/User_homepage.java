package com.example.dell.car_login;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class User_homepage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_homepage);
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
}
