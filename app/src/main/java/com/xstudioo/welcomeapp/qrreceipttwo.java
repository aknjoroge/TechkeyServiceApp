package com.xstudioo.welcomeapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import androidmads.library.qrgenearator.QRGContents;
import androidmads.library.qrgenearator.QRGEncoder;

public class qrreceipttwo extends AppCompatActivity {
    ImageView rc;
    FirebaseAuth fAuth;
    String storing;
    FirebaseFirestore fStore;
    String newRandomId;
    String userid;
    String time;
    String date;

    String receipt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qrreceipttwo);
        rc=findViewById(R.id.qrrcimgt);
        newRandomId = String.valueOf(Calendar.getInstance().getTimeInMillis());
        fStore = FirebaseFirestore.getInstance();
        fAuth = FirebaseAuth.getInstance();
        userid = fAuth.getCurrentUser().getUid();

        Intent intent = getIntent();
        time = intent.getStringExtra(eventappointment.EXTRA_TEXT);
        date = intent.getStringExtra(eventappointment.EXTRA_TEXTtwo);
        Toast.makeText(this, "Wait For Processing", Toast.LENGTH_SHORT).show();

        receipt=newRandomId+"TECHKEYautoAi_ai1";
        String tms=time,dms=date,rcp=receipt;
        storing=tms+dms+rcp;

        Map<String,Object> qrct =new HashMap<>();
        qrct.put("time",time);
        qrct.put("date",date);
        qrct.put("storing",storing);
        qrct.put("receipt",receipt);


        fStore.collection("RECEIPTS_APPOINTS").document(userid).set(qrct).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                QRGEncoder qrgEncoder = new QRGEncoder( receipt, null, QRGContents.Type.TEXT, 500);
                qrgEncoder.setColorBlack(Color.RED);
                qrgEncoder.setColorWhite(Color.WHITE);
                try {
                    Bitmap bitmap = qrgEncoder.getBitmap();
                    rc.setImageBitmap(bitmap);

                } catch (Exception e) {
                    Toast.makeText(qrreceipttwo.this, "ERROR" + e, Toast.LENGTH_SHORT).show();
                }

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(qrreceipttwo.this, "error updating"+e.toString(), Toast.LENGTH_SHORT).show();

            }
        });



    }
}