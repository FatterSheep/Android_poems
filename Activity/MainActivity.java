package com.example.myapplications.Activity;


import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.example.myapplications.Fragment.EightFragment;
import com.example.myapplications.Fragment.FiveFragment;
import com.example.myapplications.Fragment.FourFragment;
import com.example.myapplications.Fragment.OneFragment;
import com.example.myapplications.Fragment.SevenFragment;
import com.example.myapplications.Fragment.SixFragment;
import com.example.myapplications.Fragment.ThreeFragment;
import com.example.myapplications.Fragment.TwoFragment;
import com.example.myapplications.R;
import com.example.myapplications.Tool.ActivityCollectorUtil;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView one, two,three,four,five,six,seven,eight,shouye;
    private Button search;
    private ViewPager vp;
    private ImageView home,library,basket,account,menu,email,game,iv;
    private OneFragment oneFragment;
    private TwoFragment twoFragment;
    private ThreeFragment threeFragment;
    private FourFragment fourFragment;
    private FiveFragment fiveFragment;
    private SixFragment sixFragment;
    private SevenFragment sevenFragment;
    private EightFragment eightFragment;

    private List<Fragment> mFragmentList = new ArrayList<Fragment>();
    private FragmentAdapter mFragmentAdapter;
//    private ActionBar actionBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
//        initBanners();
        ActivityCollectorUtil.addActivity(this);
        mFragmentAdapter = new FragmentAdapter(this.getSupportFragmentManager(), mFragmentList);
        vp.setOffscreenPageLimit(2);//设置预加载页面数量;ViewPager的缓存为2帧
        vp.setAdapter(mFragmentAdapter);
        vp.setCurrentItem(0);//初始设置ViewPager选中第一帧
        one.setTextColor(Color.parseColor("#FF5AB5"));  //默认选择的字体颜色
        one.setTypeface(Typeface.DEFAULT_BOLD); //第一个为加粗字体

        shouye.setTextColor(Color.parseColor("#FF5AB5"));  //默认选择的字体颜色
        shouye.setTypeface(Typeface.DEFAULT_BOLD); //第一个为加粗字体
        home.setColorFilter(Color.parseColor("#FF5AB5"));

        //ViewPager的监听事件
        vp.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                /*此方法在页面被选中时调用*/
                changeTextColor(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                /*此方法是在状态改变的时候调用，其中arg0这个参数有三种状态（0，1，2）。
                arg0==1的时辰默示正在滑动，
                arg0==2的时辰默示滑动完毕了，
                arg0==0的时辰默示什么都没做。*/
            }
        });
    }

