package com.xstudioo.welcomeapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

public class Events extends AppCompatActivity {
    ViewPager viewPager;
    Button btnNext;
    int[] layouts;
    evadapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_events);

        viewPager = findViewById(R.id.pagerevent);
        btnNext = findViewById(R.id.nextBtnevent);

        layouts = new int[] {
                R.layout.eventsslider1,
                R.layout.eventsslider2,
                R.layout.eventsslider3
        };

        adapter = new evadapter(this,layouts);
       viewPager.setAdapter(adapter);
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(viewPager.getCurrentItem()+1 < layouts.length){
                    viewPager.setCurrentItem(viewPager.getCurrentItem()+1);
                }else {
                    startActivity(new Intent(getApplicationContext(),eventspage.class));
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
                btnNext.setText("Get Scratching");
            }else {
                btnNext.setText("Next");
            }
        }

        @Override
        public void onPageScrollStateChanged(int i) {

        }
    };
}
