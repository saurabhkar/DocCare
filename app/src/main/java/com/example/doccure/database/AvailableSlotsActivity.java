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
import android.widget.Toast;

import com.example.doccure.R;
import com.example.doccure.slotbook.appointmentBooking;
import com.example.doccure.slotbook.slot;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;

public class AvailableSlotsActivity extends AppCompatActivity implements View.OnClickListener {
    private List<List_Data>listData1;
    private List<List_Data>listData2;




    ImageButton btndateslotpicker ;
    private TextView inputdateslot;
    private int mYear , mMonth , mDay;
    String dateslot ;
    Button btnviewslots;

    private RecyclerView rv;
    private AvailableSlotsAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_appointment);


        btndateslotpicker = (ImageButton) findViewById(R.id.imagebtn_slots_occupy);
        btndateslotpicker.setOnClickListener(this);
        inputdateslot = (TextView) findViewById(R.id.slots_empty_date);
        btnviewslots = (Button) findViewById(R.id.view);

        btnviewslots.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listData1.clear();
                slots();

                final DatabaseReference nm = FirebaseDatabase.getInstance().getReference().child("Users").child(dateslot);

                nm.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        if (dataSnapshot.exists()) {
                            for (DataSnapshot npsnapshot : dataSnapshot.getChildren()) {
                                List_Data l = npsnapshot.getValue(List_Data.class);
                                listData1.add(l);
                            }
                            adapter = new AvailableSlotsAdapter(listData1);
                            rv.setAdapter(adapter);
                        }

                        else{
                            Toast.makeText(AvailableSlotsActivity.this, "No Booked slots on this date .", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
            }
        });


        rv = (RecyclerView) findViewById(R.id.recyclerview1);
        rv.setHasFixedSize(true);
        rv.setLayoutManager(new LinearLayoutManager(this));
        listData1 = new ArrayList<>();

    }

    @Override
    public void onClick(View v) {

        if(v == btndateslotpicker){
            final Calendar calendar = Calendar.getInstance();
            mYear = calendar.get(Calendar.YEAR);
            mMonth = calendar.get(Calendar.MONTH);
            mDay = calendar.get(Calendar.DAY_OF_MONTH);

            DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                    new DatePickerDialog.OnDateSetListener() {

                        @Override
                        public void onDateSet(DatePicker view, int year,
                                              int monthOfYear, int dayOfMonth) {
                            inputdateslot.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);
                        }
                    }, mYear, mMonth, mDay);
            datePickerDialog.show();
        }
    }


    private void slots() {
        dateslot = inputdateslot.getText().toString();
    }
}