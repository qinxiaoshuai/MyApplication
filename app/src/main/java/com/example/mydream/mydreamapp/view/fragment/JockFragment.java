package com.example.mydream.mydreamapp.view.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.mydream.mydreamapp.Adapter.JockListAdapter;
import com.example.mydream.mydreamapp.R;
import com.example.mydream.mydreamapp.appconfig.ConfigDataCons;
import com.example.mydream.mydreamapp.appconfig.ConfigDataMethod;
import com.example.mydream.mydreamapp.bean.JockBean;
import com.example.mydream.mydreamapp.tool.toast.AppMsg;
import com.lzy.okhttputils.OkHttpUtils;
import com.lzy.okhttputils.cache.CacheMode;
import com.lzy.okhttputils.callback.StringCallback;
import com.song.refresh_view.PullToRefreshView;

import java.util.List;

import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Response;


/**
 * A fragment representing a list of Items.
 * <p/>
 * Activities containing this fragment MUST implement the {@link OnListFragmentInteractionListener}
 * interface.
 */
public class JockFragment extends Fragment {
    public static final String TAG = "JockFragment";
    private View view;
    private List<JockBean> result;
    private ListView jock_list;


    TabLayout tabs;
    private PullToRefreshView mRefreshView;

    private ArrayAdapter<String> mAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ConfigDataMethod.OkhttpConfig(getActivity().getApplication());

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (view == null) {
            view = inflater.inflate(R.layout.jock_fragment, container, false);
            ButterKnife.bind(this, view);
            jock_list = (ListView) view.findViewById(R.id.jock_list);
            mRefreshView = (PullToRefreshView) view.findViewById(R.id.refreshView);
            mRefreshView.setColorSchemeColors(Color.RED, Color.BLUE); // 颜色
//        mRefreshView.setSmileStrokeWidth(8); // 设置绘制的笑脸的宽度
//        mRefreshView.setSmileInterpolator(new LinearInterpolator()); // 笑脸动画转动的插值器
//        mRefreshView.setSmileAnimationDuration(2000); // 设置笑脸旋转动画的时长
            mRefreshView.setOnRefreshListener(new PullToRefreshView.OnRefreshListener() {
                @Override
                public void onRefresh() {
                    requestData();
                }
            });
            requestUrl();

        }
// 缓存的view需要判断是否已经被加过parent，
// 如果有parent需要从parent删除，要不然会发生这个view已经有parent的错误。
        ViewGroup parent = (ViewGroup) view.getParent();
        if (parent != null) {
            parent.removeView(view);
        }
        return view;

    }

    private void requestUrl() {

        OkHttpUtils.get(ConfigDataCons.APP_JOCK_URL)     // 请求方式和请求url
                .tag(this)                       // 请求的 tag, 主要用于取消对应的请求
                .cacheKey("cacheKey")            // 设置当前请求的缓存key,建议每个不同功能的请求设置一个
                .cacheMode(CacheMode.DEFAULT)    // 缓存模式，详细请看缓存介绍
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, okhttp3.Response response) {
                        JSONObject object = JSON.parseObject(s);
                        JSONObject data = (JSONObject) object.get("result");
                        JSONArray jsonArray = data.getJSONArray("data");
                        result = JSON.parseArray(jsonArray.toJSONString(), JockBean.class);
                        JockListAdapter jockListAdapter = new JockListAdapter(getActivity(), result);
                        jock_list.setAdapter(jockListAdapter);
                    }

                    @Override
                    public void onError(Call call, Response response, Exception e) {
                        ConfigDataMethod.toastShow(getActivity(), "加载数据错误，请重新加载！", AppMsg.STYLE_ALERT);
                        super.onError(call, response, e);
                    }
                });

    }

    private void requestData() {
        mRefreshView.postDelayed(new Runnable() {
            @Override
            public void run() {
                mRefreshView.setRefreshing(false);
            }
        }, 2000);
    }


    @Override
    public void onResume() {
        super.onResume();

    }
}
