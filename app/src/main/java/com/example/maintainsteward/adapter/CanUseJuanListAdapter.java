package com.example.maintainsteward.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.maintainsteward.R;
import com.example.maintainsteward.bean.CanUseYouHuiQuanBean;
import com.example.maintainsteward.bean.KaJuanBean;
import com.example.maintainsteward.utils.ToolUitls;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/9/21.
 */

public class CanUseJuanListAdapter extends BaseAbstactRecycleAdapter<CanUseYouHuiQuanBean.DataBean, CanUseJuanListAdapter.ViewHolder> {

    Context context;
    public static final String TAG = "KaJuanListAdapter";
    int statusCode;

    public CanUseJuanListAdapter(Context context, int statusCode) {
        this.context = context;
        this.statusCode = statusCode;
    }

    @Override
    public void getViewHolder(ViewHolder baseViewHolder, final int position, List<CanUseYouHuiQuanBean.DataBean> mList) {

        CanUseYouHuiQuanBean.DataBean resultDataBean = mList.get(position);

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
        ToolUitls.print(TAG, "bonus_desc===" + bonus_desc);
        baseViewHolder.txtDesc.setText(bonus_desc);
        if (status.equals("0")) {

            switch (bonus_type) {
                case "1":
                    baseViewHolder.layoutBg.setBackgroundResource(R.mipmap.youhuijuanse);
                    baseViewHolder.txtName.setText("优惠券");
                    break;
                case "2":
                    baseViewHolder.layoutBg.setBackgroundResource(R.mipmap.manjianse);
                    baseViewHolder.txtName.setText("满减券");
                    break;
                case "3":
                    baseViewHolder.layoutBg.setBackgroundResource(R.mipmap.zhuanshujuanse);
                    if (!cat_id.equals("0")) {
                        baseViewHolder.txtName.setText("专属满减券" + bonus_condition);
                    } else {
                        baseViewHolder.txtName.setText("专属满减券");
                    }

                    break;
            }

        } else {
            baseViewHolder.layoutBg.setBackgroundResource(R.mipmap.yiguoqise);
            baseViewHolder.imgStatus.setBackgroundResource(R.mipmap.yishiyong);

            switch (bonus_type) {
                case "1":
                    baseViewHolder.txtName.setText("优惠券");
                    break;
                case "2":
                    baseViewHolder.txtName.setText("满减券");
                    break;
                case "3":
                    if (!cat_id.equals("0")) {
                        baseViewHolder.txtName.setText("专属满减券" + bonus_condition);
                    } else {
                        baseViewHolder.txtName.setText("专属满减券");
                    }

                    break;
            }

        }
        if (statusCode == 1) {
            baseViewHolder.imgStatus.setBackgroundResource(R.mipmap.yiguoqi);
        }

        baseViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (onKaJuanItemClickListener != null) {
                    onKaJuanItemClickListener.onItemClickItem(position);
                }
            }
        });

    }

    @Override
    public ViewHolder creatHolder(ViewGroup parent) {

        View view = LayoutInflater.from(context).inflate(R.layout.kajuan_list_item, parent, false);

        return new ViewHolder(view);
    }

    OnKaJuanItemClickListener onKaJuanItemClickListener;

    public void setOnKaJuanItemClickListener(OnKaJuanItemClickListener onKaJuanItemClickListener) {
        this.onKaJuanItemClickListener = onKaJuanItemClickListener;
    }

    public interface OnKaJuanItemClickListener {

        void onItemClickItem(int postion);
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.txt_name)
        TextView txtName;
        @BindView(R.id.txt_desc)
        TextView txtDesc;
        @BindView(R.id.txt_money)
        TextView txtMoney;
        @BindView(R.id.txt_time)
        TextView txtTime;
        @BindView(R.id.img_status)
        ImageView imgStatus;
        @BindView(R.id.layout_bg)
        RelativeLayout layoutBg;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
