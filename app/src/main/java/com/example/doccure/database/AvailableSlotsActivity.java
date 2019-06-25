package com.example.doccure.database;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.doccure.R;
import com.example.doccure.slotbook.appointmentBooking;
import com.example.doccure.slotbook.slot;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;

public class AvailableSlotsActivity extends AppCompatActivity {
    private List<List_Data>listData1;
    private List<List_Data>listData2;


    private RecyclerView rv;
    private AvailableSlotsAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_appointment);
        rv=(RecyclerView)findViewById(R.id.recyclerview1);


        rv.setHasFixedSize(true);
        rv.setLayoutManager(new LinearLayoutManager(this));
        listData1=new ArrayList<>();

        final DatabaseReference nm= FirebaseDatabase.getInstance().getReference().child("Users").child("25-6-2019");

        nm.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()){
                    for (DataSnapshot npsnapshot : dataSnapshot.getChildren()){
                        List_Data l=npsnapshot.getValue(List_Data.class);

                        listData1.add(l);
                    }
                    adapter=new AvailableSlotsAdapter(listData1);
                    rv.setAdapter(adapter);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


      /*  mm.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()){
                    for (DataSnapshot npsnapshot : dataSnapshot.getChildren()){
                        List_Data l=npsnapshot.getValue(List_Data.class);
                        listData2.add(l);
                    }
                    adapter=new AvailableSlotsAdapter(listData2);
                    rv.setAdapter(adapter);

                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
*/


     //   Log.i("MyAndroidClass", Arrays.(listData1));
//        System.out.println(listData2);





    }

}