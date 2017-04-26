package com.example.dell.car_login;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class Fuelreportone extends AppCompatActivity {
    EditText fuelveh,fueltodate,fuelfromdate;
    String sfuelveh,sfueltodate,sfuelfromdate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_fuelreportone);
        fuelveh=(EditText)findViewById(R.id.fvehnumberr);
        fueltodate=(EditText)findViewById(R.id.fvehtodate);
        fuelfromdate=(EditText)findViewById(R.id.fvehfdate);
    }

    public void viewfuelreport(View view) {
        sfuelveh = fuelveh.getText().toString();
        sfueltodate = fueltodate.getText().toString();
        sfuelfromdate = fuelfromdate.getText().toString();
        Intent i = new Intent(Fuelreportone.this,Fuelreportsecond.class);

        i.putExtra("vehiclenumber",sfuelveh);

        i.putExtra("fueltodate",sfueltodate);
        i.putExtra("fuelfromdate",sfuelfromdate);
        startActivity(i);
    }
}
