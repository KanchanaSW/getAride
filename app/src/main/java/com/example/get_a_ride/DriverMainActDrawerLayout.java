package com.example.get_a_ride;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import Model.Customer;


public class DriverMainActDrawerLayout extends AppCompatActivity {
    DrawerLayout drawerLayout;
    private FirebaseAuth auth;
    private FirebaseUser user;
    private DatabaseReference reference;
    private String userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_act_drawer_layout);
        Toolbar toolbar = findViewById(R.id.nav_toolbar);
        drawerLayout = findViewById(R.id.draw_layout);
        setSupportActionBar(toolbar);
        auth = FirebaseAuth.getInstance();
        NavigationView navigationView = findViewById(R.id.navigation_view);
        navigationView.setNavigationItemSelectedListener(this::onNavigationItemSelected);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new Frag_reportMyStatus()).commit();
            navigationView.setCheckedItem(R.id.nav_status);
        }
    }

    public void logout(View view) {
        auth.signOut();
        startActivity(new Intent(this, MainActivity.class));
    }

    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.nav_contact:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new FragContactSupport()).commit();
                break;
            case R.id.nav_status:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new Frag_reportMyStatus()).commit();
                break;
            case R.id.nav_home:
                startActivity(new Intent(DriverMainActDrawerLayout.this, DriverHome.class));
                // getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new FragHome()).commit();
                break;
            case R.id.nav_logSheet:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new Frag_vehicals_log_sheet()).commit();
                break;
            case R.id.nav_ride:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new DriRideStatus()).commit();
                break;


        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return false;
    }

    public void setSupportActionBar(Toolbar toolbar) {
        user = FirebaseAuth.getInstance().getCurrentUser();
        userId = user.getUid();
        reference = FirebaseDatabase.getInstance().getReference("ApprovedDrivers");

        reference.child(userId).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String dName = snapshot.getValue(Customer.class).getdName();
                toolbar.setTitle(dName);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(DriverMainActDrawerLayout.this, "error occurred userprofile is null", Toast.LENGTH_LONG).show();
            }
        });
        ActionBarDrawerToggle actionBarDrawerToggle
                = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.nav_drawer_open, R.string.nav_drawer_close);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);

        actionBarDrawerToggle.syncState();
    }
}