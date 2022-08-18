package com.example.myapplications.Adapter;

import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import java.util.ArrayList;

public class MyPagerAdapter extends PagerAdapter {
    private ArrayList<View> viewLists;
    public MyPagerAdapter(){

    }
    public MyPagerAdapter(ArrayList<View> viewLists){
        super();
        this.viewLists = viewLists;
    }
    @Override
    public int getCount() {
        return viewLists.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }
    public Object instantiateItem(ViewGroup container, int position){
        container.addView(viewLists.get(position));
        return viewLists.get(position);
    }
    public void destroyItem(ViewGroup container,int position,Object object){
        container.removeView(viewLists.get(position));
    }
}
