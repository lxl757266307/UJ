package com.example.maintainsteward2.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.maintainsteward2.R;
import com.example.maintainsteward2.bean.FensiBean;
import com.example.maintainsteward2.bean.NextFenSiBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/10/7.
 */

public class NextFenSiAdapter extends BaseAbstactRecycleAdapter<NextFenSiBean.DataBeanX.DataBean, NextFenSiAdapter.ViewHolder> {

    Context context;


    public NextFenSiAdapter(Context context) {
        this.context = context;
    }

    @Override
    public void getViewHolder(ViewHolder baseViewHolder, int position, List<NextFenSiBean.DataBeanX.DataBean> mList) {

        NextFenSiBean.DataBeanX.DataBean dataBean = mList.get(position);
        baseViewHolder.txtName.setText(dataBean.getUser_nicename());
        baseViewHolder.txtTime.setText(dataBean.getCreate_time());
    }

    @Override
    public ViewHolder creatHolder(ViewGroup parent) {
        View v = LayoutInflater.from(context).inflate(R.layout.rv_next_fensi_item, parent, false);
        return new ViewHolder(v);
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.txt_name)
        TextView txtName;
        @BindView(R.id.txt_time)
        TextView txtTime;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
