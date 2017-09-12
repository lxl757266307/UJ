package com.example.maintainsteward.adapter;

import android.content.Context;
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
 * Created by Administrator on 2017/9/12.
 */

public class DistrictAdapter extends BaseAdapter {

    List<CityListBean.DataBean.DistrictBean> district;
    Context context;

    public DistrictAdapter(List<CityListBean.DataBean.DistrictBean> district, Context context) {
        this.district = district;
        this.context = context;
    }

    @Override
    public int getCount() {
        return district == null ? 0 : district.size();
    }

    @Override
    public Object getItem(int position) {
        return district.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.city_text_view, parent, false);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.txtCaityName.setText(district.get(position).getDistrict_name());
        return convertView;
    }

    static class ViewHolder {
        @BindView(R.id.txt_caity_name)
        TextView txtCaityName;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
