package com.example.get_a_ride;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;

public class DispatcherMainDrawerLayout extends AppCompatActivity {
    DrawerLayout drawerLayout;FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_dispatcher_drawer_layout);
        auth=FirebaseAuth.getInstance();
        Toolbar toolbar=findViewById(R.id.nav_toolbar);
        drawerLayout=findViewById(R.id.draw_layout);
        setSupportActionBar(toolbar);
        //setActionBar(toolbar);
        NavigationView navigationView=findViewById(R.id.navigation_view);
        navigationView.setNavigationItemSelectedListener(this::onNavigationItemSelected);
        if(savedInstanceState==null){
           // startActivity(new Intent(DispatcherMainDrawerLayout.this, DisptcherVehicalStatus.class));
             navigationView.setCheckedItem(R.id.nav_dishome);
        }

    }
    public void logout(View view){
        auth.signOut();
        startActivity(new Intent(this,dispatch_login.class));
    }
    public void onBackPressed(){
        if(drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        }else{
            super.onBackPressed();
        }
    }

    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId()){
            case R.id.nav_dishome:
                startActivity(new Intent(DispatcherMainDrawerLayout.this, DisptcherVehicalStatus.class));
                break;
            case R.id.nav_logCabDetails:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new FragDis_log_cab_request_details()).commit();
                break;
            case R.id.nav_scheduleInterviews:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new FragDisSchedulesInterviews()).commit();
                break;
            case R.id.nav_approveDrivers:
                Intent intent=new Intent(this, ApproveDrivers.class);
                startActivity(intent);
                break;
            case R.id.nav_bill:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new ViewBill()).commit();
                break;

        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return false;
    }

    public void setSupportActionBar(Toolbar toolbar) {
      toolbar.setTitle("Dispatcher");
        ActionBarDrawerToggle actionBarDrawerToggle
                =new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.nav_drawer_open,R.string.nav_drawer_close);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);

        actionBarDrawerToggle.syncState();
    }
}

