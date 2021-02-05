package com.example.get_a_ride;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Date;

import Model.LogStatus;


public class Frag_reportMyStatus extends Fragment {
    RadioGroup rg;
    RadioButton rb;
    Button btn;
    private FirebaseUser user;
    private String userId;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference("DriverStatus");

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_report_my_status, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        user = FirebaseAuth.getInstance().getCurrentUser();
        userId = user.getUid();
        rg = view.findViewById(R.id.radiogroupRMS);
        btn = view.findViewById(R.id.reportRMSbtn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //simple date format and time
                SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy '-' HH:mm");
                String currentDateandTime = sdf.format(new Date());
                int selectedId = rg.getCheckedRadioButtonId();
                rb = view.findViewById(selectedId);
                if (selectedId == -1) {
                    Toast.makeText(getContext(), "Nothing selected", Toast.LENGTH_LONG).show();
                } else {
                    //btn.getText();
                    String status = rb.getText().toString();
                    LogStatus ls = new LogStatus(status, currentDateandTime, userId);
                    myRef.child(userId)
                            .setValue(ls).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(getContext(), "status Logged", Toast.LENGTH_LONG).show();
                            } else {
                                Toast.makeText(getContext(), "error adding the status.", Toast.LENGTH_LONG).show();
                            }
                        }
                    });
                }
            }
        });

    }

}
















