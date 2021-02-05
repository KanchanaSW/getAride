package com.example.get_a_ride;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

public class dispatch_login extends AppCompatActivity {
    Button logIn;    EditText etEmail,etPass;    ProgressBar pb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dispatch_login);
        logIn=findViewById(R.id.disLogin);
        etEmail=findViewById(R.id.emailDis);
        etEmail.setEnabled(false);
        etPass=findViewById(R.id.passwordDis);
        pb=findViewById(R.id.ProgressBar);
        logIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                disLogIn();
            }
        });
    }
    private void disLogIn() {
        pb.setVisibility(View.VISIBLE);
    String emailD=etEmail.getText().toString().trim();
    String pass=etPass.getText().toString().trim();
    if(pass.equals("dispatch")){
        startActivity(new Intent(dispatch_login.this,DispatcherMainDrawerLayout.class));
        pb.setVisibility(View.GONE);
    }else{
        Toast.makeText(dispatch_login.this, " password error.!!", Toast.LENGTH_LONG).show();
    }
    }
}