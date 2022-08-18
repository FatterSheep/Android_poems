package com.example.myapplications.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.myapplications.Data.DataBean;
import com.example.myapplications.Adapter.ImageAdapter;
import com.example.myapplications.R;
import com.youth.banner.Banner;
import com.youth.banner.indicator.CircleIndicator;

import java.util.ArrayList;
import java.util.List;

/**
 * @programName: OneFragment.java
 * @programFunction:
 * @createDate: 2018/09/25
 * @author: AnneHan
 * @version:
 * xx.   yyyy/mm/dd   ver    author    comments
 * 01.   2018/09/25   1.00   AnneHan   New Create
 */
public class SevenFragment extends Fragment {
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_seven, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initBanners();
    }

    private void initBanners() {
        //本地图片
        List<DataBean> list_DataBean = new ArrayList<>();
        list_DataBean.add(new DataBean(R.drawable.maom, "", 0));
        list_DataBean.add(new DataBean(R.drawable.mami, "", 1));

        Banner banner = this.getView().findViewById(R.id.banner);
        //默认直接设置adapter就行了

        //自定义的图片适配器，也可以使用默认的BannerImageAdapter
        ImageAdapter adapter = new ImageAdapter(list_DataBean);

        banner.setAdapter(adapter)
                .addBannerLifecycleObserver(this)//添加生命周期观察者
                .setIndicator(new CircleIndicator(getContext()));
        banner.setOnBannerListener((data, position) -> {
            getIntent(position);
        });
    }

    private void getIntent(int position) {
    }
}