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

public class DriverLS extends AppCompatActivity {
    Button btnSignupD, btnLoginD;
    ProgressBar progressBar;
    private EditText editTextEmailD, editTextPassD;
    private FirebaseAuth mAuth;
    // private DatabaseReference theReference;
    //FirebaseDatabase database = FirebaseDatabase.getInstance();
    // DatabaseReference myRef = database.getReference("ApprovedDriver");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver_l_s);
        TextView textFP = (TextView) findViewById(R.id.forgetPassword);
        btnSignupD = (Button) findViewById(R.id.btnSignupD);
        btnLoginD = (Button) findViewById(R.id.btnLoginD);
        editTextEmailD = (EditText) findViewById(R.id.etEmailD);
        editTextPassD = (EditText) findViewById(R.id.etPasswordD);
        progressBar = findViewById(R.id.ProgressBar);


        mAuth = FirebaseAuth.getInstance();
        btnSignupD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DriverLS.this, DriverSU.class));
                progressBar.setVisibility(View.GONE);
            }
        });
        btnLoginD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginUser();
            }
        });
        textFP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DriverLS.this, ForgotPassword.class));
                progressBar.setVisibility(View.GONE);
            }
        });
    }

    private void loginUser() {
        progressBar.setVisibility(View.VISIBLE);
        String email = editTextEmailD.getText().toString().trim();
        String password = editTextPassD.getText().toString().trim();
        if (email.isEmpty()) {
            editTextEmailD.setError("email is required");
            editTextEmailD.requestFocus();
            return;
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            editTextEmailD.setError("please provide valid email");
            editTextEmailD.requestFocus();
            return;
        }
        if (password.isEmpty()) {
            editTextPassD.setError("password is required");
            editTextPassD.requestFocus();
            return;
        }
        progressBar.setVisibility(View.VISIBLE);
        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

                    if (user.isEmailVerified()) {
                        startActivity(new Intent(DriverLS.this, DriverMainActDrawerLayout.class));
                        progressBar.setVisibility(View.GONE);
                    } else {
                        user.sendEmailVerification();
                        Toast.makeText(DriverLS.this, "Check your email to verify Account", Toast.LENGTH_LONG).show();
                        progressBar.setVisibility(View.GONE);
                    }
                } else {
                    Toast.makeText(DriverLS.this, " Something went wrong!!", Toast.LENGTH_LONG).show();
                    progressBar.setVisibility(View.GONE);
                }
            }
        });

    }
}