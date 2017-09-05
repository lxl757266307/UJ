package com.example.maintainsteward.fragment;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.maintainsteward.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by Administrator on 2017/8/17.
 */
@TargetApi(Build.VERSION_CODES.JELLY_BEAN)
public class KindsFragment extends Fragment {

    @BindView(R.id.edit_sousuo_fragment_kinds)
    EditText editSousuoFragmentKinds;
    @BindView(R.id.img_tianjia_fragment_kinds)
    ImageView imgTianjiaFragmentKinds;
    @BindView(R.id.txt_jiadianweixiu_fragment_kinds)
    TextView txtJiadianweixiuFragmentKinds;
    @BindView(R.id.txt_dianluweihu_fragment_kinds)
    TextView txtDianluweihuFragmentKinds;
    @BindView(R.id.txt_shuiluguanlu_fragment_kinds)
    TextView txtShuiluguanluFragmentKinds;
    @BindView(R.id.txt_jiadianqingxi_fragment_kinds)
    TextView txtJiadianqingxiFragmentKinds;
    @BindView(R.id.txt_menchuangweihu_fragment_kinds)
    TextView txtMenchuangweihuFragmentKinds;
    @BindView(R.id.txt_qiangmiandimian_fragment_kinds)
    TextView txtQiangmiandimianFragmentKinds;
    @BindView(R.id.txt_fangwujiance_fragment_kinds)
    TextView txtFangwujianceFragmentKinds;
    @BindView(R.id.txt_dengjuanzhuang_fragment_kinds)
    TextView txtDengjuanzhuangFragmentKinds;
    @BindView(R.id.txt_chuweijieju_fragment_kinds)
    TextView txtChuweijiejuFragmentKinds;
    @BindView(R.id.txt_jiazhengbaojie_fragment_kinds)
    TextView txtJiazhengbaojieFragmentKinds;
    @BindView(R.id.txt_listname_fragment_kinds)
    TextView txtListnameFragmentKinds;
    @BindView(R.id.rv_recycle_fragment_kinds)
    RecyclerView rvRecycleFragmentKinds;

    TextView[] txtArray = null;

    Unbinder unbinder;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_kinds, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setArray();

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }

    public void setArray() {
        txtArray = new TextView[10];
        txtArray[0] = txtJiadianweixiuFragmentKinds;
        txtArray[1] = txtDianluweihuFragmentKinds;
        txtArray[2] = txtShuiluguanluFragmentKinds;
        txtArray[3] = txtJiadianqingxiFragmentKinds;
        txtArray[4] = txtMenchuangweihuFragmentKinds;
        txtArray[5] = txtQiangmiandimianFragmentKinds;
        txtArray[6] = txtFangwujianceFragmentKinds;
        txtArray[7] = txtDengjuanzhuangFragmentKinds;
        txtArray[8] = txtChuweijiejuFragmentKinds;
        txtArray[9] = txtJiazhengbaojieFragmentKinds;

    }

    @OnClick({R.id.txt_jiadianweixiu_fragment_kinds,
            R.id.txt_dianluweihu_fragment_kinds,
            R.id.txt_shuiluguanlu_fragment_kinds,
            R.id.txt_jiadianqingxi_fragment_kinds,
            R.id.txt_menchuangweihu_fragment_kinds,
            R.id.txt_qiangmiandimian_fragment_kinds,
            R.id.txt_fangwujiance_fragment_kinds,
            R.id.txt_dengjuanzhuang_fragment_kinds,
            R.id.txt_chuweijieju_fragment_kinds,
            R.id.txt_jiazhengbaojie_fragment_kinds})
    public void setListener(View view) {
        switch (view.getId()) {
            case R.id.txt_jiadianweixiu_fragment_kinds:
                setTextViewBackground(0);
                break;
            case R.id.txt_dianluweihu_fragment_kinds:
                setTextViewBackground(1);
                break;
            case R.id.txt_shuiluguanlu_fragment_kinds:
                setTextViewBackground(2);
                break;
            case R.id.txt_jiadianqingxi_fragment_kinds:
                setTextViewBackground(3);
                break;
            case R.id.txt_menchuangweihu_fragment_kinds:
                setTextViewBackground(4);
                break;
            case R.id.txt_qiangmiandimian_fragment_kinds:
                setTextViewBackground(5);
                break;
            case R.id.txt_fangwujiance_fragment_kinds:
                setTextViewBackground(6);
                break;
            case R.id.txt_dengjuanzhuang_fragment_kinds:
                setTextViewBackground(7);
                break;
            case R.id.txt_chuweijieju_fragment_kinds:
                setTextViewBackground(8);
                break;
            case R.id.txt_jiazhengbaojie_fragment_kinds:
                setTextViewBackground(9);
                break;
        }

    }


    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    public void setTextViewBackground(int index) {

        for (int i = 0; i < txtArray.length; i++) {

            if (index == i) {
                txtArray[i].setBackground(getResources().getDrawable(R.drawable.border_left));
                txtArray[i].setTextColor(getResources().getColor(R.color.red));
            } else {
                txtArray[i].setBackground(getResources().getDrawable(R.drawable.text_view_bg));
                txtArray[i].setTextColor(getResources().getColor(R.color.black));
            }

        }

    }
}