//    private void initBanners() {
//        List<DataBean> list_DataBean = new ArrayList<>();
//        list_DataBean.add(new DataBean(R.drawable.maom, "", 0));
//        list_DataBean.add(new DataBean(R.drawable.mami, "", 1));
//
//        Banner banner = findViewById(R.id.banner);
//        //默认直接设置adapter就行了
//
//        //自定义的图片适配器，也可以使用默认的BannerImageAdapter
//        ImageAdapter adapter = new ImageAdapter(list_DataBean);
//
//        banner.setAdapter(adapter)
//                .addBannerLifecycleObserver(this)//添加生命周期观察者
//                .setIndicator(new CircleIndicator(this))//设置指示器
//                .setOnBannerListener((data, position) -> {
//                    getIntent(position);
//                });
//    }
//
//    private void getIntent(int position) {
//    }

    /**
     * 初始化布局View
     */
    private void initViews() {
        //找到xml中的TextView
        one = findViewById(R.id.one);
        two = findViewById(R.id.two);
        three = findViewById(R.id.three);
        four = findViewById(R.id.four);
        five = findViewById(R.id.five);
        six = findViewById(R.id.six);
        seven = findViewById(R.id.seven);
        eight = findViewById(R.id.eight);

        shouye = findViewById(R.id.shouye);
        home = findViewById(R.id.home);

        menu = findViewById(R.id.menu);
        search=findViewById(R.id.search);
        email=findViewById(R.id.email);
        game=findViewById(R.id.game);

        home = findViewById(R.id.home);
        library = findViewById(R.id.library);
        basket = findViewById(R.id.basket);
        account = findViewById(R.id.account);

        iv = findViewById(R.id.iv);
        //设置监听
        one.setOnClickListener(this);
        two.setOnClickListener(this);
        three.setOnClickListener(this);
        four.setOnClickListener(this);
        five.setOnClickListener(this);
        six.setOnClickListener(this);
        seven.setOnClickListener(this);
        eight.setOnClickListener(this);

        menu.setOnClickListener(this);
        search.setOnClickListener(this);
        email.setOnClickListener(this);
        game.setOnClickListener(this);

        basket.setOnClickListener(this);
        library.setOnClickListener(this);
        account.setOnClickListener(this);
        home.setOnClickListener(this);

        iv.setOnClickListener(this);
        //找到自定义的viewpager
        vp = findViewById(R.id.mainViewPager);
        //实例化Fragment对象
        oneFragment = new OneFragment();
        twoFragment = new TwoFragment();
        threeFragment = new ThreeFragment();
        fourFragment = new FourFragment();
        fiveFragment=new FiveFragment();
        sixFragment=new SixFragment();
        sevenFragment=new SevenFragment();
        eightFragment=new EightFragment();

        //给FragmentList添加数据
        mFragmentList.add(oneFragment);
        mFragmentList.add(twoFragment);
        mFragmentList.add(threeFragment);
        mFragmentList.add(fourFragment);
        mFragmentList.add(fiveFragment);
        mFragmentList.add(sixFragment);
        mFragmentList.add(sevenFragment);
        mFragmentList.add(eightFragment);
    }

    /**
     * 点击头部Text 动态修改ViewPager的内容
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.one:
                vp.setCurrentItem(0, true); //点击Text，view跟着改变;设置第一页为当前页面;
                break;                                          //true代表切换速度慢；false代表快速切换
            case R.id.two:
                vp.setCurrentItem(1, true);
                break;
            case R.id.three:
                vp.setCurrentItem(2, true);
                break;
            case R.id.four:
                vp.setCurrentItem(3, true);
                break;
            case R.id.five:
                vp.setCurrentItem(4, true);
                break;
            case R.id.six:
                vp.setCurrentItem(5, true);
                break;
            case R.id.seven:
                vp.setCurrentItem(6, true);
                break;
            case R.id.eight:
                vp.setCurrentItem(7, true);
                break;
            case R.id.menu:
                Intent i = new Intent(MainActivity.this, Menu.class);
                startActivity(i);
                break;
            case R.id.search:
                Intent p = new Intent(MainActivity.this, Search.class);
                startActivity(p);
                break;
            case R.id.email:
                Intent o = new Intent(MainActivity.this, Email.class);
                startActivity(o);
                break;
            case R.id.game:
                Toast.makeText(MainActivity.this,"即将开放",Toast.LENGTH_SHORT).show();
                break;
            case R.id.library:
                Intent li = new Intent(MainActivity.this,SecondActivity.class);
                startActivity(li);
                break;
            case R.id.basket:
                Intent ba = new Intent(MainActivity.this,ThirdActivity.class);
                startActivity(ba);
                break;
            case R.id.account:
            case R.id.iv:
                Intent ac = new Intent(MainActivity.this,FouthActivity.class);
                startActivity(ac);
                break;
        }
    }

    public class FragmentAdapter extends FragmentPagerAdapter {

        List<Fragment> fragmentList = new ArrayList<Fragment>();

        public FragmentAdapter(FragmentManager fm, List<Fragment> fragmentList) {
            super(fm);
            this.fragmentList = fragmentList;
        }

        @Override
        public Fragment getItem(int position) {
            return fragmentList.get(position);
        }

        @Override
        public int getCount() {
            return fragmentList.size();
        }

    }

    /**
     * 由ViewPager的滑动修改头部导航Text的颜色
     * @param position
     */
    private void changeTextColor(int position) {
        if (position == 0) {
            one.setTextColor(Color.parseColor("#FF5AB5")); //粉色
            one.setTypeface(Typeface.DEFAULT_BOLD);
            two.setTypeface(Typeface.DEFAULT);
            two.setTextColor(Color.parseColor("#808080")); //灰色
            three.setTypeface(Typeface.DEFAULT);
            three.setTextColor(Color.parseColor("#808080")); //灰色
            four.setTypeface(Typeface.DEFAULT);
            four.setTextColor(Color.parseColor("#808080")); //灰色
            five.setTypeface(Typeface.DEFAULT);
            five.setTextColor(Color.parseColor("#808080")); //灰色
            six.setTypeface(Typeface.DEFAULT);
            six.setTextColor(Color.parseColor("#808080")); //灰色
            seven.setTypeface(Typeface.DEFAULT);
            seven.setTextColor(Color.parseColor("#808080")); //灰色
            eight.setTypeface(Typeface.DEFAULT);
            eight.setTextColor(Color.parseColor("#808080")); //灰色


        } else if (position == 1) {
            two.setTextColor(Color.parseColor("#FF5AB5"));//粉色
            two.setTypeface(Typeface.DEFAULT_BOLD);
            one.setTypeface(Typeface.DEFAULT);
            one.setTextColor(Color.parseColor("#808080"));//灰色
            three.setTypeface(Typeface.DEFAULT);
            three.setTextColor(Color.parseColor("#808080"));//灰色
            four.setTypeface(Typeface.DEFAULT);
            four.setTextColor(Color.parseColor("#808080")); //灰色
            five.setTypeface(Typeface.DEFAULT);
            five.setTextColor(Color.parseColor("#808080")); //灰色
            six.setTypeface(Typeface.DEFAULT);
            six.setTextColor(Color.parseColor("#808080")); //灰色
            seven.setTypeface(Typeface.DEFAULT);
            seven.setTextColor(Color.parseColor("#808080")); //灰色
            eight.setTypeface(Typeface.DEFAULT);
            eight.setTextColor(Color.parseColor("#808080")); //灰色


        }else if (position == 2) {
            three.setTextColor(Color.parseColor("#FF5AB5"));//粉色
            three.setTypeface(Typeface.DEFAULT_BOLD);
            two.setTypeface(Typeface.DEFAULT);
            two.setTextColor(Color.parseColor("#808080"));//灰色
            one.setTypeface(Typeface.DEFAULT);
            one.setTextColor(Color.parseColor("#808080"));//灰色
            four.setTypeface(Typeface.DEFAULT);
            four.setTextColor(Color.parseColor("#808080")); //灰色
            five.setTypeface(Typeface.DEFAULT);
            five.setTextColor(Color.parseColor("#808080")); //灰色
            six.setTypeface(Typeface.DEFAULT);
            six.setTextColor(Color.parseColor("#808080")); //灰色
            seven.setTypeface(Typeface.DEFAULT);
            seven.setTextColor(Color.parseColor("#808080")); //灰色
            eight.setTypeface(Typeface.DEFAULT);
            eight.setTextColor(Color.parseColor("#808080")); //灰色


        }else if (position == 3) {
            four.setTextColor(Color.parseColor("#FF5AB5"));//粉色
            four.setTypeface(Typeface.DEFAULT_BOLD);
            three.setTypeface(Typeface.DEFAULT);
            three.setTextColor(Color.parseColor("#808080"));//灰色
            one.setTypeface(Typeface.DEFAULT);
            one.setTextColor(Color.parseColor("#808080"));//灰色
            two.setTypeface(Typeface.DEFAULT);
            two.setTextColor(Color.parseColor("#808080"));//灰色
            five.setTypeface(Typeface.DEFAULT);
            five.setTextColor(Color.parseColor("#808080")); //灰色
            six.setTypeface(Typeface.DEFAULT);
            six.setTextColor(Color.parseColor("#808080")); //灰色
            seven.setTypeface(Typeface.DEFAULT);
            seven.setTextColor(Color.parseColor("#808080")); //灰色
            eight.setTypeface(Typeface.DEFAULT);
            eight.setTextColor(Color.parseColor("#808080")); //灰色

        }else if (position == 4) {
            five.setTextColor(Color.parseColor("#FF5AB5"));//粉色
            five.setTypeface(Typeface.DEFAULT_BOLD);
            four.setTypeface(Typeface.DEFAULT);
            four.setTextColor(Color.parseColor("#808080"));//灰色
            one.setTypeface(Typeface.DEFAULT);
            one.setTextColor(Color.parseColor("#808080"));//灰色
            two.setTypeface(Typeface.DEFAULT);
            two.setTextColor(Color.parseColor("#808080"));//灰色
            three.setTypeface(Typeface.DEFAULT);
            three.setTextColor(Color.parseColor("#808080"));//灰色
            six.setTypeface(Typeface.DEFAULT);
            six.setTextColor(Color.parseColor("#808080")); //灰色
            seven.setTypeface(Typeface.DEFAULT);
            seven.setTextColor(Color.parseColor("#808080")); //灰色
            eight.setTypeface(Typeface.DEFAULT);
            eight.setTextColor(Color.parseColor("#808080")); //灰色

        }else if (position == 5) {
            six.setTextColor(Color.parseColor("#FF5AB5"));//粉色
            six.setTypeface(Typeface.DEFAULT_BOLD);
            five.setTypeface(Typeface.DEFAULT);
            five.setTextColor(Color.parseColor("#808080"));//灰色
            one.setTypeface(Typeface.DEFAULT);
            one.setTextColor(Color.parseColor("#808080"));//灰色
            two.setTypeface(Typeface.DEFAULT);
            two.setTextColor(Color.parseColor("#808080"));//灰色
            three.setTypeface(Typeface.DEFAULT);
            three.setTextColor(Color.parseColor("#808080"));//灰色
            four.setTypeface(Typeface.DEFAULT);
            four.setTextColor(Color.parseColor("#808080"));//灰色
            seven.setTypeface(Typeface.DEFAULT);
            seven.setTextColor(Color.parseColor("#808080")); //灰色
            eight.setTypeface(Typeface.DEFAULT);
            eight.setTextColor(Color.parseColor("#808080")); //灰色

        }else if (position == 6) {
            seven.setTextColor(Color.parseColor("#FF5AB5"));//粉色
            seven.setTypeface(Typeface.DEFAULT_BOLD);
            six.setTypeface(Typeface.DEFAULT);
            six.setTextColor(Color.parseColor("#808080"));//灰色
            one.setTypeface(Typeface.DEFAULT);
            one.setTextColor(Color.parseColor("#808080"));//灰色
            two.setTypeface(Typeface.DEFAULT);
            two.setTextColor(Color.parseColor("#808080")); //灰色
            three.setTypeface(Typeface.DEFAULT);
            three.setTextColor(Color.parseColor("#808080")); //灰色
            four.setTypeface(Typeface.DEFAULT);
            four.setTextColor(Color.parseColor("#808080")); //灰色
            five.setTypeface(Typeface.DEFAULT);
            five.setTextColor(Color.parseColor("#808080")); //灰色
            eight.setTypeface(Typeface.DEFAULT);
            eight.setTextColor(Color.parseColor("#808080")); //灰色

        }else if (position == 7) {
            eight.setTextColor(Color.parseColor("#FF5AB5"));//粉色
            eight.setTypeface(Typeface.DEFAULT_BOLD);
            seven.setTypeface(Typeface.DEFAULT);
            seven.setTextColor(Color.parseColor("#808080"));//灰色
            one.setTypeface(Typeface.DEFAULT);
            one.setTextColor(Color.parseColor("#808080"));//灰色
            two.setTypeface(Typeface.DEFAULT);
            two.setTextColor(Color.parseColor("#808080")); //灰色
            three.setTypeface(Typeface.DEFAULT);
            three.setTextColor(Color.parseColor("#808080")); //灰色
            four.setTypeface(Typeface.DEFAULT);
            four.setTextColor(Color.parseColor("#808080")); //灰色
            five.setTypeface(Typeface.DEFAULT);
            five.setTextColor(Color.parseColor("#808080")); //灰色
            six.setTypeface(Typeface.DEFAULT);
            six.setTextColor(Color.parseColor("#808080")); //灰色
        }

    }
    protected void onDestroy() {
        super.onDestroy();
        ActivityCollectorUtil.removeActivity(this);
    }
}
