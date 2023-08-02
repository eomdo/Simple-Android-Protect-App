package com.example.BSecure;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.BSecure.R;

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
                String urlScheme = "vuln://vulnhost?web_uri=http://www.baselinesecu.co.kr&app_uri=market://launch?id=com.nhn.android.webtoon";
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(urlScheme));
                startActivity(intent);
            }
        });

        btn_detect_link.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // String srchString = "test";
                String urlScheme = "detect://detecthost?web_uri=http://www.baselinesecu.co.kr&app_uri=market://launch?id=com.nhn.android.webtoon";
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(urlScheme));
                startActivity(intent);
            }
        });
    }
    long backPressedTime = 0;
    public void onBackPressed() {
        //2.5초이내에 한 번 더 뒤로가기 클릭 시

        if (System.currentTimeMillis() - backPressedTime < 2500) {
            super.onBackPressed();
            return;
        }
        Toast.makeText(this, "한번 더 클릭 시 인증화면으로 이동됩니다.", Toast.LENGTH_SHORT).show();
        backPressedTime = System.currentTimeMillis();
    }


}