package com.example.doccure;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class MainActivity extends AppCompatActivity {
    private FirebaseFirestore db=FirebaseFirestore.getInstance();
    private CollectionReference appointmentRef=db.collection("Appointments");
    private Button joinhomeButton , loginButton ,learnmoreButton, joinnowButton ,reset_button;
    private FirebaseAuth auth;
    private ProgressBar progressBar;
    private EditText inputEmail,inputPassword;
    private ProgressDialog loadingBar;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        auth = FirebaseAuth.getInstance();

        if (auth.getCurrentUser() != null) {
            startActivity(new Intent(MainActivity.this, Home_NavigationActivity.class));
            finish();
        }

        joinhomeButton =(Button) findViewById(R.id.joinhomebtn);
        loginButton=(Button) findViewById(R.id.loginbtn);
        joinnowButton = (Button) findViewById(R.id.joinnowbtn);
        learnmoreButton = (Button) findViewById(R.id.learnmorebtn);
        reset_button=(Button) findViewById(R.id.btn_reset_password);

        inputEmail = (EditText) findViewById(R.id.login_email);
        inputPassword = (EditText) findViewById(R.id.login_password);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        loadingBar = new ProgressDialog(this);


        reset_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this , ResetPasswordActivity.class);
                startActivity(intent);
            }
        });
        joinhomeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this , JoinActivity.class);
                startActivity(intent);
            }
        });



        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = inputEmail.getText().toString();
                final String password = inputPassword.getText().toString();

                if (TextUtils.isEmpty(email)) {
                    Toast.makeText(getApplicationContext(), "Enter email address!", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(password)) {
                    Toast.makeText(getApplicationContext(), "Enter password!", Toast.LENGTH_SHORT).show();
                    return;
                }

              //  progressBar.setVisibility(View.VISIBLE);

                loadingBar.setTitle("Login Account");
                loadingBar.setMessage("Please wait  ! We are validating ");
                loadingBar.setCanceledOnTouchOutside(false);
                loadingBar.show();

                //authenticate user
                auth.signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                // If sign in fails, display a message to the user. If sign in succeeds
                                // the auth state listener will be notified and logic to handle the
                                // signed in user can be handled in the listener.
                               // progressBar.setVisibility(View.GONE);


                                if (!task.isSuccessful())
                                {
                                    // there was an error
                                    if (password.length() < 6) {
                                        inputPassword.setError(getString(R.string.minimum_password));
                                        loadingBar.dismiss();
                                    } else {
                                        Toast.makeText(MainActivity.this, getString(R.string.auth_failed), Toast.LENGTH_LONG).show();
                                   loadingBar.dismiss();
                                    }
                                }else
                                {
                                    Intent intent = new Intent(MainActivity.this, Home_NavigationActivity.class);
                                    startActivity(intent);
                                    finish();
                                    loadingBar.dismiss();
                                }
                            }
                        });
            }
        });
    }
}