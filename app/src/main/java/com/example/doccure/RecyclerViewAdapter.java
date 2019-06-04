package com.example.doccure;

import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.SpecialisationHolder>{

    List<Specialisation> Specialisations;
    RecyclerViewAdapter(List<Specialisation> Specialisations){
        this.Specialisations = Specialisations;
    }
    public static class SpecialisationHolder extends RecyclerView.ViewHolder {
        CardView cardView;
        TextView specialisationText;
        ImageView specialisationLogo;

        SpecialisationHolder(View itemView) {
            super(itemView);
            cardView = (CardView) itemView.findViewById(R.id.specialisationcardview);
            specialisationText = (TextView) itemView.findViewById(R.id.speciali_name);
            specialisationLogo = (ImageView) itemView.findViewById(R.id.specialilogo);
        }
    }
    @Override
    public SpecialisationHolder onCreateViewHolder(ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.specialisationcardview,viewGroup,false);
        SpecialisationHolder svh = new SpecialisationHolder(view);
        return svh;
    }

    @Override
    public void onBindViewHolder(SpecialisationHolder specialisationHolder, int i) {
        specialisationHolder.specialisationText.setText(Specialisations.get(i).name);
        specialisationHolder.specialisationLogo.setImageResource(Specialisations.get(i).logoId);
    }

    @Override
    public void onAttachedToRecyclerView(@NonNull RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public int getItemCount() {
        return Specialisations.size();
    }
}
