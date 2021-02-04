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
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.ProgressBar;
import android.widget.Switch;
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

public class eventbooking extends AppCompatActivity  implements DatePickerDialog.OnDateSetListener, TimePickerDialog.OnTimeSetListener {
Button times,dates,maps,request;
Switch wedd,pas,parting,bday,other;
ProgressBar progressBar;
    public static final String EXTRA_TEXT = "com.example.application.example.EXTRA_TEXT";
    public static final String EXTRA_TEXTtwo = "com.example.application.example.EXTRA_TEXTTWO";
    public static final String EXTRA_TEXTthree = "com.example.application.example.EXTRA_TEXTTHREE";
Boolean fortime=false,fordate=false;
String serving="";
    FirebaseAuth fAuth;
    FirebaseFirestore fStore;
TextView ftime,fdate;
    String userid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eventbooking);
        times=findViewById(R.id.bktimebtn);
        dates=findViewById(R.id.bkdatebtn);
        maps=findViewById(R.id.bkmapbtn);
        progressBar=findViewById(R.id.ebprogress);
        fStore = FirebaseFirestore.getInstance();
        request=findViewById(R.id.bkrequestbtn);
        fAuth = FirebaseAuth.getInstance();
        userid = fAuth.getCurrentUser().getUid();
wedd=findViewById(R.id.wedoswitch);
pas=findViewById(R.id.paswitch);
parting=findViewById(R.id.partyswitch);
ftime=findViewById(R.id.bktxttime);
fdate=findViewById(R.id.bktxtdate);
bday=findViewById(R.id.bdayswitch);
other=findViewById(R.id.otherswitch);

wedd.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
        if(wedd.isChecked()){
            Toast.makeText(eventbooking.this, "WEDDING SELECTED", Toast.LENGTH_SHORT).show();
            serving="wedding";
        }else {
            Toast.makeText(eventbooking.this, "WEDDING REMOVED", Toast.LENGTH_SHORT).show();
            serving="";
        }

    }
});
        pas.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(pas.isChecked()){
                    Toast.makeText(eventbooking.this, "PUBLIC ADRESS SYSTEM SELECTED", Toast.LENGTH_SHORT).show();
                    serving=serving+" pa_system";
                }else {
                    Toast.makeText(eventbooking.this, "PUBLIC ADRESS SYSTEM REMOVED", Toast.LENGTH_SHORT).show();
                    serving="";
                }

            }
        });
        parting.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(parting.isChecked()){
                    Toast.makeText(eventbooking.this, "PARTY EVENT SELECTED", Toast.LENGTH_SHORT).show();
                    serving=serving+" party";
                }else {
                    Toast.makeText(eventbooking.this, "PARTY EVENT REMOVED", Toast.LENGTH_SHORT).show();
                    serving="";
                }

            }
        });
        bday.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(bday.isChecked()){
                    Toast.makeText(eventbooking.this, "BIRTHDAY EVENT SELECTED", Toast.LENGTH_SHORT).show();
                    serving=serving+" birthday";
                }else {
                    Toast.makeText(eventbooking.this, "BIRTHDAY EVENT REMOVED", Toast.LENGTH_SHORT).show();
                    serving="";
                }

            }
        });
        other.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(other.isChecked()){
                    Toast.makeText(eventbooking.this, "EXTRA CATEGORY", Toast.LENGTH_SHORT).show();
                    serving=serving+" other";

                }else {
                    Toast.makeText(eventbooking.this, "EXTRA CATEGORY REMOVED", Toast.LENGTH_SHORT).show();
                    serving="";
                }
            }
        });


        times.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogFragment timePicker = new timepicker();
                timePicker.show(getSupportFragmentManager(), "time picker");
            }
        });

        dates.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                datepicker datepicker=new datepicker();
                datepicker.show(getSupportFragmentManager(),"DATE PICKER");
            }
        });

        maps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String uri = "http://maps.google.com/maps?q=loc:" +-1.2736590648749186+ "," + 36.8194655985803;
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
                intent.setPackage("com.google.android.apps.maps");
                startActivity(intent);

            }
        });

        request.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressBar.setVisibility(View.VISIBLE);
                handledata();
            }
        });



    }



    public void handledata(){
if(fordate==true && fortime==true){
    String testai=serving;
    String tm=ftime.getText().toString();
    String dm=fdate.getText().toString();
    String id=userid;
    Map<String,Object> eventh =new HashMap<>();
    eventh.put("time",tm);
    eventh.put("date",dm);
    eventh.put("service",testai);

    fStore.collection("Event Service").document(id).set(eventh).addOnSuccessListener(new OnSuccessListener<Void>() {
        @Override
        public void onSuccess(Void aVoid) {
passdatatoreceipt();

        }
    }).addOnFailureListener(new OnFailureListener() {
        @Override
        public void onFailure(@NonNull Exception e) {
            progressBar.setVisibility(View.INVISIBLE);
            Toast.makeText(eventbooking.this, "error updating"+e.toString(), Toast.LENGTH_SHORT).show();

        }
    });




}else if(fortime==false || fordate==false){
    Toast.makeText(this, "PLEASE SET THE TIME AND DATE FOR APPOINTMENT", Toast.LENGTH_SHORT).show();
    progressBar.setVisibility(View.INVISIBLE);
}


    }

    public void passdatatoreceipt(){
        progressBar.setVisibility(View.INVISIBLE);

        String rctm=ftime.getText().toString();
        String rcdm=fdate.getText().toString();
        String act=serving;

        Intent intent = new Intent(this, qrreceipt.class);
        intent.putExtra(EXTRA_TEXTthree, act);
        intent.putExtra(EXTRA_TEXT, rctm);
        intent.putExtra(EXTRA_TEXTtwo, rcdm);

        startActivity(intent);

    }


    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        TextView timetx = findViewById(R.id.bktxttime);
        fortime=true;
        timetx.setText("Hour: " + hourOfDay + " Minute: " + minute);
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR, year);
        c.set(Calendar.MONTH, month);
        c.set(Calendar.DAY_OF_MONTH, dayOfMonth);
        String currentDateString = DateFormat.getDateInstance(DateFormat.FULL).format(c.getTime());
        fordate=true;
        TextView datetx = findViewById(R.id.bktxtdate);
        datetx.setText(currentDateString);
    }

}