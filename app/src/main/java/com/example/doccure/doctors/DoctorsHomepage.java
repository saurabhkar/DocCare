package com.example.doccure.doctors;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.doccure.R;

import java.util.Calendar;

public class DoctorsHomepage extends AppCompatActivity implements View.OnClickListener {


    ImageButton docbtnsee ;
    private TextView inputdateslot;
    private int mYear , mMonth , mDay;
    String dateslot ;
    Button btnviewslots;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_homepage);

        docbtnsee = (ImageButton) findViewById(R.id.doc_slots_view);
        docbtnsee.setOnClickListener(this);
        inputdateslot = (TextView) findViewById(R.id.doc_date_slots);
        btnviewslots = (Button) findViewById(R.id.doc_book_appointment);

        btnviewslots.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    @Override
    public void onClick(View v) {

        if(v == docbtnsee){
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
}

