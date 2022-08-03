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
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.myproject.Utils.NetworkChangeReceiver;

public class user_account extends AppCompatActivity implements NetworkChangeReceiver.ConnectivityReceiverListener{

    NetworkChangeReceiver networkChangeReceiver ;
    IntentFilter intentFilter ;
    LinearLayout acount_master;
    RelativeLayout account_o_master;
    private Context context;
    EditText acc_name;
    EditText acc_phoneno;
    EditText acc_mailid;
    EditText acc_password;
    EditText acc_cnfrm_password;
    Button btncreate;
    private Object v;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_account);

        acc_name=findViewById(R.id.acc_name);
        acc_phoneno=findViewById(R.id.acc_phoneno);
        acc_mailid=findViewById(R.id.acc_mailid);
        acc_password=findViewById(R.id.acc_password);
        acc_cnfrm_password=findViewById(R.id.acc_cnfrm_password);
        btncreate=findViewById(R.id.btncreate);

        networkChangeReceiver = new NetworkChangeReceiver();
        intentFilter = new IntentFilter();
        acount_master=findViewById(R.id.acount_master);
        account_o_master=findViewById(R.id.account_o_master);
        intentFilter.addAction(ConnectivityManager.CONNECTIVITY_ACTION);

        btncreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(user_account.this,home.class));
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
            acount_master.setVisibility(View.VISIBLE);
            account_o_master.setVisibility(View.GONE);


        }
        else{
            acount_master.setVisibility(View.GONE);
            account_o_master.setVisibility(View.VISIBLE);

        }
    }

}