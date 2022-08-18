
package com.example.myapplications.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.myapplications.Data.DataBean;
import com.example.myapplications.Adapter.ImageAdapter;
import com.example.myapplications.R;
import com.example.myapplications.Tool.ActivityCollectorUtil;
import com.youth.banner.Banner;
import com.youth.banner.indicator.CircleIndicator;

import java.util.ArrayList;
import java.util.List;

public class ThirdActivity extends AppCompatActivity implements View.OnClickListener{
    private TextView shopping;
    private ImageView home,library,basket,account;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);
        initviews();
        initBanners();
        ActivityCollectorUtil.addActivity(this);
        shopping.setTextColor(Color.parseColor("#FF5AB5"));  //默认选择的字体颜色
        shopping.setTypeface(Typeface.DEFAULT_BOLD); //第一个为加粗字体
        basket.setColorFilter(Color.parseColor("#FF5AB5"));
    }

    private void initBanners() {
        List<DataBean> list_DataBean = new ArrayList<>();
        list_DataBean.add(new DataBean(R.drawable.maom, "", 0));
        list_DataBean.add(new DataBean(R.drawable.mami, "", 1));

        Banner banner = findViewById(R.id.banner);
        //默认直接设置adapter就行了

        //自定义的图片适配器，也可以使用默认的BannerImageAdapter
        ImageAdapter adapter = new ImageAdapter(list_DataBean);

        banner.setAdapter(adapter)
                .addBannerLifecycleObserver(this)//添加生命周期观察者
                .setIndicator(new CircleIndicator(this))//设置指示器
                .setOnBannerListener((data, position) -> {
                    getIntent(position);
                });
    }
    private void getIntent(int position) {
    }

    private void initviews() {
        shopping = findViewById(R.id.shopping);
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
                Intent ho = new Intent(ThirdActivity.this,MainActivity.class);
                startActivity(ho);
                break;
            case R.id.library:
                Intent li = new Intent(ThirdActivity.this,SecondActivity.class);
                startActivity(li);
                break;
            case R.id.basket:
                Intent ba = new Intent(ThirdActivity.this,ThirdActivity.class);
                startActivity(ba);
                break;
            case R.id.account:
                Intent ac = new Intent(ThirdActivity.this,FouthActivity.class);
                startActivity(ac);
                break;
        }
    }
    protected void onDestroy() {
        super.onDestroy();
        ActivityCollectorUtil.removeActivity(this);
    }
}
