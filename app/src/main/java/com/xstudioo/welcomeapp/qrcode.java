package com.xstudioo.welcomeapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class qrcode extends AppCompatActivity {
Button one,two;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qrcode);
 one=findViewById(R.id.scanbtn);
 two=findViewById(R.id.genbtn);

 one.setOnClickListener(new View.OnClickListener() {
     @Override
     public void onClick(View view) {
         startActivity(new Intent(getApplicationContext(),scanqr.class));
     }
 });
        two.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),generate.class));
            }
        });

    }
}