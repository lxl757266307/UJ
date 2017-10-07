package com.example.maintainsteward2.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.maintainsteward2.R;
import com.example.maintainsteward2.bean.PaiHangBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by Administrator on 2017/10/7.
 */

public class PaiHangBangAdapter extends BaseAbstactRecycleAdapter<PaiHangBean.DataBeanX.DataBean, PaiHangBangAdapter.ViewHolder> {

    Context context;


    public PaiHangBangAdapter(Context context) {
        this.context = context;
    }

    @Override
    public void getViewHolder(ViewHolder baseViewHolder, int position, List<PaiHangBean.DataBeanX.DataBean> mList) {

        PaiHangBean.DataBeanX.DataBean dataBean = mList.get(position);
        baseViewHolder.txtRank.setText(dataBean.getRank());
        baseViewHolder.txtName.setText(dataBean.getUser_nicename());
        Glide.with(context).load(dataBean.getAvatar()).into(baseViewHolder.imgPhoto);
        switch (dataBean.getType()) {
            case 1:
                baseViewHolder.txtType.setText("月排行");
                break;
            case 2:
                baseViewHolder.txtType.setText("日排行");
                break;
            case 3:
                baseViewHolder.txtType.setText("总排行");
                break;
        }
        baseViewHolder.txtNumber.setText(dataBean.getCounts());

    }

    @Override
    public ViewHolder creatHolder(ViewGroup parent) {

        View view = LayoutInflater.from(context).inflate(R.layout.rv_paihang_item, parent, false);
        return new ViewHolder(view);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.txt_rank)
        TextView txtRank;
        @BindView(R.id.img_photo)
        CircleImageView imgPhoto;
        @BindView(R.id.txt_name)
        TextView txtName;
        @BindView(R.id.txt_type)
        TextView txtType;
        @BindView(R.id.txt_number)
        TextView txtNumber;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
