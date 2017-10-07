package com.example.maintainsteward2.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.maintainsteward2.R;
import com.example.maintainsteward2.bean.FensiBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/10/7.
 */

public class FenSiAdapter extends BaseAbstactRecycleAdapter<FensiBean.DataBeanX.DataBean, FenSiAdapter.ViewHolder> {

    Context context;


    public FenSiAdapter(Context context) {
        this.context = context;
    }

    @Override
    public void getViewHolder(ViewHolder baseViewHolder, final int position, List<FensiBean.DataBeanX.DataBean> mList) {

        FensiBean.DataBeanX.DataBean dataBean = mList.get(position);
        baseViewHolder.txtName.setText(dataBean.getUser_nicename());
        baseViewHolder.txtMoney.setText("￥" + dataBean.getTotal_money());
        baseViewHolder.txtNumber.setText(dataBean.getCount());
        baseViewHolder.txtFensiNumber.setText(dataBean.getChild_count());
        baseViewHolder.txtFensiNumber.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onFenSiClickListener != null) {
                    onFenSiClickListener.onFenSiClick(position);
                }
            }
        });
    }

    @Override
    public ViewHolder creatHolder(ViewGroup parent) {

        View v = LayoutInflater.from(context).inflate(R.layout.rv_item_fensi, parent, false);
        return new ViewHolder(v);
    }

    OnFenSiClickListener onFenSiClickListener;

    public void setOnFenSiClickListener(OnFenSiClickListener onFenSiClickListener) {
        this.onFenSiClickListener = onFenSiClickListener;
    }

    public interface OnFenSiClickListener {

        void onFenSiClick(int position);
    }


    static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.txt_fensi_number)
        TextView txtFensiNumber;
        @BindView(R.id.img_fensijibie)
        ImageView imgFensijibie;
        @BindView(R.id.txt_name)
        TextView txtName;
        @BindView(R.id.txt_number)
        TextView txtNumber;
        @BindView(R.id.txt_money)
        TextView txtMoney;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
