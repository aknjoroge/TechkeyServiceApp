package com.xstudioo.welcomeapp;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

public class mainpage extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private static final String TAG = " ";
    private DrawerLayout nav;
    TextView setname, setemail, setphone;

   NavigationView navigationView;
    Button logouts, accountss,budgets,tracks;
    Button change,qrcodes;
    StorageReference storageReference;
    ImageView profileimage;
    FirebaseAuth fAuth;
   Button toaccout;
    ImageView nav_image;
    FirebaseFirestore fStore;
    String userid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mainpage);



        //start
        navigationView = findViewById(R.id.navigationmain);
        navigationView.setNavigationItemSelectedListener(this);
        nav = findViewById(R.id.maindrawer);
        setemail = findViewById(R.id.textemail);
        setname = findViewById(R.id.textname);
        budgets=findViewById(R.id.budgetbtn);
        tracks=findViewById(R.id.trackbtns);
        tracks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(mainpage.this, "under Development", Toast.LENGTH_SHORT).show();
            }
        });
        budgets.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               startActivity(new Intent(getApplicationContext(),budget.class));
            }
        });
        setphone = findViewById(R.id.textphone);
        storageReference = FirebaseStorage.getInstance().getReference();
toaccout =findViewById(R.id.myaccount);
qrcodes=findViewById(R.id.toqrbtn);
        change = findViewById(R.id.chageprofilebtn);

        profileimage = findViewById(R.id.profilepic);
        nav_image =findViewById(R.id.navheaderimage);
        Toolbar toolbar = findViewById(R.id.maintools);
        fAuth = FirebaseAuth.getInstance();
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


        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, nav, toolbar, R.string.nav_opener, R.string.nav_closer);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            toggle.getDrawerArrowDrawable().setColor(getColor(R.color.red));
        }
        nav.addDrawerListener(toggle);
        toggle.syncState();
        logouts = findViewById(R.id.logoutbtn);
        logouts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
CallAlert();


            }
        });

        change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeprofile();



            }
        });

        qrcodes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),qrcode.class));
            }
        });

        autochangeprofile();

        toaccout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),accounts.class));
            }
        });


    }

    public  void CallAlert(){
        AlertDialog dialog = new AlertDialog.Builder(this,R.style.AlertDialogStyle)
                .setTitle("EXIT THE APP?")
                .setMessage("Are You Sure")
                .setPositiveButton("YES", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                        System.exit(0);

                    }
                })
                .setNegativeButton("NO", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(mainpage.this, "Exit Cancelled", Toast.LENGTH_SHORT).show();
                    }
                })
                .show();


    }
    private void autochangeprofile() {
        StorageReference profileref = storageReference.child("Users/"+fAuth.getCurrentUser().getUid()+"/profile.jpg");
        profileref.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                Toast.makeText(mainpage.this, "Loading profile", Toast.LENGTH_SHORT).show();
                Picasso.get().load(uri).into(profileimage);
               // Picasso.get().load(uri).into(nav_image);

            }
        });
    }

public void changeprofile(){

    AlertDialog dialog = new AlertDialog.Builder(this,R.style.AlertDialogStyle)
            .setTitle("SET PROFILE PICTURE")
            .setMessage("Upload an Image?")
            .setPositiveButton("YES", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    //opening gallery intent
                    Intent intent = new Intent(Intent.ACTION_PICK);
                    intent.setType("image/*");
                    startActivityForResult(intent, 1);

                }
            })
            .setNegativeButton("NO", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                }
            })
            .show();

}
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1 && resultCode == RESULT_OK) {
            try {
                Toast.makeText(this, "Data received", Toast.LENGTH_SHORT).show();
                Toast.makeText(this, "Please wait", Toast.LENGTH_SHORT).show();
                Uri imageuri = data.getData();
                profileimage.setImageURI(imageuri);

                uploadimage(imageuri);
            }catch (Exception e){

                Toast.makeText(this, "Run time exception gained", Toast.LENGTH_SHORT).show();
            }


        }else {
            Toast.makeText(this, "No image selected", Toast.LENGTH_SHORT).show();
        }

    }


    public void uploadimage(Uri imageuri) {
        final StorageReference fileref = storageReference.child("Users/"+fAuth.getCurrentUser().getUid()+"/profile.jpg");
        fileref.putFile(imageuri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                fileref.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {
                        Picasso.get().load(uri).into(profileimage);
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(mainpage.this, "Failed in getting url", Toast.LENGTH_SHORT).show();
                    }
                });

                Toast.makeText(mainpage.this, "IMAGE UPLOADED", Toast.LENGTH_SHORT).show();


            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(mainpage.this, "UPLOAD ERROOR", Toast.LENGTH_SHORT).show();
            }
        });

    }



    @Override
    public void onBackPressed() {
        if(nav.isDrawerOpen(GravityCompat.START)){
            nav.closeDrawer(GravityCompat.START);
        }else {super.onBackPressed();}

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.photoshop:
                startActivity(new Intent(getApplicationContext(),photolanch.class));

                break;
            case R.id.events:
               startActivity(new Intent(getApplicationContext(),eventwelcome.class));
                break;
            case R.id.shops:
              startActivity(new Intent(getApplicationContext(),shop.class));
                break;
            case R.id.software:
               startActivity(new Intent(getApplicationContext(),softwares.class));
                break;
            case R.id.websites:
                startActivity(new Intent(getApplicationContext(),weblanch.class));
                break;
            case R.id.about:
                startActivity(new Intent(getApplicationContext(),about.class));
                break;
            case R.id.messnav:
                startActivity(new Intent(getApplicationContext(),helppage.class));
                break;

        }
        nav.closeDrawer(GravityCompat.START);

        return true;
    }



}