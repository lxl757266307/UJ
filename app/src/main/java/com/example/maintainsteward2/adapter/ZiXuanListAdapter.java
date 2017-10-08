package com.example.maintainsteward2.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.maintainsteward2.R;
import com.example.maintainsteward2.bean.ZiXuanListBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/10/8.
 */

public class ZiXuanListAdapter extends BaseAdapter {

    Context context;
    List<ZiXuanListBean.DataBean.SetMealDataBean> set_meal_data;

    public ZiXuanListAdapter(Context context) {
        this.context = context;

    }

    public List<ZiXuanListBean.DataBean.SetMealDataBean> getSet_meal_data() {
        return set_meal_data;
    }

    public void setSet_meal_data(List<ZiXuanListBean.DataBean.SetMealDataBean> set_meal_data) {
        this.set_meal_data = set_meal_data;
    }

    @Override
    public int getCount() {
        return set_meal_data == null ? 0 : set_meal_data.size();
    }

    @Override
    public Object getItem(int position) {
        return set_meal_data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.zixuan_item, parent, false);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        final ZiXuanListBean.DataBean.SetMealDataBean setMealDataBean = set_meal_data.get(position);

        viewHolder.txtName.setText(setMealDataBean.getName());
        viewHolder.txtPrice.setText("￥" + setMealDataBean.getExpenses() + "元" + setMealDataBean.getUnit());

        if (setMealDataBean.getCount() == 0) {
            viewHolder.imgReduce.setVisibility(View.INVISIBLE);
            viewHolder.txtNumber.setText("0");
        } else {
            viewHolder.imgReduce.setVisibility(View.VISIBLE);
            viewHolder.txtNumber.setText(setMealDataBean.getCount() + "");
        }

        viewHolder.imgReduce.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (setMealDataBean.getCount() == 0) {
                    return;
                } else {

                    if (onServiceChangeListener != null) {
                        onServiceChangeListener.reduceService(position);
                    }
                }

            }
        });
        viewHolder.imgAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (onServiceChangeListener != null) {
                    onServiceChangeListener.addService(position);
                }
            }
        });

        return convertView;
    }

    OnServiceChangeListener onServiceChangeListener;

    public void setOnServiceChangeListener(OnServiceChangeListener onServiceChangeListener) {
        this.onServiceChangeListener = onServiceChangeListener;
    }

    public interface OnServiceChangeListener {

        void addService(int position);

        void reduceService(int position);

    }


    static class ViewHolder {
        @BindView(R.id.txt_name)
        TextView txtName;
        @BindView(R.id.txt_price)
        TextView txtPrice;
        @BindView(R.id.img_reduce)
        ImageView imgReduce;
        @BindView(R.id.txt_number)
        TextView txtNumber;
        @BindView(R.id.img_add)
        ImageView imgAdd;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
