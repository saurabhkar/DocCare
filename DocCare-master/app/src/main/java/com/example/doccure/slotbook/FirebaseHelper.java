package com.example.doccure.slotbook;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseException;
import com.google.firebase.database.DatabaseReference;

import java.util.ArrayList;

public class FirebaseHelper {

    DatabaseReference db;
    Boolean saved=null;

    public FirebaseHelper(DatabaseReference db) {
        this.db = db;
    }

 /*   //SAVE
    public  Boolean save(slot slots)
    {
        if(slots==null)
        {
            saved=false;
        }else
        {
            try
            {
                db.child("Users").push().setValue(slots);
                saved=true;
            }catch (DatabaseException e)
            {
                e.printStackTrace();
                saved=false;
            }
        }

        return saved;
    }
*/
    //READ
    public ArrayList<String> retrieve()
    {
        final ArrayList<String> slots=new ArrayList<>();

        db.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                fetchData(dataSnapshot,slots);
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                fetchData(dataSnapshot,slots);

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        return slots;
    }

    private void fetchData(DataSnapshot snapshot,ArrayList<String> slots)
    {
        slots.clear();
        for (DataSnapshot ds:snapshot.getChildren())
        {
         //   String name=ds.getValue(slot.class).getName();
            String name=ds.getValue(slot.class).getName();
          //  String timeInterval=ds.getValue(slot.class).getTimeInterval();
          //  String dob=ds.getValue(slot.class).getDob();
           // String phoneno=ds.getValue(slot.class).getPhoneno();
          //  slots.add(name);
            slots.add(name);
          //  slots.add(timeInterval);
           // slots.add(dob);
           // slots.add(phoneno);
        }

    }
}
