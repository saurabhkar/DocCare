package com.example.doccure;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import static com.example.doccure.R.id.medicine_list;

public class MedicineActivity extends AppCompatActivity {

    String[] medicineArray = {"Calpol","NamCold","Benydryl","med1",
            "med2","med3","med4","med5"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {



        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medicine);
        ArrayAdapter adapter = new ArrayAdapter<String>(this,

                R.layout.medicine_activity_listview, medicineArray);

        ListView listView = (ListView) findViewById(medicine_list);
        listView.setAdapter(adapter);
    }
    }

