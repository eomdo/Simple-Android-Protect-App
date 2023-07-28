package com.example.BSecure;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class DetectDeeplink extends AppCompatActivity {

    private Button btn_uri_link;
    private Button btn_app_link;
    static String[] allow_domain_list = {
            "baselinesecu.co.kr", "com.nhn.android.webtoon"
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detect_deeplink);

        Intent intent = getIntent();
        String action = intent.getAction();
        Uri data = intent.getData();
        String homepage_url = data.getQueryParameter("url");
        String app_id = data.getQueryParameter("app_id");

        btn_uri_link = findViewById(R.id.btn_uri_link);
        btn_app_link = findViewById(R.id.btn_app_link);

        btn_uri_link.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (whitedomainlist(app_id)) {
                    String srchString = "market://details?id=" + app_id;
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(srchString));
                    intent.setPackage("com.android.vending");
                    startActivity(intent);
//                    finish();
                } else {
                    Toast.makeText(getApplicationContext(), "Detect disallowed links!!!\nfiltering: " + app_id, Toast.LENGTH_SHORT).show();
                }
            }
        });

        btn_app_link.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (whitedomainlist(homepage_url)) {
                    String srchString = "http://" + homepage_url;
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(srchString));
                    intent.setPackage("com.sec.android.app.sbrowser");
                    startActivity(intent);
//                    finish();
                } else {
                    Toast.makeText(getApplicationContext(), "Detect disallowed links!!!\nfiltering: " + homepage_url, Toast.LENGTH_SHORT).show();
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