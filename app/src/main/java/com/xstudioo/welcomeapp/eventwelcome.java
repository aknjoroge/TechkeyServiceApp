package com.xstudioo.welcomeapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.KeyguardManager;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

public class eventwelcome extends AppCompatActivity {
    lanchermanager lanchermanager;
    private static int CODE_AUTHENTICATION_VERIFICATION=241;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        lanchermanager = new lanchermanager(this);
       chechslide();

    }

    private void chechslide() {
        if(lanchermanager.isFirstTime()){
            lanchermanager.setFirstLunch(false);
            startActivity(new Intent(getApplicationContext(),Events.class));
        }else {
            startActivity(new Intent(getApplicationContext(),eventspage.class));
        }
    }


}