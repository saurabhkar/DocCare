package com.example.doccure;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.TextureView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.doccure.model.TimeSlot;
import com.example.doccure.model.common;
import com.google.android.gms.common.internal.service.Common;

import java.util.List;


public abstract class MyTimeSlotAdapter  extends RecyclerView.Adapter<MyTimeSlotAdapter.MyViewHolder> {

    Context context;
    List<TimeSlot> timeSlotList;
    public MyTimeSlotAdapter(Context context,List<TimeSlot> timeSlotList)
    {
        this.context=context;
        this.timeSlotList=timeSlotList;
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView= LayoutInflater.from(context).inflate(R.layout.activity_time_slot,viewGroup,false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {

        myViewHolder.txt_time_slot.setText(new StringBuilder(common.convertTimeSlotToString(i)).toString());
if(timeSlotList.size()==0)
{
    myViewHolder.card_time_slot.setCardBackgroundColor(context.getResources().getColor(android.R.color.darker_gray));
    myViewHolder.txt_time_slot_description.setText("Available");
    myViewHolder.txt_time_slot_description.setTextColor(context.getResources().getColor(android.R.color.white));
    myViewHolder.txt_time_slot.setTextColor(context.getResources().getColor(android.R.color.black));

}
else
{
    for(TimeSlot slotValue:timeSlotList)
    {
        int slot=Integer.parseInt(slotValue.getSlot().toString());
        if(slot==i) {
            myViewHolder.card_time_slot.setCardBackgroundColor(context.getResources().getColor(android.R.color.darker_gray));
            myViewHolder.txt_time_slot_description.setText("Full");
            myViewHolder.txt_time_slot_description.setTextColor(context.getResources().getColor(android.R.color.white));
        }
    }
}


    }

    @Override
    public int getItemCount() {
        return common.TIME_SLOT_TOTAL;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView txt_time_slot,txt_time_slot_description;
        CardView card_time_slot;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            card_time_slot=(CardView)itemView.findViewById(R.id.card_time_Slot);
            txt_time_slot=(TextView)itemView.findViewById(R.id.text_time_slot);

            txt_time_slot_description=(TextView)itemView.findViewById(R.id.text_time_slot_description);

        }
    }
}
