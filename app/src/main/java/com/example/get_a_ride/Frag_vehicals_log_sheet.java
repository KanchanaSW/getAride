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

import Model.logSheet;


public class Frag_vehicals_log_sheet extends Fragment {
    EditText etdrEmail,etdrVNo,eddrmeterB,etmeterA;
    Button log;
    private FirebaseUser user; private String userId;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference("LogSheet");
        @Nullable
        @Override
        public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
            return inflater.inflate(R.layout.fragment_log_sheets,container,false);
        }
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        user= FirebaseAuth.getInstance().getCurrentUser();
        userId=user.getUid();
        etdrEmail=(EditText)view.findViewById(R.id.dEmailLS);
        etdrVNo=(EditText)view.findViewById(R.id.vehicleIDLS);
        eddrmeterB=(EditText)view.findViewById(R.id.readingbeforeLS);
        etmeterA=(EditText)view.findViewById(R.id.readingafterLS);
        log=(Button)view.findViewById(R.id.logLSbtn);

        log.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email=etdrEmail.getText().toString().trim();
                String vNo=etdrVNo.getText().toString().trim();
                String before=eddrmeterB.getText().toString().trim();
                String after=etmeterA.getText().toString().trim();

                logSheet ls=new logSheet(email,vNo,before,after);
                myRef.child(vNo+"_"+after)
                        .setValue(ls).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(getContext(),"success",Toast.LENGTH_LONG).show();

                        }else{
                            Toast.makeText(getContext(),"error",Toast.LENGTH_LONG).show();
                        }
                    }
                });

            }
        });
    }


    }













