package com.example.doccure;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class AppointmentActivity extends AppCompatActivity {


    private EditText appoin_name, date_birth, input_appoin_phone, input_city, inputstate, input_time;
    private Button Book_appoin_Button;


    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appointment);

        appoin_name = (EditText) findViewById(R.id.input_appoin_name);
        date_birth = (EditText) findViewById(R.id.input_dob);
        input_appoin_phone = (EditText) findViewById(R.id.input_appoin_phone);
        input_city = (EditText) findViewById(R.id.inputcity);
        inputstate = (EditText) findViewById(R.id.inputstate);
        input_time = (EditText) findViewById(R.id.inputTime);
        Book_appoin_Button = (Button) findViewById(R.id.book_appoinment_btn);



        Book_appoin_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = appoin_name.getText().toString();
                String dob = date_birth.getText().toString();
                String phone = input_appoin_phone.getText().toString();
                String city = input_city.getText().toString();
                String state = inputstate.getText().toString();
                String time = input_time.getText().toString();
                if(TextUtils.isEmpty(name) || TextUtils.isEmpty(dob) || TextUtils.isEmpty(phone) || TextUtils.isEmpty(city) || TextUtils.isEmpty(state) || TextUtils.isEmpty(time))
                {
                    Toast.makeText(getApplicationContext(), "Please fill up every area.", Toast.LENGTH_SHORT).show();
                    return;
                }
                CollectionReference appointmentRef= FirebaseFirestore.getInstance().collection("Appointments");
                appointmentRef.add(new Note1(name ,dob,phone,city,state,time));
                Toast.makeText(AppointmentActivity.this,"Appointment Added",Toast.LENGTH_SHORT).show();
                finish();
            }

            });


}





}