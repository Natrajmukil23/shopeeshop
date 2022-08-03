package com.example.myproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.myproject.Utils.NetworkChangeReceiver;

public class home extends AppCompatActivity implements NetworkChangeReceiver.ConnectivityReceiverListener {
    NetworkChangeReceiver networkChangeReceiver;
    IntentFilter intentFilter;
    LinearLayout h_master;
    RelativeLayout ho_master;
    private Context context;
    ImageView logouthome;
    ImageView btaccount;
    TextView mobiles;
    TextView headphones;
    TextView pcs;
    TextView smartwatch;
    TextView speakers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);

        logouthome = findViewById(R.id.logouthome);
        btaccount = findViewById(R.id.btaccount);
        mobiles = findViewById(R.id.mobiles);
        headphones = findViewById(R.id.headphones);
        pcs = findViewById(R.id.pcs);
        smartwatch = findViewById(R.id.smartwatch);
        speakers = findViewById(R.id.speakers);
        context = home.this;
        networkChangeReceiver = new NetworkChangeReceiver();
        intentFilter = new IntentFilter();
        h_master = findViewById(R.id.h_master);
        ho_master = findViewById(R.id.ho_master);
        intentFilter.addAction(ConnectivityManager.CONNECTIVITY_ACTION);

        logouthome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(home.this, login.class));
            }
        });
        btaccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(home.this, user_account.class));
            }
        });
        mobiles.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(home.this, mobiles.class));
            }
        });
        headphones.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(home.this, headphones.class));
            }
        });
        pcs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(home.this, laptops.class));
            }
        });
        smartwatch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(home.this, smartwatch.class));
            }
        });
        speakers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(home.this, hometheater.class));
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
            h_master.setVisibility(View.VISIBLE);
            ho_master.setVisibility(View.GONE);


        }
        else{
            h_master.setVisibility(View.GONE);
            ho_master.setVisibility(View.VISIBLE);

        }
    }
}
