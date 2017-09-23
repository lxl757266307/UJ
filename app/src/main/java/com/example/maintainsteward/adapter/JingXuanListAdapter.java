package com.example.maintainsteward.adapter;

import android.content.Context;
import android.graphics.Color;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
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

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.lv_jingxuan_item, parent, false);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        switch (position) {
            case 1:
                viewHolder.imgLogo.setImageResource(R.mipmap.x1);
                break;
            case 2:
                viewHolder.imgLogo.setImageResource(R.mipmap.x2);
                break;
            case 3:
                viewHolder.imgLogo.setImageResource(R.mipmap.x3);
                break;
            default:
                viewHolder.imgLogo.setVisibility(View.GONE);
                viewHolder.txtNo.setVisibility(View.VISIBLE);
                viewHolder.txtNo.setBackgroundResource(R.mipmap.x4);
                viewHolder.txtNo.setText(position + "");
                break;
        }

        Glide.with(context).load(data.get(position).getLogourl()).into(viewHolder.imgPhoto);
        String name = data.get(position).getName();
        SpannableString spannableString = new SpannableString(name.substring(0, name.length() - 2));

        spannableString.setSpan(new AbsoluteSizeSpan(20), 10, 12, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        spannableString.setSpan(new ForegroundColorSpan(Color.parseColor("#da0a0a")), 16, 18, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        viewHolder.txtName.setText(spannableString + name.substring(name.length() - 2));

        if (name.contains("清洗")) {
            viewHolder.txtBao.setVisibility(View.INVISIBLE);
            viewHolder.imgBao.setVisibility(View.INVISIBLE);
        }
        String min_price = data.get(position).getMin_price();

        SpannableString spannableString1=new SpannableString("￥");


        return convertView;
    }

    static class ViewHolder {
        @BindView(R.id.txt_no)
        TextView txtNo;
        @BindView(R.id.img_logo)
        ImageView imgLogo;
        @BindView(R.id.img_photo)
        ImageView imgPhoto;
        @BindView(R.id.txt_name)
        TextView txtName;
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
