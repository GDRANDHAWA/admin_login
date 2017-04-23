package com.example.dell.car_login;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class Maintenanceviewone extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maintenanceviewone);
    }

    public void viewmaintenaced(View view) {
        Intent i = new Intent(Maintenanceviewone.this,viewmaintenance.class);
        startActivity(i);
    }
}
