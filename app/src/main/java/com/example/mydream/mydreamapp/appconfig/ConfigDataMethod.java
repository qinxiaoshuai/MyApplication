package com.example.mydream.mydreamapp.appconfig;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.widget.AdapterView;

import com.example.mydream.mydreamapp.tool.dialog.CustomBaseDialog;
import com.example.mydream.mydreamapp.tool.dialog.T;
import com.example.mydream.mydreamapp.tool.toast.AppMsg;
import com.flyco.dialog.entity.DialogMenuItem;
import com.flyco.dialog.listener.OnOperItemClickL;
import com.flyco.dialog.widget.NormalListDialog;
import com.lzy.okhttputils.OkHttpUtils;
import com.lzy.okhttputils.model.HttpHeaders;
import com.lzy.okhttputils.model.HttpParams;

import java.util.ArrayList;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobConfig;

/**
 * 项目名称：MyAnimation
 * 创建人：My Dream
 * 创建时间：2017/5/5 11:39
 * 修改人：My Dream
 * 修改时间：2017/5/5 11:39
 */
public class ConfigDataMethod {


    /**
     * 初始化Bmob数据库
     *
     * @param context
     */
    public static void bmobInit(Context context) {
        Bmob.initialize(context, ConfigDataCons.APP_BMOB_ID);
    }

    /**
     * 配置Bmob数据库
     **/
    public static void bmobConfig(Context context) {

        //第二：自v3.4.7版本开始,设置BmobConfig,允许设置请求超时时间、文件分片上传时每片的大小、文件的过期时间(单位为秒)，
        BmobConfig config = new BmobConfig.Builder(context)
                //设置appkey
                .setApplicationId(ConfigDataCons.APP_BMOB_ID)
                //请求超时时间（单位为秒）：默认15s
                .setConnectTimeout(30)
                //文件分片上传时每片的大小（单位字节），默认512*1024
                .setUploadBlockSize(1024 * 1024)
                //文件的过期时间(单位为秒)：默认1800s
                .setFileExpiration(2500)
                .build();
        Bmob.initialize(config);

    }

    /**
     * 应用退出弹窗
     * <p>
     * 例如
     * ArrayList<DialogMenuItem> mMenuItems = new ArrayList<>();
     * mMenuItems.add(new DialogMenuItem("收藏", R.mipmap.ic_winstyle_favor));
     * mMenuItems.add(new DialogMenuItem("下载", R.mipmap.ic_winstyle_download));
     * mMenuItems.add(new DialogMenuItem("分享", R.mipmap.ic_winstyle_share));
     * mMenuItems.add(new DialogMenuItem("删除", R.mipmap.ic_winstyle_delete));
     * mMenuItems.add(new DialogMenuItem("歌手", R.mipmap.ic_winstyle_artist));
     * mMenuItems.add(new DialogMenuItem("专辑", R.mipmap.ic_winstyle_album));
     **/
    public static void dialogShow(final Context context, int num, final ArrayList<DialogMenuItem> menuItems) {
//弹出退出对话框
        if (num == 0) {
            final CustomBaseDialog dialog = new CustomBaseDialog(context);
            dialog.show();
            dialog.setCanceledOnTouchOutside(false);
//弹出应用对话框
        } else if (num == 1) {
            final NormalListDialog dialog = new NormalListDialog(context, menuItems);
            dialog.title("请选择")
                    .isTitleShow(false)
                    .itemPressColor(Color.parseColor("#85D3EF"))
                    .itemTextColor(Color.parseColor("#303030"))
                    .itemTextSize(15)
                    .cornerRadius(2)
                    .widthScale(0.75f)
                    .show();

            dialog.setOnOperItemClickL(new OnOperItemClickL() {
                @Override
                public void onOperItemClick(AdapterView<?> parent, View view, int position, long id) {
                    T.showShort(context, menuItems.get(position).mOperName);
                    dialog.dismiss();
                }
            });

        } else if (num == 2) {

        }
    }

    /**
     * Toast提示
     */
    public static void toastShow(Context context, String msg, AppMsg.Style style) {

        // 创建AppMsg对象，并定义显示的信息和样式
        AppMsg appMsg = AppMsg.makeText((Activity) context, msg, style);
        // 设置Toast显示的位置,默认显示在屏幕的最上方
        appMsg.show();
    }


    /**
     * 初始化网络框架
     */
    public static void OkhttpConfig(Application application) {


        HttpHeaders headers = new HttpHeaders();
        headers.put("commonHeaderKey1", "commonHeaderValue1");    //所有的 header 都 不支持 中文
        headers.put("commonHeaderKey2", "commonHeaderValue2");
        HttpParams params = new HttpParams();
        params.put("commonParamsKey1", "commonParamsValue1");     //所有的 params 都 支持 中文
        params.put("commonParamsKey2", "这里支持中文参数");
        //必须调用初始化
        OkHttpUtils.init(application);
        //以下都不是必须的，根据需要自行选择
        OkHttpUtils.getInstance()//
//                .debug("OkHttpUtils")                                              //是否打开调试
                .setConnectTimeout(OkHttpUtils.DEFAULT_MILLISECONDS)               //全局的连接超时时间
                .setReadTimeOut(OkHttpUtils.DEFAULT_MILLISECONDS)                  //全局的读取超时时间
                .setWriteTimeOut(OkHttpUtils.DEFAULT_MILLISECONDS)                 //全局的写入超时时间
                //.setCookieStore(new MemoryCookieStore())                           //cookie使用内存缓存（app退出后，cookie消失）
                //.setCookieStore(new PersistentCookieStore())                       //cookie持久化存储，如果cookie不过期，则一直有效
                .addCommonHeaders(headers)                                         //设置全局公共头
                .addCommonParams(params);

    }


}
