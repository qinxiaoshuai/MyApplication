package com.example.mydream.mydreamapp.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.mydream.mydreamapp.R;
import com.example.mydream.mydreamapp.bean.JockBean;

import java.util.List;

/**
 * 项目名称：MyApplication
 * 创建人：My Dream
 * 创建时间：2017/5/24 15:42
 */
public class JockListAdapter extends BaseAdapter {
    private Context con;
    private List<JockBean> jockBeenList;
    private LayoutInflater inflater;
    private JockBean jockBean;

    public JockListAdapter(Context context, List<JockBean> jockBeen) {
        this.con = context;
        this.jockBeenList = jockBeen;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return 10;
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder vd = null;
        if (convertView == null) {
            vd = new ViewHolder();
            convertView = inflater.inflate(R.layout.jock_list_layout, null);

            vd.tv_time = (TextView) convertView.findViewById(R.id.tv_time);
            vd.tv_content = (TextView) convertView.findViewById(R.id.tv_content);

            convertView.setTag(vd);
        } else {
            vd = (ViewHolder) convertView.getTag();
        }
        jockBean = jockBeenList.get(position);
        if (jockBean != null) {
            vd.tv_time.setText(jockBean.getUpdatetime() + "");
            vd.tv_content.setText("\t\t\t"+jockBean.getContent() + "");
//            Log.e(TAG, "getView: " + jockBean.getContent() + "");

        }

        return convertView;
    }

    class ViewHolder {
        TextView tv_time, tv_content;


    }
}
