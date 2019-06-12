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


public class DoctorsNameAdapter extends FirestoreRecyclerAdapter<Note, DoctorsNameAdapter.DoctorsNameHolder> {

    public DoctorsNameAdapter(FirestoreRecyclerOptions<Note> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull DoctorsNameHolder viewHolder, int position ,@NonNull Note model) {
        // super.onBindViewHolder(viewHolder, position);
        viewHolder.textViewTitle.setText(model.getTitle());
        viewHolder.textViewDescription.setText(model.getDescription());
        viewHolder.textViewPriority.setText(model.getPriority());
    }

    @Override
    public DoctorsNameHolder onCreateViewHolder( ViewGroup parent, int viewType) {
        //return super.onCreateViewHolder(parent, viewType);
        View v;
        v = LayoutInflater.from(parent.getContext()).inflate(R.layout.doctors_cardview,
                parent,false);
        return new DoctorsNameHolder(v);
    }

      class DoctorsNameHolder extends RecyclerView.ViewHolder{
        TextView textViewTitle;
        TextView textViewDescription;
        TextView textViewPriority;

        public DoctorsNameHolder(View itemView) {
            super(itemView);
            textViewTitle = itemView.findViewById(R.id.text_view_title);
            textViewDescription=itemView.findViewById(R.id.text_view_description);
            textViewPriority = itemView.findViewById(R.id.text_view_priority);
        }
    }


}
