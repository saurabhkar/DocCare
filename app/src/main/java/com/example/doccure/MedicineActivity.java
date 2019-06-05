package com.example.doccure;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import static com.example.doccure.R.id.listView;

public class MedicineActivity extends AppCompatActivity implements SearchView.OnQueryTextListener{
    SearchView searchView;
    ListView listView;
    ArrayAdapter<String> adapter;
    String[] medicineArray = {"Calpol","NamCold","Benydryl","med1",
            "med2","med3","med4","med5"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medicine);

        searchView = (SearchView) findViewById(R.id.searchView);
        listView =(ListView) findViewById(R.id.listView);

        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,android.R.id.text1, medicineArray);

        listView.setAdapter(adapter);
        searchView.setOnQueryTextListener(this);
    }

    @Override
    public boolean onQueryTextSubmit(String s) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        String text = newText;
        adapter.getFilter().filter(text);
        return false;
    }
}

