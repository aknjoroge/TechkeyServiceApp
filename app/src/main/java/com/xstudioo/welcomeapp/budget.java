package com.xstudioo.welcomeapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.webkit.CookieSyncManager;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

public class budget extends AppCompatActivity {
    WebView webView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_budget);
        webView=findViewById(R.id.adwebv);
        webView.setWebViewClient(new WebViewClient());

        webView.loadUrl("file:///android_asset/app.html");
       // Toast.makeText(this, "THIS SECTION IS STILL UNDER DEVELOPMENT", Toast.LENGTH_SHORT).show();
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setDomStorageEnabled(true);



        webView.setWebChromeClient(new WebChromeClient() {
            public void onProgressChanged(WebView view, int progress) {

            }
        });

        webView.setWebViewClient(new WebViewClient() {

            public void onPageFinished(WebView view, String url) {
                CookieSyncManager.getInstance().sync();

            }
        });


    }

    @Override
    public void onBackPressed() {
        if (webView.canGoBack()) {
            webView.goBack();
        } else {
            super.onBackPressed();
        }
    }

}