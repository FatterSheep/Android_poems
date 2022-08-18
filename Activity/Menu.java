package com.example.myapplications.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplications.Country.Burma;
import com.example.myapplications.Country.Japan;
import com.example.myapplications.Country.Korea;
import com.example.myapplications.Country.Laos;
import com.example.myapplications.Country.NorthKorea;
import com.example.myapplications.Country.Singapore;
import com.example.myapplications.Country.Thailand;
import com.example.myapplications.Country.Vietnamese;
import com.example.myapplications.R;
import com.example.myapplications.Tool.ActivityCollectorUtil;

public class Menu extends AppCompatActivity implements View.OnClickListener{
    private ImageView back;
    private LinearLayout v1,v2,v3,v4,v5,v6,v7,v8;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        initView();
        ActivityCollectorUtil.addActivity(this);
    }

    private void initView() {
        back = findViewById(R.id.back);
        v1 = findViewById(R.id.v1);
        v2 = findViewById(R.id.v2);
        v3 = findViewById(R.id.v3);
        v4 = findViewById(R.id.v4);
        v5 = findViewById(R.id.v5);
        v6 = findViewById(R.id.v6);
        v7 = findViewById(R.id.v7);
        v8 = findViewById(R.id.v8);

        back.setOnClickListener(this);
        v1.setOnClickListener(this);
        v2.setOnClickListener(this);
        v3.setOnClickListener(this);
        v4.setOnClickListener(this);
        v5.setOnClickListener(this);
        v6.setOnClickListener(this);
        v7.setOnClickListener(this);
        v8.setOnClickListener(this);

    }
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back:
                Intent i = new Intent(Menu.this, MainActivity.class);
                startActivity(i);
                break;
            case R.id.v1:
                Intent i1 = new Intent(Menu.this, Korea.class);
                startActivity(i1);
                break;
            case R.id.v2:
                Intent i2 = new Intent(Menu.this, NorthKorea.class);
                startActivity(i2);
                break;
            case R.id.v3:
                Intent i3 = new Intent(Menu.this, Singapore.class);
                startActivity(i3);
                break;
            case R.id.v4:
                Intent i4 = new Intent(Menu.this, Japan.class);
                startActivity(i4);
                break;
            case R.id.v5:
                Intent i5 = new Intent(Menu.this, Laos.class);
                startActivity(i5);
                break;
            case R.id.v6:
                Intent i6 = new Intent(Menu.this, Thailand.class);
                startActivity(i6);
                break;
            case R.id.v7:
                Intent i7 = new Intent(Menu.this, Vietnamese.class);
                startActivity(i7);
                break;
            case R.id.v8:
                Intent i8 = new Intent(Menu.this, Burma.class);
                startActivity(i8);
                break;
        }
    }
    protected void onDestroy() {
        super.onDestroy();
        ActivityCollectorUtil.removeActivity(this);
    }
}