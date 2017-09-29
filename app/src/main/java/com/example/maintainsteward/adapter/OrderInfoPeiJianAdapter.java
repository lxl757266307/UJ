package com.example.maintainsteward.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.maintainsteward.R;
import com.example.maintainsteward.bean.OrderInfoBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/9/23.
 */

public class OrderInfoPeiJianAdapter extends BaseAdapter {

    Context context;

    public OrderInfoPeiJianAdapter(Context context) {
        this.context = context;
    }


    List<OrderInfoBean.DataBean.GoodsInfoBean> service;

    public void setService(List<OrderInfoBean.DataBean.GoodsInfoBean> service) {
        this.service = service;
    }

    @Override
    public int getCount() {
        return service == null ? 0 : service.size();
    }

    @Override
    public Object getItem(int position) {
        return service.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder viewHolder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.lv_orderinfo_item, parent, false);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.txtServiceName.setText(service.get(position).getGoods_name());
        viewHolder.txtCount.setText("X" + service.get(position).getGoods_num());
        String goods_price = service.get(position).getGoods_price();
        if (Double.parseDouble(goods_price) == 0) {
            viewHolder.txtPrice.setText("面议");
        } else {
            viewHolder.txtPrice.setText("￥" + goods_price);
        }
        return convertView;
    }


    static class ViewHolder {
        @BindView(R.id.txt_service_name)
        TextView txtServiceName;
        @BindView(R.id.txt_count)
        TextView txtCount;
        @BindView(R.id.txt_price)
        TextView txtPrice;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
