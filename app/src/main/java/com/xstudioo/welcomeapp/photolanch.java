package com.xstudioo.welcomeapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

public class photolanch extends AppCompatActivity {
    piclanchermanager piclanchermanager;
    private static int CODE_AUTHENTICATION_VERIFICATION=241;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       piclanchermanager = new piclanchermanager(this);
        chechslide();

    }

    private void chechslide() {
        if(piclanchermanager.isFirstTime()){
            piclanchermanager.setFirstLunch(false);
            startActivity(new Intent(getApplicationContext(),photoshopwelcome.class));
        }else {
            startActivity(new Intent(getApplicationContext(),Photochoose.class));
        }
    }


}