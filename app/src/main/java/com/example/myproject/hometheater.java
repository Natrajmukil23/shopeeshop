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

import com.example.myproject.Utils.NetworkChangeReceiver;

public class hometheater extends AppCompatActivity implements NetworkChangeReceiver.ConnectivityReceiverListener{
    NetworkChangeReceiver networkChangeReceiver ;
    IntentFilter intentFilter ;
    LinearLayout hometheater_master;
    RelativeLayout hometheater_o_master;
    private Context context;
    Button btnhometheater;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hometheater);

        btnhometheater=findViewById(R.id.btnhometheater);
        hometheater ontext = hometheater.this;
        networkChangeReceiver = new NetworkChangeReceiver();
        intentFilter = new IntentFilter();
        hometheater_master=findViewById(R.id.hometheater_master);
        hometheater_o_master=findViewById(R.id.hometheater_o_master);
        intentFilter.addAction(ConnectivityManager.CONNECTIVITY_ACTION);

        btnhometheater.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(hometheater.this,home.class));
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
    public void onNetworkConnectionChanged(boolean isConnected) {
        callInternet(isConnected);
    }

    private void callInternet(boolean isConnected) {
        Log.e("Internet check",String.valueOf(isConnected));
        if(isConnected){
            hometheater_master.setVisibility(View.VISIBLE);
            hometheater_o_master.setVisibility(View.GONE);


        }
        else{
            hometheater_master.setVisibility(View.GONE);
            hometheater_o_master.setVisibility(View.VISIBLE);

        }
    }
}