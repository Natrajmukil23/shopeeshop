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

public class smartwatch extends AppCompatActivity implements NetworkChangeReceiver.ConnectivityReceiverListener{
    NetworkChangeReceiver networkChangeReceiver ;
    IntentFilter intentFilter ;
    LinearLayout watch_master;
    RelativeLayout watch_o_master;
    private Context context;
    Button btnsmartwatch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.smartwatch);

        btnsmartwatch=findViewById(R.id.btnsmartwatch);
        smartwatch ontext = smartwatch.this;
        networkChangeReceiver = new NetworkChangeReceiver();
        intentFilter = new IntentFilter();
        watch_master=findViewById(R.id.watch_master);
        watch_o_master=findViewById(R.id.watch_o_master);
        intentFilter.addAction(ConnectivityManager.CONNECTIVITY_ACTION);

        btnsmartwatch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(smartwatch.this,home.class));
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
            watch_master.setVisibility(View.VISIBLE);
            watch_o_master.setVisibility(View.GONE);


        }
        else{
            watch_master.setVisibility(View.GONE);
            watch_o_master.setVisibility(View.VISIBLE);

        }
    }
}