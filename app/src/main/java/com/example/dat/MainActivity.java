package com.example.dat;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity {
    Button btnlogin,btnregister,rsubmit,llogin,lclear;

    TextInputEditText remail,rpass,lemail,lpass,rfname,rlname;
    LinearLayout signinform,signupform;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String adminemail="Admin@gmail.com";
        String adminpass="123";

        init();


        llogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = lemail.getText().toString().trim();
                String pass = lpass.getText().toString().trim();

                FirebaseDatabase.getInstance().getReference().child("Members")
                        .addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                String demail, dpass,df,dl;

                                for (DataSnapshot s : snapshot.getChildren()) {
                                    demail = s.child("email").getValue(String.class);
                                    dpass = s.child("Pass").getValue(String.class);
                                    df=s.child("First").getValue(String.class);
                                    dl=s.child("Last").getValue(String.class);

                                    if (email.equals(demail) && pass.equals(dpass)) {

                                        Intent intent = new Intent(MainActivity.this, User_Activity.class);
                                        intent.putExtra("First",df);
                                        intent.putExtra("Last",dl);

                                        startActivity(intent);
                                        return;
                                    }
                                }

                                if (email.equals(adminemail) && pass.equals(adminpass)) {
                                    Intent intent = new Intent(MainActivity.this, Admin_Activity.class);
                                    startActivity(intent);
                                } else {
                                    Toast.makeText(MainActivity.this, "Wrong email or password. Email: " + email + ", Pass: " + pass, Toast.LENGTH_SHORT).show();
                                }
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {
                            }
                        });
            }
        });



        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signinform.setVisibility(View.VISIBLE);
                signupform.setVisibility(View.GONE);

            }
        });



        btnregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signinform.setVisibility(View.GONE);
                signupform.setVisibility(View.VISIBLE);

            }
        });

        rsubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email,pass,first,last;
                first=rfname.getText().toString().trim();
                last=rlname.getText().toString().trim();
                email=remail.getText().toString().trim();
                pass=rpass.getText().toString().trim();

                HashMap<String, Object> data=new HashMap<>();
                data.put("email",email);
                data.put("Pass",pass);
                data.put("First",first);
                data.put("Last",last);

                FirebaseDatabase.getInstance().getReference().child("Members").push()
                        .setValue(data).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void unused) {

                                Toast.makeText(MainActivity.this, "Data added successfully", Toast.LENGTH_SHORT).show();
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(MainActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        });
                signinform.setVisibility(View.VISIBLE);
                signupform.setVisibility(View.GONE);

            }
        });


    }
    public void init(){
        btnlogin=findViewById(R.id.btnsignin);
        btnregister=findViewById(R.id.btnsignup);
        llogin=findViewById(R.id.llogin);
        lclear=findViewById(R.id.lclear);
        signinform=findViewById(R.id.signinform);
        signupform=findViewById(R.id.signupForm);
        rsubmit=findViewById(R.id.rsubmit);
        remail=findViewById(R.id.remail);
        rpass=findViewById(R.id.rpass);
        lemail=findViewById(R.id.lemail);
        lpass=findViewById(R.id.lpass);
        rfname=findViewById(R.id.rfname);
        rlname=findViewById(R.id.rlname);


    }
}