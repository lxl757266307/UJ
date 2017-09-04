package com.example.maintainsteward.fragment;

import android.app.Dialog;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.astuetz.PagerSlidingTabStrip;
import com.example.maintainsteward.R;
import com.example.maintainsteward.adapter.DialogFragmentPagerAdapter;
import com.example.maintainsteward.inter.OnLocationItemClickListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by Administrator on 2017/8/11.
 */

public class MyDialogFragment extends DialogFragment implements ViewPager.OnPageChangeListener, OnLocationItemClickListener {
    public static final String TAG = "MyDialogFragment";
    @BindView(R.id.img_dilaog_dismiss)
    ImageView imgDilaogDismiss;
    @BindView(R.id.pst_dialog_bottom)
    PagerSlidingTabStrip pstDialogBottom;
    @BindView(R.id.vip_dialog_bottom_pager)
    ViewPager vipDialogBottomPager;
    @BindView(R.id.dialog_fragment)
    LinearLayout dialogFragment;
    Unbinder unbinder;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        /*fragment  铺满整个屏幕*/
        setStyle(DialogFragment.STYLE_NO_TITLE, R.style.BottomDialog);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);
        View view = inflater.inflate(R.layout.dialog_fragment, null);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        pstDialogBottom.setOnPageChangeListener(this);
        initViewPager();


    }


    @Override
    public void onStart() {
        super.onStart();

        //得到dialog对应的window

        Dialog dialog = getDialog();
        Window window = dialog.getWindow();
        if (window != null) {
            dialog.setCanceledOnTouchOutside(true);

            //得到LayoutParams
            WindowManager.LayoutParams params = window.getAttributes();
            //修改gravity
            window.setGravity(Gravity.BOTTOM);
            params.width = window.getWindowManager().getDefaultDisplay().getWidth();
            params.height = (int) (window.getWindowManager().getDefaultDisplay().getHeight() * 0.6);
            window.setAttributes(params);
        }
    }


    String[] titleArray = {"请选择", "", ""};
    DialogFragmentPagerAdapter pagerAdapter;

    ProvinceFragment provinceFragment;
    CityFragment cityFragment;
    DistrictFragment districtFragment;

    List<Fragment> list;

    public void initViewPager() {

        provinceFragment = new ProvinceFragment();
        provinceFragment.setmOnLocationItemClickListener(this);
        cityFragment = new CityFragment();
        cityFragment.setmOnLocationItemClickListener(this);
        districtFragment = new DistrictFragment();
        districtFragment.setmOnLocationItemClickListener(this);
        list = new ArrayList<>();

        list.add(provinceFragment);
        list.add(cityFragment);
        list.add(districtFragment);


        pagerAdapter = new DialogFragmentPagerAdapter(getChildFragmentManager(), list);
        pagerAdapter.setArray(titleArray);
        vipDialogBottomPager.setAdapter(pagerAdapter);

        pstDialogBottom.setViewPager(vipDialogBottomPager);
        pstDialogBottom.setTextSize(30);


    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }


    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @OnClick(R.id.img_dilaog_dismiss)
    public void dismiss() {
        getDialog().dismiss();
    }

    @Override
    public void provinceClickListener(String province) {
        vipDialogBottomPager.setCurrentItem(1);
        setTitleText(province, 0);

    }

    @Override
    public void cityClickListener(String city) {
        vipDialogBottomPager.setCurrentItem(2);
        setTitleText(city, 1);

    }

    @Override
    public void districtClickListener(String district) {
        setTitleText(district, 2);
//        dismiss();
    }

    private void setTitleText(String location, int index) {
        titleArray[index] = location;
        pagerAdapter.setArray(titleArray);
        pstDialogBottom.notifyDataSetChanged();

    }
}
