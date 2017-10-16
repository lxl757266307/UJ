package com.example.maintainsteward2.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.maintainsteward2.R;
import com.example.maintainsteward2.bean.YongJinJiLuBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/10/16.
 */

public class YongJinJiLuAdapter extends BaseAdapter {
    List<YongJinJiLuBean.DataBeanX.DataBean> dataBeanList;

    Context context;

    public YongJinJiLuAdapter(Context context) {
        this.context = context;
    }

    public void setDataBeanList(List<YongJinJiLuBean.DataBeanX.DataBean> dataBeanList) {
        this.dataBeanList = dataBeanList;
    }

    @Override
    public int getCount() {
        return dataBeanList == null ? 0 : dataBeanList.size();
    }

    @Override
    public Object getItem(int position) {
        return dataBeanList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    ViewHolder viewHolder=null;

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.lv_yongjijinlu, parent, false);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        YongJinJiLuBean.DataBeanX.DataBean dataBean = dataBeanList.get(position);
        viewHolder.txtTime.setText(dataBean.getCreate_time());
        viewHolder.txtMoney.setText("￥" + dataBean.getMoney());
        switch (dataBean.getStatus()) {
            case "0":
                viewHolder.txtStatus.setText("已提交");
                break;
            case "1":
                viewHolder.txtStatus.setText("已发放");
                break;
            case "2":
                viewHolder.txtStatus.setText("未通过");
                break;
        }


        return convertView;
    }


    static class ViewHolder {
        @BindView(R.id.txt_time)
        TextView txtTime;
        @BindView(R.id.txt_money)
        TextView txtMoney;
        @BindView(R.id.txt_status)
        TextView txtStatus;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
