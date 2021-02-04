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


public class androidfrag extends Fragment {
    EditText anname,anappname,anservicet,anbudget;
    Switch ancheckdesign;
    Button anplace;
    FirebaseAuth fAuth;
    FirebaseFirestore fStore;
    String userid;
    String andesign="no";
ProgressBar progressBar;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =inflater.inflate(R.layout.fragment_androidfrag, container, false);

        anname=view.findViewById(R.id.andapp);
        anappname=view.findViewById(R.id.andname);
        fAuth = FirebaseAuth.getInstance();
        userid = fAuth.getCurrentUser().getUid();
        fStore = FirebaseFirestore.getInstance();
       anservicet=view.findViewById(R.id.andserve);
        progressBar=view.findViewById(R.id.fragandbar);
        anbudget=view.findViewById(R.id.andbudget);
      ancheckdesign=view.findViewById(R.id.androidswitch);
     ancheckdesign.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(ancheckdesign.isChecked()){
                    andesign="HAS";
                }else{
                    andesign ="No";
                }
            }
        });
        anplace=view.findViewById(R.id.andsubmitbtn);
        anplace.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!TextUtils.isEmpty(anname.getText().toString())){
                    if(!TextUtils.isEmpty(anappname.getText().toString())){
                        if(!TextUtils.isEmpty(anservicet.getText().toString())){
                            if(!TextUtils.isEmpty(anbudget.getText().toString())){
                                String namepc=anname.getText().toString();
                                String apppc=anappname.getText().toString();
                                String serv=anservicet.getText().toString();
                                String pcbud=anbudget.getText().toString();
                                String id=userid;
                                progressBar.setVisibility(View.VISIBLE);

                                Map<String,Object> andf= new HashMap<>();
                                andf.put("name",namepc);
                                andf.put("domain",apppc);
                                andf.put("description",serv);
                                andf.put("budget",pcbud);
                                fStore.collection("Android Design").document(id).set(andf).addOnSuccessListener(new OnSuccessListener<Void>() {
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
                                anbudget.setError("field is reuired");
                            }
                        }
                        else {
                            anservicet.setError("field is reuired");
                        }
                    }else {
                        anappname.setError("field is reuired");
                    }
                }else {
                    anname.setError("field is reuired");
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