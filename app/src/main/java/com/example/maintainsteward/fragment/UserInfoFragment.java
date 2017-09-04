package com.example.maintainsteward.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.maintainsteward.uj.R;
import com.example.maintainsteward.view.StrokeCircleImageView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by Administrator on 2017/8/17.
 */

public class UserInfoFragment extends Fragment {

    @BindView(R.id.circleview_my_info)
    StrokeCircleImageView circleviewMyInfo;
    @BindView(R.id.img_my_info_logo)
    ImageView imgMyInfoLogo;
    @BindView(R.id.txt_user_name_infofragment)
    TextView txtUserNameInfofragment;
    @BindView(R.id.btn_set_info_infofragment)
    Button btnSetInfoInfofragment;
    @BindView(R.id.txt_order_infofragment)
    TextView txtOrderInfofragment;
    @BindView(R.id.txt_address_infofragment)
    TextView txtAddressInfofragment;
    @BindView(R.id.txt_walert_infofragment)
    TextView txtWalertInfofragment;
    @BindView(R.id.txt_ka_juan_infofragment)
    TextView txtKaJuanInfofragment;
    @BindView(R.id.txt_evelution_infofragment)
    TextView txtEvelutionInfofragment;
    @BindView(R.id.txt_service_infofragment)
    TextView txtServiceInfofragment;
    @BindView(R.id.txt_message_infofragment)
    TextView txtMessageInfofragment;
    @BindView(R.id.txt_setting_infofragment)
    TextView txtSettingInfofragment;
    Unbinder unbinder;

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
}
