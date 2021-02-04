package com.xstudioo.welcomeapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.text.TextUtils;
import android.view.View;
import android.webkit.DownloadListener;
import android.webkit.URLUtil;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.github.clans.fab.FloatingActionButton;
import com.github.clans.fab.FloatingActionMenu;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;

public class websites extends AppCompatActivity {
FloatingActionButton one,two,three,four;
FloatingActionMenu main;
ProgressBar webp;
    private WebView webView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_websites);
        one=findViewById(R.id.homebtn);
        two=findViewById(R.id.alexbtn);
        three= findViewById(R.id.martobtn);
        webp=findViewById(R.id.progressweb);
        four=findViewById(R.id.custombtn);
        main=  findViewById(R.id.floatmenu);
        Toast.makeText(this, "Receiving Data Please Wait", Toast.LENGTH_SHORT).show();
        webView = (WebView) findViewById(R.id.webview);
        webView.setWebViewClient(new WebViewClient());

        webView.loadUrl("https://techkey.co.ke/");
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);

        webView.setWebViewClient(new WebViewClient() {

            public void onPageFinished(WebView view, String url) {
                // do your stuff here
                webp.setVisibility(view.INVISIBLE);
            }
        });
        webView.setWebChromeClient(new WebChromeClient() {
            public void onProgressChanged(WebView view, int progress) {
                webp.setVisibility(view.VISIBLE);
            }
        });

        webView.setDownloadListener(new DownloadListener() {
            @Override
            public void onDownloadStart(String url, String userAgent,
                                        String contentDisposition, String mimetype,
                                        long contentLength) {
                String fileName = URLUtil.guessFileName(url, contentDisposition, mimetype);

                try {
                    String address = Environment.getExternalStorageDirectory().getAbsolutePath() + "/"
                            + Environment.DIRECTORY_DOWNLOADS + "/" +
                            fileName;
                    File file = new File(address);
                    boolean a = file.createNewFile();

                    URL link = new URL(url);
                    downloadFile(link, address);

                } catch (Exception e) {
                    e.printStackTrace();
                }


            }
        });




        one.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(websites.this, "Home", Toast.LENGTH_SHORT).show();
                webView.loadUrl("https://techkey.co.ke/");
            }
        });
        two.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(websites.this, "Google.com", Toast.LENGTH_SHORT).show();
                webView.loadUrl("https://google.com/");
            }
        });
        three.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(websites.this, "YouTube", Toast.LENGTH_SHORT).show();
                webView.loadUrl("https://youtube.com/");
            }
        });
        four.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(websites.this, "Custom", Toast.LENGTH_SHORT).show();

                final EditText customurl= new EditText(websites.this);
                android.app.AlertDialog dialog = new AlertDialog.Builder(websites.this,R.style.AlertDialogStyle)
                        .setTitle("GO To Web Page")
                        .setMessage("Enter Url To Visit; Please write Full Address ie: .com / co.ke ")
                        .setView(customurl)
                        .setPositiveButton("GO", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                if(!TextUtils.isEmpty(customurl.getText().toString())) {
                                    Toast.makeText(websites.this, "Loading", Toast.LENGTH_SHORT).show();
                                    String takenew = customurl.getText().toString();

                                    webView.loadUrl("https://"+takenew+"/");
                                    }
                                else {
                                    Toast.makeText(websites.this, "Field is empty", Toast.LENGTH_SHORT).show();
                                }

                            }
                        })
                        .setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Toast.makeText(websites.this, "Web Change Cancelled", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .show();


            }
        });

    }
    public void downloadFile(URL url, String outputFileName) throws IOException {

        try (InputStream in = url.openStream();
             ReadableByteChannel rbc = Channels.newChannel(in);
             FileOutputStream fos = new FileOutputStream(outputFileName)) {
            fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
        }
        // do your work here

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