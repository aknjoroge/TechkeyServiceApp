package com.xstudioo.welcomeapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

public class weblanch extends AppCompatActivity {
    weblanchermanager weblanchermanager;
    private static int CODE_AUTHENTICATION_VERIFICATION=241;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        weblanchermanager = new weblanchermanager(this);
        chechslide();

    }

    private void chechslide() {
        if(weblanchermanager.isFirstTime()){
            weblanchermanager.setFirstLunch(false);
            startActivity(new Intent(getApplicationContext(),webslider.class));
        }else {
            startActivity(new Intent(getApplicationContext(),websites.class));
        }
    }


}