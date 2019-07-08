package com.example.doccure.service;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.doccure.Home_NavigationActivity;
import com.example.doccure.R;
import com.example.doccure.doctors.DoctorLogin;
import com.example.doccure.doctors.Doctors;
import com.example.doccure.doctors.DoctorsHomepage;
import com.example.doccure.service.Prevalent.Prevalent;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import io.paperdb.Paper;

public class FirstActivity extends AppCompatActivity {

    private Button loginuser;
    private Button logindoctor;
    private ProgressDialog loadingBar;
    private FirebaseAuth auth;

    private String parentDbName = "Doctors";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);

        loginuser = (Button)findViewById(R.id.loginuser);
        logindoctor = (Button)findViewById(R.id.logindoctor);
        loadingBar = new ProgressDialog(this);

        Paper.init(this);
       auth = FirebaseAuth.getInstance();

   if (auth.getCurrentUser() != null) {
            startActivity(new Intent(FirstActivity.this, Home_NavigationActivity.class));
            finish();
        }

        loginuser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FirstActivity.this , MainActivity.class);
            startActivity(intent);
           finish();
            }
        });

        logindoctor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FirstActivity.this , DoctorLogin.class);
                startActivity(intent);
               finish();
            }
        });


        String UserUsernameKey = Paper.book().read(Prevalent.userUsernameKey);
        String UserPasswordKey = Paper.book().read(Prevalent.userPasswordKey);

        if(UserUsernameKey != "" && UserPasswordKey != ""){
                if(!TextUtils.isEmpty(UserUsernameKey) && !TextUtils.isEmpty(UserPasswordKey)){
                    AllowAccess(UserUsernameKey,UserPasswordKey);

                    loadingBar.setTitle("Already Logged in");
                    loadingBar.setMessage("Please Wait !");
                    loadingBar.setCanceledOnTouchOutside(false);
                    loadingBar.show();

                }
            }

    }

    private void AllowAccess(final String userUsernameKey, final String userPasswordKey) {


        final DatabaseReference Rootref;
        Rootref = FirebaseDatabase.getInstance().getReference();

        Rootref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                if(dataSnapshot.child(parentDbName).child(userUsernameKey).exists())
                {
                    Doctors userData = dataSnapshot.child(parentDbName).child(userUsernameKey).getValue(Doctors.class);

                    if(userData.getUsername().equals(userUsernameKey)){
                        if(userData.getPassword().equals(userPasswordKey)){
                            Toast.makeText(FirstActivity.this, "Logged in succesfully", Toast.LENGTH_SHORT).show();
                            loadingBar.dismiss();

                            Intent intent = new Intent(FirstActivity.this, DoctorsHomepage.class);
                            startActivity(intent);
                            finish();
                        }
                    }
                }
                else{
                    Toast.makeText(FirstActivity.this, "Incorrect! Get your username and password from management .", Toast.LENGTH_SHORT).show();
                    loadingBar.dismiss();


                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }
}
