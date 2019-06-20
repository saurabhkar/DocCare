package com.example.doccure;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.SoundPool;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;


import com.example.doccure.model.common;
import com.google.android.gms.common.internal.service.Common;
import com.google.firebase.firestore.DocumentReference;

import java.text.SimpleDateFormat;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import static com.example.doccure.model.common.currentBarber;

public class bookingFragment<BindView> extends Fragment {
    DocumentReference appointdoc;
    ITimeSlotLoadListener iTimeSlotLoadListener;
    AlertDialog dialog;
    Unbinder unbinder;
    LocalBroadcastManager localBroadcastManager;
    Calender selected_date;
    @BindView(R.id.recycler_time_slot)
    RecyclerView recycler_time_slot;
    @BindView(R.id.calenderView)
    HorizontalCalenderView calenderView;
    SimpleDateFormat simpleDateFormat;
    BroadcastReceiver displayTimeSlot =new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            Calender date=Calender.getInstance();
            date.add(Calender.DATE,0);
            loadAvailableTimeSlotOfDoc(currentBarber.getBarberId(),simpleDateFormat.format(date.getTime()));
        }
    };
    @Override
    public void loadAvailableTimeSlotOfDoc(String docId,String date)
    {
        dialog.show();
    }
    public void onCreate(@Nullable Bundle savedInstanceState)

    {
        super.onCreate(savedInstanceState);
        iTimeSlotLoadListener=this;
        localBroadcastManager=LocalBroadcastManager.getInstance(getContext());
        localBroadcastManager.registerReceiver(displayTimeSlot,new IntentFilter(common.KEY_DISPLAY_TIME_SLOT));
        simpleDateFormat=new SimpleDateFormat("dd_MM_yyyy");
        dialog=new SpotsDialog.Builder().setContext(getContext()).setCancelable(false).build();
        selected_date=Calender.getInstance();
        selected_date.add(Calender.Date,0);
    }

    @Override
    public void onDestroy() {
        localBroadcastManager.unregisterReceiver(displayTimeSlot);
        super.onDestroy();
    }
    @Nullable
    @Override
    public View onCreateView(@Nonnull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle)
    {
        super.onCreateView(inflater,container,onSaveInstanceState);
        View itemView =inflater.inflate(R.layout.bookingfragment,container,false);
        unbinder=ButterKnife.bind(this,itemView);
        init(itemView);
        return itemView;
    }

    private void init(View itemView) {
        recycler_time_slot.setHasFixedSize(true);
        GridLayoutManager gridLayoutManager=new GridLayoutManager(getActivity(),3);
        recycler_time_slot.setLayoutManager(gridLayoutManager);
        recycler_time_slot.addItemDecoration(new SpacesItemDecoration(8));


        Calender startDate=Calender.getInstance();
        startDate.add(Calender.DATE,0);
        Calender endDate=Calender.getInstance();
        endDate.add(Calender.DATE,2);
        HorizontalCalender horizontalCalender=new HorizontalCalender.Builder(itemView,R.id.calenderView).range(startDate,endDate)
                .datesNumberOnScreen(1).mode(HorizontalCalender.Mode.DAYS).defaultSelectedDate(startDate).build();
        horizontalCalender.setCalenderListener(new HorizontalCalenderListener() {
            @Override
            public void onDateSelected(Calender date,int position)
            {
                if(selected_date.getTimeInMillis()!= date.getTimeInMillis())
                {
                    selected_date=date;
                    loadAvailableTimeSlotOfDoc(currentBarber.getBarberID(),simpleDateFormat(date.getTime()));
                }
            }
        }

    }
}
