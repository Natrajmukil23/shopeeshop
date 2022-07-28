package com.example.myproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;


import com.example.myproject.Utils.NetworkChangeReceiver;



public class splash extends AppCompatActivity implements NetworkChangeReceiver.ConnectivityReceiverListener{

    NetworkChangeReceiver networkChangeReceiver ;
    IntentFilter intentFilter ;
    LinearLayout smaster;
    RelativeLayout omaster;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);
        context=splash.this;
        networkChangeReceiver = new NetworkChangeReceiver();
        intentFilter = new IntentFilter();
        intentFilter.addAction(ConnectivityManager.CONNECTIVITY_ACTION);
        omaster=findViewById(R.id.omaster);
        smaster=findViewById(R.id.smaster);



    }

    @Override
    protected void onResume() {
        super.onResume();

        registerReceiver(networkChangeReceiver, intentFilter);
        networkChangeReceiver.setConnectivityReceiverListener(this);
        boolean isConnected = NetworkChangeReceiver.isConnected(this);

    }

    @Override
    public void onNetworkConnectionChanged(boolean isConnected) {

        callInternet(isConnected);

    }

    private void callInternet(boolean isConnected) {


        Log.e("internet check",String.valueOf(isConnected));
        if (isConnected){
            omaster.setVisibility(View.GONE);
            smaster.setVisibility(View.VISIBLE);

            new Handler().postDelayed(new Runnable() {



                @Override

                public void run() {

                    Intent i = new Intent(splash.this,welcome.class);

                    startActivity(i);



                    finish();

                }

            }, 3000);


        }else{
            smaster.setVisibility(View.GONE);
            omaster.setVisibility(View.VISIBLE);


        }
    }

}
