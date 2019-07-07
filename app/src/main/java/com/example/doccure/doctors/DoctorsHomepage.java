package com.example.doccure.doctors;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.PopupMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.doccure.R;
import com.example.doccure.service.MainActivity;
import com.example.doccure.service.ResetPasswordActivity;

import java.util.Calendar;

public class DoctorsHomepage extends AppCompatActivity implements View.OnClickListener {


    ImageButton docbtnsee ,doc_signout;
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
doc_signout=(ImageButton)findViewById(R.id.doc_sign_out);
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
   /* @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.menu_doctor_homepage,menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch(item.getItemId()) {
            case R.id.signOut_action:
                Intent intent = new Intent(DoctorsHomepage.this, LoginDoctorActivity.class);
                startActivity(intent);
                break;
        }
        return super.onOptionsItemSelected(item);

        }*/

    public void showMenu(View view) {

        PopupMenu popupMenu = new PopupMenu(this, doc_signout);
        // This activity implements OnMenuItemClickListener
      //  popup.setOnMenuItemClickListener(this);
        popupMenu.getMenuInflater().inflate(R.menu.menu_doctor_homepage,popupMenu.getMenu());
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem Item) {
                switch (Item.getItemId()) {
                    case R.id.signOut_action:
                        Intent intent = new Intent(DoctorsHomepage.this, LoginDoctorActivity.class);
                        startActivity(intent);
                        break;
                }
                return true;
            }
        });
        popupMenu.show();
    }




}
    /*public boolean onMenuItemClick(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.signOut_action:
                Intent intent = new Intent(DoctorsHomepage.this , LoginDoctorActivity.class);
                startActivity(intent);
                return true;

            default:
                return false;
        }
    }*/


