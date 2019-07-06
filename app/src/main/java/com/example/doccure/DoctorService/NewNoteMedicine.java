package com.example.doccure.DoctorService;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.doccure.R;
import com.example.doccure.database.NoteMedicine;
import com.example.doccure.service.MainActivity;
import com.example.doccure.service.ResetPasswordActivity;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class NewNoteMedicine extends AppCompatActivity {
    private EditText editTextMedicine;
    private Button savemed;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_note_medicine);

        editTextMedicine=findViewById(R.id.edit_text_medicine);
savemed=findViewById(R.id.save_med);
        savemed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveNote();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater=getMenuInflater();
        menuInflater.inflate(R.menu.medicine_note_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId())
        {
            case R.id.save_medicine:
                saveNote();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }

    private void saveNote() {
              String MedName=editTextMedicine.getText().toString();

              if(MedName.trim().isEmpty() )
              {
                  Toast.makeText(this,"Please enter Medicine name",Toast.LENGTH_SHORT).show();
                  return;
              }
        CollectionReference medicineRef= FirebaseFirestore.getInstance().collection("Medicine");
        medicineRef.add(new NoteMedicine(MedName));
        Toast.makeText(this,"Note Added",Toast.LENGTH_SHORT).show();
        finish();
    }
}
