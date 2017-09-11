package com.example.maintainsteward.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.maintainsteward.R;
import com.example.maintainsteward.bean.SearchKeyWordBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/9/6.
 */

public class SearviceInfoAdapter extends BaseAdapter {

    Context context;
    List<SearchKeyWordBean.DataBean> data;

    public SearviceInfoAdapter(Context context) {
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
            convertView = LayoutInflater.from(context).inflate(R.layout.service_info_item, parent, false);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.txtNameServiceInfoItem.setText(data.get(position).getName());
        viewHolder.txtPriceServiceInfoItem.setText(data.get(position).getMin_price());
//        viewHolder.txtUnitServiceInfoItem.setText(data.get(position).get);



        return convertView;
    }

    static class ViewHolder {
        @BindView(R.id.txt_name_service_info_item)
        TextView txtNameServiceInfoItem;
        @BindView(R.id.img_bao_service_info_item)
        ImageView imgBaoServiceInfoItem;
        @BindView(R.id.txt_price_service_info_item)
        TextView txtPriceServiceInfoItem;
        @BindView(R.id.txt_unit_service_info_item)
        TextView txtUnitServiceInfoItem;
        @BindView(R.id.img_reduce_service_info_item)
        ImageView imgReduceServiceInfoItem;
        @BindView(R.id.txt_number_service_info_item)
        TextView txtNumberServiceInfoItem;
        @BindView(R.id.img_add_service_info_item)
        ImageView imgAddServiceInfoItem;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
