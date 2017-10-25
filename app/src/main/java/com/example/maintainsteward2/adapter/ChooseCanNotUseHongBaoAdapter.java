package com.example.maintainsteward2.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.maintainsteward2.R;
import com.example.maintainsteward2.bean.HongBaoBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/10/25.
 */

public class ChooseCanNotUseHongBaoAdapter extends BaseAdapter {

    Context context;
    List<HongBaoBean.DataBean.LuckMoneyNoBean> luck_money_no;

    public void setLuck_money_no(List<HongBaoBean.DataBean.LuckMoneyNoBean> luck_money_no) {
        this.luck_money_no = luck_money_no;
    }

    public ChooseCanNotUseHongBaoAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return luck_money_no == null ? 0 : luck_money_no.size();
    }

    @Override
    public Object getItem(int position) {
        return luck_money_no.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;

        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.rv_hongbao_item, parent, false);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        HongBaoBean.DataBean.LuckMoneyNoBean luckMoneyNoBean = luck_money_no.get(position);
        String end_time_format = luckMoneyNoBean.getEnd_time_format();
        String money = luckMoneyNoBean.getMoney();

        viewHolder.txtPrice.setText(money);
        viewHolder.txtTime.setText(end_time_format + "到期");
        viewHolder.txtPrice.setTextColor(Color.parseColor("#bbbbbb"));
        viewHolder.txtName.setTextColor(Color.parseColor("#bbbbbb"));
        return convertView;
    }

//    public interface

    static class ViewHolder {
        @BindView(R.id.txt_danwei)
        TextView txtDanwei;
        @BindView(R.id.txt_price)
        TextView txtPrice;
        @BindView(R.id.txt_name)
        TextView txtName;
        @BindView(R.id.txt_time)
        TextView txtTime;
        @BindView(R.id.txt_yuanyin)
        TextView txtYuanyin;
        @BindView(R.id.txt_decfription)
        TextView txtDecfription;
        @BindView(R.id.layout_yuanyin)
        LinearLayout layoutYuanyin;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
