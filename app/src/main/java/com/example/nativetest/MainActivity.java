package com.example.nativetest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private Button btn_vuln_link;
    private Button btn_detect_link;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn_vuln_link = findViewById(R.id.btn_vuln_link);
        btn_detect_link = findViewById(R.id.btn_detect_link);

        btn_vuln_link.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                String urlScheme = "market://details?id=com.nhn.android.webtoon";
                String urlScheme = "vuln://vulnhost?url=www.baselinesecu.co.kr";
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(urlScheme));
                startActivity(intent);
                finish();
            }
        });

        btn_detect_link.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // String srchString = "test";
                String urlScheme = "detect://detecthost?url=www.baselinesecu.co.kr";
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(urlScheme));
                startActivity(intent);
                finish();
            }
        });
    }

}