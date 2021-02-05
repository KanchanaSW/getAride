package com.example.get_a_ride;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import Adaptor.RecyclerAdaptor;
import Model.Contact;
import Model.Driver;
import Model.DriverSche;
import Model.User;

public class ApproveDrivers extends AppCompatActivity {
    RecyclerView recyclerView;
    RecyclerAdaptor recyclerAdaptor;
    ArrayList<Driver> list;
    EditText driName,driNIC,driEmail;
    Button approve;
    private DatabaseReference reference;
    private FirebaseAuth mAuth;
    private DatabaseReference theReference;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference("ApprovedDriverDetails");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_approve_drivers);
        driName=findViewById(R.id.etNameAD);
        driEmail=findViewById(R.id.etEmailAD);
        driNIC=findViewById(R.id.etNICAD);
        approve=(Button)findViewById(R.id.approve);
        reference= FirebaseDatabase.getInstance().getReference().child("Drivers");
        recyclerView=findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        DividerItemDecoration dividerItemDecoration=new DividerItemDecoration(this,DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(dividerItemDecoration);
        reference.addListenerForSingleValueEvent(new ValueEventListener() {
           @Override
           public void onDataChange(@NonNull DataSnapshot snapshot) {
               ArrayList<Driver> list=new ArrayList<>();
                for(DataSnapshot ds: snapshot.getChildren()){
                   Driver ur=ds.getValue(Driver.class);
                    list.add(ur);
                }
               recyclerAdaptor=new RecyclerAdaptor(ApproveDrivers.this,list);
                recyclerView.setAdapter(recyclerAdaptor);
           }
           @Override
           public void onCancelled(@NonNull DatabaseError error) {
               Toast.makeText(ApproveDrivers.this, "Opsss.... Something is wrong", Toast.LENGTH_SHORT).show();
           }
       });
        mAuth = FirebaseAuth.getInstance();
        theReference = FirebaseDatabase.getInstance().getReference().child("ApprovedDrivers");
        approve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String dName=driName.getText().toString().trim();
                String dEmail=driEmail.getText().toString().trim();
                String dNic=driNIC.getText().toString().trim();
                String tempPass="123456";
                mAuth.createUserWithEmailAndPassword(dEmail,tempPass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Driver newDriver=new Driver(dName,dEmail,dNic);
                            FirebaseDatabase.getInstance().getReference("ApprovedDrivers")
                                    .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                    .setValue(newDriver).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if(task.isSuccessful()) {
                                        Toast.makeText(ApproveDrivers.this, "Driver added success.", Toast.LENGTH_LONG).show();
                                        //saveDriver();
                                        deletePendingD();
                                        }else{
                                        Toast.makeText(ApproveDrivers.this,"error occurs.",Toast.LENGTH_LONG).show();
                                    }
                                }
                            });
                        }else{
                            Toast.makeText(ApproveDrivers.this,"db error while adding driver.",Toast.LENGTH_LONG).show();
                        }
                    }
                });
            }
        });
    }
    /*private void saveDriver() {
        String toDname=driName.getText().toString().trim();
        String toDemail=driEmail.getText().toString().trim();
        String toDnic=driNIC.getText().toString().trim();

        Driver newDriver=new Driver(toDname,toDemail,toDnic);
        myRef.child(toDnic).setValue(newDriver).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()) {
                    Toast.makeText(ApproveDrivers.this, "Added to driver details db", Toast.LENGTH_LONG).show();
                        deletePendingD();
                }else{
                    Toast.makeText(ApproveDrivers.this, "some error occured while add data to driver details", Toast.LENGTH_LONG).show();
                }
            }
        });
    }*/

    private void deletePendingD() {
        //delete chile where nic =? so it the driver wont show in pending
        String toDnic=driNIC.getText().toString().trim();
        reference.child(toDnic).removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()) {
                    Toast.makeText(ApproveDrivers.this, "pending driver removed from drivers db", Toast.LENGTH_LONG).show();
                    sendMail();
                }else {
                    Toast.makeText(ApproveDrivers.this, "some error occured while deleting driver from pending list", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
    public void sendMail(){
        String toDriver=driName.getText().toString().trim();
        String toEmail=driEmail.getText().toString().trim();
        String toNic=driNIC.getText().toString().trim();

        Intent intent =new Intent(Intent.ACTION_VIEW
                , Uri.parse("mailto:"+toEmail));

        intent.putExtra(Intent.EXTRA_SUBJECT, "GET A RIDE Driver Application.");
        intent.putExtra(Intent.EXTRA_TEXT, "Hello \n "+toDriver+"\n  "+
                "\n your application is approved \n Login details are"+
                        "\n Email : "+toEmail+" "+
                "\n Password : 123456 "+
                "\n change your password ");
        startActivity(intent);
    }
}