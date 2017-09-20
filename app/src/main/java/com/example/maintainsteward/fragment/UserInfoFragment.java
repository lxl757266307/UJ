package com.example.maintainsteward.fragment;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.maintainsteward.R;
import com.example.maintainsteward.activity.OrderActivity;
import com.example.maintainsteward.activity.UserActivity;
import com.example.maintainsteward.base.Contacts;
import com.example.maintainsteward.bean.OrderListBean;
import com.example.maintainsteward.bean.PublicBean;
import com.example.maintainsteward.bean.UserInfoBean;
import com.example.maintainsteward.mvp_presonter.OrderListPresonter;
import com.example.maintainsteward.mvp_presonter.UserInfoPresonter;
import com.example.maintainsteward.mvp_view.GetOrderListListener;
import com.example.maintainsteward.mvp_view.UserInfoListener;
import com.example.maintainsteward.utils.ToolUitls;

import java.io.Serializable;
import java.util.List;
import java.util.TreeMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by Administrator on 2017/8/17.
 */

public class UserInfoFragment extends Fragment implements GetOrderListListener, UserInfoListener {


    Unbinder unbinder;
    @BindView(R.id.img_xiaoxi_userinfo)
    ImageView imgXiaoxiUserinfo;
    @BindView(R.id.txt_xiaoxi_number_userinfo)
    TextView txtXiaoxiNumberUserinfo;
    @BindView(R.id.img_my_info_logo)
    CircleImageView imgMyInfoLogo;
    @BindView(R.id.txt_user_name_infofragment)
    TextView txtUserNameInfofragment;
    @BindView(R.id.img_vip_userinfo)
    ImageView imgVipUserinfo;
    @BindView(R.id.layout_edit_userinfo)
    LinearLayout layoutEditUserinfo;
    @BindView(R.id.layout_see_all_order_userinfo)
    LinearLayout layoutSeeAllOrderUserinfo;
    @BindView(R.id.txt_wei_wan_cheng_number)
    TextView txtWeiWanChengNumber;
    @BindView(R.id.layout_wei_wan_cheng_userinfo)
    LinearLayout layoutWeiWanChengUserinfo;
    @BindView(R.id.txt_yi_wan_cheng_number)
    TextView txtYiWanChengNumber;
    @BindView(R.id.layout_yi_wan_cheng_userinfo)
    LinearLayout layoutYiWanChengUserinfo;
    @BindView(R.id.txt_yi_qu_xiao_number)
    TextView txtYiQuXiaoNumber;
    @BindView(R.id.layout_yi_qu_xiao_userinfo)
    LinearLayout layoutYiQuXiaoUserinfo;
    @BindView(R.id.layout_ping_jia_userinfo)
    LinearLayout layoutPingJiaUserinfo;
    @BindView(R.id.layout_tao_can_userinfo)
    LinearLayout layoutTaoCanUserinfo;
    @BindView(R.id.layout_dizhi_userinfo)
    LinearLayout layoutDizhiUserinfo;
    @BindView(R.id.layout_qianbao_userinfo)
    LinearLayout layoutQianbaoUserinfo;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_my_info, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initPrsonter();
        registerReciver();
        getOrderByType("3");
        getOrderByType("5");
        getOrderByType("7");

