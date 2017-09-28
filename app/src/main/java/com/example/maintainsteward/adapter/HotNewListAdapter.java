package com.example.maintainsteward.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.maintainsteward.R;
import com.example.maintainsteward.bean.HotNewsList;
import com.example.maintainsteward.utils.ToolUitls;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/9/28.
 */

public class HotNewListAdapter extends BaseAbstactRecycleAdapter<HotNewsList.DataBean, HotNewListAdapter.ViewHolder> {

    Context context;


    public HotNewListAdapter(Context context) {
        this.context = context;
    }

    @Override
    public void getViewHolder(ViewHolder baseViewHolder, final int position, List<HotNewsList.DataBean> mList) {
        HotNewsList.DataBean dataBean = mList.get(position);
        Glide.with(context).load(dataBean.getCover()).into(baseViewHolder.imgLogo);
        baseViewHolder.txtDes.setText(dataBean.getSummary()+".....");
        baseViewHolder.txtTime.setText(dataBean.getAdd_time());
        baseViewHolder.txtTitle.setText(dataBean.getTitle());
        baseViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (onItemClickListener != null) {

                    onItemClickListener.onItemClick(position);
                }

            }
        });
    }

    @Override
    public ViewHolder creatHolder(ViewGroup parent) {

        View view = LayoutInflater.from(context).inflate(R.layout.rv_hotnews_item, parent, false);
        return new ViewHolder(view);
    }


    OnItemClickListener onItemClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public interface OnItemClickListener {

        void onItemClick(int postion);
    }


    static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.img_logo)
        ImageView imgLogo;
        @BindView(R.id.txt_title)
        TextView txtTitle;
        @BindView(R.id.txt_des)
        TextView txtDes;
        @BindView(R.id.txt_time)
        TextView txtTime;

        public ViewHolder(View itemView) {
            super(itemView);

            ButterKnife.bind(this, itemView);
        }
    }
}



