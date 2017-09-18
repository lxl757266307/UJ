package com.example.maintainsteward.adapter;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.maintainsteward.R;
import com.example.maintainsteward.bean.OrderListBean;
import com.example.maintainsteward.utils.ToolUitls;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import in.srain.cube.views.ptr.PtrClassicFrameLayout;

/**
 * Created by Administrator on 2017/9/18.
 */

public class OrderListFragment extends Fragment {

    Unbinder unbinder;
    @BindView(R.id.lv_order_list)
    ListView lvOrderList;
    @BindView(R.id.prt_framelayout)
    PtrClassicFrameLayout prtFramelayout;

    List<OrderListBean.DataBean.DemandOrderDataBean> demand_order_data;

    public void setDemand_order_data(List<OrderListBean.DataBean.DemandOrderDataBean> demand_order_data) {
        this.demand_order_data = demand_order_data;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.fragment_order_list, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    OrderListAdapter orderListAdapter;

    public static final String TAG = "OrderListFragment";

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        orderListAdapter = new OrderListAdapter();
        if (demand_order_data != null) {
            ToolUitls.print(TAG, "demand_order_data=" + demand_order_data.size());
            orderListAdapter.setContext(getActivity());
            orderListAdapter.setDemand_order_data(demand_order_data);
            lvOrderList.setAdapter(orderListAdapter);
            orderListAdapter.notifyDataSetChanged();
        }

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }


}
