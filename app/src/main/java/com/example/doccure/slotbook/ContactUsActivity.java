package com.example.doccure.slotbook;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.doccure.R;

public class ContactUsActivity extends AppCompatActivity {
private  ImageButton conmail;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_us);
        conmail=(ImageButton)findViewById(R.id.contact_mail);
        conmail.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                sendEmail();
            }
        });
    }

    protected void sendEmail() {
        Log.i("Send email", "");
        String[] TO = {""};
        String[] CC = {""};
        Intent emailIntent = new Intent(Intent.ACTION_SEND);

        emailIntent.setData(Uri.parse("mailto:"));
        emailIntent.setType("text/plain");
        emailIntent.putExtra(Intent.EXTRA_EMAIL, new String[]{"iiit.developers@gmail.com"});
        emailIntent.putExtra(Intent.EXTRA_CC, CC);
       // emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Your subject");
        //emailIntent.putExtra(Intent.EXTRA_TEXT);

        try {
            startActivity(Intent.createChooser(emailIntent, "Send mail..."));
            finish();
            Log.i("Finished sending email.", "");
        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(ContactUsActivity.this, "There is no email client installed.", Toast.LENGTH_SHORT).show();
        }
    }
    }

