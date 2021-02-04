package com.xstudioo.welcomeapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class eventspage extends AppCompatActivity {
Button event,music,djs;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eventspage);
        event=findViewById(R.id.toeventbtn);
        music=findViewById(R.id.toappoint);
        djs=findViewById(R.id.toappoint2);

        event.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),eventbooking.class));
            }
        });
        music.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),eventappointment.class));
            }
        });

        djs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),eventappointment.class));
            }
        });

    }
}