package com.example.get_a_ride;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import Adaptor.RecyclerAbill;
import Model.RideStatus;

public class ViewBill extends Fragment {
    RecyclerView recyclerView;
    RecyclerAbill recyclerAbill;
    ArrayList<RideStatus> list;
    DatabaseReference mRef;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_view_bill, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        mRef = FirebaseDatabase.getInstance().getReference().child("RideDetails");
        recyclerView = view.findViewById(R.id.recyclerBill);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(dividerItemDecoration);
        mRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                list = new ArrayList<>();
                for (DataSnapshot ds : snapshot.getChildren()) {
                    RideStatus rs = ds.getValue(RideStatus.class);
                    list.add(rs);
                }
                recyclerAbill = new RecyclerAbill(list, getContext());
                recyclerView.setAdapter(recyclerAbill);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getContext(), "error viewing the bill.", Toast.LENGTH_LONG).show();
            }
        });
    }
}