package com.example.mydream.mydreamapp.view;

import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.example.mydream.mydreamapp.Adapter.FragmentMainAdapter;
import com.example.mydream.mydreamapp.Adapter.SystemBarTintManager;
import com.example.mydream.mydreamapp.R;
import com.example.mydream.mydreamapp.appconfig.ConfigDataMethod;
import com.example.mydream.mydreamapp.tool.toast.AppMsg;
import com.example.mydream.mydreamapp.view.fragment.JockFragment;
import com.example.mydream.mydreamapp.view.fragment.MusicFragment;
import com.example.mydream.mydreamapp.view.fragment.MyFragment;
import com.example.mydream.mydreamapp.view.fragment.NewsFragment;
import com.example.mydream.mydreamapp.view.fragment.SetingFragment;
import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnTabReselectListener;
import com.roughike.bottombar.OnTabSelectListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class FragmentActivity extends android.support.v4.app.FragmentActivity {

    private static String TAG = "FragmentActivity";
    @BindView(R.id.bottomBar)
    BottomBar bottomBar;
    @BindView(R.id.viewpager)
    ViewPager viewpager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment);
        ButterKnife.bind(this);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window window = getWindow();
            // Translucent status bar
            window.setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS,
                    WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            SystemBarTintManager tintManager = new SystemBarTintManager(this);
            tintManager.setStatusBarTintEnabled(true);
            tintManager.setStatusBarTintResource(R.color.colorPrimary);// 通知栏所需颜色
        }

        List<Fragment> fragments = new ArrayList<Fragment>();
        fragments.add(new JockFragment());
        fragments.add(new MusicFragment());
        fragments.add(new NewsFragment());
        fragments.add(new MyFragment());
        fragments.add(new SetingFragment());

        FragmentMainAdapter adapter = new FragmentMainAdapter(getSupportFragmentManager(), fragments);
        viewpager.setAdapter(adapter);


        //设置主viewpager不能滑动
        viewpager.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return true;
            }
        });//
        bottomBar.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelected(int tabId) {
                getFragment(tabId);

            }
        });

        bottomBar.setOnTabReselectListener(new OnTabReselectListener() {
            @Override
            public void onTabReSelected(int tabId) {
                //弹窗提示
                ConfigDataMethod.toastShow(FragmentActivity.this, "Displaying the current page", AppMsg.STYLE_ALERT);

            }
        });
    }


    //退出函数
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            ConfigDataMethod.dialogShow(this, 0, null);
        }
        return false;
    }

    public String getFragment(int menuItemId) {
        switch (menuItemId) {
            case R.id.tab_recents:
                Log.e(TAG, "getFragment: " + "点击了1");
                viewpager.setCurrentItem(0, false);
                break;
            case R.id.tab_favorites:
                Log.e(TAG, "getFragment: " + "点击了2");
                viewpager.setCurrentItem(1, false);
                break;
            case R.id.tab_nearby:
                Log.e(TAG, "getFragment: " + "点击了3");
                viewpager.setCurrentItem(2, false);
                break;
            case R.id.tab_friends:
                Log.e(TAG, "getFragment: " + "点击了4");
                viewpager.setCurrentItem(3, false);
                break;
            case R.id.tab_food:
                Log.e(TAG, "getFragment: " + "点击了5");
                viewpager.setCurrentItem(4, false);
                break;
        }

        return "";
    }


}
