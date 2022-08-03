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

public class headphones extends AppCompatActivity implements NetworkChangeReceiver.ConnectivityReceiverListener {
    NetworkChangeReceiver networkChangeReceiver ;
    IntentFilter intentFilter ;
    LinearLayout headphone_master;
    RelativeLayout headphone_o_master;
    private Context context;
    Button btnheadphone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.headphones);

        btnheadphone=findViewById(R.id.btnheadphone);
        headphones ontext = headphones.this;
        networkChangeReceiver = new NetworkChangeReceiver();
        intentFilter = new IntentFilter();
        headphone_master=findViewById(R.id.headphone_master);
        headphone_o_master=findViewById(R.id.headphone_o_master);
        intentFilter.addAction(ConnectivityManager.CONNECTIVITY_ACTION);

        btnheadphone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(headphones.this,home.class));
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
            headphone_master.setVisibility(View.VISIBLE);
            headphone_o_master.setVisibility(View.GONE);


        }
        else{
            headphone_master.setVisibility(View.GONE);
            headphone_o_master.setVisibility(View.VISIBLE);

        }
    }
}
