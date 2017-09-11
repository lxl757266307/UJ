package com.example.maintainsteward.adapter;

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.maintainsteward.R;
import com.example.maintainsteward.bean.FirstKindsBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/9/11.
 */

public class FragmentKindsLeftMenuAdapter extends BaseAdapter {
    Context context;
    List<FirstKindsBean.DataBean> data;

    public FragmentKindsLeftMenuAdapter(Context context, List<FirstKindsBean.DataBean> data) {
        this.context = context;
        this.data = data;
    }


    public void setData(List<FirstKindsBean.DataBean> data) {
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

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.text_view, parent, false);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        if (data.get(position).isCheck()) {
            viewHolder.txtLeftmenuFragmentKinds.setBackground(context.getResources().getDrawable(R.drawable.border_left));
            viewHolder.txtLeftmenuFragmentKinds.setTextColor(context.getResources().getColor(R.color.red));
        } else {
            viewHolder.txtLeftmenuFragmentKinds.setBackground(context.getResources().getDrawable(R.drawable.text_view_bg));
            viewHolder.txtLeftmenuFragmentKinds.setTextColor(context.getResources().getColor(R.color.black));
        }
        viewHolder.txtLeftmenuFragmentKinds.setText(data.get(position).getName());
        return convertView;
    }

    static class ViewHolder {
        @BindView(R.id.txt_left_menu_fragment_kinds)
        TextView txtLeftmenuFragmentKinds;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
