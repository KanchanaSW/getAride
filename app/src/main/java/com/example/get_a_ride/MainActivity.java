package com.example.get_a_ride;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;


public class MainActivity extends AppCompatActivity {
    Button driver;Button customer,dispatcher;
    ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        driver=(Button)findViewById(R.id.driver1);
        customer=(Button)findViewById(R.id.Customer);
        dispatcher=(Button)findViewById(R.id.dispatcher);
        progressBar=findViewById(R.id.ProgressBar);
        customer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressBar.setVisibility(View.VISIBLE);
                startActivity(new Intent(MainActivity.this, CustomerLS.class));
                progressBar.setVisibility(View.GONE);
            }
        });
        driver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressBar.setVisibility(View.VISIBLE);
                startActivity(new Intent(MainActivity.this, DriverLS.class));
                progressBar.setVisibility(View.GONE);
            }
        });
        dispatcher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressBar.setVisibility(View.VISIBLE);
                startActivity(new Intent(MainActivity.this, dispatch_login.class));
                progressBar.setVisibility(View.GONE);
            }
        });
    }
}
