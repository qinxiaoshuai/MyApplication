package com.example.mydream.mydreamapp.view;

import android.app.Activity;
import android.os.Bundle;


import android.widget.TextView;


import com.example.mydream.mydreamapp.R;

import butterknife.BindView;
import butterknife.ButterKnife;


public class MainActivity extends Activity {
    @BindView(R.id.text)
    TextView text;
//    private Handler hd = new5MyHandler();
    //监听床垫数据线程


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

    }

}
//// 定义一个内部类继承自Handler，并且覆盖handleMessage方法用于处理子线程传过来的消息
//class MyHandler extends Handler {
//    @Override
//    public void handleMessage(Message msg) {
//        super.handleMessage(msg);
//        switch (msg.what) {
//            case 0:
//                //第三种用handler更新UI的方法
//                runOnUiThread(new Runnable() {
//                    @Override
//                    public void run() {
//                        text.setText("hello");
//                    }
//                });
//                break;
//            default:
//                break;
//        }
//    }
//}

//if(existSDCard()==true){
//        System.out.println("sd卡纯在");
//
//        }else{
//        System.out.println("sd卡不存在");
//        }
//
//public boolean existSDCard() {
//        if (android.os.Environment.getExternalStorageState().equals(
//        android.os.Environment.MEDIA_MOUNTED)) {
//        return true;
//        } else
//        return false;
//        }