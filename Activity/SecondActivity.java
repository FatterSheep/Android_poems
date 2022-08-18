package com.example.myapplications.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.myapplications.R;
import com.example.myapplications.Tool.ActivityCollectorUtil;

public class SecondActivity extends AppCompatActivity implements View.OnClickListener{
    private TextView dongtai;
    private ImageView home,library,basket,account,back,edit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        initviews();
        ActivityCollectorUtil.addActivity(this);
        dongtai.setTextColor(Color.parseColor("#FF5AB5"));  //默认选择的字体颜色
        dongtai.setTypeface(Typeface.DEFAULT_BOLD); //第一个为加粗字体
        library.setColorFilter(Color.parseColor("#FF5AB5"));
    }

    private void initviews() {
       back= findViewById(R.id.back);
       back.setOnClickListener(this);
       edit=findViewById(R.id.edit);
       edit.setOnClickListener(this);
       dongtai =findViewById(R.id.dongtai);
       home = findViewById(R.id.home);
       home.setOnClickListener(this);
       library = findViewById(R.id.library);
       library.setOnClickListener(this);
       basket = findViewById(R.id.basket);
       basket.setOnClickListener(this);
       account = findViewById(R.id.account);
       account.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.home:
            case R.id.back:
                Intent ho = new Intent(SecondActivity.this,MainActivity.class);
                startActivity(ho);
                break;
            case R.id.library:
                Intent li = new Intent(SecondActivity.this,SecondActivity.class);
                startActivity(li);
                break;
            case R.id.basket:
                Intent ba = new Intent(SecondActivity.this,ThirdActivity.class);
                startActivity(ba);
                break;
            case R.id.account:
                Intent ac = new Intent(SecondActivity.this,FouthActivity.class);
                startActivity(ac);
                break;
            case R.id.edit:
                Intent al = new Intent(SecondActivity.this,FaBu.class);
                startActivity(al);
                break;
        }
    }
    protected void onDestroy() {
        super.onDestroy();
        ActivityCollectorUtil.removeActivity(this);
    }
}