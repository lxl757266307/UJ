package com.example.maintainsteward.activity;

import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.KeyEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.maintainsteward.R;
import com.example.maintainsteward.adapter.OrderFragmentsAdapter;
import com.example.maintainsteward.base.BaseActivity;
import com.example.maintainsteward.base.Contacts;
import com.example.maintainsteward.bean.OrderListBean;
import com.example.maintainsteward.fragment.AllOrderListFragement;
import com.example.maintainsteward.fragment.WeiWanChengOrderListFragement;
import com.example.maintainsteward.fragment.YiQuXiaoOrderListFragement;
import com.example.maintainsteward.fragment.YiWanChengOrderListFragement;
import com.example.maintainsteward.mvp_presonter.OrderListPresonter;
import com.example.maintainsteward.mvp_view.GetOrderListListener;
import com.example.maintainsteward.utils.ToolUitls;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/9/19.
 */

public class OrderActivity extends BaseActivity implements GetOrderListListener {

    TextView[] txtArray = null;
    @BindView(R.id.layout_back)
    LinearLayout layoutBack;
    @BindView(R.id.txt_title)
    TextView txtTitle;

    @BindView(R.id.vp_order)
    ViewPager vpOrder;
    @BindView(R.id.txt_order_all_order)
    TextView txtOrderAllOrder;
    @BindView(R.id.txt_wei_wan_cheng_order)
    TextView txtWeiWanChengOrder;
    @BindView(R.id.txt_wei_wan_cheng_number)
    TextView txtWeiWanChengNumber;
    @BindView(R.id.txt_yi_wan_cheng_order)
    TextView txtYiWanChengOrder;
    @BindView(R.id.txt_yi_wan_cheng_number)
    TextView txtYiWanChengNumber;
    @BindView(R.id.txt_yi_qu_xiao_order)
    TextView txtYiQuXiaoOrder;
    @BindView(R.id.txt_yi_qu_xiao_number)
    TextView txtYiQuXiaoNumber;

    int item;

