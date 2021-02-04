package com.xstudioo.welcomeapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Switch;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.HashMap;
import java.util.Map;

public class logocreation extends AppCompatActivity {
    EditText name,moto,type,desc;
    Switch logo;
    ProgressBar progressBar;
    public static final String EXTRA_TEXT = "com.example.application.example.LOGOEXTRA_TEXT";
    public static final String EXTRA_TEXTtwo = "com.example.application.example.LOGOEXTRA_TEXTTWO";
    Uri paths;
    int orderno=1;
    FirebaseAuth fAuth;
    FirebaseFirestore fStore;
    String userid;
    StorageReference storageReference;
    Boolean haslogo=false;
    Boolean picset=false;
    ImageView oflogo;
    Button logoupload,logosubmit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logocreation);
        name=findViewById(R.id.compnameedit);
        moto=findViewById(R.id.compmotoedit);
        type=findViewById(R.id.bstypeedit);
        desc=findViewById(R.id.logodescedit);
        progressBar=findViewById(R.id.logobar);
        logo=findViewById(R.id.logouploadswitch);
        logoupload=findViewById(R.id.logouploadbtn);
        logosubmit=findViewById(R.id.logosubmitbtn);
        oflogo=findViewById(R.id.logoimgview);
        fAuth = FirebaseAuth.getInstance();
        userid = fAuth.getCurrentUser().getUid();
        storageReference = FirebaseStorage.getInstance().getReference();
        fStore = FirebaseFirestore.getInstance();

        logo.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(logo.isChecked()){
                    haslogo=true;


                }else {
                    haslogo=false;
                }
            }
        });

        logoupload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(haslogo==true){
                    Intent intent = new Intent(Intent.ACTION_PICK);
                    intent.setType("image/*");
                    startActivityForResult(intent, 1);

                }else if(haslogo==false){
                    Toast.makeText(logocreation.this, "HAS LOGO SWITCH IS NOT CHECKED", Toast.LENGTH_SHORT).show();
                }
            }
        });

        logosubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!TextUtils.isEmpty(name.getText().toString())){
                    if(!TextUtils.isEmpty(moto.getText().toString())){
                        if(!TextUtils.isEmpty(type.getText().toString())){
                            if(!TextUtils.isEmpty(desc.getText().toString())){
                                if(haslogo==true){
                                    if(picset ==true){
                                        progressBar.setVisibility(View.VISIBLE);
                                        String id=userid;

                                        String forname=name.getText().toString();
                                        String fordesc=desc.getText().toString();
                                        String fortype=type.getText().toString();
                                        String formoto=moto.getText().toString();

                                        Map<String,Object> logos =new HashMap<>();
                                        logos.put("Name",forname);
                                        logos.put("Moto",formoto);
                                        logos.put("description",fordesc);
                                        logos.put("type",fortype);

                                        fStore.collection("LOGO CREATION").document(id).set(logos).addOnSuccessListener(new OnSuccessListener<Void>() {
                                            @Override
                                            public void onSuccess(Void aVoid) {
                                               confirmtwo(paths);

                                            }
                                        }).addOnFailureListener(new OnFailureListener() {
                                            @Override
                                            public void onFailure(@NonNull Exception e) {
                                                progressBar.setVisibility(View.INVISIBLE);
                                                Toast.makeText(logocreation.this, "error updating"+e.toString(), Toast.LENGTH_SHORT).show();

                                            }
                                        });


                                        Toast.makeText(logocreation.this, "moto is "+formoto+"desc is "+fordesc+"type is "+fortype+"name is"+forname, Toast.LENGTH_SHORT).show();
                                    }else if(picset==false){
                                        Toast.makeText(logocreation.this, "SELECT IMAGE TO UPLOAD", Toast.LENGTH_SHORT).show();
                                    }

                                }else if(haslogo==false){

                                  progressBar.setVisibility(View.VISIBLE);
                                    String id=userid;

                                    String forname=name.getText().toString();
                                    String fordesc=desc.getText().toString();
                                    String fortype=type.getText().toString();
                                    String formoto=moto.getText().toString();

                                    Map<String,Object> logos =new HashMap<>();
                                    logos.put("Name",forname);
                                    logos.put("Moto",formoto);
                                    logos.put("description",fordesc);
                                    logos.put("type",fortype);

                                    fStore.collection("LOGOCREATION").document(id).set(logos).addOnSuccessListener(new OnSuccessListener<Void>() {
                                        @Override
                                        public void onSuccess(Void aVoid) {
                                            confirm();

                                        }
                                    }).addOnFailureListener(new OnFailureListener() {
                                        @Override
                                        public void onFailure(@NonNull Exception e) {
                                            progressBar.setVisibility(View.INVISIBLE);
                                            Toast.makeText(logocreation.this, "error updating"+e.toString(), Toast.LENGTH_SHORT).show();

                                        }
                                    });

                                }


                            }
                            else {
                                desc.setError("Field is Required");
                            }
                        }
                        else {
                            type.setError("Field is Required");
                        }

                    }else {
                        moto.setError("Field is Required");
                    }

                }
                else {
                    name.setError("Field is Required");
                }



            }
        });

    }
    //end of oncreate

public void confirm(){
        progressBar.setVisibility(View.INVISIBLE);
    AlertDialog dialog = new AlertDialog.Builder(this,R.style.AlertDialogStyle)
            .setTitle("YOUR ORDER HAS BEEN RECIVED")
            .setMessage("TechKey Will Contact You Soon")
            .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                }
            })
            .setNegativeButton("WHEN?", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Toast.makeText(logocreation.this, "In Less Than Three (3) Hours Please Be Patient", Toast.LENGTH_SHORT).show();
                }
            })
            .show();


}
public void confirmtwo(Uri imageuri){
    orderno=orderno+1;
    final StorageReference fileref = storageReference.child("LOGOS/projects/"+fAuth.getCurrentUser().getUid()+"/"+orderno+"/pic.jpg");
    fileref.putFile(imageuri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
        @Override
        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
            progressBar.setVisibility(View.INVISIBLE);
            cong();

        }
    }).addOnFailureListener(new OnFailureListener() {
        @Override
        public void onFailure(@NonNull Exception e) {
            Toast.makeText(logocreation.this, "UPLOAD ERROOR", Toast.LENGTH_SHORT).show();
        }
    });


}
    public void cong(){
        progressBar.setVisibility(View.INVISIBLE);
        AlertDialog dialog = new AlertDialog.Builder(this,R.style.AlertDialogStyle)
                .setTitle("YOUR ORDER HAS BEEN RECIVED")
                .setMessage("TechKey Will Contact You Soon")
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .setNegativeButton("WHEN?", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(logocreation.this, "In Less Than Three (3) Hours Please Be Patient", Toast.LENGTH_SHORT).show();
                    }
                })
                .show();


    }





    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1 && resultCode == RESULT_OK) {
            try {
                Uri imageuri = data.getData();
                oflogo.setImageURI(imageuri);
                picset=true;
                paths=imageuri;

            }catch (Exception e){
                Toast.makeText(this, "Run time exception gained", Toast.LENGTH_SHORT).show();
            }

        }else {
            picset=false;
            Toast.makeText(this, "No image selected", Toast.LENGTH_SHORT).show();
        }

    }
}