package com.example.doccure;

import android.content.Context;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.ContentFrameLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

public class SpecialisationActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    Context context;
    private List<Specialisation> specialisations;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.specialisationrecycler);

        recyclerView = (RecyclerView) findViewById(R.id.recycleview);
        recyclerView.setHasFixedSize(true);

        LinearLayoutManager layoutManager = new LinearLayoutManager(context);
        recyclerView.setLayoutManager(layoutManager);

        RecyclerViewAdapter adapter = new RecyclerViewAdapter(specialisations);
        initializeData();
        initializeAdapter();
    }

    private void initializeData() {
        specialisations = new ArrayList<>();
        specialisations.add(new Specialisation("Cardiology",R.drawable.cardio ));
        specialisations.add(new Specialisation("Gasterology", R.drawable.gatero));
        specialisations.add(new Specialisation("Hermatology", R.drawable.hematology));
        specialisations.add(new Specialisation("Immunology", R.drawable.immunoogy));
        specialisations.add(new Specialisation("Gynaecology",R.drawable.index));
        specialisations.add(new Specialisation("Nephrology",R.drawable.nephrology));
        specialisations.add(new Specialisation("Obstetrics",R.drawable.obstetrics));
        specialisations.add(new Specialisation("Onocology",R.drawable.oncology));
        specialisations.add(new Specialisation("Pulmonology",R.drawable.pulmonolgy));
        specialisations.add(new Specialisation("Rheumatology",R.drawable.rheumatology));
    }

    private void initializeAdapter() {
        RecyclerViewAdapter adapter =new RecyclerViewAdapter(specialisations);
        recyclerView.setAdapter(adapter);
    }
}
    class Specialisation {
        String name;
        int logoId;

        Specialisation(String name , int logoId){
            this.name = name;
            this.logoId= logoId;
        }
    }


