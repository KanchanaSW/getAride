package com.example.get_a_ride;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import java.util.Calendar;

public class FragDisSchedulesInterviews extends Fragment {
    EditText etnameSI,etemailSI,etdateSI,ettimeSI,etvenuSI,etdetailsSI;
    Button btnscheduleSI,btnbackSI;
    DatePickerDialog picker;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
          return inflater.inflate(R.layout.fragment_dis_schedules_interviews,container,false);
    }
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
       etnameSI=(EditText)view.findViewById(R.id.nameSI);
       etemailSI=(EditText)view.findViewById(R.id.emailSI);
       etdateSI=view.findViewById(R.id.dateSI);
       ettimeSI=(EditText)view.findViewById(R.id.timeSI);
       etvenuSI=(EditText)view.findViewById(R.id.venuSI);
       etdetailsSI=(EditText)view.findViewById(R.id.detailsSI);
        etdateSI.setInputType(InputType.TYPE_NULL);
        etdateSI.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar dob=Calendar.getInstance();
                int day=dob.get(Calendar.DAY_OF_MONTH);
                int month=dob.get(Calendar.MONTH);
                int year=dob.get(Calendar.YEAR);
                picker=new DatePickerDialog(getActivity(),
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                                etdateSI.setText(dayOfMonth + "/" + (month + 1) + "/" + year);
                            }
                        },year,month,day);
                picker.show();
            }
        });
        btnscheduleSI=(Button)view.findViewById(R.id.scheduleSI);

       btnscheduleSI.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               String Fname =etnameSI.getText().toString();
               String Email =etemailSI.getText().toString();
               String Date =etdateSI.getText().toString();
               String Time =ettimeSI.getText().toString();
               String Venu =etvenuSI.getText().toString();
               String Details =etdetailsSI.getText().toString();

               Intent intent =new Intent(Intent.ACTION_VIEW
               ,Uri.parse("mailto:"+Email));
               intent.putExtra(Intent.EXTRA_SUBJECT, "GET A RIDE Interview is schedule on.");
               intent.putExtra(Intent.EXTRA_TEXT, "Hello \n "+Fname+"\n more details "
                       +Details+"\n here is the venue "+Venu+" \n be here at "
                       +" time : "+Time+"\n on "+Date);
               startActivity(intent);
           }
       });
    }

}