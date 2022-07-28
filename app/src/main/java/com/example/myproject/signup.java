package com.example.myproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class signup extends AppCompatActivity {
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
}