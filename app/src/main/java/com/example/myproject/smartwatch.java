package com.example.myproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class smartwatch extends AppCompatActivity {
    Button btnsmartwatch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.smartwatch);

        btnsmartwatch=findViewById(R.id.btnsmartwatch);

        btnsmartwatch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(smartwatch.this,home.class));
            }
        });
    }
}