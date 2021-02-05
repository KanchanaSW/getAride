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
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class CustomerLS extends AppCompatActivity {
    Button btnSignup,btnLogin;
    ProgressBar progressBar;
    private EditText editTextEmail,editTextPass;
    private FirebaseAuth mAuth;
    private String userId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_l_s);
       TextView textFP=(TextView) findViewById(R.id.forgetPassword);
        btnSignup=(Button)findViewById(R.id.btnSignup);
        btnLogin=(Button)findViewById(R.id.btnLogin);
        editTextEmail=(EditText)findViewById(R.id.etEmail);
        editTextPass=(EditText)findViewById(R.id.etPassword);
        progressBar=findViewById(R.id.ProgressBar);
        mAuth=FirebaseAuth.getInstance();

        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //start signup activity for customer
                startActivity(new Intent(CustomerLS.this, CustomerSU.class));
                progressBar.setVisibility(View.GONE);
            }
        });
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 loginCustomer();
            }
        });
        textFP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CustomerLS.this, ForgotPassword.class));
                progressBar.setVisibility(View.GONE);
            }
        });
    }
    private void loginCustomer() {
        progressBar.setVisibility(View.VISIBLE);
        String email=editTextEmail.getText().toString().trim();
        String password=editTextPass.getText().toString().trim();

        if(email.isEmpty()){
            editTextEmail.setError("email is required");
            editTextEmail.requestFocus();
            return;
        }
        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            editTextEmail.setError("please provide valid email");
            editTextEmail.requestFocus();
            return;
        }
        if(password.isEmpty()){
            editTextPass.setError("password is required");
            editTextPass.requestFocus();
            return;
        }
        if(password.length()<5){
            editTextPass.setError("min length for password is 6 characters");
            editTextPass.requestFocus();
            return;
        }

        progressBar.setVisibility(View.VISIBLE);
        mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

                    if (user.isEmailVerified()) {
                        startActivity(new Intent(CustomerLS.this, CustomerMainDrawerLayout.class));
                        progressBar.setVisibility(View.GONE);
                    } else {
                        user.sendEmailVerification();
                        Toast.makeText(CustomerLS.this, "Check your email to verify Account", Toast.LENGTH_LONG).show();
                        progressBar.setVisibility(View.GONE);
                    }
                } else {
                    Toast.makeText(CustomerLS.this, " Something went wrong!!", Toast.LENGTH_LONG).show();
                    progressBar.setVisibility(View.GONE);
                }
            }
        });

    }

}