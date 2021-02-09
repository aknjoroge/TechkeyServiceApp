package com.xstudioo.welcomeapp;

import android.content.Intent;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;
import android.widget.Toolbar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.SetOptions;

import java.util.HashMap;
import java.util.Map;

public class register extends AppCompatActivity {
    private static final String TAG =null ;
    Button signin,signup;
    EditText name,phone,email,pass;
    String takename,takephone,takemail,takepass;
    ProgressBar progressBar;
    String userid;
    private FirebaseAuth fAuth;
    FirebaseFirestore fStore;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        fAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();
        name=findViewById(R.id.regname);
        phone=findViewById(R.id.regphone);
        email=findViewById(R.id.regmail);
        pass=findViewById(R.id.regpass);
        progressBar=findViewById(R.id.progressBar);
        takename=name.getText().toString();
        takephone=phone.getText().toString();
        takemail=email.getText().toString();
        takepass=pass.getText().toString();
        signup=findViewById(R.id.signupbtn);

        signin =findViewById(R.id.tosignin);


        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),MainActivity.class));
            }
        });


        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                takename=name.getText().toString();
                takephone=phone.getText().toString();
                if(TextUtils.isEmpty(takemail=email.getText().toString())){
                    email.setError("Email is required");
                    return;
                }
                if(TextUtils.isEmpty( takepass=pass.getText().toString())){
                    pass.setError("password required");
                    return;
                }
                takepass=pass.getText().toString();
                if(takepass.length()< 6 ){
                    pass.setError("password need to be above 6 characters");
                }
                progressBar.setVisibility(View.VISIBLE);
                fAuth.createUserWithEmailAndPassword(takemail,takepass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){

                            FirebaseUser fuser =fAuth.getCurrentUser();
                            fuser.sendEmailVerification().addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    Toast.makeText(register.this, "Verification Email Sent ", Toast.LENGTH_SHORT).show();

                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Toast.makeText(register.this, "Error sending verification , Contact Admin", Toast.LENGTH_SHORT).show();
                                }
                            });


                            Toast.makeText(register.this, "Successfully Registered", Toast.LENGTH_SHORT).show();
                            userid= fAuth.getCurrentUser().getUid();
                            DocumentReference documentReference = fStore.collection("users").document(userid);

                            Map<String, Object> user = new HashMap<>();
                            user.put("name", takename);
                            user.put("phone", takephone);
                            user.put("mail", takemail);

                            documentReference.set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void avoid) {
                                    //regforbudget();
                                    createbank();
                                    Log.d(TAG, "user profile created " + userid);
                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Log.d(TAG ,"on failure: "+e.toString());
                                }
                            });
                            progressBar.setVisibility(View.INVISIBLE);
                            startActivity(new Intent(getApplicationContext(),mainpage.class));
                        }
                        else {
                            Toast.makeText(register.this, "An error occured" +task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                            progressBar.setVisibility(View.INVISIBLE);
                        }
                    }
                });


            }
        });




    }
    private void createbank() {

        Map<String,Object> userbank =new HashMap<>();
        userbank.put("income","0");
        userbank.put("expense","0");
        final DocumentReference documentReference = fStore.collection("BudgetCart").document("Foruser").collection(userid).document("bank");

        documentReference.set(userbank, SetOptions.merge());



    }

    private void regforbudget() {




    }
}