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

public class signup extends AppCompatActivity implements NetworkChangeReceiver.ConnectivityReceiverListener {
    NetworkChangeReceiver networkChangeReceiver ;
    IntentFilter intentFilter ;
    LinearLayout s_master;
    RelativeLayout so_master;
    private Context context;
    Button btnsignin;
    TextView login3;
    TextView name;
    TextView section;
    TextView mailid;
    TextView password2;
    TextView cnfrmpassword;
    private Object v;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup);

        btnsignin=findViewById(R.id.btnsignin);
        login3=findViewById(R.id.login3);
        name=findViewById(R.id.name);
        section=findViewById(R.id.section);
        mailid=findViewById(R.id.mailid);
        password2=findViewById(R.id.password2);
        cnfrmpassword=findViewById(R.id.cnfrmpassword);
        signup ontext = signup.this;
        networkChangeReceiver = new NetworkChangeReceiver();
        intentFilter = new IntentFilter();
        s_master=findViewById(R.id.s_master);
        so_master=findViewById(R.id.so_master);
        intentFilter.addAction(ConnectivityManager.CONNECTIVITY_ACTION);

        btnsignin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(signup.this,home.class));
            }
        });
        login3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(signup.this,login.class));
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
            s_master.setVisibility(View.VISIBLE);
            so_master.setVisibility(View.GONE);


        }
        else{
            s_master.setVisibility(View.GONE);
            so_master.setVisibility(View.VISIBLE);

        }
    }
}