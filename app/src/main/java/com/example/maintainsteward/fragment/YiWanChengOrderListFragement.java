package com.example.maintainsteward.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ListView;

import com.example.maintainsteward.R;
import com.example.maintainsteward.adapter.OrderListAdapter;
import com.example.maintainsteward.base.Contacts;
import com.example.maintainsteward.bean.OrderListBean;
import com.example.maintainsteward.bean.PublicBean;
import com.example.maintainsteward.mvp_presonter.QuXiaoOrderPresonter;
import com.example.maintainsteward.mvp_view.OnOrderCancleListener;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import in.srain.cube.views.ptr.PtrClassicFrameLayout;
import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.PtrHandler2;

/**
 * Created by Administrator on 2017/9/19.
 */

public class YiWanChengOrderListFragement extends Fragment implements PtrHandler2, OrderListAdapter.OnQuXiaoOrderListener, OnOrderCancleListener {
    List<OrderListBean.DataBean.DemandOrderDataBean> demand_order_data;
    @BindView(R.id.lv_order_list)
    ListView lvOrderList;
    @BindView(R.id.prt_framelayout)
    PtrClassicFrameLayout prtFramelayout;
    Unbinder unbinder;


    QuXiaoOrderPresonter presonter;
    OrderListAdapter orderListAdapter;
    boolean canLoad;
    boolean canRefresh;

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


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        presonter = new QuXiaoOrderPresonter();
        presonter.setOnOrderCancleListener(this);
        orderListAdapter = new OrderListAdapter();
        orderListAdapter.setContext(getActivity());
        orderListAdapter.setOnQuXiaoOrderListenerl(this);
        if (demand_order_data != null) {
            orderListAdapter.setDemand_order_data(demand_order_data);
        }
        lvOrderList.setAdapter(orderListAdapter);
        orderListAdapter.notifyDataSetChanged();
        prtFramelayout.setPtrHandler(this);
        lvOrderList.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {

            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                //首先在onScroll方法中判断listview到达底部：
                if ((firstVisibleItem + visibleItemCount) == totalItemCount) {
                    View lastVisibleItemView = view.getChildAt(view.getChildCount() - 1);
                    if (lastVisibleItemView != null) {
                        if (lvOrderList.getHeight() == lastVisibleItemView.getBottom()) {
                            canLoad = true;
                        } else {
                            canLoad = false;
                        }
                    }
                }


                if (firstVisibleItem == 0) {
                    View firstVisibleItemView = view.getChildAt(0);
                    if (firstVisibleItemView != null && firstVisibleItemView.getTop() == 0) {
                        canRefresh = true;
                    } else {
                        canRefresh = false;
                    }
                }
            }
        });
    }

    @Override
    public void quXiaoOrder(String orderId, int position) {

    }

    @Override
    public boolean checkCanDoLoadMore(PtrFrameLayout frame, View content, View footer) {
        return canLoad;
    }

    @Override
    public void onLoadMoreBegin(final PtrFrameLayout frame) {

        frame.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(Contacts.ORDER_REFRESH);
                intent.putExtra("type", "7");
                intent.putExtra("flag", "load");
                getActivity().sendBroadcast(intent);
                frame.refreshComplete();

            }
        }, 1000);

    }

    @Override
    public boolean checkCanDoRefresh(PtrFrameLayout frame, View content, View header) {
        return canRefresh;
    }

    @Override
    public void onRefreshBegin(final PtrFrameLayout frame) {
        frame.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(Contacts.ORDER_REFRESH);
                intent.putExtra("type", "7");
                intent.putExtra("flag", "fresh");
                getActivity().sendBroadcast(intent);
                frame.refreshComplete();

            }
        }, 1000);
    }

    @Override
    public void onOrderCancle(PublicBean bean, int position) {

    }
}
