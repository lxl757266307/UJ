package com.example.maintainsteward.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.maintainsteward.R;
import com.example.maintainsteward.bean.LogListBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/9/21.
 */

public class LogListAdapter extends BaseAbstactRecycleAdapter<LogListBean.DataBean, LogListAdapter.ViewHolder> {

    Context context;

    public LogListAdapter(Context context) {
        this.context = context;
    }

    @Override
    public void getViewHolder(ViewHolder baseViewHolder, int position, List<LogListBean.DataBean> mList) {

        LogListBean.DataBean dataBean = mList.get(position);
        baseViewHolder.txtName.setText(dataBean.getName());
        baseViewHolder.txtMoney.setText(dataBean.getMoney()+"元");
        baseViewHolder.txtTime.setText(dataBean.getAdd_time());
        baseViewHolder.txtYue.setText("余额:￥"+dataBean.getBalance()+"元");

    }

    @Override
    public ViewHolder creatHolder(ViewGroup parent) {
        View view = LayoutInflater.from(context).inflate(R.layout.log_list_item, parent, false);

        return new ViewHolder(view);
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.txt_name)
        TextView txtName;
        @BindView(R.id.txt_yue)
        TextView txtYue;
        @BindView(R.id.txt_money)
        TextView txtMoney;
        @BindView(R.id.txt_time)
        TextView txtTime;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
