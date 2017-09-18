package com.example.maintainsteward.fragment;

import android.annotation.TargetApi;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.maintainsteward.R;
import com.example.maintainsteward.adapter.OrderListFragment;
import com.example.maintainsteward.base.Contacts;
import com.example.maintainsteward.bean.OrderListBean;
import com.example.maintainsteward.mvp_presonter.OrderListPresonter;
import com.example.maintainsteward.mvp_view.GetOrderListListener;
import com.example.maintainsteward.utils.ToolUitls;

import java.util.List;
import java.util.TreeMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by Administrator on 2017/8/17.
 */

@TargetApi(Build.VERSION_CODES.JELLY_BEAN)
public class OrerFragment extends Fragment implements GetOrderListListener {
    TextView[] txtArray = null;


    Unbinder unbinder;
    @BindView(R.id.layout_back)
    LinearLayout layoutBack;
    @BindView(R.id.txt_title)
    TextView txtTitle;
    @BindView(R.id.txt_order_all_fragment_order)
    TextView txtOrderAllFragmentOrder;
    @BindView(R.id.txt_commit_fragment_order)
    TextView txtCommitFragmentOrder;
    @BindView(R.id.txt_wait_pay_fragment_order)
    TextView txtWaitPayFragmentOrder;
    @BindView(R.id.txt_wait_evaluation_fragment_order)
    TextView txtWaitEvaluationFragmentOrder;
    @BindView(R.id.txt_cancled_fragment_order)
    TextView txtCancledFragmentOrder;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_order, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    FragmentManager supportFragmentManager;

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        supportFragmentManager = getActivity().getSupportFragmentManager();
        setArray();

        initOrderList();

//        initViewpager();
    }

    String order_type = "1";
    int page = 1;
    OrderListPresonter orderListPresonter;

    private void initOrderList() {

        SharedPreferences sharedPreferences = getActivity().getSharedPreferences(Contacts.USER, Context.MODE_PRIVATE);
        String id = sharedPreferences.getString("id", null);
        String time = System.currentTimeMillis() + "";
        TreeMap<String, String> map = new TreeMap<>();
        map.put("timestamp", time);
        map.put("user_id", id);
        map.put("order_type", order_type);
        map.put("page", page + "");
        String sign = ToolUitls.getSign(map);


        orderListPresonter = new OrderListPresonter();

        orderListPresonter.setOrderListListener(this);

        orderListPresonter.getOrderList(id, order_type, page + "", time, sign, Contacts.KEY);

//        ToolUitls.getCallBackStr(Contacts.TEST_BASE_URL +
//                "orderListByUser?"
//                + "user_id=" + id +
//                "&order_type=" + order_type +
//                "&page=" + page +
//                "&timestamp=" + time +
//                "&sign=" + sign +
//                "&key=" + Contacts.KEY);

    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }

    public void setArray() {
        txtArray = new TextView[5];
        txtArray[0] = txtOrderAllFragmentOrder;
        txtArray[1] = txtCommitFragmentOrder;
        txtArray[2] = txtWaitPayFragmentOrder;
        txtArray[3] = txtWaitEvaluationFragmentOrder;
        txtArray[4] = txtCancledFragmentOrder;
    }

    @OnClick({R.id.txt_order_all_fragment_order,
            R.id.txt_commit_fragment_order,
            R.id.txt_wait_pay_fragment_order,
            R.id.txt_wait_evaluation_fragment_order,
            R.id.txt_cancled_fragment_order,
    })
    public void setListener(View view) {
        switch (view.getId()) {
            case R.id.txt_order_all_fragment_order:
                setTextViewBackground(0);
                break;
            case R.id.txt_commit_fragment_order:
                setTextViewBackground(1);
                break;
            case R.id.txt_wait_pay_fragment_order:
                setTextViewBackground(2);
                break;
            case R.id.txt_wait_evaluation_fragment_order:
                setTextViewBackground(3);
                break;
            case R.id.txt_cancled_fragment_order:
                setTextViewBackground(4);
                break;

        }

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


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    ProgressDialog dialog;

    @Override
    public void showDialog() {
        dialog = ProgressDialog.show(getActivity(), "提示", "正在加载中");
    }

    List<OrderListBean.DataBean.DemandOrderDataBean> demand_order_data;

    @Override
    public void getAllList(OrderListBean listBean) {
        switch (listBean.getStatus()) {
            case "1":
                OrderListBean.DataBean data = listBean.getData();
                demand_order_data = data.getDemand_order_data();
                replace(demand_order_data);
                orderListPresonter.dialogDismiss();

                break;
        }
    }

    OrderListFragment orderListFragment;

    public void replace(List<OrderListBean.DataBean.DemandOrderDataBean> demand_order_data) {
        FragmentTransaction fragmentTransaction = supportFragmentManager.beginTransaction();
        orderListFragment = new OrderListFragment();
        orderListFragment.setDemand_order_data(demand_order_data);
        fragmentTransaction.replace(R.id.layout_fragment_order, orderListFragment);
        fragmentTransaction.commit();


    }

    @Override
    public void dialogDismiss() {
        dialog.dismiss();
    }
}
