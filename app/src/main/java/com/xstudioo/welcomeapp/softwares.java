package com.xstudioo.welcomeapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class softwares extends AppCompatActivity {
Button ofweb,ofdesk,ofand;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_softwares);
        ofweb =findViewById(R.id.softweb);
        ofdesk=findViewById(R.id.softdesk);
        ofand=findViewById(R.id.softandroid);
        onrun();



        ofdesk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment main =new desktopfrag();
                Fragment button =new buttononfrag();
                Fragment off1=new buttonofffrag();
                Fragment off2=new buttonofffrag();
                getSupportFragmentManager().beginTransaction().replace(R.id.framethree,button).commit();
               getSupportFragmentManager().beginTransaction().replace(R.id.frametwo,off1).commit();
               getSupportFragmentManager().beginTransaction().replace(R.id.frameone,off2).commit();
                getSupportFragmentManager().beginTransaction().replace(R.id.framemain,main).commit();

            }
        });
        ofand.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment main =new androidfrag();
                Fragment selecteds =new buttononfrag();
               Fragment off1=new buttonofffrag();
                Fragment off2=new buttonofffrag();
                getSupportFragmentManager().beginTransaction().replace(R.id.framethree,off1).commit();
               getSupportFragmentManager().beginTransaction().replace(R.id.frameone,off2).commit();
                getSupportFragmentManager().beginTransaction().replace(R.id.frametwo,selecteds).commit();
                getSupportFragmentManager().beginTransaction().replace(R.id.framemain,main).commit();
            }
        });



        ofweb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Fragment main =new webfragment();
                Fragment selecteds =new buttononfrag();
                Fragment off1=new buttonofffrag();
                Fragment off2=new buttonofffrag();
               getSupportFragmentManager().beginTransaction().replace(R.id.framethree,off2).commit();
                getSupportFragmentManager().beginTransaction().replace(R.id.frametwo,off1).commit();
                getSupportFragmentManager().beginTransaction().replace(R.id.frameone,selecteds).commit();
                getSupportFragmentManager().beginTransaction().replace(R.id.framemain,main).commit();

            }
        });

    }
    public void onrun(){
        Fragment main =new webfragment();
        Fragment selecteds =new buttononfrag();
        Fragment off1=new buttonofffrag();
        Fragment off2=new buttonofffrag();
        getSupportFragmentManager().beginTransaction().replace(R.id.framethree,off2).commit();
        getSupportFragmentManager().beginTransaction().replace(R.id.frametwo,off1).commit();
        getSupportFragmentManager().beginTransaction().replace(R.id.frameone,selecteds).commit();
        getSupportFragmentManager().beginTransaction().replace(R.id.framemain,main).commit();
    }
}