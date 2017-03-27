package com.example.dell.car_login;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class user_login extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_login);

    }
    public void usrhmpage(View v) {
        Intent i = new Intent(user_login.this, User_homepage.class);
        startActivity(i);
    }
}