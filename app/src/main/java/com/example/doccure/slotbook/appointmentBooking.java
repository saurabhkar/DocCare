package com.example.doccure.slotbook;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.support.v7.widget.Toolbar;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.doccure.Home_NavigationActivity;
import com.example.doccure.R;
import com.example.doccure.service.MainActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class appointmentBooking  extends AppCompatActivity {

    DatabaseReference db;
    FirebaseHelper helper;
    private EditText inputname,inputdob,inputphonenumber;
    private Button appointbtn;
    private ProgressDialog loadingBar;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_appointment);

       // Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

       // setSupportActionBar(toolbar);

        Spinner sp1= (Spinner) findViewById(R.id.spinner1);
        Spinner sp2 = (Spinner) findViewById(R.id.spinner2);

         inputname= (EditText) findViewById(R.id.input_appoin_name);
         inputdob = (EditText) findViewById(R.id.input_dob);
         inputphonenumber = (EditText) findViewById(R.id.input_appoin_phone);
         appointbtn = (Button) findViewById(R.id.book_appoinment_btn);
        loadingBar= new ProgressDialog(this);

        //SETUP FB
        db= FirebaseDatabase.getInstance().getReference();
        helper=new FirebaseHelper(db);
        sp1.setAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,helper.retrieve()));


        appointbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                book();
            }
        });


    }

    private void book() {

        String name = inputname.getText().toString();
        String dob = inputdob.getText().toString();
        String phno = inputphonenumber.getText().toString();

        if(TextUtils.isEmpty(name))
        {
            Toast.makeText(this, "Please write your name", Toast.LENGTH_SHORT).show();
        }
        else if (TextUtils.isEmpty(phno))
        {
            Toast.makeText(this, "Provide your phone number", Toast.LENGTH_SHORT).show();
        }
        else if(TextUtils.isEmpty(dob)){
            Toast.makeText(this, "write your date of birth", Toast.LENGTH_SHORT).show();
        }
        else
        {
            loadingBar.setTitle("Create Account");
            loadingBar.setMessage("Please wait  ! We are validating ");
            loadingBar.setCanceledOnTouchOutside(false);
            loadingBar.show();
            WriteData(name,phno,dob);
        }



    }

    private void WriteData(final String name, final String phno, final String dob) {


        final DatabaseReference RootRef;
        RootRef = FirebaseDatabase.getInstance().getReference();

        RootRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(!(dataSnapshot.child("Users").child(phno).exists())){
                    HashMap<String, Object> userdataMap = new HashMap<>();
                    userdataMap.put("phone",phno);
                    userdataMap.put("name",name);
                    userdataMap.put("dob",dob);



                    RootRef.child("Users").child(phno).updateChildren(userdataMap)
                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if(task.isSuccessful())
                                    {
                                        Toast.makeText(appointmentBooking.this, " Your slot has been Booked successfully ", Toast.LENGTH_SHORT).show();
                                        loadingBar.dismiss();

                                        Intent intent = new Intent(appointmentBooking.this, Home_NavigationActivity.class);
                                        startActivity(intent);
                                    }
                                    else{
                                        Toast.makeText(appointmentBooking.this, "Network Error ! Please try again ", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });

                }

    }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }


        });
    }

}