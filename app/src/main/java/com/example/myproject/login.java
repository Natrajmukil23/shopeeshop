package com.example.myproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class login extends AppCompatActivity {
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

        btnlogin2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(login.this,home.class));
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
}