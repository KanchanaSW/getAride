package com.example.get_a_ride;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;


public class FragContactSupport extends Fragment {
    private EditText editText;
    private static final int CALL_REQUEST = 1;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_contact_support,container,false);
    }
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        editText = view.findViewById(R.id.dispatcherNO);
        editText.setEnabled(false);
        ImageView imageView = view.findViewById((R.id.image_view));
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                makeCall();
            }
        });
    }
    private void makeCall(){
        String number=editText.getText().toString();
        if(number.trim().length()>0){
            if(ContextCompat.checkSelfPermission(getContext(),
                    Manifest.permission.CALL_PHONE)!= PackageManager.PERMISSION_GRANTED){
                ActivityCompat.requestPermissions(getActivity(),new String[]
                        {Manifest.permission.CALL_PHONE},CALL_REQUEST);
            }else{
                String dial="tel:"+number;
                startActivity(new Intent(Intent.ACTION_CALL, Uri.parse(dial)));
            }

        }else{
            Toast.makeText(getContext(),"enter phone number",Toast.LENGTH_SHORT).show();
        }
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if(requestCode==CALL_REQUEST){
            if(grantResults.length>0 && grantResults[0]==PackageManager.PERMISSION_GRANTED){
                makeCall();
            }else{
                Toast.makeText(getContext(),"Permission is not granted",Toast.LENGTH_SHORT).show();
            }
        }
    }
}