package com.example.myapplications.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplications.LoginRe.Login;
import com.example.myapplications.R;
import com.example.myapplications.Tool.ActivityCollectorUtil;

public class SplashActivity extends AppCompatActivity {
private Button skip;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityCollectorUtil.addActivity(this);
        setContentView(R.layout.activity_splash);
    //    setContentView(R.layout.activity_splash);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);  //隐藏状态栏
//        skip = findViewById(R.id.skip);
//    //    skip.setBackgroundColor(Color.TRANSPARENT); //背景透明
//    //    skip.getBackground().setAlpha(200); //int 在0-255之间, 设置半透明
//
//            skip.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    Intent i = new Intent(SplashActivity.this, Login.class);
//                    startActivity(i);
//                }
//            });
//        getSupportActionBar().hide(); //隐藏标题栏

        Thread myThread = new Thread(){
            public void run() {
                try {
                    sleep(1000);
                    Intent it = new Intent(getApplicationContext(), Login.class);
                    startActivity(it);
                    finish();
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        };
        myThread.start();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityCollectorUtil.removeActivity(this);
    }
}