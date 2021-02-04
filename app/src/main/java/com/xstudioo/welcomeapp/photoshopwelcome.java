package com.xstudioo.welcomeapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

public class photoshopwelcome extends AppCompatActivity {
    ViewPager viewPager;
    Button btnNext;
    int[] layouts;
    psadapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_photoshopwelcome);

        viewPager = findViewById(R.id.pagerpics);
        btnNext = findViewById(R.id.nextBtnpics);

        layouts = new int[] {
                R.layout.photoslider1,
                R.layout.photoslider2,
                R.layout.photoslider3,
                R.layout.photoslider4
        };

        adapter = new psadapter(this,layouts);
        viewPager.setAdapter(adapter);
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(viewPager.getCurrentItem()+1 < layouts.length){
                    viewPager.setCurrentItem(viewPager.getCurrentItem()+1);
                }else {
                    startActivity(new Intent(getApplicationContext(),Photochoose.class));
                }
            }
        });
        viewPager.addOnPageChangeListener(viewPagerChangeListener);

    }

    ViewPager.OnPageChangeListener viewPagerChangeListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int i, float v, int i1) {

        }

        @Override
        public void onPageSelected(int i) {
            if(i == layouts.length - 1){
                btnNext.setText("BEGIN DESIGN");
            }else {
                btnNext.setText("Next");
            }
        }

        @Override
        public void onPageScrollStateChanged(int i) {

        }
    };
}
