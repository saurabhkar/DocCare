package com.example.doccure.service;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.doccure.R;
import com.example.doccure.doctors.DoctorLogin;

public class FirstActivity extends AppCompatActivity {

    private Button loginuser;
    private Button logindoctor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);

        loginuser = (Button)findViewById(R.id.loginuser);
        logindoctor = (Button)findViewById(R.id.logindoctor);

        loginuser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FirstActivity.this , MainActivity.class);
            startActivity(intent);
            }
        });

        logindoctor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FirstActivity.this , DoctorLogin.class);
                startActivity(intent);
            }
        });
    }
}
