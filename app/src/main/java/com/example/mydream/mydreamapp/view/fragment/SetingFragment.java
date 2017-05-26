package com.example.mydream.mydreamapp.view.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mydream.mydreamapp.R;

public class SetingFragment extends Fragment {
private View settingView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (settingView == null) {
            settingView = inflater.inflate(R.layout.setting_fragment, container, false);
        }
// 缓存的viewiew需要判断是否已经被加过parent，
// 如果有parent需要从parent删除，要不然会发生这个view已经有parent的错误。
        ViewGroup parent = (ViewGroup) settingView.getParent();
        if (parent != null) {
            parent.removeView(settingView);
        }

        return settingView;
    }


}