    public static final String TAG = "OrderActivity";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        item = this.getIntent().getIntExtra("page", -1);
        setContentView(R.layout.activity_order);
        ButterKnife.bind(this);
        setArray();
        initViewPager();
        initPrsonter();
        initList();
        getOrderByType("1");
        getOrderByType("3");
        getOrderByType("5");
        getOrderByType("7");


    }

    public void setArray() {
        txtArray = new TextView[4];
        txtArray[0] = txtOrderAllOrder;
        txtArray[1] = txtWeiWanChengOrder;
        txtArray[2] = txtYiWanChengOrder;
        txtArray[3] = txtYiQuXiaoOrder;
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @OnClick({R.id.txt_order_all_order, R.id.txt_wei_wan_cheng_order, R.id.txt_yi_wan_cheng_order, R.id.txt_yi_qu_xiao_order, R.id.layout_back})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.txt_order_all_order:
//                setTextViewBackground(0);
                vpOrder.setCurrentItem(0);
                break;
            case R.id.txt_wei_wan_cheng_order:
//                setTextViewBackground(1);
                vpOrder.setCurrentItem(1);
                break;
            case R.id.txt_yi_wan_cheng_order:
//                setTextViewBackground(2);
                vpOrder.setCurrentItem(2);
                break;
            case R.id.txt_yi_qu_xiao_order:
//                setTextViewBackground(3);
                vpOrder.setCurrentItem(3);
                break;
            case R.id.layout_back:
                finish();
                break;
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if (keyCode == KeyEvent.KEYCODE_BACK) {
            finish();
            return true;
        }

        return super.onKeyDown(keyCode, event);
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    public void setTextViewBackground(int index) {

        for (int i = 0; i < txtArray.length; i++) {

            if (index == i) {
                txtArray[i].setBackground(getResources().getDrawable(R.drawable.border_bottom));
                txtArray[i].setTextColor(getResources().getColor(R.color.red));
            } else {
                txtArray[i].setBackgroundResource(R.color.background);
                txtArray[i].setTextColor(getResources().getColor(R.color.black));
            }

        }

    }

    AllOrderListFragement allOrderListFragement;
    WeiWanChengOrderListFragement weiWanChengOrderListFragement;
    YiWanChengOrderListFragement yiWanChengOrderListFragement;
    YiQuXiaoOrderListFragement yiQuXiaoOrderListFragement;

    OrderFragmentsAdapter adapter;

    public void initViewPager() {


        vpOrder.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                setTextViewBackground(position);
            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        allOrderListFragement = new AllOrderListFragement();
        weiWanChengOrderListFragement = new WeiWanChengOrderListFragement();
        yiWanChengOrderListFragement = new YiWanChengOrderListFragement();
        yiQuXiaoOrderListFragement = new YiQuXiaoOrderListFragement();

        List<Fragment> fragments = new ArrayList<>();

        fragments.add(allOrderListFragement);
        fragments.add(weiWanChengOrderListFragement);
        fragments.add(yiWanChengOrderListFragement);
        fragments.add(yiQuXiaoOrderListFragement);

        adapter = new OrderFragmentsAdapter(getSupportFragmentManager());
        adapter.setFragments(fragments);
        adapter.notifyDataSetChanged();

        vpOrder.setAdapter(adapter);

        vpOrder.setCurrentItem(item);


    }

    OrderListPresonter orderListPresonter;

    public void initPrsonter() {
        orderListPresonter = new OrderListPresonter();
        orderListPresonter.setOrderListListener(this);
    }


    int page = 1;

    public void getOrderByType(String type) {
        SharedPreferences sharedPreferences = getSharedPreferences(Contacts.USER, Context.MODE_PRIVATE);
        String id = sharedPreferences.getString("id", null);
        String time = System.currentTimeMillis() + "";
        TreeMap<String, String> map = new TreeMap<>();
        map.put("timestamp", time);
        map.put("user_id", id);
        map.put("order_type", type);
        map.put("page", page + "");
        String sign = ToolUitls.getSign(map);
        switch (type) {
            case "1":
                orderListPresonter.getOrderList(id, type, page + "", time, sign, Contacts.KEY);
                break;
            case "3":
                orderListPresonter.getWeiWanChengOrderList(id, type, page + "", time, sign, Contacts.KEY);
                break;
            case "5":
                orderListPresonter.getYiQuXiaoOrderList(id, type, page + "", time, sign, Contacts.KEY);
                break;
            case "7":
                orderListPresonter.getYiWanChengOrderList(id, type, page + "", time, sign, Contacts.KEY);
                break;

        }

    }

    ProgressDialog dialog;

    @Override
    public void showDialog() {
        dialog = ProgressDialog.show(this, "提示", "正在加载中");
    }


    List<OrderListBean.DataBean.DemandOrderDataBean> allOrder;
    List<OrderListBean.DataBean.DemandOrderDataBean> weiWanCheng;
    List<OrderListBean.DataBean.DemandOrderDataBean> yiWanCheng;
    List<OrderListBean.DataBean.DemandOrderDataBean> yiQuXiao;

    public void initList() {
        allOrder = new ArrayList<>();
        weiWanCheng = new ArrayList<>();
        yiWanCheng = new ArrayList<>();
        yiQuXiao = new ArrayList<>();
    }

    @Override
    public void getAllList(OrderListBean listBean) {
        switch (listBean.getStatus()) {
            case "1":

                OrderListBean.DataBean data = listBean.getData();
                allOrder.addAll(data.getDemand_order_data());

                break;
            default:
                ToolUitls.toast(this, "暂无数据");
                orderListPresonter.dialogDismiss();

                break;
        }
    }

    @Override
    public void getWeiWanChengList(OrderListBean listBean) {
        switch (listBean.getStatus()) {
            case "1":
                OrderListBean.DataBean data = listBean.getData();
                weiWanCheng.addAll(data.getDemand_order_data());
                if (weiWanCheng.size() > 0) {
                    txtWeiWanChengNumber.setVisibility(View.VISIBLE);
                    txtWeiWanChengNumber.setText(weiWanCheng.size() + "");
                }

                break;
        }
    }

    @Override
    public void getYiWanChengList(OrderListBean listBean) {
        switch (listBean.getStatus()) {
            case "1":
                OrderListBean.DataBean data = listBean.getData();
                yiWanCheng.addAll(data.getDemand_order_data());
                if (yiWanCheng.size() > 0) {
                    txtYiWanChengNumber.setVisibility(View.VISIBLE);
                    txtYiWanChengNumber.setText(yiWanCheng.size() + "");
                }
                break;
        }

    }

    @Override
    public void getYiQuXiaoList(OrderListBean listBean) {
        switch (listBean.getStatus()) {
            case "1":
                OrderListBean.DataBean data = listBean.getData();
                yiQuXiao.addAll(data.getDemand_order_data());
                if (yiQuXiao.size() > 0) {
                    txtYiQuXiaoNumber.setVisibility(View.VISIBLE);
                    txtYiQuXiaoNumber.setText(yiQuXiao.size() + "");
                }
                break;
        }
    }

    @Override
    public void dialogDismiss() {

    }



}
