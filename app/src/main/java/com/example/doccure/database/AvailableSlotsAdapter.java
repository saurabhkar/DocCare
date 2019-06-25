package com.example.doccure.database;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.doccure.R;

import java.util.List;

public class AvailableSlotsAdapter extends RecyclerView.Adapter<AvailableSlotsAdapter.ViewHolder>{
    private List<List_Data>listData;

    public AvailableSlotsAdapter(List<List_Data> listData) {
        this.listData = listData;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_availability,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        List_Data ld=listData.get(position);

        holder.txtname.setText(ld.getTime());
    }

    @Override
    public int getItemCount() {
        return listData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView txtname;
        public ViewHolder(View itemView) {
            super(itemView);
//            txtid=(TextView)itemView.findViewById(R.id.idtxt);
            txtname=(TextView)itemView.findViewById(R.id.nametxt);

        }
    }
}