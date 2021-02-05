package com.example.get_a_ride;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Random;

import Model.RideStatus;

public class DriRideStatus extends Fragment {
    EditText etCusName, etCusPhone, etMilage, etPrice,etID;
    Button reportBtn;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference("RideDetails");
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_dri_ride_status,container,false);
    }
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        etCusName=view.findViewById(R.id.cusNameRMRS);
        etCusPhone=view.findViewById(R.id.cusPhoneRMRS);
        etMilage=view.findViewById(R.id.cusMilageRMRS);
        etPrice=view.findViewById(R.id.cusPriceRMRS);
        etID=view.findViewById(R.id.idRMRS);
        reportBtn=view.findViewById(R.id.reportDRSbtn);
        reportBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name=etCusName.getText().toString();
                String phone=etCusPhone.getText().toString();
                String mileage=etMilage.getText().toString();
                String price=etPrice.getText().toString();
                String id=etID.getText().toString();
                RideStatus rs=new RideStatus(name,phone,mileage,price);
                //int i = new Random().nextInt(900000) + 100000;
                myRef.child(id)
                        .setValue(rs).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(getContext(),"Ride details added success",Toast.LENGTH_LONG).show();

                            Intent intent =new Intent(Intent.ACTION_VIEW
                                    , Uri.parse("mailto:"+phone));
                            intent.putExtra(Intent.EXTRA_SUBJECT, "GET A RIDE Bill for your ride.");
                            intent.putExtra(Intent.EXTRA_TEXT, "Hello \n "+name+"\n  "+
                                    "\n your bill id : "+id+ " \n "+
                                    "\n Email : "+phone+" "+
                                    "\n Mileage : "+mileage+" Km"+
                                    "\n Price : "+price+" Rs"+
                                    "\n Pay your Bill ");
                            startActivity(intent);

                        }else{
                            Toast.makeText(getContext(),"error",Toast.LENGTH_LONG).show();
                        }
                    }
                });
            }
        });
    }


}