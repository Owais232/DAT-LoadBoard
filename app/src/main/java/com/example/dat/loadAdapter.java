package com.example.dat;


import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

public class loadAdapter extends FirebaseRecyclerAdapter<load, loadAdapter.loadAdpterViewholder> {


    public loadAdapter(@NonNull FirebaseRecyclerOptions<load> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull loadAdpterViewholder holder, int position, @NonNull load model) {


        Log Log = null;
        Log.d("ViewHolderBinding", "Origin: " + model.getOrigin());
        Log.d("ViewHolderBinding", "Destination: " + model.getDestination());
        Log.d("ViewHolderBinding", "Description: " + model.getDescription());
        Log.d("ViewHolderBinding", "Contact: " + model.getContact());
        
        
        holder.contact.setText(model.getContact());
       holder.origin.setText(model.getOrigin());
       holder.destination.setText(model.getDestination());
       holder.desc.setText(model.getDescription());

       holder.itemView.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               String cont=model.getContact();

               if(cont!=null && !cont.isEmpty()){
                   Intent Dialintent=new Intent(Intent.ACTION_DIAL, Uri.parse("tel:"+cont));
                   v.getContext().startActivity(Dialintent);
               }
           }
       });


    }

    @NonNull
    @Override
    public loadAdpterViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).
                inflate(R.layout.load_design,parent,false);

        return new loadAdpterViewholder(v);
    }

    public class loadAdpterViewholder extends RecyclerView.ViewHolder{

        TextView origin,destination,desc,contact;

        public loadAdpterViewholder(@NonNull View itemView) {
            super(itemView);
            origin=itemView.findViewById(R.id.loadorign);
            destination=itemView.findViewById(R.id.loaddestination);
            desc=itemView.findViewById(R.id.loaddesc);
            contact=itemView.findViewById(R.id.loadcont);
        }
    }



}
