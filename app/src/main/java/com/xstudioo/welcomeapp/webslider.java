package com.xstudioo.welcomeapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

public class webslider extends AppCompatActivity {
    ViewPager viewPager;
    Button btnNext;
    int[] layouts;
    webadapter webadapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_webslider);



        viewPager = findViewById(R.id.webpager);
        btnNext = findViewById(R.id.webnextBtn);

        layouts = new int[] {
                R.layout.webslider1
        };

        webadapter = new webadapter(this,layouts);
        viewPager.setAdapter(webadapter);
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(viewPager.getCurrentItem()+1 < layouts.length){
                    viewPager.setCurrentItem(viewPager.getCurrentItem()+1);
                }else {
                    startActivity(new Intent(getApplicationContext(),websites.class));
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
                btnNext.setText("Continue");
            }else {
                btnNext.setText("Next");
            }
        }

        @Override
        public void onPageScrollStateChanged(int i) {

        }
    };
}
