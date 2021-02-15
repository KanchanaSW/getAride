package com.example.get_a_ride;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentTransaction;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import Model.Customer;
import Model.RequestCabD;

public class DriverHome extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private FirebaseUser user; private String userId;
    private DatabaseReference reference;
    private DatabaseReference reference1;
    private String userName;
    String number=null;
    Button btncallCustomer;

    public TextView gtv;
    public TextView gtv2;
    String nameDD=null;
    private static final int CALL_REQUEST = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver_home);
        user=FirebaseAuth.getInstance().getCurrentUser();
        //reference1= FirebaseDatabase.getInstance().getReference("DriverSch");
        reference1= FirebaseDatabase.getInstance().getReference("CabRequestDetails");
        gtv=(TextView)findViewById(R.id.test);
        btncallCustomer=findViewById(R.id.callCustomer);
        btncallCustomer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                makeCall();
            }
        });

        user= FirebaseAuth.getInstance().getCurrentUser();
        userId=user.getUid();
        reference= FirebaseDatabase.getInstance().getReference("ApprovedDrivers");


        reference.child(userId).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                 nameDD=snapshot.getValue(Customer.class).getdName();
               // Query query=FirebaseDatabase.getInstance().getReference().child("CabRequestDetails").orderByChild("driverName").equalTo(nameDD);
                reference1.child(nameDD).addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        RequestCabD details=snapshot.getValue(RequestCabD.class);
                        if(details!=null){
                            String cusName=details.getCustomerName();
                            String dateAndTime=details.getDateAndtime();
                            String destination=details.getDestination();
                            String driverName=details.getDriverName();
                            String cusEmail=details.getEmail();
                            String custp=details.getTp();
                            String location=details.getLocation();
                            String id=details.getId();

                            gtv.setText("Customer Name : "+cusName+"\n Date and time : "+dateAndTime+"\n Destination : "+destination+"\n Location : "+location+
                                    "\n Customer phone : "+custp+"\n Customer Email : "+cusEmail+"\n Ride ID : "+id+" ");
                            number = custp.toString();
                        }else{
                            Toast.makeText(DriverHome.this,"no rides yet",Toast.LENGTH_LONG).show();
                        }

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(DriverHome.this,"error no name",Toast.LENGTH_LONG).show();
            }
        });

    }

    private void makeCall() {

        if(number.trim().length()>0){
            if(ContextCompat.checkSelfPermission(DriverHome.this, Manifest.permission.CALL_PHONE)!= PackageManager.PERMISSION_GRANTED){
                ActivityCompat.requestPermissions(DriverHome.this,new String[]{Manifest.permission.CALL_PHONE},CALL_REQUEST);
            }else{
                String dial="tel:"+number;
                startActivity(new Intent(Intent.ACTION_CALL, Uri.parse(dial)));
            }

        }else{
            Toast.makeText(DriverHome.this,"enter phone number",Toast.LENGTH_SHORT).show();
        }
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if(requestCode==CALL_REQUEST){
            if(grantResults.length>0 && grantResults[0]==PackageManager.PERMISSION_GRANTED){
                makeCall();
            }else{
                Toast.makeText(this,"Permision is not granted",Toast.LENGTH_SHORT).show();
            }
        }
    }

}