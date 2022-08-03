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

public class forgot_pass extends AppCompatActivity implements NetworkChangeReceiver.ConnectivityReceiverListener  {

    NetworkChangeReceiver networkChangeReceiver ;
    IntentFilter intentFilter ;
    LinearLayout forgot_master;
    RelativeLayout forgot_o_master;
    private Context context;
    Button for_btsave;
    TextView for_mobileno;
    TextView for_mailid;
    TextView for_password;
    TextView for_cnf_password;
    private Object v;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.forgot_pass);

        for_btsave=findViewById(R.id.for_btsave);
        for_mobileno=findViewById(R.id.for_mobileno);
        for_password=findViewById(R.id.for_password);
        for_mailid=findViewById(R.id.for_mailid);
        for_cnf_password=findViewById(R.id.for_cnf_password);

        forgot_pass ontext = forgot_pass.this;
        networkChangeReceiver = new NetworkChangeReceiver();
        intentFilter = new IntentFilter();
        forgot_master=findViewById(R.id.forgot_master);
        forgot_o_master=findViewById(R.id.forgot_o_master);
        intentFilter.addAction(ConnectivityManager.CONNECTIVITY_ACTION);

        for_btsave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(forgot_pass.this,login.class));
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
            forgot_master.setVisibility(View.VISIBLE);
            forgot_o_master.setVisibility(View.GONE);


        }
        else{
            forgot_master.setVisibility(View.GONE);
            forgot_o_master.setVisibility(View.VISIBLE);

        }
    }
}
