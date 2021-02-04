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


public class desktopfrag extends Fragment {
    EditText pcname,pcappname,pcservicet,pcbudget;
    Switch pccheckdesign;
    Button pcplace;
    FirebaseAuth fAuth;

    FirebaseFirestore fStore;
    String userid;
    String pcdesign="no";
ProgressBar progressBar;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =inflater.inflate(R.layout.fragment_desktopfrag, container, false);

        pcname=view.findViewById(R.id.deskapp);
        pcappname=view.findViewById(R.id.deskname);
        pcservicet=view.findViewById(R.id.deskserve);
        pcbudget=view.findViewById(R.id.deskbudget);
        progressBar=view.findViewById(R.id.fragdeskbar);
        fAuth = FirebaseAuth.getInstance();
        userid = fAuth.getCurrentUser().getUid();
        fStore = FirebaseFirestore.getInstance();
        pccheckdesign=view.findViewById(R.id.switch1rd);
        pccheckdesign.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(pccheckdesign.isChecked()){
                    pcdesign="HAS";
                }else{
                    pcdesign ="No";
                }
            }
        });

        pcplace=view.findViewById(R.id.desksubmitbtn);
        pcplace.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!TextUtils.isEmpty(pcname.getText().toString())){
                    if(!TextUtils.isEmpty(pcappname.getText().toString())){
                        if(!TextUtils.isEmpty(pcservicet.getText().toString())){
                            if(!TextUtils.isEmpty(pcbudget.getText().toString())){
                                String namepc=pcname.getText().toString();
                                String apppc=pcappname.getText().toString();
                                String serv=pcservicet.getText().toString();
                                String pcbud=pcbudget.getText().toString();
                                String id=userid;
                                progressBar.setVisibility(View.VISIBLE);

                                Map<String,Object> pcf= new HashMap<>();
                                pcf.put("name",namepc);
                                pcf.put("domain",apppc);
                                pcf.put("description",serv);
                                pcf.put("budget",pcbud);
                                fStore.collection("PC Design").document(id).set(pcf).addOnSuccessListener(new OnSuccessListener<Void>() {
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


                            }
                            else {
                                pcbudget.setError("field is reuired");
                            }
                        }
                        else {
                            pcservicet.setError("field is reuired");
                        }
                    }else {
                        pcappname.setError("field is reuired");
                    }
                }else {
                    pcname.setError("field is reuired");
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