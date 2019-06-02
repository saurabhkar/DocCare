package com.example.doccure;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button joinhomeButton , loginButton ,learnmoreButton, joinnowButton ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        joinhomeButton =(Button) findViewById(R.id.joinhomebtn);
        loginButton=(Button) findViewById(R.id.loginbtn);
        joinnowButton = (Button) findViewById(R.id.joinnowbtn);
        learnmoreButton = (Button) findViewById(R.id.learnmorebtn);

        joinhomeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this , JoinActivity.class);
                startActivity(intent);
            }
        });

    }
}
