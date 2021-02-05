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
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import Model.Customer;


public class CustomerSU extends AppCompatActivity {
    EditText etname, etpass1, etpass2, etemail, etphone;
    Button btnSignup;
    ProgressBar progressBar;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_s_u);
        btnSignup = (Button) findViewById(R.id.sighUpD);
        etname = (EditText) findViewById(R.id.etName);
        etpass1 = (EditText) findViewById(R.id.etPass1);
        etpass2 = (EditText) findViewById(R.id.etPass2);
        etemail = (EditText) findViewById(R.id.etEmails);
        etphone = (EditText) findViewById(R.id.etPhone);
        mAuth = FirebaseAuth.getInstance();
        // theReference = FirebaseDatabase.getInstance().getReference().child("Customers");
        progressBar = findViewById(R.id.ProgressBar);
        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RegisterUser();
            }
        });
    }

    private void RegisterUser() {
        String name = etname.getText().toString().trim();
        String pass1 = etpass1.getText().toString().trim();
        String pass2 = etpass2.getText().toString().trim();
        String email = etemail.getText().toString().trim();
        String phone = etphone.getText().toString().trim();
        if (name.isEmpty()) {
            etname.setError("Full name cannot be blank");
            etname.requestFocus();
            return;
        }
        if (email.isEmpty()) {
            etemail.setError("Email cannot be blank");
            etemail.requestFocus();
            return;
        }
        if (pass1.isEmpty()) {
            etpass1.setError("Password cannot be blank");
            etpass1.requestFocus();
            return;
        }
        if (pass2.isEmpty()) {
            etpass2.setError("Password don't match");
            etpass2.requestFocus();
            return;
        }
        if (phone.isEmpty()) {
            etphone.setError("Phone cannot be blank");
            etphone.requestFocus();
            return;
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            etemail.setError("please provide a valid email address");
            etemail.requestFocus();
            return;
        }
        if (pass1.length() < 5) {
            etpass1.setError("plz provide a pass with min 6 chars");
            etpass1.requestFocus();
            return;
        }
        progressBar.setVisibility(View.VISIBLE);
        mAuth.createUserWithEmailAndPassword(email, pass1)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Customer newCus = new Customer(name, email, phone);
                            FirebaseDatabase.getInstance().getReference("Customers")
                                    .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                    .setValue(newCus).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()) {
                                        Toast.makeText(CustomerSU.this, "user is regestered sucessfully", Toast.LENGTH_LONG).show();
                                        progressBar.setVisibility(View.GONE);
                                        startActivity(new Intent(CustomerSU.this, CustomerLS.class));
                                    } else {
                                        Toast.makeText(CustomerSU.this, "failed to register", Toast.LENGTH_LONG).show();
                                        progressBar.setVisibility(View.GONE);
                                    }
                                }
                            });

                        } else {
                            Toast.makeText(CustomerSU.this, "failed to register", Toast.LENGTH_LONG).show();
                            progressBar.setVisibility(View.GONE);
                        }
                    }
                });
    }

}