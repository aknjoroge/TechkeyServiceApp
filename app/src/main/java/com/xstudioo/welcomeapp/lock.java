package com.xstudioo.welcomeapp;

import android.app.AlertDialog;

import android.app.KeyguardManager;
import android.content.Context;
import android.content.DialogInterface;



import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;


import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import static androidx.core.content.ContextCompat.getSystemService;

public class lock extends AppCompatActivity {
    LuncherManager luncherManager;
    private static int CODE_AUTHENTICATION_VERIFICATION=241;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        luncherManager = new LuncherManager(this);
        callpassword();

    }

    private void callpassword() {
        KeyguardManager km = (KeyguardManager)getSystemService(KEYGUARD_SERVICE);
        if(km.isKeyguardSecure()) {
            Intent i = km.createConfirmDeviceCredentialIntent("Authentication required", "password");
            startActivityForResult(i, CODE_AUTHENTICATION_VERIFICATION);
        }
        else{
            Toast.makeText(lock.this, "No any security setup done by user(pattern or password or pin or fingerprint", Toast.LENGTH_SHORT).show();
            //alert to continue without password

            AlertDialog dialog = new AlertDialog.Builder(this,R.style.AlertDialogStyle)
                    .setTitle("No Security Found")
                    .setMessage("Continue?")
                    .setPositiveButton("YES", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            startActivity(new Intent(getApplicationContext(),MainActivity.class));


                        }
                    })
                    .setNegativeButton("NO", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Toast.makeText(lock.this, "App wont Launch,Restart and click Yes ", Toast.LENGTH_SHORT).show();
                        }
                    })
                    .show();


        }

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode==RESULT_OK && requestCode==CODE_AUTHENTICATION_VERIFICATION)
        {
            if(luncherManager.isFirstTime()){
                luncherManager.setFirstLunch(false);
                startActivity(new Intent(getApplicationContext(),Slider.class));
            }else {
                startActivity(new Intent(getApplicationContext(),MainActivity.class));
            }

        }
        else
        {
            Toast.makeText(this, "Failure: Unable to verify user's identity", Toast.LENGTH_SHORT).show();
        }
    }
}