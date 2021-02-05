package com.example.get_a_ride;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import Adaptor.RecyclerAdStatus;
import Adaptor.RecyclerAdaptor;
import Model.Driver;
import Model.LogStatus;

public class DisptcherVehicalStatus extends AppCompatActivity {
    RecyclerView recyclerView;    RecyclerAdStatus recyclerAdStatus;
    ArrayList<LogStatus> list;
    DatabaseReference mRef;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_disp_vehical_status);
        mRef= FirebaseDatabase.getInstance().getReference().child("DriverStatus");
        recyclerView = findViewById(R.id.recyclerViewStatus);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        DividerItemDecoration dividerItemDecoration=new DividerItemDecoration(this,DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(dividerItemDecoration);
        mRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                list=new ArrayList<LogStatus>();
                for(DataSnapshot ds: snapshot.getChildren()){
                    LogStatus ls=ds.getValue(LogStatus.class);
                    list.add(ls);
                }
                recyclerAdStatus=new RecyclerAdStatus(list,DisptcherVehicalStatus.this);
                recyclerView.setAdapter(recyclerAdStatus);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(DisptcherVehicalStatus.this, "Oops.... Something is wrong", Toast.LENGTH_SHORT).show();
            }
        });
    }
}












