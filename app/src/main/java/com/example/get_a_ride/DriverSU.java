package com.example.get_a_ride;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import Model.Driver;

public class DriverSU extends AppCompatActivity {
    EditText etnameD,etaddressD,etNic,etemailD,etphoneD;
    Button btnSignupD;
    ProgressBar progressBar;
    private FirebaseAuth mAuth;
    private DatabaseReference theReference;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference("Drivers");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver_s_u);
        btnSignupD=(Button)findViewById(R.id.sighUpD);
        etnameD=(EditText)findViewById(R.id.etNameD);
        etaddressD=(EditText)findViewById(R.id.etaddressD);
        etNic=(EditText)findViewById(R.id.etNIC);
        etemailD=(EditText)findViewById(R.id.etEmailD);
        etphoneD=(EditText)findViewById(R.id.etPhoneD);
        progressBar=findViewById(R.id.ProgressBar);
        mAuth = FirebaseAuth.getInstance();
        btnSignupD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String DriName =etnameD.getText().toString();
                String DriNIC =etNic.getText().toString();
                String DriEmail =etemailD.getText().toString();
                String DriPhone =etphoneD.getText().toString();
                String DriAddress =etaddressD.getText().toString();

                if(DriName.isEmpty()) {
                    etnameD.setError("Full name cannot be blank");
                    etnameD.requestFocus();
                    return;
                }else if(DriNIC.isEmpty()){
                    etNic.setError("nic number cannot be blank");
                    etNic.requestFocus();return;
                }else if(DriEmail.isEmpty()){
                    etemailD.setError("email cannot be blank");
                    etemailD.requestFocus();return;
                }
                else if(DriPhone.isEmpty()){
                    etphoneD.setError("phone number cannot be blank");
                    etphoneD.requestFocus();return;
                }else if(DriAddress.isEmpty()){
                    etaddressD.setError("address cannot be blank");
                    etaddressD.requestFocus();
                    return;
                }else  if(!Patterns.EMAIL_ADDRESS.matcher(DriEmail).matches()){
                    etemailD.setError("please provide a valid email address");
                    etemailD.requestFocus();
                    return;
                }
                progressBar.setVisibility(View.VISIBLE);
                Driver newDriver=new Driver(DriName,DriNIC,DriEmail,DriPhone,DriAddress);
                myRef.child(DriNIC)
                        .setValue(newDriver).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(DriverSU.this,"Registration success. Email with further will send in few days.",Toast.LENGTH_LONG).show();
                            progressBar.setVisibility(View.GONE);
                            startActivity(new Intent(DriverSU.this,DriverLS.class));
                        }else{
                            Toast.makeText(DriverSU.this,"un successful",Toast.LENGTH_LONG).show();
                        }
                    }
                });
            }
        });

    }

}













