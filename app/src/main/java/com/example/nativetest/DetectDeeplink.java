package com.example.nativetest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.Iterator;

public class DetectDeeplink extends AppCompatActivity {

    private Button btn_uri_link;
    private Button btn_app_link;
    static String[] allow_domain_list = {
            "baselinesecu.co.kr", "google.com"
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detect_deeplink);

        Intent intent = getIntent();
        String action = intent.getAction();
        Uri data = intent.getData();
        String homepage_url = data.getQueryParameter("url");

        btn_uri_link = findViewById(R.id.btn_uri_link);
        btn_app_link = findViewById(R.id.btn_app_link);

        btn_uri_link.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                String urlScheme = "market://details?id=com.nhn.android.webtoon";
                String urlScheme = "main://mainhost";
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(urlScheme));
                startActivity(intent);
                finish();
            }
        });

        btn_app_link.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (whitedomainlist(homepage_url)) {
                    String srchString = "http://" + homepage_url;
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(srchString));
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(getApplicationContext(), "Detect disallowed links!!!", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    public static boolean whitedomainlist(String str) {
        for (String startsWith : allow_domain_list) {
            if(str.startsWith(startsWith)) {
                return true;
            }
        }
        return false;
    }
}