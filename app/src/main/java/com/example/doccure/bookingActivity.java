package com.example.doccure;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

import com.example.doccure.model.common;
import com.google.android.gms.common.internal.service.Common;
import com.google.firebase.firestore.CollectionReference;

public class bookingActivity extends AppCompatActivity {
    LocalBroadcastManager localBroadcastManager;
    AlertDialog dialog;
    CollectionReference barberRef;

    @BindView(R.id.next_button)
    Button next_button;

    @OnClick(R.id.next_button)
    void nextClick() {
        if (common.currentBarber != null) {
            loadTimeSlotOfBarber(common.currentBarber.getBarberId());
        }
    }
}


private void loadTimeSlotOfBarber(String barberId) {
    Intent intent=new Intent(common.KEY_DISPLAY_TIME_SLOT);
    localBroadcastManager.sendBroadcast(intent);
}
    protected void onDestroy()
    {
        localBroadcastManager.unregisterReceiver(button_next_receiver);
        super.onDestroy();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking);
        ButteeKnife.bind(bookingActivity.this);
        dialog=new SpotsDialog.Builder().setContext(this).build();
        localBroadcastManager=LocalBroadcastManager.getInstance(this);
        localBroadcastManager.registerReceiver(button_next_receiver,new IntentFilter(common.KEY_ENABLE_BUTTON_NEXT));
    }


}
