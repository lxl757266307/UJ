package com.example.maintainsteward.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.maintainsteward.R;
import com.example.maintainsteward.bean.SearviceInfoBean;
import com.example.maintainsteward.bean.ServiceGoodsGetBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/9/15.
 */

public class OrderPeiJianListAdapter extends BaseAdapter {

    List<ServiceGoodsGetBean.DataBean> data;

    Context context;

    public OrderPeiJianListAdapter(List<ServiceGoodsGetBean.DataBean> data, Context context) {
        this.data = data;
        this.context = context;
    }

    @Override
    public int getCount() {
        return data == null ? 0 : data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;

        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.service_and_peijian_item, parent, false);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.txtNameLijiyuyue.setTextColor(Color.parseColor("#bbbbbb"));
        viewHolder.txtCountLijiyuyue.setTextColor(Color.parseColor("#bbbbbb"));
        viewHolder.txtPriceLijiyuyue.setTextColor(Color.parseColor("#bbbbbb"));
        viewHolder.txtNameLijiyuyue.setText(data.get(position).getName());
        viewHolder.txtCountLijiyuyue.setText("×" + data.get(position).getNumber());
        viewHolder.txtPriceLijiyuyue.setText("￥" + data.get(position).getPrice());
        return convertView;
    }

    static class ViewHolder {
        @BindView(R.id.txt_name_lijiyuyue)
        TextView txtNameLijiyuyue;
        @BindView(R.id.txt_count_lijiyuyue)
        TextView txtCountLijiyuyue;
        @BindView(R.id.txt_price_lijiyuyue)
        TextView txtPriceLijiyuyue;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
