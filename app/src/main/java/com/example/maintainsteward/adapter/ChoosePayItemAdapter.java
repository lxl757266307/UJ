package com.example.maintainsteward.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;

import com.example.maintainsteward.R;
import com.example.maintainsteward.bean.ChooseBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/9/26.
 */

public class ChoosePayItemAdapter extends BaseAbstactRecycleAdapter<ChooseBean, ChoosePayItemAdapter.ViewHolder> {

    Context context;


    public ChoosePayItemAdapter(Context context) {
        this.context = context;
    }

    @Override
    public void getViewHolder(ViewHolder baseViewHolder, final int position, List<ChooseBean> mList) {


        boolean check = mList.get(position).isCheck();

        if (check) {
            baseViewHolder.imgXuanzhong.setVisibility(View.VISIBLE);
            baseViewHolder.imgXuanzhong.setChecked(true);
        }


        baseViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (onItemClickListener != null) {
                    onItemClickListener.onItemClicl(position);
                }
            }
        });

    }

    @Override
    public ViewHolder creatHolder(ViewGroup parent) {

        View view = LayoutInflater.from(context).inflate(R.layout.pay_choose_item, parent, false);

        return new ViewHolder(view);
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.img_xuanzhong)
        CheckBox imgXuanzhong;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    OnItemClickListener onItemClickListener;

    public interface OnItemClickListener {

        void onItemClicl(int postion);
    }
}
