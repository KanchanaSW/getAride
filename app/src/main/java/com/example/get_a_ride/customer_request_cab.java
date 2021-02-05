package com.example.get_a_ride;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class customer_request_cab extends AppCompatActivity {
    private EditText editText;
    private static final int CALL_REQUEST = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_request_cab);
        editText = findViewById(R.id.dispatcherNO);
        editText.setEnabled(false);
        ImageView imageView = findViewById((R.id.image_view));
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
            if(ContextCompat.checkSelfPermission(customer_request_cab.this,
                    Manifest.permission.CALL_PHONE)!= PackageManager.PERMISSION_GRANTED){
                ActivityCompat.requestPermissions(customer_request_cab.this,new String[]
                        {Manifest.permission.CALL_PHONE},CALL_REQUEST);
            }else{
                String dial="tel:"+number;
                startActivity(new Intent(Intent.ACTION_CALL, Uri.parse(dial)));
            }

        }else{
            Toast.makeText(customer_request_cab.this,"enter phone number",Toast.LENGTH_SHORT).show();
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