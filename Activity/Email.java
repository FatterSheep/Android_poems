package com.example.myapplications.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplications.R;
import com.example.myapplications.Tool.ActivityCollectorUtil;

public class Email extends AppCompatActivity implements View.OnClickListener {

    private ImageView back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_email);
        initView();
        ActivityCollectorUtil.addActivity(this);
    }

    private void initView() {
        back = findViewById(R.id.back);

        back.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back:
                Intent i =new Intent(Email.this, MainActivity.class);
                startActivity(i);
                break;
        }
    }
    protected void onDestroy() {
        super.onDestroy();
        ActivityCollectorUtil.removeActivity(this);
    }
}