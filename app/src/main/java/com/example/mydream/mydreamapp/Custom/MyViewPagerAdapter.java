package com.example.mydream.mydreamapp.Custom;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * 创建人：张高明
 * 创建时间：2015/12/1 09:32
 * 类描述：
 */
public class MyViewPagerAdapter extends PagerAdapter {

    private List<View> mListViews;
    private List<String> mTatitleList;

    public MyViewPagerAdapter(List<View> views,List<String> tatitleList) {
        this.mListViews = views;
        this.mTatitleList=tatitleList;
    }

    @Override
    public int getCount() {
        return mListViews.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView(mListViews.get(position));//删除页卡
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {    //这个方法用来实例化页卡
        container.addView(mListViews.get(position), 0);//添加页卡
        return mListViews.get(position);
    }
    @Override
    public CharSequence getPageTitle(int position) {
        return mTatitleList.get(position);//页卡标题
    }
}

