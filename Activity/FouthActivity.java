package com.example.myapplications.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.myapplications.LoginRe.Login;
import com.example.myapplications.R;
import com.example.myapplications.Tool.ActivityCollectorUtil;

public class FouthActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView me;
    private ImageView home,library,basket,account;
    private Button exit;
    private TextView customer;
    private Bundle bundle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fouth);
        initviews();
        ActivityCollectorUtil.addActivity(this);
        me.setTextColor(Color.parseColor("#FF5AB5"));  //默认选择的字体颜色
        me.setTypeface(Typeface.DEFAULT_BOLD); //第一个为加粗字体
        account.setColorFilter(Color.parseColor("#FF5AB5"));
//        if(bundle == null){
//        bundle = this.getIntent().getExtras();
//        String name = bundle.getString("Name");
//        customer.setText(name);
//        }
    }

    private void initviews() {
        me = findViewById(R.id.me);
        home = findViewById(R.id.home);
        home.setOnClickListener(this);
        library = findViewById(R.id.library);
        library.setOnClickListener(this);
        basket = findViewById(R.id.basket);
        basket.setOnClickListener(this);
        account = findViewById(R.id.account);
        account.setOnClickListener(this);
        exit = findViewById(R.id.exit);
        exit.setOnClickListener(this);
        customer = findViewById(R.id.customer);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.home:
                Intent ho = new Intent(FouthActivity.this,MainActivity.class);
                startActivity(ho);
                break;
            case R.id.library:
                Intent li = new Intent(FouthActivity.this,SecondActivity.class);
                startActivity(li);
                break;
            case R.id.basket:
                Intent ba = new Intent(FouthActivity.this,ThirdActivity.class);
                startActivity(ba);
                break;
            case R.id.account:
                Intent ac = new Intent(FouthActivity.this,FouthActivity.class);
                startActivity(ac);
                break;
            case R.id.exit:
                ActivityCollectorUtil.finishAllActivity();
                Intent ak = new Intent(FouthActivity.this, Login.class);
                startActivity(ak);
                break;
        }
    }
    protected void onDestroy() {
        super.onDestroy();
        ActivityCollectorUtil.removeActivity(this);
    }
}
