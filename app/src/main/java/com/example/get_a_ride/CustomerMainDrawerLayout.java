package com.example.get_a_ride;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.jar.Attributes;

import Model.User;

public class CustomerMainDrawerLayout extends AppCompatActivity {
    DrawerLayout drawerLayout;

    private FirebaseAuth auth;
    private FirebaseUser user;
    private DatabaseReference reference;
    private String userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_main_drawer_layout);
        Toolbar toolbar = findViewById(R.id.nav_toolbar);
        drawerLayout = findViewById(R.id.draw_layout);
        setSupportActionBar(toolbar);
        auth = FirebaseAuth.getInstance();
        //setActionBar(toolbar);
        NavigationView navigationView = findViewById(R.id.navigation_view);
        navigationView.setNavigationItemSelectedListener(this::onNavigationItemSelected);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new FragHome()).commit();
            navigationView.setCheckedItem(R.id.nav_cushome);
        }
    }
    public void logout(View view){
        auth.signOut();
        startActivity(new Intent(this,CustomerLS.class));
    }
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    private boolean onNavigationItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.nav_dishome:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new FragHome()).commit();
                break;
            case R.id.nav_conSupp:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new FragContactSupport()).commit();
                break;
            case R.id.nav_requestCab:
                Intent intent = new Intent(this, customer_request_cab.class);
                startActivity(intent);
                break;
            case R.id.nav_rideStatus:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new FragCusRideStatus()).commit();
                break;


        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return false;
    }

    public void setSupportActionBar(Toolbar toolbar) {
        user = FirebaseAuth.getInstance().getCurrentUser();
        userId = user.getUid();
        reference = FirebaseDatabase.getInstance().getReference("Customers");

        reference.child(userId).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String name = snapshot.getValue(User.class).getName();
                toolbar.setTitle(name);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(CustomerMainDrawerLayout.this, "error occurred userprofile is null", Toast.LENGTH_LONG).show();
            }
        });
        /////


        ActionBarDrawerToggle actionBarDrawerToggle
                = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.nav_drawer_open, R.string.nav_drawer_close);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);

        actionBarDrawerToggle.syncState();
    }

    public void psDetails() {

    }
}