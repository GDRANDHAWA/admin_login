package com.example.dell.car_login;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Welcome_page extends AppCompatActivity {
   Button adbtn,usrbtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_page);

        adbtn =(Button) findViewById(R.id.btn_1);
        usrbtn =(Button) findViewById(R.id.btnn2);
        View.OnClickListener adclick = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Welcome_page.this,MainActivity.class);

                startActivity(i);

            }
        };
        View.OnClickListener usrclick = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Welcome_page.this,user_login.class);

                startActivity(i);

            }
        };
        adbtn.setOnClickListener(adclick);
        usrbtn.setOnClickListener(usrclick);
    }


}
