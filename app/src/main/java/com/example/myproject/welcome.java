package com.example.myproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.myproject.Utils.NetworkChangeReceiver;

public class welcome extends AppCompatActivity implements NetworkChangeReceiver.ConnectivityReceiverListener{

    NetworkChangeReceiver networkChangeReceiver ;
    IntentFilter intentFilter ;
    LinearLayout w_master;
    RelativeLayout wo_master;
    private Context context;
    Button btlogin;
    Button btsignup;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.welcome);

        context=welcome.this;
        networkChangeReceiver = new NetworkChangeReceiver();
        intentFilter = new IntentFilter();
        w_master=findViewById(R.id.w_master);
        wo_master=findViewById(R.id.wo_master);
        intentFilter.addAction(ConnectivityManager.CONNECTIVITY_ACTION);
        btlogin=findViewById(R.id.btlogin);
        btsignup=findViewById(R.id.btsignup);

        btlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(welcome.this,login.class));
            }
        });
        btsignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(welcome.this,signup.class));
            }
        });


    }

    @Override
    protected void onResume() {
        super.onResume();

        registerReceiver(networkChangeReceiver, intentFilter);
        networkChangeReceiver.setConnectivityReceiverListener(this);
        boolean isConnected = NetworkChangeReceiver.isConnected(this);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        try {
            unregisterReceiver(networkChangeReceiver);
        } catch (IllegalArgumentException ex) {
            ex.printStackTrace();
        }

        }
        @Override
        public void onNetworkConnectionChanged(boolean isConnected){
            callInternet(isConnected);
        }

    private void callInternet(boolean isConnected) {

        Log.e("Internet check",String.valueOf(isConnected));
        if(isConnected){
            w_master.setVisibility(View.VISIBLE);
            wo_master.setVisibility(View.GONE);


        }
        else{
            w_master.setVisibility(View.GONE);
            wo_master.setVisibility(View.VISIBLE);

        }

    }


}