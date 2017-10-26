package com.example.maintainsteward2.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.maintainsteward2.R;
import com.example.maintainsteward2.bean.CanUseYouHuiQuanBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/9/21.
 */

public class CanNotUseJuanListAdapter extends BaseAbstactRecycleAdapter<CanUseYouHuiQuanBean.DataBean.BonusNoBean, CanNotUseJuanListAdapter.ViewHolder> {

    Context context;
    public static final String TAG = "KaJuanListAdapter";
    int statusCode;

    String money;


    public void setMoney(String money) {
        this.money = money;
    }

    public CanNotUseJuanListAdapter(Context context, int statusCode) {
        this.context = context;
        this.statusCode = statusCode;
    }


    @Override
    public void getViewHolder(ViewHolder baseViewHolder, final int position, List<CanUseYouHuiQuanBean.DataBean.BonusNoBean> mList) {

        CanUseYouHuiQuanBean.DataBean.BonusNoBean resultDataBean = mList.get(position);

        String status = resultDataBean.getStatus();
        String bonus_amount = resultDataBean.getBonus_amount();
        String bonus_type = resultDataBean.getBonus_type();
        String bonus_condition = resultDataBean.getBonus_condition();
        String bonus_desc = resultDataBean.getBonus_desc();
        String start_time_format = resultDataBean.getStart_time_format();
        String end_time_format = resultDataBean.getEnd_time_format();
        String cat_id = resultDataBean.getCat_id();
        baseViewHolder.txtTime.setText(start_time_format + "---" + end_time_format);
        baseViewHolder.txtMoney.setText("￥ " + bonus_amount);

        baseViewHolder.txtCondition.setText("满"+money+"元即可使用");
        baseViewHolder.layoutBg.setBackgroundResource(R.mipmap.yiguoqise);
        baseViewHolder.txtYunayin.setVisibility(View.VISIBLE);
        baseViewHolder.txtYunayin.setText("不可用原因:未满" + money + "不可使用");

    }

    @Override
    public ViewHolder creatHolder(ViewGroup parent) {

        View view = LayoutInflater.from(context).inflate(R.layout.rv_youhuijuan_item, parent, false);

        return new ViewHolder(view);
    }


    static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.txt_condition)
        TextView txtCondition;
        @BindView(R.id.txt_money)
        TextView txtMoney;
        @BindView(R.id.txt_time)
        TextView txtTime;
        @BindView(R.id.layout_bg)
        LinearLayout layoutBg;
        @BindView(R.id.txt_yunayin)
        TextView txtYunayin;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
