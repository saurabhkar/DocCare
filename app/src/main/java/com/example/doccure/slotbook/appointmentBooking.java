package com.example.doccure.slotbook;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.support.v7.widget.Toolbar;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextClock;
import android.widget.TextView;
import android.widget.Toast;

import com.example.doccure.Home_NavigationActivity;
import com.example.doccure.R;
import com.example.doccure.database.AvailableSlotsActivity;
import com.example.doccure.service.MainActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

public class appointmentBooking  extends AppCompatActivity implements AdapterView.OnItemSelectedListener, View.OnClickListener {

    DatabaseReference db;
    FirebaseHelper helper;
    private EditText inputname;
    private TextView inputdob;

    private EditText inputphonenumber;
    private Spinner inputtime;
    private Button appointbtn;
    private ProgressDialog loadingBar;

    ImageButton btndatepicker ;
    private TextView inputdate;
    private int mYear , mMonth , mDay;


    ImageButton btndatebirthpicker ;
    private int bYear , bMonth , bDay;

    Button occupybtn;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_appointment);

       // Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

       // setSupportActionBar(toolbar);

        Spinner sp1= (Spinner) findViewById(R.id.spinner1);

        inputtime = (Spinner) findViewById(R.id.spinner2);

         inputname= (EditText) findViewById(R.id.input_appoin_name);
         inputdob = (TextView) findViewById(R.id.input_dob);
         inputphonenumber = (EditText) findViewById(R.id.input_appoin_phone);
         appointbtn = (Button) findViewById(R.id.book_appoinment_btn);
         loadingBar= new ProgressDialog(this);
        inputdate = (TextView) findViewById(R.id.appoin_date);
        btndatepicker = (ImageButton) findViewById(R.id.apoin_date_calender);
        btndatebirthpicker = (ImageButton) findViewById(R.id.dob_calender);
        btndatepicker.setOnClickListener(this);
        btndatebirthpicker.setOnClickListener(this);

     //   occupybtn = (Button) findViewById(R.id.book_occupy_btn);

        sp1.setOnItemSelectedListener(this);


        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.time_interval, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        sp1.setAdapter(adapter);


