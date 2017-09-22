package com.example.maintainsteward.fragment;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.example.maintainsteward.R;
import com.example.maintainsteward.activity.OrderMessageActivity;
import com.example.maintainsteward.adapter.OrderListAdapter;
import com.example.maintainsteward.base.Contacts;
import com.example.maintainsteward.bean.OrderListBean;
import com.example.maintainsteward.bean.PublicBean;
import com.example.maintainsteward.main.MainActivity;
import com.example.maintainsteward.mvp_presonter.OrderListPresonter;
import com.example.maintainsteward.mvp_presonter.QuXiaoOrderPresonter;
import com.example.maintainsteward.mvp_view.GetOrderListListener;
import com.example.maintainsteward.mvp_view.OnOrderCancleListener;
import com.example.maintainsteward.utils.ToolUitls;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import in.srain.cube.views.ptr.PtrClassicFrameLayout;
import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.PtrHandler2;

/**
 * Created by Administrator on 2017/9/19.
 */

public class YiQuXiaoOrderListFragement extends Fragment implements PtrHandler2,
        OrderListAdapter.OnQuXiaoOrderListener, OnOrderCancleListener,
        GetOrderListListener, AdapterView.OnItemClickListener {
    List<OrderListBean.DataBean.DemandOrderDataBean> demand_order_data;
    @BindView(R.id.lv_order_list)
    ListView lvOrderList;
    @BindView(R.id.prt_framelayout)
    PtrClassicFrameLayout prtFramelayout;
    Unbinder unbinder;
    @BindView(R.id.btn_xiadan_order)
    Button btnXiadanOrder;
    @BindView(R.id.layout_xidan_order)
    LinearLayout layoutXidanOrder;

    public void setDemand_order_data(List<OrderListBean.DataBean.DemandOrderDataBean> demand_order_data) {
        this.demand_order_data = demand_order_data;
        if (demand_order_data != null && orderListAdapter != null) {
            orderListAdapter.setDemand_order_data(demand_order_data);
            lvOrderList.setAdapter(orderListAdapter);
            orderListAdapter.notifyDataSetChanged();
        }
        if (demand_order_data.size() > 0) {
            layoutXidanOrder.setVisibility(View.INVISIBLE);
        }
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

    QuXiaoOrderPresonter presonter;
    OrderListAdapter orderListAdapter;
    boolean canLoad;
    boolean canRefresh;


    OrderListPresonter orderListPresonter;

    List<OrderListBean.DataBean.DemandOrderDataBean> allOrder;

    public void initPrsonter() {
        allOrder = new ArrayList<>();
        orderListPresonter = new OrderListPresonter();
        orderListPresonter.setOrderListListener(this);
    }


    int page = 1;

    public void getOrderByType(String type) {
        orderListPresonter.showDialog();
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences(Contacts.USER, Context.MODE_PRIVATE);
        String id = sharedPreferences.getString("id", null);
        String time = System.currentTimeMillis() + "";
        TreeMap<String, String> map = new TreeMap<>();
        map.put("timestamp", time);
        map.put("user_id", id);
        map.put("order_type", type);
        map.put("page", page + "");
        String sign = ToolUitls.getSign(map);
        orderListPresonter.getOrderList(id, type, page + "", time, sign, Contacts.KEY);
        handler.sendEmptyMessageDelayed(1, 1500);

    }

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            dialog.dismiss();
        }
    };

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        lvOrderList.setOnItemClickListener(this);
        presonter = new QuXiaoOrderPresonter();
        presonter.setOnOrderCancleListener(this);
        orderListAdapter = new OrderListAdapter();
        orderListAdapter.setContext(getActivity());
        orderListAdapter.setOnQuXiaoOrderListenerl(this);
        initPrsonter();
        getOrderByType("5");
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
    public boolean checkCanDoLoadMore(PtrFrameLayout frame, View content, View footer) {
        return canLoad;
    }

    @Override
    public void onLoadMoreBegin(final PtrFrameLayout frame) {

        frame.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(Contacts.ORDER_REFRESH);
                intent.putExtra("type", "1");
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
                intent.putExtra("type", "1");
                intent.putExtra("flag", "fresh");
                getActivity().sendBroadcast(intent);
                frame.refreshComplete();

            }
        }, 1000);
    }


    @Override
    public void quXiaoOrder(String orderId, int position) {
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences(Contacts.USER, Context.MODE_PRIVATE);
        String userId = sharedPreferences.getString("id", null);
        String time = System.currentTimeMillis() + "";
        TreeMap<String, String> map = new TreeMap<>();
        map.put("timestamp", time);
        map.put("user_id", userId);
        map.put("order_id", orderId);
        String sign = ToolUitls.getSign(map);
        presonter.quXiaoOrder(userId, orderId, time, sign, Contacts.KEY, position);
    }

    @Override
    public void onOrderCancle(PublicBean bean, int position) {
        switch (bean.getStatus()) {

            case "1":
                ToolUitls.toast(getActivity(), "取消成功");
                demand_order_data.remove(position);
                orderListAdapter.setDemand_order_data(demand_order_data);
                orderListAdapter.notifyDataSetChanged();
//                Intent intent = new Intent(Contacts.ORDER_REFRESH);
//                intent.putExtra("type", "5");
//                getActivity().sendBroadcast(intent);

                break;
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        Intent intent = new Intent(getActivity(), OrderMessageActivity.class);
        String orderId = demand_order_data.get(position).getId();
        intent.putExtra("id", orderId);
        startActivity(intent);

    }

    @OnClick(R.id.btn_xiadan_order)
    public void onViewClicked() {
        Intent intent = new Intent(getActivity(), MainActivity.class);
        intent.putExtra("page", 1);
        startActivity(intent);
    }

    ProgressDialog dialog;

    @Override
    public void showDialog() {
        dialog = ProgressDialog.show(getActivity(), "", "正在加载");
    }


    @Override
    public void getAllList(OrderListBean listBean) {
        switch (listBean.getStatus()) {
            case "1":

                OrderListBean.DataBean data = listBean.getData();
                allOrder.addAll(data.getDemand_order_data());
                setDemand_order_data(allOrder);


                break;
            default:
                ToolUitls.toast(getActivity(), "暂无数据");
                orderListPresonter.dialogDismiss();

                break;
        }
    }

    @Override
    public void getWeiWanChengList(OrderListBean listBean) {

    }

    @Override
    public void getYiWanChengList(OrderListBean listBean) {

    }

    @Override
    public void getYiQuXiaoList(OrderListBean listBean) {
        switch (listBean.getStatus()) {
            case "1":
                OrderListBean.DataBean data = listBean.getData();
                allOrder.addAll(data.getDemand_order_data());
                setDemand_order_data(allOrder);
//                orderListPresonter.dialogDismiss();

                break;
        }
    }

    @Override
    public void dialogDismiss() {
        dialog.dismiss();
    }
}
