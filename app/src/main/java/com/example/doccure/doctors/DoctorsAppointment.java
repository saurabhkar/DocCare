package com.example.doccure.doctors;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.example.doccure.R;
import com.example.doccure.database.AvailableSlotsAdapter;
import com.example.doccure.database.List_Data;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class DoctorsAppointment extends AppCompatActivity {

    private List<Doctors_Data> doctorsData1;
    private RecyclerView rv;
    private doctorsAppointmentAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.doctors_view_appointment_recycler);
        rv=(RecyclerView)findViewById(R.id.recyclerview_doctorview);

        rv.setHasFixedSize(true);
        rv.setLayoutManager(new LinearLayoutManager(this));
        doctorsData1=new ArrayList<>();

        Intent intent  = getIntent();
        String bookDate = intent.getStringExtra("slot_date");

        final DatabaseReference nm= FirebaseDatabase.getInstance().getReference().child("Users").child(bookDate);

        nm.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()){
                    for (DataSnapshot npsnapshot : dataSnapshot.getChildren()){
                        Doctors_Data l=npsnapshot.getValue(Doctors_Data.class);

                        doctorsData1.add(l);
                    }
                    adapter=new doctorsAppointmentAdapter(doctorsData1);
                    rv.setAdapter(adapter);

                }
                else{
                    Toast.makeText(DoctorsAppointment.this, "No appointments booked on the given date .", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }



}
