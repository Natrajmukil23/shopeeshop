package com.example.myproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class home extends AppCompatActivity {
    ImageView logouthome;
    TextView mobiles;
    TextView headphones;
    TextView pcs;
    TextView smartwatch;
    TextView speakers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);

        logouthome=findViewById(R.id.logouthome);
        mobiles=findViewById(R.id.mobiles);
        headphones=findViewById(R.id.headphones);
        pcs=findViewById(R.id.pcs);
        smartwatch=findViewById(R.id.smartwatch);
        speakers=findViewById(R.id.speakers);

        logouthome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(home.this,login.class));
            }
        });
        mobiles.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(home.this,mobiles.class));
            }
        });
        headphones.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(home.this,headphones.class));
            }
        });
        pcs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(home.this,laptops.class));
            }
        });
        smartwatch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(home.this,smartwatch.class));
            }
        });
        speakers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(home.this,hometheater.class));
            }
        });
    }
}