package com.example.dell.car_login;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Start_page extends AppCompatActivity {
     Button btnclick1,btnclick2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_page);
        btnclick1 =(Button) findViewById(R.id.btnn);
        btnclick2=(Button)findViewById(R.id.btnn2);
        View.OnClickListener cclick1 = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Start_page.this,MainActivity.class);

                startActivity(i);

            }
        };

        View.OnClickListener cclick2 = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Start_page.this,user_login.class);

                startActivity(i);

            }
        };
              btnclick1.setOnClickListener(cclick1);
        btnclick2.setOnClickListener(cclick2);
    }
}
