package com.example.mydream.mydreamapp.view.fragment;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.mydream.mydreamapp.Custom.MyViewPagerAdapter;
import com.example.mydream.mydreamapp.R;
import com.example.mydream.mydreamapp.appconfig.ConfigDataCons;
import com.example.mydream.mydreamapp.appconfig.ConfigDataMethod;
import com.example.mydream.mydreamapp.bean.NewsBean;
import com.example.mydream.mydreamapp.tool.toast.AppMsg;
import com.lzy.okhttputils.OkHttpUtils;
import com.lzy.okhttputils.cache.CacheMode;
import com.lzy.okhttputils.callback.StringCallback;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Response;

public class NewsFragment extends Fragment {
    @BindView(R.id.tabs)
    TabLayout tabs;
    @BindView(R.id.viewpagers)
    ViewPager viewpagers;

    private String TAG = "NewsFragment";
    private View recommendView, amorView, militaryView, funnyView, physicalView;
    private List<String> titleList;
    private List<View> mViewList = new ArrayList<>();//页卡视图集合
    private LayoutInflater mInflater;
    private List<NewsBean> newsBeen;
    private View newsView;

    private ListView recommendList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //判断view是否存在
        if (newsView == null) {
            newsView = inflater.inflate(R.layout.news_fragment, container, false);
            ButterKnife.bind(this, newsView);
            initview();
        }
        // 缓存的viewiew需要判断是否已经被加过parent，
        // 如果有parent需要从parent删除，要不然会发生这个view已经有parent的错误。
        ViewGroup parent = (ViewGroup) newsView.getParent();
        if (parent != null) {
            parent.removeView(newsView);
        }
        return newsView;
    }

    private void initview() {
        Log.e(TAG, "initview: " + "initview");
        mInflater = LayoutInflater.from(getActivity());
        recommendView = mInflater.inflate(R.layout.news_recommend_layout, null);
        recommendInit();
        amorView = mInflater.inflate(R.layout.news_amor_layout, null);
        amorInit();
        militaryView = mInflater.inflate(R.layout.news_military_layout, null);
        militaryInit();
        funnyView = mInflater.inflate(R.layout.news_funny_layout, null);
        funnyInit();
        physicalView = mInflater.inflate(R.layout.news_physical_layout, null);
        physicalInit();

        mViewList.add(recommendView);
        mViewList.add(amorView);
        mViewList.add(militaryView);
        mViewList.add(funnyView);
        mViewList.add(physicalView);

        titleList = new ArrayList<String>();
        titleList.add("推荐");
        titleList.add("爱情");
        titleList.add("军事");
        titleList.add("搞笑");
        titleList.add("体育");

        tabs.addTab(tabs.newTab().setText(titleList.get(0)));
        tabs.addTab(tabs.newTab().setText(titleList.get(1)));
        tabs.addTab(tabs.newTab().setText(titleList.get(2)));
        tabs.addTab(tabs.newTab().setText(titleList.get(3)));
        tabs.addTab(tabs.newTab().setText(titleList.get(4)));

        MyViewPagerAdapter myViewPagerAdapter = new MyViewPagerAdapter(mViewList, titleList);
        //给ViewPager设置适配器
        viewpagers.setAdapter(myViewPagerAdapter);
        //将TabLayout和ViewPager关联起来。
        tabs.setupWithViewPager(viewpagers);
        //给TabLayout设置适配器
        tabs.setTabsFromPagerAdapter(myViewPagerAdapter);

    }

    private void physicalInit() {


    }

    private void funnyInit() {


    }

    private void militaryInit() {


    }

    private void amorInit() {


    }

    //推荐
    private void recommendInit() {
        recommendList= (ListView) recommendView.findViewById(R.id.recommend_list);


    }


    private void requestUrl() {

        OkHttpUtils.get(ConfigDataCons.APP_NEWS_URL)     // 请求方式和请求url
                .tag(this)                       // 请求的 tag, 主要用于取消对应的请求
                .cacheKey("cacheKey")            // 设置当前请求的缓存key,建议每个不同功能的请求设置一个
                .cacheMode(CacheMode.DEFAULT)    // 缓存模式，详细请看缓存介绍
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, okhttp3.Response response) {
                        JSONObject object = JSON.parseObject(s);
                        JSONObject data = (JSONObject) object.get("result");
                        JSONArray jsonArray = data.getJSONArray("data");
                        newsBeen = JSON.parseArray(jsonArray.toJSONString(), NewsBean.class);

                    }

                    @Override
                    public void onError(Call call, Response response, Exception e) {
                        ConfigDataMethod.toastShow(getActivity(), "加载数据错误，请重新加载！", AppMsg.STYLE_ALERT);
                        super.onError(call, response, e);
                    }
                });

    }

    @Override
    public void onResume() {
        super.onResume();

    }


}
