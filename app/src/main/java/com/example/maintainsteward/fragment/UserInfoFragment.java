package com.example.maintainsteward.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.maintainsteward.R;
import com.example.maintainsteward.activity.OrderActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by Administrator on 2017/8/17.
 */

public class UserInfoFragment extends Fragment {


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
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }


    @OnClick({R.id.img_xiaoxi_userinfo, R.id.layout_edit_userinfo, R.id.layout_see_all_order_userinfo, R.id.layout_wei_wan_cheng_userinfo, R.id.layout_yi_wan_cheng_userinfo, R.id.layout_yi_qu_xiao_userinfo, R.id.layout_ping_jia_userinfo, R.id.layout_tao_can_userinfo, R.id.layout_dizhi_userinfo, R.id.layout_qianbao_userinfo})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.img_xiaoxi_userinfo:
                break;
            case R.id.layout_edit_userinfo:
                break;
            case R.id.layout_see_all_order_userinfo: {
                Intent intent = new Intent(getActivity(), OrderActivity.class);
                startActivity(intent);
            }

            break;
            case R.id.layout_wei_wan_cheng_userinfo:
                break;
            case R.id.layout_yi_wan_cheng_userinfo:
                break;
            case R.id.layout_yi_qu_xiao_userinfo:
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
}
