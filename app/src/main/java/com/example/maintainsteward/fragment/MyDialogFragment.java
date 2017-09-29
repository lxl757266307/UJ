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
import com.example.maintainsteward.bean.CityListBean;
import com.example.maintainsteward.inter.OnLocationItemClickListener;
import com.example.maintainsteward.utils.LocationUtils;
import com.example.maintainsteward.utils.ToolUitls;

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
    @BindView(R.id.layout_dilaog_dismiss)
    LinearLayout imgDilaogDismiss;
    @BindView(R.id.pst_dialog_bottom)
    PagerSlidingTabStrip pstDialogBottom;
    @BindView(R.id.vip_dialog_bottom_pager)
    ViewPager vipDialogBottomPager;
    @BindView(R.id.dialog_fragment)
    LinearLayout dialogFragment;
    Unbinder unbinder;
    List<CityListBean.DataBean> data;

    public List<CityListBean.DataBean> getData() {
        return data;
    }

    public void setData(List<CityListBean.DataBean> data) {
        this.data = data;
    }

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


    String[] titleArray = {"请选择", ""};
    DialogFragmentPagerAdapter pagerAdapter;

    CityFragment cityFragment;
    DistrictFragment districtFragment;

    List<Fragment> list;

    public void initViewPager() {


        cityFragment = new CityFragment();
        if (data != null) {
            cityFragment.setList(data);
        }
        cityFragment.setmOnLocationItemClickListener(this);
        districtFragment = new DistrictFragment();
        districtFragment.setmOnLocationItemClickListener(this);
        list = new ArrayList<>();


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

    String address;

    public void setAddress(String address) {
        this.address = address;
    }

    @OnClick(R.id.layout_dilaog_dismiss)
    public void dismiss() {
        for (String s : titleArray) {
            address += s;
        }
        getDialog().dismiss();
        if (onAddressChoosedListener != null) {
            onAddressChoosedListener.onAddressChoosed(titleArray, address, xId);
        }
    }


    String[] xId = new String[2];

    @Override
    public void cityClickListener(String city, int position, String id) {
        xId[0] = id;
        titleArray[1] = "请选择";
        vipDialogBottomPager.setCurrentItem(1);
        districtFragment.setList(data.get(position).getDistrict());
        setTitleText(city, 0);

    }

    @Override
    public void districtClickListener(String district, String id) {
        xId[1] = id;
        setTitleText(district, 1);
//        dismiss();
    }

    private void setTitleText(String location, int index) {
        titleArray[index] = location;
        pagerAdapter.setArray(titleArray);
        pstDialogBottom.notifyDataSetChanged();


    }

    OnAddressChoosedListener onAddressChoosedListener;

    public OnAddressChoosedListener getOnAddressChoosedListener() {
        return onAddressChoosedListener;
    }

    public void setOnAddressChoosedListener(OnAddressChoosedListener onAddressChoosedListener) {
        this.onAddressChoosedListener = onAddressChoosedListener;
    }

    public interface OnAddressChoosedListener {

        void onAddressChoosed(String[] array, String str, String[] id);

    }
}
