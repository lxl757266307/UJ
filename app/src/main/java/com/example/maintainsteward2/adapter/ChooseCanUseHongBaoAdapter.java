package com.example.maintainsteward2.adapter;

import android.content.Context;
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

public class ChooseCanUseHongBaoAdapter extends BaseAdapter {

    Context context;

    private List<HongBaoBean.DataBean.LuckMoneyOkBean> luck_money_ok;
    ;

    public void setLuck_money_no(List<HongBaoBean.DataBean.LuckMoneyOkBean> luck_money_ok) {
        this.luck_money_ok = luck_money_ok;
    }

    public ChooseCanUseHongBaoAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return luck_money_ok == null ? 0 : luck_money_ok.size();
    }

    @Override
    public Object getItem(int position) {
        return luck_money_ok.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;

        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.rv_hongbao_item, parent, false);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        HongBaoBean.DataBean.LuckMoneyOkBean luckMoneyNoBean = luck_money_ok.get(position);
        String end_time_format = luckMoneyNoBean.getEnd_time_format();
        String money = luckMoneyNoBean.getMoney();

        viewHolder.layoutYuanyin.setVisibility(View.INVISIBLE);
        viewHolder.txtPrice.setText(money);
        viewHolder.txtTime.setText(end_time_format + "到期");

        viewHolder.layoutItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (onItemClickListener != null) {
                    onItemClickListener.onItemClick(position);
                }
            }
        });

        return convertView;
    }

    OnItemClickListener onItemClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public interface OnItemClickListener {

        void onItemClick(int position);
    }

    static class ViewHolder {
        @BindView(R.id.layout_item)
        LinearLayout layoutItem;
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
