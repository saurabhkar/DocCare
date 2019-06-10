package com.example.doccure;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.TextView;

        import com.firebase.ui.database.FirebaseRecyclerAdapter;
        import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
        import com.firebase.ui.firestore.FirestoreRecyclerOptions;


public class MedicineAdapter extends FirestoreRecyclerAdapter<NoteMedicine, MedicineAdapter.MedicineNameHolder> {


    public MedicineAdapter(FirestoreRecyclerOptions<NoteMedicine> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull MedicineNameHolder viewHolder, int position , @NonNull NoteMedicine model) {
        viewHolder.textViewName.setText(model.getName());
        viewHolder.textViewSlno.setText(model.getSlno());
    }

    @Override
    public MedicineNameHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //return super.onCreateViewHolder(parent, viewType);
        View v;
        v = LayoutInflater.from(parent.getContext()).inflate(R.layout.medicin_cardview,
                parent,false);
        return new MedicineNameHolder(v);
    }

    class MedicineNameHolder extends RecyclerView.ViewHolder{
        TextView textViewName;
        TextView textViewSlno;

        public MedicineNameHolder(View itemView) {
            super(itemView);
            textViewName = itemView.findViewById(R.id.text_view_name);
            textViewSlno=itemView.findViewById(R.id.text_view_slno);
        }
    }


}