        getUserInfo();
    }

    UserInfoPresonter userInfoPresonter;

    public void getUserInfo() {

        SharedPreferences sharedPreferences = getActivity().getSharedPreferences(Contacts.USER, Context.MODE_PRIVATE);
        String id = sharedPreferences.getString("id", null);
        String time = System.currentTimeMillis() + "";
        TreeMap<String, String> map = new TreeMap<>();
        map.put("timestamp", time);
        map.put("user_id", id);
        String sign = ToolUitls.getSign(map);
//        ToolUitls.getCallBackStr(Contacts.TEST_BASE_URL + "GetUserInfos?" + "user_id=" + id + "&timestamp=" + time + "&sign=" + sign + "&key=" + Contacts.KEY);
        userInfoPresonter.getUserInfo(id, time, sign, Contacts.KEY);

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {

            Bitmap bitmap = data.getParcelableExtra("bitmap");
            if (null != bitmap) {
                imgMyInfoLogo.setImageBitmap(bitmap);
            }

            String name = data.getStringExtra("name");

            if (name != null) {

                txtUserNameInfofragment.setText(name);
            }


        }

    }

    public static final int UPDATE_USER = 1;

    @OnClick({R.id.img_xiaoxi_userinfo, R.id.layout_edit_userinfo, R.id.layout_see_all_order_userinfo, R.id.layout_wei_wan_cheng_userinfo, R.id.layout_yi_wan_cheng_userinfo, R.id.layout_yi_qu_xiao_userinfo, R.id.layout_ping_jia_userinfo, R.id.layout_tao_can_userinfo, R.id.layout_dizhi_userinfo, R.id.layout_qianbao_userinfo})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.img_xiaoxi_userinfo:
                break;
            case R.id.layout_edit_userinfo: {
                Intent intent = new Intent(getActivity(), UserActivity.class);
                intent.putExtra("data", (Serializable) data);
                startActivityForResult(intent, UPDATE_USER);
            }

            break;
            case R.id.layout_see_all_order_userinfo: {
                Intent intent = new Intent(getActivity(), OrderActivity.class);
                startActivity(intent);
            }

            break;
            case R.id.layout_wei_wan_cheng_userinfo: {
                Intent intent = new Intent(getActivity(), OrderActivity.class);
                intent.putExtra("page", 1);
                startActivity(intent);
            }
            break;
            case R.id.layout_yi_wan_cheng_userinfo: {
                Intent intent = new Intent(getActivity(), OrderActivity.class);
                intent.putExtra("page", 2);
                startActivity(intent);
            }
            break;
            case R.id.layout_yi_qu_xiao_userinfo: {
                Intent intent = new Intent(getActivity(), OrderActivity.class);
                intent.putExtra("page", 3);
                startActivity(intent);
            }
            break;
            case R.id.layout_ping_jia_userinfo:
                break;
            case R.id.layout_tao_can_userinfo:
                break;
            case R.id.layout_dizhi_userinfo:
                break;
            case R.id.layout_qianbao_userinfo:
                break;
        }
    }

    OrderListPresonter orderListPresonter;

    public void initPrsonter() {
        orderListPresonter = new OrderListPresonter();
        orderListPresonter.setOrderListListener(this);
        userInfoPresonter = new UserInfoPresonter();
        userInfoPresonter.setUserInfoListener(this);
        orderListPresonter.showDialog();
    }


    int page = 1;

    public void getOrderByType(String type) {
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences(Contacts.USER, Context.MODE_PRIVATE);
        String id = sharedPreferences.getString("id", null);
        String time = System.currentTimeMillis() + "";
        TreeMap<String, String> map = new TreeMap<>();
        map.put("timestamp", time);
        map.put("user_id", id);
        map.put("order_type", type);
        map.put("page", page + "");
        String sign = ToolUitls.getSign(map);
        switch (type) {
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
        dialog = ProgressDialog.show(getActivity(), "", "正在加载");
    }

    @Override
    public void getAllList(OrderListBean listBean) {

    }

    @Override
    public void getWeiWanChengList(OrderListBean listBean) {
        switch (listBean.getStatus()) {
            case "1":
                List<OrderListBean.DataBean.DemandOrderDataBean> demand_order_data = listBean.getData().getDemand_order_data();
                if (demand_order_data.size() > 0) {

                    txtWeiWanChengNumber.setVisibility(View.VISIBLE);
                    txtWeiWanChengNumber.setText(demand_order_data.size() + "");

                }


                break;
        }
    }

    @Override
    public void getYiWanChengList(OrderListBean listBean) {
        switch (listBean.getStatus()) {
            case "1":
                List<OrderListBean.DataBean.DemandOrderDataBean> demand_order_data = listBean.getData().getDemand_order_data();
                if (demand_order_data.size() > 0) {

                    txtYiWanChengNumber.setVisibility(View.VISIBLE);
                    txtYiWanChengNumber.setText(demand_order_data.size() + "");

                }

                break;
        }
    }

    @Override
    public void getYiQuXiaoList(OrderListBean listBean) {
        switch (listBean.getStatus()) {
            case "1":
                List<OrderListBean.DataBean.DemandOrderDataBean> demand_order_data = listBean.getData().getDemand_order_data();
                if (demand_order_data.size() > 0) {

                    txtYiQuXiaoNumber.setVisibility(View.VISIBLE);
                    txtYiQuXiaoNumber.setText(demand_order_data.size() + "");

                }

                break;
        }
        orderListPresonter.dialogDismiss();
    }

    @Override
    public void dialogDismiss() {
        dialog.dismiss();
    }

    UserInfoBean.DataBean data;
    public static final String TAG = "UserInfoFragment";

    @Override
    public void getUserInfo(UserInfoBean bean) {
        switch (bean.getStatus()) {

            case "1":
                data = bean.getData();

                ToolUitls.print(TAG, "data====" + data);

                if (!"".equals(data.getAvatar())) {
                    Glide.with(getActivity()).load(data.getAvatar()).into(imgMyInfoLogo);
                }

                ToolUitls.print(TAG, "name====" + data.getUser_nicename());

                if ("".equals(data.getUser_nicename())) {
                    txtUserNameInfofragment.setText("请设置昵称");
                } else {
                    txtUserNameInfofragment.setText(data.getUser_nicename());
                }
                String is_vip = data.getIs_vip();
                switch (is_vip) {
                    case "0":
                        imgVipUserinfo.setImageResource(R.mipmap.vip2);
                        break;
                    case "1":
                        imgVipUserinfo.setImageResource(R.mipmap.putogyonghu);
                        break;
                }


                break;
        }
    }

    @Override
    public void editUserInfoSucess(PublicBean bean) {

    }

    UpdateInfoReciver updateInfoReciver;

    public void registerReciver() {
        updateInfoReciver = new UpdateInfoReciver();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(Contacts.UPDATE_USER);
        getActivity().registerReceiver(updateInfoReciver, intentFilter);

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        getActivity().unregisterReceiver(updateInfoReciver);

    }

    public class UpdateInfoReciver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {

            if (intent.getAction().equals(Contacts.UPDATE_USER)) {

                getUserInfo();
            }
        }
    }
}
