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

public class login extends AppCompatActivity implements NetworkChangeReceiver.ConnectivityReceiverListener {

    NetworkChangeReceiver networkChangeReceiver ;
    IntentFilter intentFilter ;
    LinearLayout l_master;
    RelativeLayout lo_master;
    private Context context;
    Button btnlogin2;
    TextView signup2;
    TextView forgot;
    EditText username;
    EditText edittextpassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        username=findViewById(R.id.username);
        edittextpassword=findViewById(R.id.edittextPassword);
        btnlogin2=findViewById(R.id.btnlogin2);
        signup2=findViewById(R.id.signup2);
        forgot=findViewById(R.id.forgot);
        context=login.this;
        networkChangeReceiver = new NetworkChangeReceiver();
        intentFilter = new IntentFilter();
        l_master=findViewById(R.id.l_master);
        lo_master=findViewById(R.id.lo_master);
        intentFilter.addAction(ConnectivityManager.CONNECTIVITY_ACTION);

        btnlogin2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(login.this,home.class));
            }
        });
        forgot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(login.this,forgot_pass.class));
            }
        });

        signup2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(login.this,signup.class));
            }
        });
                btnlogin2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(username.getText().toString().trim().equalsIgnoreCase("admin") &&
                        edittextpassword.getText().toString().trim().equalsIgnoreCase("admin")){
                    Intent a= new Intent(login.this,home.class);
                    startActivity(a);
                }
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
            l_master.setVisibility(View.VISIBLE);
            lo_master.setVisibility(View.GONE);


        }
        else{
            l_master.setVisibility(View.GONE);
            lo_master.setVisibility(View.VISIBLE);

        }
    }
}