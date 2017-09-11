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
import com.example.maintainsteward.bean.SecondKindsBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/9/11.
 */

public class SecondKindsAdapter extends BaseAbstactRecycleAdapter<SecondKindsBean.DataBean.ResultBean, SecondKindsAdapter.ViewHolder> {

    Context context;


    public SecondKindsAdapter(Context context) {
        this.context = context;
    }


    @Override
    public void getViewHolder(ViewHolder baseViewHolder, final int position, List<SecondKindsBean.DataBean.ResultBean> list) {

        Glide.with(context).load(list.get(position).getLogourl()).into(baseViewHolder.imgSecondKindsItem);
        baseViewHolder.txtSecondKindsItem.setText(list.get(position).getName());
        baseViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (onSecondeItemClickListener != null) {

                    onSecondeItemClickListener.onItemClick(position);
                }

            }
        });

    }

    @Override
    public ViewHolder creatHolder(ViewGroup parent) {

        View view = LayoutInflater.from(context).inflate(R.layout.second_item, parent, false);
        return new ViewHolder(view);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.img_second_kinds_item)
        ImageView imgSecondKindsItem;
        @BindView(R.id.txt_second_kinds_item)
        TextView txtSecondKindsItem;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public void setOnSecondeItemClickListener(OnSecondeItemClickListener onSecondeItemClickListener) {
        this.onSecondeItemClickListener = onSecondeItemClickListener;
    }

    OnSecondeItemClickListener onSecondeItemClickListener;

    public interface OnSecondeItemClickListener {

        void onItemClick(int position);
    }
}
