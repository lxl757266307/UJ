package com.example.maintainsteward.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.maintainsteward.R;
import com.example.maintainsteward.bean.ServiceGoodsGetBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/9/12.
 */

public class ServicePeiJianAdapter extends BaseAdapter {

    Context context;

    List<ServiceGoodsGetBean.DataBean> peiJian;

    public ServicePeiJianAdapter(Context context, List<ServiceGoodsGetBean.DataBean> peiJian) {
        this.context = context;
        this.peiJian = peiJian;
    }

    public void setPeiJian(List<ServiceGoodsGetBean.DataBean> peiJian) {
        this.peiJian = peiJian;
    }

    @Override
    public int getCount() {
        return peiJian == null ? 0 : peiJian.size();
    }

    @Override
    public Object getItem(int position) {
        return peiJian.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        ViewHolder viewHolder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.peijian_item, parent, false);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.txtNamePeijianitem.setText(peiJian.get(position).getName());

        viewHolder.txtPricePeijianitem.setText("￥ " + peiJian.get(position).getPrice());

        int number = peiJian.get(position).getNumber();
        if (number == 0) {
            viewHolder.txtNumberPeijianitem.setVisibility(View.INVISIBLE);
            viewHolder.imgReducePeijianitem.setVisibility(View.INVISIBLE);
        } else {
            viewHolder.txtNumberPeijianitem.setText(number + "");
            viewHolder.txtNumberPeijianitem.setVisibility(View.VISIBLE);
            viewHolder.imgReducePeijianitem.setVisibility(View.VISIBLE);
        }


        viewHolder.imgAddPeijianitem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (onPeiJianNumberChangeListener != null) {

                    onPeiJianNumberChangeListener.addPeiJian(position);
                }
            }
        });
        viewHolder.imgReducePeijianitem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onPeiJianNumberChangeListener != null) {

                    int number = peiJian.get(position).getNumber();

                    if (number > 0) {
                        onPeiJianNumberChangeListener.reducePeiJian(position);
                    } else {
                        return;
                    }

                }
            }
        });


        return convertView;
    }

    OnPeiJianNumberChangeListener onPeiJianNumberChangeListener;

    public void setOnPeiJianNumberChangeListener(OnPeiJianNumberChangeListener onPeiJianNumberChangeListener) {
        this.onPeiJianNumberChangeListener = onPeiJianNumberChangeListener;
    }

    public interface OnPeiJianNumberChangeListener {


        void addPeiJian(int position);

        void reducePeiJian(int position);
    }

    static class ViewHolder {
        @BindView(R.id.txt_name_peijianitem)
        TextView txtNamePeijianitem;
        @BindView(R.id.txt_price_peijianitem)
        TextView txtPricePeijianitem;
        @BindView(R.id.img_reduce_peijianitem)
        ImageView imgReducePeijianitem;
        @BindView(R.id.txt_number_peijianitem)
        TextView txtNumberPeijianitem;
        @BindView(R.id.img_add_peijianitem)
        ImageView imgAddPeijianitem;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