/*        occupybtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(appointmentBooking.this , AvailableSlotsActivity.class);
                startActivity(intent);
            }
        });
*/
        //SETUP FB
        db= FirebaseDatabase.getInstance().getReference();
        helper=new FirebaseHelper(db);
       // sp2.setAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,helper.retrieve()));


        appointbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                book();
            }
        });


    }









    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        TextView textView = (TextView)parent.getChildAt(0);
        textView.setTextColor(getResources().getColor(R.color.colorPrimaryDark));

        Spinner sp1= (Spinner) findViewById(R.id.spinner1);
        Spinner inputtime= (Spinner) findViewById(R.id.spinner2);


        String st= String.valueOf(sp1.getSelectedItem());


        if(st.contentEquals("10 AM - 11 AM")) {
            List<String> list = new ArrayList<String>();
            list.add("10:00 AM");list.add("10:05 AM");list.add("10:10 AM");list.add("10:15 AM");list.add("10:20 AM");list.add("10:25 AM");list.add("10:30 AM");list.add("10:35 AM");list.add("10:40 AM");list.add("10:45 AM");list.add("10:50 AM");list.add("10:55 AM");
            ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                    android.R.layout.simple_spinner_item, list);
            dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            dataAdapter.notifyDataSetChanged();
            inputtime.setAdapter(dataAdapter);
            TextView textView1 = (TextView)parent.getChildAt(0);
            textView1.setTextColor(getResources().getColor(R.color.colorPrimaryDark));

        }
        if(st.contentEquals("11 AM - 12 PM")) {
            List<String> list = new ArrayList<String>();
            list.add("11:00 AM");list.add("11:05 AM");list.add("11:10 AM");list.add("11:15 AM");
            list.add("11:20 AM");list.add("11:25 AM");list.add("11:30 AM");list.add("11:35 AM");
            list.add("11:40 AM");list.add("11:45 AM");list.add("11:50 AM");list.add("11:55 AM");
            ArrayAdapter<String> dataAdapter2 = new ArrayAdapter<String>(this,
                    android.R.layout.simple_spinner_item, list);
            dataAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            dataAdapter2.notifyDataSetChanged();
            inputtime.setAdapter(dataAdapter2);


            TextView textView2 = (TextView)parent.getChildAt(0);
            textView2.setTextColor(getResources().getColor(R.color.pink));

        }

        if(st.contentEquals("12 PM - 1 PM ")) {
            List<String> list = new ArrayList<String>();
            list.add("12:00 PM");list.add("12:05 PM");list.add("12:10 PM");list.add("12:15 PM");
            list.add("12:20 PM");list.add("12:25 PM");list.add("12:30 PM");list.add("12:35 PM");list.add("12:40 PM");
            list.add("12:45 PM");list.add("12:50 PM");list.add("12:55 PM");
            ArrayAdapter<String> dataAdapter2 = new ArrayAdapter<String>(this,
                    android.R.layout.simple_spinner_item, list);
            dataAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            dataAdapter2.notifyDataSetChanged();
            inputtime.setAdapter(dataAdapter2);
            TextView textView3 = (TextView)parent.getChildAt(0);
            textView3.setTextColor(getResources().getColor(R.color.pink));

        }


        if(st.contentEquals("3 PM - 4 PM")) {
            List<String> list = new ArrayList<String>();
            list.add("3:00 PM");list.add("3:05 PM");list.add("3:10 PM");list.add("3:15 PM");list.add("3:20 PM");list.add("3:25 PM");list.add("3:30 PM");list.add("3:35 PM");list.add("3:40 PM");list.add("3:45 PM");list.add("3:50 PM");list.add("3:55 PM");
            ArrayAdapter<String> dataAdapter2 = new ArrayAdapter<String>(this,
                    android.R.layout.simple_spinner_item, list);
            dataAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            dataAdapter2.notifyDataSetChanged();
            inputtime.setAdapter(dataAdapter2);
            TextView textView4 = (TextView)parent.getChildAt(0);
            textView4.setTextColor(getResources().getColor(R.color.colorPrimaryDark));

        }

        if(st.contentEquals("4 PM - 5 PM")) {
            List<String> list = new ArrayList<String>();
            list.add("4:00 PM");list.add("4:05 PM");list.add("4:10 PM");list.add("4:15 PM");list.add("4:20 PM");list.add("4:25 PM");list.add("4:30 PM");list.add("4:35 PM");list.add("4:40 PM");list.add("4:45 PM");list.add("4:50 PM");list.add("4:55 PM");
            ArrayAdapter<String> dataAdapter2 = new ArrayAdapter<String>(this,
                    android.R.layout.simple_spinner_item, list);
            dataAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            dataAdapter2.notifyDataSetChanged();
            inputtime.setAdapter(dataAdapter2);
            TextView textView5 = (TextView)parent.getChildAt(0);
            textView5.setTextColor(getResources().getColor(R.color.colorPrimaryDark));

        }







          /*  Toast.makeText(parent.getContext(),
                    "OnItemSelectedListener : " + parent.getItemAtPosition(position).toString(),
                    Toast.LENGTH_SHORT).show();*/
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }














    private void book() {

        String name = inputname.getText().toString();
        String dob = inputdob.getText().toString();
        String phno = inputphonenumber.getText().toString();
        String date = inputdate.getText().toString();
        String time = inputtime.getSelectedItem().toString();
       // String time = inputtime.getText().toString();

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
        else if(TextUtils.isEmpty(time)){
            Toast.makeText(this, "select you slot", Toast.LENGTH_SHORT).show();
        }
        else if(TextUtils.isEmpty(date)){
            Toast.makeText(this, "select the date", Toast.LENGTH_SHORT).show();
        }
        else
        {
            loadingBar.setTitle("Booking in Progress ");
            loadingBar.setMessage("Please wait ! ");
            loadingBar.setCanceledOnTouchOutside(false);
            loadingBar.show();
            WriteData(name,phno,dob,time,date);
        }



    }

    private void WriteData(final String name, final String phno, final String dob , final String time , final String date) {


        final DatabaseReference RootRef;
        RootRef = FirebaseDatabase.getInstance().getReference();

        RootRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(!(dataSnapshot.child("Users").child(date).child(time).exists())){
                    HashMap<String, Object> userdataMap = new HashMap<>();
                    userdataMap.put("name",name);
                    userdataMap.put("phone",phno);
                    userdataMap.put("dob",dob);
                    userdataMap.put("time",time);
                    userdataMap.put("date",date);

                    RootRef.child("Users").child(date).child(time).updateChildren(userdataMap)
                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if(task.isSuccessful())
                                    {
                                        Toast.makeText(appointmentBooking.this, " Your slot has been Booked successfully ", Toast.LENGTH_SHORT).show();
                                        loadingBar.dismiss();

                                        Intent intent = new Intent(appointmentBooking.this, Home_NavigationActivity.class);
                                        startActivity(intent);
                                        notifications();

                                    }
                                    else{
                                        Toast.makeText(appointmentBooking.this, "Network Error ! Please try again ", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });

                }
else{
                    Toast.makeText(appointmentBooking.this, "Slot already booked , Check for availabilty ", Toast.LENGTH_SHORT).show();
                    loadingBar.dismiss();
                }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }









        });
    }

    private void notifications() {
        String name = inputname.getText().toString();
        String date = inputdate.getText().toString();
        String time = inputtime.getSelectedItem().toString();

        int notific_id =1;
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this);
        builder.setSmallIcon(R.drawable.logo)
                .setLargeIcon(BitmapFactory.decodeResource(getResources(),R.drawable.logo))
                .setContentTitle(name)
                .setStyle(new NotificationCompat.BigTextStyle().bigText(getResources().getString(R.string.quote_notifi)))
                .setContentText(time)
                .setAutoCancel(true);

        Uri path = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        builder.setSound(path);

        NotificationManager notificationManager=(NotificationManager) getSystemService(NOTIFICATION_SERVICE);

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            String channelId ="DocCure";
            NotificationChannel channel = new NotificationChannel(channelId,
                    "Channel human readable title",
                    NotificationManager.IMPORTANCE_DEFAULT);
            notificationManager.createNotificationChannel(channel);

            builder.setChannelId(channelId);
        }
        notificationManager.notify(notific_id,builder.build());
    }


    @Override
    public void onClick(View v) {


        if(v == btndatebirthpicker){
            final Calendar calendar = Calendar.getInstance();
            bYear = calendar.get(Calendar.YEAR);
            bMonth = calendar.get(Calendar.MONTH);
            bDay = calendar.get(Calendar.DAY_OF_MONTH);



            DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                    new DatePickerDialog.OnDateSetListener() {

                        @Override
                        public void onDateSet(DatePicker view, int year,
                                              int monthOfYear, int dayOfMonth) {

                            inputdob.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);

                        }
                    }, bYear, bMonth, bDay);
            datePickerDialog.show();
        }










        if(v == btndatepicker){
            final Calendar calendar = Calendar.getInstance();
            mYear = calendar.get(Calendar.YEAR);
            mMonth = calendar.get(Calendar.MONTH);
            mDay = calendar.get(Calendar.DAY_OF_MONTH);



            DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                    new DatePickerDialog.OnDateSetListener() {

                        @Override
                        public void onDateSet(DatePicker view, int year,
                                              int monthOfYear, int dayOfMonth) {

                            inputdate.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);

                        }
                    }, mYear, mMonth, mDay);
            datePickerDialog.show();
        }



    }




        }