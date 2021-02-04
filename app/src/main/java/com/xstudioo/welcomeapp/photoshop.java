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
import android.util.Log;
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
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import java.util.HashMap;
import java.util.Map;

public class photoshop extends AppCompatActivity {
    private static final String TAG = "";
    Button ordering,selecting;
EditText info;
int orderno=1;
ImageView sub;
    StorageReference storageReference;
    FirebaseAuth fAuth;
    Uri paths;
    FirebaseFirestore fStore;
    String userid;
String text;
Boolean set=false;
String orderinfo;
String servic ="";
Switch ofai,ofps,ofxd;
ProgressBar psprog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photoshop);

        ordering=findViewById(R.id.orderpsbtn);
    selecting=findViewById(R.id.psselectbtn);
    info=findViewById(R.id.psedittext);
    ofai=findViewById(R.id.aiswitch);
    ofps=findViewById(R.id.psswitch);
    psprog=findViewById(R.id.psprogresbar);
    ofxd=findViewById(R.id.xdswitch);
sub=findViewById(R.id.photoshopimg);
        storageReference = FirebaseStorage.getInstance().getReference();
        fAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();
        userid = fAuth.getCurrentUser().getUid();

  ofai.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
      @Override
      public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
          if(ofai.isChecked()){
              Toast.makeText(photoshop.this, "adobe illustrator selected", Toast.LENGTH_SHORT).show();
              servic=servic+"Adobe Illustrator";
          }else{
              Toast.makeText(photoshop.this, "ai deselceted", Toast.LENGTH_SHORT).show();
              servic= " ";
          }
      }
  });

        ofps.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(ofps.isChecked()){
                    Toast.makeText(photoshop.this, "adobe Photoshop selected", Toast.LENGTH_SHORT).show();
                    servic=servic+"Adobe Photoshop";
                }else{
                    Toast.makeText(photoshop.this, "ps deselceted", Toast.LENGTH_SHORT).show();
                    servic= " ";
                }
            }
        });

        ofxd.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(ofxd.isChecked()){
                    Toast.makeText(photoshop.this, "adobe XD selected", Toast.LENGTH_SHORT).show();
                    servic=servic+"Adobe XD";
                }else{
                    Toast.makeText(photoshop.this, "XD deselceted", Toast.LENGTH_SHORT).show();
                    servic= " ";
                }
            }
        });

selecting.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent, 1);
    }
});

ordering.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {

        uploadtocloud();
    }
});


    }
    public void uploadtocloud(){

        if(set==true){
            String id=userid;
           String takeinfo1=info.getText().toString();
            String taketype1=servic;

            Map<String,Object> note =new HashMap<>();
            note.put("info",takeinfo1);
            note.put("type",taketype1);

            fStore.collection("servicetype").document(id).set(note).addOnSuccessListener(new OnSuccessListener<Void>() {
                @Override
                public void onSuccess(Void aVoid) {
                    psprog.setVisibility(View.VISIBLE);
                    uploadimage(paths);
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(photoshop.this, "error updating"+e.toString(), Toast.LENGTH_SHORT).show();
                    psprog.setVisibility(View.INVISIBLE);
                }
            });


            Toast.makeText(this, "Image Will be uploaded", Toast.LENGTH_SHORT).show();

        }else if(set==false){

            Toast.makeText(this, "SELECT IMAGE FIRST AND ADD PROJECT DESCRIPTION", Toast.LENGTH_SHORT).show();
        }

    }


    public void uploadimage(Uri imageuri) {

        orderno=orderno+1;
        final StorageReference fileref = storageReference.child("Orders/projects/"+fAuth.getCurrentUser().getUid()+"/"+orderno+"/pic.jpg");
        fileref.putFile(imageuri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                psprog.setVisibility(View.INVISIBLE);
                cong();

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(photoshop.this, "UPLOAD ERROOR", Toast.LENGTH_SHORT).show();
            }
        });

    }

public void cong(){
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
                    Toast.makeText(photoshop.this, "In Less Than Three (3) Hours Be Patient", Toast.LENGTH_SHORT).show();
                }
            })
            .show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1 && resultCode == RESULT_OK) {
            try {
                Toast.makeText(this, "Image Selected", Toast.LENGTH_SHORT).show();
                set=true;
                Toast.makeText(this, "Add Project Description", Toast.LENGTH_SHORT).show();
                Uri imageuri = data.getData();
              sub.setImageURI(imageuri);
              paths=imageuri;


            }catch (Exception e){

                Toast.makeText(this, "Run time exception gained", Toast.LENGTH_SHORT).show();
            }

        }else {
            set=false;
            Toast.makeText(this, "No image selected", Toast.LENGTH_SHORT).show();
        }

    }



}