package com.xstudioo.welcomeapp;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Switch;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;


public class webfragment extends Fragment {
    EditText name,domain,descrpt,budget;
    String design="no";
    Switch checkdesign;
    FirebaseAuth fAuth;
    FirebaseFirestore fStore;
    Button place;
    String userid;
    ProgressBar progressBar;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=  inflater.inflate(R.layout.fragment_webfragment, container, false);

        name=view.findViewById(R.id.webname);
        domain=view.findViewById(R.id.webdomain);
        descrpt=view.findViewById(R.id.webdesc);
        budget=view.findViewById(R.id.webbudget);
        progressBar=view.findViewById(R.id.fragwebbar);
        fAuth = FirebaseAuth.getInstance();
        userid = fAuth.getCurrentUser().getUid();
        fStore = FirebaseFirestore.getInstance();
        checkdesign=view.findViewById(R.id.androidswitchwebs);
        checkdesign.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(checkdesign.isChecked()){
                    design="HAS";
                }else{
                    design ="No";
                }
            }
        });
        place=view.findViewById(R.id.websubmitbtn);
        place.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!TextUtils.isEmpty(name.getText().toString())){
                if(!TextUtils.isEmpty(domain.getText().toString())){
                    if(!TextUtils.isEmpty(descrpt.getText().toString())){
                        if(!TextUtils.isEmpty(budget.getText().toString())){
                            String forname=name.getText().toString();
                            String fordomains=domain.getText().toString();
                            String fordesc=descrpt.getText().toString();
                            String forbudget=budget.getText().toString();
                            progressBar.setVisibility(View.VISIBLE);
                            String id=userid;

                            Map<String,Object> webf= new HashMap<>();
                            webf.put("name",forname);
                            webf.put("domain",fordomains);
                            webf.put("description",fordesc);
                            webf.put("budget",forbudget);

                            fStore.collection("Web Design").document(id).set(webf).addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    confirm();

                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    progressBar.setVisibility(View.INVISIBLE);
                                    Toast.makeText(getActivity(), "error updating"+e.toString(), Toast.LENGTH_SHORT).show();

                                }
                            });



                        }else {
                            budget.setError("field required");
                        }


                    }else {
                        descrpt.setError("field required");
                    }
                }else {
                    domain.setError("field required");
                }



                }else {
                    name.setError("field required");
                }





            }
        });








        return view;
    }

    public void confirm(){
        progressBar.setVisibility(View.INVISIBLE);
        AlertDialog dialog = new AlertDialog.Builder(getActivity(),R.style.AlertDialogStyle)
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
                        Toast.makeText(getActivity(), "In Less Than Three (3) Hours Please Be Patient", Toast.LENGTH_SHORT).show();
                    }
                })
                .show();
        progressBar.setVisibility(View.INVISIBLE);

    }



}