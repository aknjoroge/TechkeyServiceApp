package com.xstudioo.welcomeapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.budiyev.android.codescanner.CodeScanner;
import com.budiyev.android.codescanner.CodeScannerView;
import com.budiyev.android.codescanner.DecodeCallback;
import com.google.zxing.Result;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;

public class scanqr extends AppCompatActivity {
    private CodeScanner mCodeScanner;
    TextView scan;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scanqr);
scan=findViewById(R.id.scanmsg);
        CodeScannerView scannerView = findViewById(R.id.scanner_view);
        mCodeScanner = new CodeScanner(this, scannerView);
        mCodeScanner.setDecodeCallback(new DecodeCallback() {
            @Override
            public void onDecoded(@NonNull final Result result) {
                scanqr.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                     scan.setText(result.getText());

                    }
                });
            }
        });
        scannerView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mCodeScanner.startPreview();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        requestperm();

    }
public void requestperm(){
    Dexter.withActivity(this).withPermission(Manifest.permission.CAMERA).withListener(new PermissionListener() {
        @Override
        public void onPermissionGranted(PermissionGrantedResponse permissionGrantedResponse) {
            mCodeScanner.startPreview();
        }

        @Override
        public void onPermissionDenied(PermissionDeniedResponse permissionDeniedResponse) {
            Toast.makeText(scanqr.this, "Permission Required", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onPermissionRationaleShouldBeShown(PermissionRequest permissionRequest, PermissionToken permissionToken) {
            permissionToken.continuePermissionRequest();
        }
    }).check();

}
    @Override
    protected void onPause() {
        mCodeScanner.releaseResources();
        super.onPause();
    }


    }