package com.example.maintainsteward.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.maintainsteward.R;
import com.example.maintainsteward.bean.CityListBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/8/11.
 */

public class DialogFragmentDistrictListViewAdapter extends BaseAdapter {
    List<CityListBean.DataBean.DistrictBean> mData;
    Context mContext;

    public DialogFragmentDistrictListViewAdapter(List<CityListBean.DataBean.DistrictBean> mData, Context mContext) {
        this.mData = mData;
        this.mContext = mContext;
    }

    public void setmData(List<CityListBean.DataBean.DistrictBean> mData) {
        this.mData = mData;
    }

    @Override
    public int getCount() {
        return mData == null ? 0 : mData.size();
    }

    @Override
    public Object getItem(int position) {
        return mData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.lv_item, parent, false);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.txtLvItem.setText(mData.get(position).getDistrict_name());

        if (mData.get(position).isCheck()) {
            viewHolder.txtLvItem.setTextColor(Color.parseColor("#da0a0a"));
        } else {
            viewHolder.txtLvItem.setTextColor(Color.parseColor("#181818"));
        }
        return convertView;
    }


    static class ViewHolder {
        @BindView(R.id.txt_lv_item)
        TextView txtLvItem;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
