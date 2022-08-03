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

public class mobiles extends AppCompatActivity implements NetworkChangeReceiver.ConnectivityReceiverListener {

    NetworkChangeReceiver networkChangeReceiver ;
    IntentFilter intentFilter ;
    LinearLayout m_master;
    RelativeLayout mo_master;
    private Context context;
    Button btnmobile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mobiles);

        btnmobile=findViewById(R.id.btnmobile);
        mobiles ontext = mobiles.this;
        networkChangeReceiver = new NetworkChangeReceiver();
        intentFilter = new IntentFilter();
        m_master=findViewById(R.id.m_master);
        mo_master=findViewById(R.id.mo_master);
        intentFilter.addAction(ConnectivityManager.CONNECTIVITY_ACTION);

        btnmobile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(mobiles.this,home.class));
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
            m_master.setVisibility(View.VISIBLE);
            mo_master.setVisibility(View.GONE);


        }
        else{
            m_master.setVisibility(View.GONE);
            mo_master.setVisibility(View.VISIBLE);

        }
    }
}