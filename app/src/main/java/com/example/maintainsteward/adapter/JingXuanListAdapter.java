package com.example.maintainsteward.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.maintainsteward.R;
import com.example.maintainsteward.bean.JingXuanBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/9/23.
 */

public class JingXuanListAdapter extends BaseAdapter {

    Context context;
    List<JingXuanBean.DataBean> data;


    public void setData(List<JingXuanBean.DataBean> data) {
        this.data = data;
    }

    public JingXuanListAdapter(Context context) {
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

    ViewHolder viewHolder = null;

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.lv_jingxuan_item, parent, false);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
            viewHolder.imgLogo.setTag(position);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        int index = (int) viewHolder.imgLogo.getTag();

        if (index == 0) {
            viewHolder.imgLogo.setImageResource(R.mipmap.x1);
        } else if (index == 1) {
            viewHolder.imgLogo.setImageResource(R.mipmap.x2);
        } else if (index == 2) {
            viewHolder.imgLogo.setImageResource(R.mipmap.x3);
        } else {
            viewHolder.imgLogo.setVisibility(View.GONE);
            viewHolder.txtNo.setVisibility(View.VISIBLE);
            viewHolder.txtNo.setBackgroundResource(R.mipmap.x4);
            viewHolder.txtNo.setText(data.get(position).getIndex() + 1 + "");
        }


        Glide.with(context).load(data.get(position).getLogourl()).into(viewHolder.imgPhoto);
        String name = data.get(position).getName();

        viewHolder.txtName1.setText(name);

        if (name != null && name.contains("清洗")) {
            viewHolder.txtBao.setVisibility(View.INVISIBLE);
            viewHolder.imgBao.setVisibility(View.INVISIBLE);
        }
        String min_price = data.get(position).getMin_price();

        viewHolder.txtPrice.setText(min_price);
//        viewHolder.layoutView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                if (onItemClickListener != null) {
//                    onItemClickListener.onItemClick(position);
//                }
//            }
//        });

        return convertView;
    }

//    OnItemClickListener onItemClickListener;
//
//    public interface OnItemClickListener {
//        void onItemClick(int position);
//    }

    static class ViewHolder {
        @BindView(R.id.layout_view)
        LinearLayout layoutView;
        @BindView(R.id.txt_no)
        TextView txtNo;
        @BindView(R.id.img_logo)
        ImageView imgLogo;
        @BindView(R.id.img_photo)
        ImageView imgPhoto;
        @BindView(R.id.txt_name1)
        TextView txtName1;

        @BindView(R.id.img_bao)
        ImageView imgBao;
        @BindView(R.id.txt_bao)
        TextView txtBao;
        @BindView(R.id.txt_price)
        TextView txtPrice;
        @BindView(R.id.ttx_buhaohancailiaofei)
        TextView ttxBuhaohancailiaofei;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
