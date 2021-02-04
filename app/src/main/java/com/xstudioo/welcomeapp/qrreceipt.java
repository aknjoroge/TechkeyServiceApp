package com.xstudioo.welcomeapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Calendar;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import androidmads.library.qrgenearator.QRGContents;
import androidmads.library.qrgenearator.QRGEncoder;

public class qrreceipt extends AppCompatActivity {
ImageView rc;
    FirebaseAuth fAuth;
    String storing;
    FirebaseFirestore fStore;
    String newRandomId;
    String userid;
    String time;
    String date;
    String actss;
    String receipt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qrreceipt);
        rc=findViewById(R.id.qrrcimg);
         newRandomId = String.valueOf(Calendar.getInstance().getTimeInMillis());
        fStore = FirebaseFirestore.getInstance();
        fAuth = FirebaseAuth.getInstance();
        userid = fAuth.getCurrentUser().getUid();
        Intent intent = getIntent();
        time = intent.getStringExtra(eventbooking.EXTRA_TEXT);

         date = intent.getStringExtra(eventbooking.EXTRA_TEXTtwo);
       actss = intent.getStringExtra(eventbooking.EXTRA_TEXTthree);

        Toast.makeText(this, "Wait For Processing", Toast.LENGTH_SHORT).show();

        receipt=newRandomId+"TECHKEYautoAi_ai1";
        String tms=time,dms=date,rcp=receipt,tps=actss;

        storing=tms+dms+rcp+tps;

        Map<String,Object> qrct =new HashMap<>();
        qrct.put("time",time);
        qrct.put("date",date);
        qrct.put("storing",storing);
        qrct.put("receipt",receipt);


        fStore.collection("RECEIPTS_BOOKING").document(userid).set(qrct).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                QRGEncoder qrgEncoder = new QRGEncoder( receipt, null, QRGContents.Type.TEXT, 500);
                qrgEncoder.setColorBlack(Color.RED);
                qrgEncoder.setColorWhite(Color.WHITE);
                try {
                    Bitmap bitmap = qrgEncoder.getBitmap();
                    rc.setImageBitmap(bitmap);

                  //  savetogl();

                } catch (Exception e) {
                    Toast.makeText(qrreceipt.this, "ERROR" + e, Toast.LENGTH_SHORT).show();
                }

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(qrreceipt.this, "error updating"+e.toString(), Toast.LENGTH_SHORT).show();

            }
        });







    }
    public void savetogl() throws IOException {

        Date now = new Date();
        android.text.format.DateFormat.format("yyyy-MM-dd_hh:mm:ss", now);

        try {
            // image naming and path  to include sd card  appending name you choose for file
            String mPath = Environment.getExternalStorageDirectory().toString() + "/" + now + ".jpg";

            // create bitmap screen capture
            View v1 = getWindow().getDecorView().getRootView();
            v1.setDrawingCacheEnabled(true);
            Bitmap bitmap = Bitmap.createBitmap(v1.getDrawingCache());
            v1.setDrawingCacheEnabled(false);

            File imageFile = new File(mPath);

            FileOutputStream outputStream = new FileOutputStream(imageFile);
            int quality = 100;
            bitmap.compress(Bitmap.CompressFormat.JPEG, quality, outputStream);
            outputStream.flush();
            outputStream.close();

        } catch (Throwable e) {
            Toast.makeText(this, e.toString(), Toast.LENGTH_SHORT).show();
            // Several error may come out with file handling or DOM
            e.printStackTrace();
        }





        /*
        BitmapDrawable draw = (BitmapDrawable) rc.getDrawable();
        Bitmap bitmap = draw.getBitmap();

        FileOutputStream outStream = null;
        File sdCard = Environment.getExternalStorageDirectory();
        File dir = new File(sdCard.getAbsolutePath() + "/TechKey Receipt");
        dir.mkdirs();
        String fileName = String.format("%d.jpg", System.currentTimeMillis());
        File outFile = new File(dir, fileName);
        outStream = new FileOutputStream(outFile);
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outStream);
        outStream.flush();
        outStream.close();*/

    }

}