package com.example.dell.car_login;

import android.content.Intent;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;

public class admin_menu extends AppCompatActivity {
DrawerLayout dr1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_menu);
        dr1=(DrawerLayout)findViewById(R.id.drawer2);

    }public void adminnmenu(View v ){
        Intent i = new Intent(admin_menu.this,User_signup.class);
        startActivity(i);
    }
    public void addvehicle(View v ){
        Intent i = new Intent(admin_menu.this,Vehicleadd.class);
        startActivity(i);
    }
    public void viewfuel(View v ){
        Intent i = new Intent(admin_menu.this,Fuel_viewone.class);
        startActivity(i);
    }

    public void viewdriver(View view) {
        Intent i = new Intent(admin_menu.this,Viewdriver.class);
        startActivity(i);
    }

    public void more(View view) {
        dr1.openDrawer(Gravity.LEFT);
    }

    public void viewdprofile(View view) {
    }

    public void logout1(View view) {
        finish();
    }
}
