package com.example.maintainsteward.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.maintainsteward.R;
import com.example.maintainsteward.bean.SearviceInfoBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/9/6.
 */

public class SearviceInfoAdapter extends BaseAdapter {

    Context context;
    List<SearviceInfoBean.DataBean> data;

    public SearviceInfoAdapter(Context context) {
        this.context = context;
    }

    public void setData(List<SearviceInfoBean.DataBean> data) {
        this.data = data;
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
    public View getView(final int position, View convertView, ViewGroup parent) {

        ViewHolder viewHolder = null;

        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.service_info_item, parent, false);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        String name = data.get(position).getName();
        if (name.contains("清洗")) {
            viewHolder.imgBaoServiceInfoItem.setVisibility(View.INVISIBLE);
        }
        int number = data.get(position).getNumber();
        if (number == 0) {
            viewHolder.txtNumberServiceInfoItem.setVisibility(View.INVISIBLE);
            viewHolder.imgReduceServiceInfoItem.setVisibility(View.INVISIBLE);
        } else {
            viewHolder.txtNumberServiceInfoItem.setVisibility(View.VISIBLE);
            viewHolder.imgReduceServiceInfoItem.setVisibility(View.VISIBLE);
            viewHolder.txtNumberServiceInfoItem.setText(number + "");
        }

        viewHolder.txtNameServiceInfoItem.setText(data.get(position).getName());
        String expenses = data.get(position).getExpenses();
        if ("0".equals(expenses)) {
            viewHolder.txtYang.setVisibility(View.INVISIBLE);
            viewHolder.txtUnitServiceInfoItem.setText("面议");
            viewHolder.txtUnitServiceInfoItem.setTextSize(18);
            viewHolder.txtPriceServiceInfoItem.setVisibility(View.GONE);
        }
        viewHolder.txtPriceServiceInfoItem.setText(expenses);

        viewHolder.txtUnitServiceInfoItem.setText(data.get(position).getUnit());

        viewHolder.imgAddServiceInfoItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (onServiceNumberChangeListener != null) {


                    onServiceNumberChangeListener.add(position);
                }
            }
        });
        viewHolder.imgReduceServiceInfoItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onServiceNumberChangeListener != null) {
                    if (data.get(position).getNumber() > 0) {
                        onServiceNumberChangeListener.reduce(position);
                    } else {
                        return;
                    }
                }
            }
        });


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
        @BindView(R.id.txt_yang)
        TextView txtYang;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }

    OnServiceNumberChangeListener onServiceNumberChangeListener;

    public void setOnServiceNumberChangeListener(OnServiceNumberChangeListener onServiceNumberChangeListener) {
        this.onServiceNumberChangeListener = onServiceNumberChangeListener;
    }

    public interface OnServiceNumberChangeListener {

        void add(int postion);

        void reduce(int postion);

    }


}
