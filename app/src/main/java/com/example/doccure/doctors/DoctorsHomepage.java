package com.example.doccure.doctors;

import android.app.ActionBar;
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
import android.widget.ImageView;
import android.widget.TextView;

import com.example.doccure.DoctorService.MedicineListActivity;
import com.example.doccure.R;
import com.example.doccure.service.MainActivity;
import com.example.doccure.service.ResetPasswordActivity;

import java.util.Calendar;

import io.paperdb.Paper;

public class DoctorsHomepage extends AppCompatActivity implements View.OnClickListener {


    ImageView docbtnsee ;
    ImageView doc_signout;
    private TextView inputdateslot;
    private int mYear , mMonth , mDay;
    String dateslot ;
    Button btnviewslots,doctor_med;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_homepage);

doctor_med=(Button)findViewById(R.id.doc_medicine);
        docbtnsee = (ImageView) findViewById(R.id.doc_slots_view);
        docbtnsee.setOnClickListener(this);
        inputdateslot = (TextView) findViewById(R.id.doc_date_slots);
        btnviewslots = (Button) findViewById(R.id.doc_book_appointment);
        doc_signout=(ImageView) findViewById(R.id.doc_sign_out);
        btnviewslots.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewAppointment();
                Intent intent = new Intent(DoctorsHomepage.this , DoctorsAppointment.class);
                intent.putExtra("slot_date",dateslot);

                startActivity(intent);
            }
        });
        doctor_med.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewAppointment();
                Intent intent = new Intent(DoctorsHomepage.this , MedicineListActivity.class);
                intent.putExtra("slot_date",dateslot);

                startActivity(intent);
            }
        });
    }
    private void viewAppointment() {
        dateslot = inputdateslot.getText().toString();
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
                        Paper.book().destroy();
                        Intent intent = new Intent(DoctorsHomepage.this, DoctorLogin.class);
                        startActivity(intent);
                        finish();
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


