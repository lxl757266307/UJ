package com.example.maintainsteward2.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.maintainsteward2.R;
import com.example.maintainsteward2.bean.ZiXuanListBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/10/8.
 */

public class ZiXuanXinXiListAdapter extends BaseAdapter {

    Context context;
    List<ZiXuanListBean.DataBean.SetMealDataBean> list;

    public ZiXuanXinXiListAdapter(Context context) {
        this.context = context;
    }

    public List<ZiXuanListBean.DataBean.SetMealDataBean> getList() {
        return list;
    }

    public void setList(List<ZiXuanListBean.DataBean.SetMealDataBean> list) {
        this.list = list;
    }

    @Override
    public int getCount() {
        return list == null ? 0 : list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.zixuanxinxi_list_item, parent, false);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();

        }
        ZiXuanListBean.DataBean.SetMealDataBean setMealDataBean = list.get(position);
        viewHolder.txtName.setText(setMealDataBean.getName());
        viewHolder.txtNumber.setText("X" + setMealDataBean.getCount());
        viewHolder.txtPrice.setText("￥" + setMealDataBean.getExpenses());


        return convertView;
    }

    static class ViewHolder {
        @BindView(R.id.txt_name)
        TextView txtName;
        @BindView(R.id.txt_number)
        TextView txtNumber;
        @BindView(R.id.txt_price)
        TextView txtPrice;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
