package com.example.maintainsteward2.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.maintainsteward2.R;
import com.example.maintainsteward2.bean.SystemInfoBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/11/21.
 */

public class SystemInfoAdapter extends BaseAbstactRecycleAdapter<SystemInfoBean.DataBean, SystemInfoAdapter.ViewHolder> {


    @Override
    public void getViewHolder(ViewHolder baseViewHolder, int position, List<SystemInfoBean.DataBean> mList) {

        SystemInfoBean.DataBean dataBean = mList.get(position);
        baseViewHolder.txtMessage.setText(dataBean.getContent());
        baseViewHolder.txtTitle.setText(dataBean.getTitle());
        baseViewHolder.txtTime.setText(dataBean.getAdd_time());

    }

    @Override
    public ViewHolder creatHolder(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_system_info, parent, false);
        return new ViewHolder(view);
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.txt_title)
        TextView txtTitle;
        @BindView(R.id.txt_message)
        TextView txtMessage;
        @BindView(R.id.txt_time)
        TextView txtTime;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
