package com.xstudioo.welcomeapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class accounts extends AppCompatActivity {
 Button pass,emails,names,dells;
    FirebaseAuth fAuth;
    Button verifingbtn ;
    TextView verify;
    StorageReference storageReference;
    FirebaseUser using;
    FirebaseFirestore fStore;
    TextView setname, setemail, setphone;
    String userid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accounts);

        fAuth = FirebaseAuth.getInstance();

using=fAuth.getCurrentUser();
        setemail = findViewById(R.id.textemail);
        storageReference = FirebaseStorage.getInstance().getReference();
        setname = findViewById(R.id.textname);
        setphone = findViewById(R.id.textphone);
        pass=findViewById(R.id.passbtn);
        emails=findViewById(R.id.emailbtn);
        names=findViewById(R.id.namebtn);
        verifingbtn=findViewById(R.id.verifybtn);
        verify=findViewById(R.id.verifymsg);
        dells=findViewById(R.id.delbtn);
        fStore = FirebaseFirestore.getInstance();
        userid = fAuth.getCurrentUser().getUid();


        final DocumentReference documentReference = fStore.collection("users").document(userid);
        documentReference.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException e) {
                setphone.setText(documentSnapshot.getString("phone"));
                setname.setText(documentSnapshot.getString("name"));
                setemail.setText(documentSnapshot.getString("mail"));
            }
        });


        pass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              changepasswords();
            }
        });
        checkverification();

        emails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeemail();

            }
        });
        names.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(accounts.this, "Contact Administrator to Change Name", Toast.LENGTH_SHORT).show();
            }
        });
        dells.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
dellaccounts();
               // Toast.makeText(accounts.this, " Not Yet Initialized", Toast.LENGTH_SHORT).show();
            }
        });

    }
    //end of on_create
    public void dellaccounts(){

        final EditText dellac= new EditText(this);
        android.app.AlertDialog dialog = new AlertDialog.Builder(this,R.style.AlertDialogStyle)
                .setTitle("DELETE ACCOUNT AND LOOSE DATA!!!")
                .setMessage("Enter The Word 'DELETE' TO CONFIRM")
                .setView(dellac)
                .setPositiveButton("DELETE", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if(!TextUtils.isEmpty(dellac.getText().toString())) {
                            String in=dellac.getText().toString();
                            if(in.equals("DELETE")){
                                using.delete().addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void aVoid) {
                                        Toast.makeText(accounts.this, "YOU HAVE BENN DELETED CLOSING APP", Toast.LENGTH_SHORT).show();
                                    }
                                }).addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        Toast.makeText(accounts.this, "Delete was Not Successfull", Toast.LENGTH_SHORT).show();
                                    }
                                });

                            }else {
                                Toast.makeText(accounts.this, "DELETE not typed Correctly", Toast.LENGTH_SHORT).show();
                            }


                        }
                        else {
                            Toast.makeText(accounts.this, "Field is empty", Toast.LENGTH_SHORT).show();
                        }

                    }
                })
                .setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(accounts.this, "Deleting Cancelled", Toast.LENGTH_SHORT).show();
                    }
                })
                .show();

    }

public void checkverification(){

        userid = fAuth.getCurrentUser().getUid();
final FirebaseUser fusers  = fAuth.getCurrentUser();
if(!fusers.isEmailVerified()){
    verifingbtn.setVisibility(View.VISIBLE);
    verify.setVisibility(View.VISIBLE);

    verifingbtn.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            fusers.sendEmailVerification().addOnSuccessListener(new OnSuccessListener<Void>() {
                @Override
                public void onSuccess(Void aVoid) {
                    Toast.makeText(accounts.this, "Verification Email Sent ", Toast.LENGTH_SHORT).show();

                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(accounts.this, "Error sending verification , Contact Admin", Toast.LENGTH_SHORT).show();
                }
            });
        }
    });

}


}

    public void changepasswords(){

        final EditText resetpassw= new EditText(this);
        android.app.AlertDialog dialog = new AlertDialog.Builder(this,R.style.AlertDialogStyle)
                .setTitle("Reset Password?")
                .setMessage("Enter new Password :char > 6")
                .setView(resetpassw)
                .setPositiveButton("Done", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if(!TextUtils.isEmpty(resetpassw.getText().toString())) {

              String takenew = resetpassw.getText().toString();
            using.updatePassword(takenew).addOnSuccessListener(new OnSuccessListener<Void>() {
             @Override
        public void onSuccess(Void aVoid) {
        Toast.makeText(accounts.this, "Updated successfully", Toast.LENGTH_SHORT).show();
    }
            }).addOnFailureListener(new OnFailureListener() {
     @Override
    public void onFailure(@NonNull Exception e) {
        Toast.makeText(accounts.this, "NOT Updated !", Toast.LENGTH_SHORT).show();
           }
            });}
                        else {
                            Toast.makeText(accounts.this, "Field is empty", Toast.LENGTH_SHORT).show();
                        }

                    }
                })
                .setNegativeButton("NO", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(accounts.this, "Password change Cancelled", Toast.LENGTH_SHORT).show();
                    }
                })
                .show();
    }


    public void changeemail(){

        final EditText resetpassw= new EditText(this);
        android.app.AlertDialog dialog = new AlertDialog.Builder(this,R.style.AlertDialogStyle)
                .setTitle("Change Email?")
                .setMessage("Enter new Email : You will need to verify Again")
                .setView(resetpassw)
                .setPositiveButton("Done", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if(!TextUtils.isEmpty(resetpassw.getText().toString())) {
                            String takenew = resetpassw.getText().toString();

                            using.updateEmail(takenew).addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    Toast.makeText(accounts.this, "Updated successfully", Toast.LENGTH_SHORT).show();
                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Toast.makeText(accounts.this, "NOT Updated !", Toast.LENGTH_SHORT).show();
                                }
                            });
                        }
                        else {
                            Toast.makeText(accounts.this, "Field is empty", Toast.LENGTH_SHORT).show();
                        }
                    }
                })
                .setNegativeButton("NO", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(accounts.this, "Email change Cancelled", Toast.LENGTH_SHORT).show();
                    }
                })
                .show();
    }







}