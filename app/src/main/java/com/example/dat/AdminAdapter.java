package com.example.dat;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;

public class AdminAdapter extends FirebaseRecyclerAdapter<load, AdminAdapter.AdminAdpterViewHolder> {

    public AdminAdapter(@NonNull FirebaseRecyclerOptions<load> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull AdminAdpterViewHolder holder, int position, @NonNull load model) {

        holder.contact.setText(model.getContact());
        holder.origin.setText(model.getOrigin());
        holder.destination.setText(model.getDestination());
        holder.desc.setText(model.getDescription());

        holder.deleteload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get the reference to the item to be deleted
                DatabaseReference itemRef = getRef(position);

                // Remove the item from the database
                itemRef.removeValue();
            }
        });
    }

    @NonNull
    @Override
    public AdminAdpterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.adminload, parent, false);

        return new AdminAdpterViewHolder(v);
    }

    public static class AdminAdpterViewHolder extends RecyclerView.ViewHolder {

        TextView origin, destination, desc, contact;
        Button deleteload;

        public AdminAdpterViewHolder(@NonNull View itemView) {
            super(itemView);
            origin = itemView.findViewById(R.id.loadorign);
            destination = itemView.findViewById(R.id.loaddestination);
            desc = itemView.findViewById(R.id.loaddesc);
            contact = itemView.findViewById(R.id.loadcont);
            deleteload=itemView.findViewById(R.id.deleteloadinadmin);
        }
    }
}
