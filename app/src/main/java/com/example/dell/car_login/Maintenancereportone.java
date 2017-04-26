package com.example.dell.car_login;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class Maintenancereportone extends AppCompatActivity {
    EditText mnveh,mntodate,mnfromdate;
    String smnveh,smntodate,smnfromdate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_maintenancereportone);
        mnveh=(EditText)findViewById(R.id.mvehnumberr);
        mnfromdate=(EditText)findViewById(R.id.mfdate);
        mntodate=(EditText)findViewById(R.id.mtodate);
    }

    public void viewmaintenancereport(View view) {
        smnveh = mnveh.getText().toString();
        smntodate = mntodate.getText().toString();
        smnfromdate = mnfromdate.getText().toString();
        Intent i = new Intent(Maintenancereportone.this,Maintenancereportsecond.class);
        i.putExtra("vehiclenumber",smnveh);

        i.putExtra("maintenancetodate",smntodate);
        i.putExtra("maintenancefromdate",smnfromdate);
        startActivity(i);;
    }
}
