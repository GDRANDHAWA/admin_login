package com.example.dell.car_login;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class Adminreport extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_adminreport);
    }



    public void viewfuelrprt(View view) {
        Intent i = new Intent(Adminreport.this,Fuelreportone.class);
        startActivity(i);

    }

    public void viewmaintenancerpt(View view) {
        Intent i = new Intent(Adminreport.this,Maintenancereportone.class);
        startActivity(i);
    }
}
