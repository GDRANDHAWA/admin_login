package com.example.dell.car_login;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class Carnumentry extends AppCompatActivity {
    EditText eddt;
    String snum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carnumentry);
        eddt=(EditText)findViewById(R.id.txtnum);
    }
    public void viewcar(View view) {
        snum=eddt.getText().toString();

        Intent i = new Intent(Carnumentry.this,Cartracking.class);
        i.putExtra("vehnum",snum);
        startActivity(i);


    }
}
