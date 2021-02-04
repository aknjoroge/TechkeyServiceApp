package com.xstudioo.welcomeapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class eventappointment extends AppCompatActivity implements DatePickerDialog.OnDateSetListener,TimePickerDialog.OnTimeSetListener {
Button datepic,timepic,apllying,mappic;
TextView fordate,fortime;
    public static final String EXTRA_TEXT = "com.example.application.example.EXTRA_TEXT";
    public static final String EXTRA_TEXTtwo = "com.example.application.example.EXTRA_TEXTTWO";
    FirebaseAuth fAuth;
    ProgressBar progressBar;
    FirebaseFirestore fStore;
    Boolean timeset=false,dateset=false;
    String userid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eventappointment);
        datepic=findViewById(R.id.datebtn);
        timepic=findViewById(R.id.timebtn);
        progressBar=findViewById(R.id.progbks);
        apllying=findViewById(R.id.apllybtn);
        fordate=findViewById(R.id.textdate);
        fAuth = FirebaseAuth.getInstance();
        userid = fAuth.getCurrentUser().getUid();
        fStore = FirebaseFirestore.getInstance();
        fortime=findViewById(R.id.texttime);
        mappic=findViewById(R.id.mapbtn);

        mappic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String uri = "http://maps.google.com/maps?q=loc:" +-1.2736590648749186+ "," + 36.8194655985803;
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
                intent.setPackage("com.google.android.apps.maps");
                startActivity(intent);
            }
        });

        datepic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                datepicker datepicker=new datepicker();
                datepicker.show(getSupportFragmentManager(),"DATE PICKER");
            }
        });

        timepic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogFragment timePicker = new timepicker();
                timePicker.show(getSupportFragmentManager(), "time picker");
            }
        });


        apllying.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressBar.setVisibility(View.VISIBLE);
                applybook();

            }
        });



    }
    public void applybook(){


        if(dateset==true && timeset ==true){

            String dates=fordate.getText().toString();
            String times=fortime.getText().toString();
            String id=userid;
            Map <String,Object> appli =new HashMap<>();
            appli.put("date",dates);
            appli.put("time",times);


            fStore.collection("Boooking Service").document(id).set(appli).addOnSuccessListener(new OnSuccessListener<Void>() {
                @Override
                public void onSuccess(Void aVoid) {
                    passdatatoreceipt();

                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    progressBar.setVisibility(View.INVISIBLE);
                    Toast.makeText(eventappointment.this, "error updating"+e.toString(), Toast.LENGTH_SHORT).show();

                }
            });


        }else if(dateset==false || timeset==false){
            Toast.makeText(this, "PLEASE SET THE TIME AND DATE FIRST", Toast.LENGTH_SHORT).show();
        }




    }


    public void passdatatoreceipt(){
        progressBar.setVisibility(View.INVISIBLE);

        String rctm=fortime.getText().toString();
        String rcdm=fordate.getText().toString();

        Intent intent = new Intent(this, qrreceipttwo.class);
        intent.putExtra(EXTRA_TEXT, rctm);
        intent.putExtra(EXTRA_TEXTtwo, rcdm);

        startActivity(intent);

    }



    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        TextView timetxt = findViewById(R.id.texttime);
        timeset=true;
        timetxt.setText("Hour: " + hourOfDay + " Minute: " + minute);
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR, year);
        c.set(Calendar.MONTH, month);
        c.set(Calendar.DAY_OF_MONTH, dayOfMonth);
        dateset=true;
        String currentDateString = DateFormat.getDateInstance(DateFormat.FULL).format(c.getTime());
        TextView datetxt = findViewById(R.id.textdate);
        datetxt.setText(currentDateString);
    }


}