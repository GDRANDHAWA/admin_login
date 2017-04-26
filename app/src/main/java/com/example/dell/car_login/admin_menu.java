package com.example.dell.car_login;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

public class admin_menu extends AppCompatActivity {
DrawerLayout dr1;TextView adminname;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_menu);
        dr1=(DrawerLayout)findViewById(R.id.drawer2);
        adminname=(TextView)findViewById (R.id.admin);
        SharedPreferences sp = getSharedPreferences("admin_info",MODE_PRIVATE);
        String adminnamee = sp.getString("name_id" , "");
        adminname.setText(adminnamee);



    }
    public void adminnmenu(View v ){
        Intent i = new Intent(admin_menu.this,User_signup.class);
        startActivity(i);
    }
    public void addvehicle(View v ){
        Intent i = new Intent(admin_menu.this,Vehicleadd.class);
        startActivity(i);
    }
    public void viewdfuel(View v ){
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

    public void view_vehicle(View view) {
        Intent i = new Intent(admin_menu.this,Viewvehicle.class);
        startActivity(i);
    }

    public void Mndetail(View view) {
        Intent i = new Intent(admin_menu.this,Maintenanceviewone.class);
        startActivity(i);
    }

    public void viewdistt(View view) {
        Intent i = new Intent(admin_menu.this,Distanceviewone.class);
        startActivity(i);
    }

    public void tracker(View view) {
        Intent i = new Intent(admin_menu.this,Carnumentry.class);
        startActivity(i);
    }

    public void viewreports(View view) {
        Intent i = new Intent(admin_menu.this,Adminreport.class);
        startActivity(i);
    }
}
