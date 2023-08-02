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
    static String[] allow_web_uri_list = {
            "http://www.baselinesecu.co.kr/"
    };
    static String[] allow_app_uri_list = {
            "market://launch?id=com.nhn.android.webtoon"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detect_deeplink);

        Intent intent = getIntent();
        String action = intent.getAction();
        Uri data = intent.getData();
        String web_uri = data.getQueryParameter("web_uri");
        String app_uri = data.getQueryParameter("app_uri");

        btn_uri_link = findViewById(R.id.btn_uri_link);
        btn_app_link = findViewById(R.id.btn_app_link);

        btn_uri_link.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (whiteDomainList(app_uri)) {
                    String srchString = app_uri;
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(srchString));
                    intent.setPackage("com.android.vending");
                    startActivity(intent);
//                    finish();
                } else {
                    Toast.makeText(getApplicationContext(), "Detect disallowed links!!!\nfiltering: " + app_uri, Toast.LENGTH_SHORT).show();
                }
            }
        });

        btn_app_link.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (whiteDomainList(web_uri)) {
                    String srchString = web_uri;
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(srchString));
                    intent.setPackage("com.sec.android.app.sbrowser");
                    startActivity(intent);
//                    finish();
                } else {
                    Toast.makeText(getApplicationContext(), "Detect disallowed links!!!\nfiltering: " + web_uri, Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    public static boolean whiteDomainList(String str) {
        for (String startsWith : allow_web_uri_list) {
            if(str.startsWith(startsWith)) {
                return true;
            }
        }
        for (String startsWith : allow_app_uri_list) {
            if(str.startsWith(startsWith)) {
                return true;
            }
        }
        return false;
    }
}