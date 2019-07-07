package com.example.doccure.doctors;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.doccure.R;
import com.example.doccure.service.Prevalent.Prevalent;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import io.paperdb.Paper;

public class DoctorLogin extends AppCompatActivity {

    private EditText password , username ;
    private Button loginbtn;
    private ProgressDialog loadingBar;
    private String parentDbName = "Doctors";



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_doctor_activity);


        loadingBar= new ProgressDialog(this);
        loginbtn = (Button) findViewById(R.id.doctorloginbtn);
        password = (EditText)findViewById(R.id.doctorloginpass);
        username = (EditText)findViewById(R.id.doctorloginname);

        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginuser();
            }
        });

        Paper.init(this);

    }

    private void loginuser() {

        String name = username.getText().toString();
        String pass  = password.getText().toString();

        if(TextUtils.isEmpty(name)){
            Toast.makeText(this, "Please enter your phonenumber", Toast.LENGTH_SHORT).show();
        }
        else if(TextUtils.isEmpty(pass)){
            Toast.makeText(this, "Please enter your password", Toast.LENGTH_SHORT).show();
        }
        else{
            loadingBar.setTitle("Login Account");
            loadingBar.setMessage("Please Wait !");
            loadingBar.setCanceledOnTouchOutside(false);
            loadingBar.show();

            AllowAccess(name,pass);

        }
    }

    private void AllowAccess(final String username,final String password) {

        Paper.book().write(Prevalent.userUsernameKey,username);
        Paper.book().write(Prevalent.userPasswordKey,password);

        final DatabaseReference Rootref;
        Rootref = FirebaseDatabase.getInstance().getReference();

        Rootref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                if(dataSnapshot.child(parentDbName).child(username).exists())
                {
                    Doctors userData = dataSnapshot.child(parentDbName).child(username).getValue(Doctors.class);

                    if(userData.getUsername().equals(username)){
                        if(userData.getPassword().equals(password)){
                            Toast.makeText(DoctorLogin.this, "Logged in succesfully", Toast.LENGTH_SHORT).show();
                            loadingBar.dismiss();

                            Intent intent = new Intent(DoctorLogin.this, DoctorsHomepage.class);
                            startActivity(intent);
                        }
                    }
                }
                else{
                    Toast.makeText(DoctorLogin.this, "Incorrect! Get your username and password from management .", Toast.LENGTH_SHORT).show();
                    loadingBar.dismiss();


                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

}
