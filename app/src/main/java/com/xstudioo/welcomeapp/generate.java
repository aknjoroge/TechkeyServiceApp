package com.xstudioo.welcomeapp;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.zxing.WriterException;

import androidmads.library.qrgenearator.QRGContents;
import androidmads.library.qrgenearator.QRGEncoder;

public class generate extends AppCompatActivity {
Button gen;
ImageView code;
EditText input;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_generate);
        gen=findViewById(R.id.genbtn);
        code=findViewById(R.id.codeview);
        input=findViewById(R.id.messagefield);


        gen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String data = input.getText().toString();
                if(data.isEmpty()){
                    input.setError("Value required");
                }
                else {

                    QRGEncoder qrgEncoder = new QRGEncoder(data, null, QRGContents.Type.TEXT, 500);
                    qrgEncoder.setColorBlack(Color.RED);
                    qrgEncoder.setColorWhite(Color.WHITE);
                    try {
                        Bitmap bitmap = qrgEncoder.getBitmap();
                        code.setImageBitmap(bitmap);

                    } catch (Exception e) {
                        Toast.makeText(generate.this, "ERROR" + e, Toast.LENGTH_SHORT).show();
                    }

                }
            }
        });


    }
}