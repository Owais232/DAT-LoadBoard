package com.example.dat;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class Admin_Activity extends AppCompatActivity {
    Button loadadd,deleteload;
    TextInputEditText origin,destination,description,contact;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
        init();

        deleteload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(Admin_Activity.this,Admin_all_loads.class);
                startActivity(intent);
            }
        });
        loadadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                View view= LayoutInflater.from(Admin_Activity.this).inflate(R.layout.load_add,null);

                AlertDialog.Builder newload=new AlertDialog.Builder(Admin_Activity.this)
                        .setTitle("Add Load Information here ")
                        .setView(view)
                        .setPositiveButton("Save", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                String orig,dest,desc,cont;
                                orig = ((TextInputEditText) view.findViewById(R.id.origin)).getText().toString().trim();
                                dest = ((TextInputEditText) view.findViewById(R.id.destination)).getText().toString().trim();
                                desc = ((TextInputEditText) view.findViewById(R.id.description)).getText().toString().trim();
                                cont = ((TextInputEditText) view.findViewById(R.id.contact)).getText().toString().trim();

                                HashMap<String,Object> data=new HashMap<>();
                                data.put("Origin",orig);
                                data.put("Destination",dest);
                                data.put("Description",desc);
                                data.put("Contact",cont);


                                FirebaseDatabase.getInstance().getReference().child("Loads").push()
                                        .setValue(data).addOnSuccessListener(new OnSuccessListener<Void>() {
                                            @Override
                                            public void onSuccess(Void unused) {
                                                Toast.makeText(Admin_Activity.this, "Data added Successfully", Toast.LENGTH_SHORT).show();
                                            }
                                        }).addOnFailureListener(new OnFailureListener() {
                                            @Override
                                            public void onFailure(@NonNull Exception e) {
                                                Toast.makeText(Admin_Activity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                                            }
                                        });

                            }
                        })
                        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        });
                newload.create();
                newload.show();

            }
        });
    }
    public void init() {
        loadadd = findViewById(R.id.addload);
        deleteload=findViewById(R.id.deleteload);
        origin = findViewById(R.id.origin);
        description = findViewById(R.id.description);
        destination = findViewById(R.id.destination);
        contact = findViewById(R.id.contact);
    }
}