package com.example.doccure;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class JoinActivity extends AppCompatActivity {
    private Button Joinnowbutton;
    private EditText InputName, InputPhoneNumber, InputPassword , Inputdob,InputAddress,InputEmail,InputCountry;
    private ProgressDialog loadingBar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.joinnow);

        Joinnowbutton =(Button) findViewById(R.id.joinnowbtn);
        InputName =(EditText) findViewById(R.id.inputname);
        InputPhoneNumber =(EditText) findViewById(R.id.inputphone);
        InputPassword =(EditText) findViewById(R.id.inputpassword);
        Inputdob =(EditText) findViewById(R.id.inputdate);
        InputAddress =(EditText) findViewById(R.id.inputaddress);
        InputEmail =(EditText) findViewById(R.id.inputemail);
        InputCountry =(EditText) findViewById(R.id.inputcountry);
        loadingBar = new ProgressDialog(this);

        Joinnowbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                JoinNow();
            }
        });

    }

    private void JoinNow() {
        String name= InputName.getText().toString();
        String phone=InputPhoneNumber.getText().toString();;
        String password=InputPassword.getText().toString();;
        String address=InputAddress.getText().toString();;
        String dateofbirth=Inputdob.getText().toString();;
        String email=InputEmail.getText().toString();;
        String country=InputCountry.getText().toString();;

        if(TextUtils.isEmpty(name))
        {
            Toast.makeText(this, "Please write your name", Toast.LENGTH_SHORT).show();
        }
        else if (TextUtils.isEmpty(phone))
        {
            Toast.makeText(this, "Provide your phone number", Toast.LENGTH_SHORT).show();
        }
        else if(TextUtils.isEmpty(password)){
            Toast.makeText(this, "Provide a Strong Password", Toast.LENGTH_SHORT).show();
        }
        else if(TextUtils.isEmpty(address))
        {
            Toast.makeText(this, "Please write your address", Toast.LENGTH_SHORT).show();
        }
        else if (TextUtils.isEmpty(dateofbirth))
        {
            Toast.makeText(this, "Date of Birth cannot be left blank", Toast.LENGTH_SHORT).show();
        }
        else if(TextUtils.isEmpty(country)){
            Toast.makeText(this, "Specify a country", Toast.LENGTH_SHORT).show();
        }
        else
        {
            loadingBar.setTitle("Create Account");
            loadingBar.setMessage("Please wait  ! We are validating ");
            loadingBar.setCanceledOnTouchOutside(false);
            loadingBar.show();
            ValidatephoneNumber(name,phone,password,address,dateofbirth,email,country);
        }


    }

    private void ValidatephoneNumber(String name1, String phone, String password, String address, String dob, String email, String country) {
    }
}
