package com.example.doccure.doctors;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.doccure.R;
import com.example.doccure.database.AvailableSlotsAdapter;
import com.example.doccure.database.List_Data;

import java.util.List;
 public class doctorsAppointmentAdapter extends RecyclerView.Adapter<com.example.doccure.doctors.doctorsAppointmentAdapter.ViewHolder>{
        private List<Doctors_Data> doctors_data;


        public doctorsAppointmentAdapter(List<Doctors_Data> doctors_data) {
            this.doctors_data = doctors_data;
        }


        @NonNull
        @Override
        public com.example.doccure.doctors.doctorsAppointmentAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_doctors_card_appoint,parent,false);
            return new com.example.doccure.doctors.doctorsAppointmentAdapter.ViewHolder(view);
        }


        @Override
        public void onBindViewHolder(@NonNull com.example.doccure.doctors.doctorsAppointmentAdapter.ViewHolder holder, int position) {
            Doctors_Data ld=doctors_data.get(position);

            holder.txtdate.setText(ld.getDate());
            holder.txttime.setText(ld.getTime());
            holder.txtname.setText(ld.getName());
            holder.txtphone.setText(ld.getPhone());
            holder.txtdob.setText(ld.getDob());

        }


        @Override
        public int getItemCount() {
            return doctors_data.size();
        }


        public class ViewHolder extends RecyclerView.ViewHolder{
            private TextView txtname , txttime , txtdate , txtphone ,txtdob;
            public ViewHolder(View itemView) {
                super(itemView);
                txtdate=(TextView)itemView.findViewById(R.id.datedoc);
                txttime=(TextView)itemView.findViewById(R.id.timedoc);
                txtname=(TextView)itemView.findViewById(R.id.namedoc);
                txtphone=(TextView)itemView.findViewById(R.id.phonedoc);
                txtdob=(TextView)itemView.findViewById(R.id.dobdoc);


            }
        }
    }