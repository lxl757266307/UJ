package com.example.maintainsteward2.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.maintainsteward2.R;
import com.example.maintainsteward2.bean.MyHongBaoListBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/10/24.
 */

public class MyHongBaoListAdapter extends BaseAbstactRecycleAdapter<MyHongBaoListBean.DataBean, MyHongBaoListAdapter.ViewHolder> {


    Context context;


    public MyHongBaoListAdapter(Context context) {
        this.context = context;
    }

    @Override
    public void getViewHolder(ViewHolder baseViewHolder, final int position, List<MyHongBaoListBean.DataBean> mList) {

        MyHongBaoListBean.DataBean dataBean = mList.get(position);
        String end_time_format = dataBean.getEnd_time_format();
        String money = dataBean.getMoney();

        baseViewHolder.txtPrice.setText(money);
        baseViewHolder.txtTime.setText(end_time_format + "到期");

        baseViewHolder.txtUse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (onUseOnClickListener != null) {
                    onUseOnClickListener.onUseClick(position);
                }

            }
        });

    }

    @Override
    public ViewHolder creatHolder(ViewGroup parent) {
        View view = LayoutInflater.from(context).inflate(R.layout.rv_myhongbao_item, parent, false);

        return new ViewHolder(view);
    }

    OnUseOnClickListener onUseOnClickListener;

    public void setOnUseOnClickListener(OnUseOnClickListener onUseOnClickListener) {
        this.onUseOnClickListener = onUseOnClickListener;
    }

    public interface OnUseOnClickListener {


        void onUseClick(int position);

    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.txt_danwei)
        TextView txtDanwei;
        @BindView(R.id.txt_price)
        TextView txtPrice;
        @BindView(R.id.txt_name)
        TextView txtName;
        @BindView(R.id.txt_time)
        TextView txtTime;
        @BindView(R.id.txt_use)
        TextView txtUse;
        @BindView(R.id.layout_yuanyin)
        LinearLayout layoutYuanyin;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
