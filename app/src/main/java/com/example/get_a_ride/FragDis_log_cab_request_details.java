package com.example.get_a_ride;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import Model.RequestCabD;

public class FragDis_log_cab_request_details extends Fragment {
    EditText etemail,etlocation,etdestination,etphone,etdriverName,etCusName;
    Button button;

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference("CabRequestDetails");
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_dispatcher_log_request_details,container,false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {

        etemail=view.findViewById(R.id.cusEmailLCD);
        etphone=view.findViewById(R.id.tpCRD);
        etCusName=view.findViewById(R.id.customerNameCRD);
        etlocation=view.findViewById(R.id.locationCRD);
        etdestination=view.findViewById(R.id.desentinationCRD);
        etdriverName=view.findViewById(R.id.driverNameCRD);
        button=view.findViewById(R.id.LogCRDbtn);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy '-' HH:mm");
                String currentDateandTime = sdf.format(new Date());

                String email=etemail.getText().toString();
                String tp=etphone.getText().toString();
                String cusName=etCusName.getText().toString();
                String location=etlocation.getText().toString();
                String destination=etdestination.getText().toString();
                String name=etdriverName.getText().toString();
                String dT=currentDateandTime.toString();
                int i = new Random().nextInt(900000) + 100000;

                RequestCabD rdl=new RequestCabD(email,tp,cusName,location,destination,name,dT,String.valueOf(i));

                myRef.child(name)
                        .setValue(rdl).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(getContext(),"cab request added success",Toast.LENGTH_LONG).show();

                        }else{
                            Toast.makeText(getContext(),"error",Toast.LENGTH_LONG).show();
                        }
                    }
                });
            }
        });
    }
}

















