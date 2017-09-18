package com.example.maintainsteward.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.maintainsteward.R;
import com.example.maintainsteward.bean.OrderListBean;
import com.example.maintainsteward.utils.ToolUitls;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/9/18.
 */

public class OrderListAdapter extends BaseAdapter {

    Context context;
    List<OrderListBean.DataBean.DemandOrderDataBean> demand_order_data;
    public static final String TAG = "OrderListAdapter";

    public OrderListAdapter() {
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public void setDemand_order_data(List<OrderListBean.DataBean.DemandOrderDataBean> demand_order_data) {
        this.demand_order_data = demand_order_data;
    }

    @Override
    public int getCount() {
        return demand_order_data == null ? 0 : demand_order_data.size();
    }

    @Override
    public Object getItem(int position) {
        return demand_order_data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder viewHolder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.lv_order_list_item, parent, false);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        OrderListBean.DataBean.DemandOrderDataBean demandOrderDataBean = demand_order_data.get(position);
        List<OrderListBean.DataBean.DemandOrderDataBean.ServiceItemBean> service_item = demandOrderDataBean.getService_item();

        ToolUitls.print(TAG, "service_item===" + service_item.size());
        for (int i = 0; i < service_item.size(); i++) {

            if (service_item.get(i) != null) {
                TextView textView = (TextView) LayoutInflater.from(context).inflate(R.layout.order_item_textview, parent, false);
                textView.setText(service_item.get(i).getName() + "X" + service_item.get(i).getNum());
                viewHolder.layoutService.addView(textView);
            }

        }


        viewHolder.txtServiceName.setText(demandOrderDataBean.getName());
        switch (demandOrderDataBean.getOrder_status()) {
            case "1":
                viewHolder.txtServieStatus.setText("已提交");
                break;
            case "2":
                viewHolder.txtServieStatus.setText("预约成功");
                break;
        }

        viewHolder.txtAddress.setText(demandOrderDataBean.getAddress());
        viewHolder.txtTime.setText(demandOrderDataBean.getCreate_time());


        viewHolder.txtQuxiao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });


        return convertView;
    }

    static class ViewHolder {
        @BindView(R.id.layout_service)
        LinearLayout layoutService;
        @BindView(R.id.txt_service_name)
        TextView txtServiceName;
        @BindView(R.id.txt_servie_status)
        TextView txtServieStatus;
        @BindView(R.id.txt_address)
        TextView txtAddress;
        @BindView(R.id.txt_time)
        TextView txtTime;
        @BindView(R.id.txt_quxiao)
        TextView txtQuxiao;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}