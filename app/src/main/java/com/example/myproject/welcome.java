package com.example.myproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.myproject.Utils.NetworkChangeReceiver;

public class welcome extends AppCompatActivity implements NetworkChangeReceiver.ConnectivityReceiverListener{

    NetworkChangeReceiver networkChangeReceiver ;
    IntentFilter intentFilter ;
    LinearLayout smaster;
    RelativeLayout omaster;
    private Context context;
    Button btnlogin;
    Button btnsignup;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.welcome);

        btnlogin=findViewById(R.id.btnlogin);
        btnsignup=findViewById(R.id.btnsignup);

        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(welcome.this,login.class));
            }
        });
        btnsignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(welcome.this,signup.class));
            }
        });


    }

    @Override
    public void onNetworkConnectionChanged(boolean isConnected) {

    }
}