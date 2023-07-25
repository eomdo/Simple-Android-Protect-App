package com.example.nativetest;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.util.Timer;
import java.util.TimerTask;

public class LoginActivity extends AppCompatActivity {
    private EditText et_id, et_pass;
    private Button btn_login, btn_register;
    private Timer timerCall;
    private int nCnt;

    static {
        System.loadLibrary("nativetest");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //Detect Logic start===============================================
        nCnt = 0;
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                androidDetect();
            }
        };

        timerCall = new Timer();
        timerCall.schedule(timerTask, 0, 3000);
        //Detect Logic End===============================================

        et_id = findViewById(R.id.et_id);
        btn_login = findViewById(R.id.btn_login);

        // 회원가입 버튼을 클릭 시 수행

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // EditText에 현재 입력되어있는 값을 get(가져온다)해온다.
                String userID = et_id.getText().toString();
                if (userID.equals("baseline")) {
                    Toast.makeText(getApplicationContext(), "접속에 성공하였습니다.", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    intent.putExtra("userID", userID);
                    startActivity(intent);
                } else {
                    Toast.makeText(getApplicationContext(), "키가 올바르지 않습니다.", Toast.LENGTH_SHORT).show();
                    return;
                }
            }
        });


    }

    private void androidDetect() {
        Log.d("[info]", nCnt++ + " - Run Android Detect");
        Handler mHandler = new Handler(Looper.getMainLooper());


        mHandler.postDelayed(new Runnable() {
            int is_exit = 0;
            @Override
            public void run() {
//                if (RootCheck()) {
//                    is_exit++;
//                }

                detectFrida();

                if (is_exit != 0) {
                    timerCall.cancel();
                    System.exit(0);
                }
            }
        }, 1000);


    }

    boolean chk_root2() {
        final String[] files = {
                "/sbin/su",
                "/system/su",
                "/system/bin/su",
                "/system/sbin/su",
                "/system/xbin/su",
                "/system/xbin/mu",
                "/system/bin/.ext/.su",
                "/system/usr/su-backup",
                "/data/data/com.noshufou.android.su",
                "/system/app/Superuser.apk",
                "/system/app/su.apk",
                "/system/bin/.ext",
                "/system/xbin/.ext",
                "/data/local/xbin/su",
                "/data/local/bin/su",
                "/system/sd/xbin/su",
                "/system/bin/failsafe/su",
                "/data/local/su",
                "/su/bin/su"};

        for (int i = 0; i < files.length; i++) {
            File file = new File(files[i]);
            if (null != file && file.exists()) {
                return true;
            }
        }
        return false;
    }

    boolean chk_root3() {
        try {
            Runtime.getRuntime().exec("su");
            return true;
        } catch (Error e) {

        } catch (Exception e) {

        }
        return false;
    }

    public boolean RootCheck() {
        final boolean[] is_root = {false};
        if (chk_root2() || chk_root3()) {

            androidx.appcompat.app.AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("종료");
            builder.setMessage("변경된 OS(루팅)의 기기는 사용이 제한됩니다.");
            builder.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int whichButton) {
//                    System.exit(0);
                }
            }).setCancelable(false).show();
            return true;
        } else {
            return false;
        }
    }

//    public void detectFrida() {
////        if (detectFrida()) {
////
////            androidx.appcompat.app.AlertDialog.Builder builder = new AlertDialog.Builder(this);
////            builder.setTitle("종료");
////            builder.setMessage("변경된 OS(루팅)의 기기는 사용이 제한됩니다.");
////            builder.setPositiveButton("확인", new DialogInterface.OnClickListener() {
////                public void onClick(DialogInterface dialog, int whichButton) {
////                    System.exit(0);
////                }
////            }).setCancelable(false).show();
////        }
//
//    }
//
//
    public native String detectFrida();
}
