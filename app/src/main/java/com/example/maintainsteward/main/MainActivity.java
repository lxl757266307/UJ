package com.example.maintainsteward.main;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.maintainsteward.R;
import com.example.maintainsteward.adapter.MainFragmentPagerAdapter;
import com.example.maintainsteward.application.MyApplication;
import com.example.maintainsteward.base.BaseActivity;
import com.example.maintainsteward.base.Contacts;
import com.example.maintainsteward.fragment.KindsFragment;
import com.example.maintainsteward.fragment.MainFragment;
import com.example.maintainsteward.fragment.TuiJianFragment;
import com.example.maintainsteward.fragment.UserInfoFragment;
import com.example.maintainsteward.utils.PermissionRegisterUtils;
import com.example.maintainsteward.utils.ToolUitls;
import com.tencent.mm.opensdk.modelpay.PayReq;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import pub.devrel.easypermissions.EasyPermissions;

@RequiresApi(api = Build.VERSION_CODES.M)
public class MainActivity extends BaseActivity implements EasyPermissions.PermissionCallbacks, OnPageChangeListener {

    public static final String TAG = "MainFragment";
    @BindView(R.id.vip_main_activity)
    ViewPager vipMain;
    @BindView(R.id.rg_radio_group_main)
    RadioGroup rgRadioGroupMain;
    @BindView(R.id.radio_first)
    RadioButton radioFirst;
    @BindView(R.id.radio_two)
    RadioButton radioTwo;
    @BindView(R.id.radio_three)
    RadioButton radioThree;
    @BindView(R.id.radio_four)
    RadioButton radioFour;
    @BindView(R.id.layout_main_first)
    LinearLayout layoutMainFirst;
    @BindView(R.id.layout_main_two)
    LinearLayout layoutMainTwo;
    @BindView(R.id.layout_main_three)
    LinearLayout layoutMainThree;
    @BindView(R.id.layout_main_four)
    LinearLayout layoutMainFour;

    RadioButton[] buttonArray;
    int page = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        page = this.getIntent().getIntExtra("page", -1);

        MyApplication.getActivitiesList().add(this);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
//        ToolUitls.debug = false;
//        setTitleContent("主页面", this);
//        back(this);
        PermissionRegisterUtils.registerPermission(this);

        setRadioArray();
        setFragment();


    }

    MainFragmentPagerAdapter mainFragmentPagerAdapter;

    public void setFragment() {

        MainFragment mainFragment = new MainFragment();
        KindsFragment kindsFragment = new KindsFragment();
        TuiJianFragment orerFragment = new TuiJianFragment();
        UserInfoFragment userInfoFragment = new UserInfoFragment();
        List<Fragment> list = new ArrayList<>();
        list.add(mainFragment);
        list.add(kindsFragment);
        list.add(orerFragment);
        list.add(userInfoFragment);


        mainFragmentPagerAdapter = new MainFragmentPagerAdapter(getSupportFragmentManager(), list);

        vipMain.setAdapter(mainFragmentPagerAdapter);

        vipMain.addOnPageChangeListener(this);

        if (page != -1) {
            vipMain.setCurrentItem(page);
        }

    }


    public void pay() {
        ToolUitls.print(TAG, "ssss");
        PayReq payReq = new PayReq();
        payReq.appId = Contacts.APP_ID;
        payReq.partnerId = "1364879302";
        payReq.prepayId = "2165161061456121325136";
        payReq.packageValue = "Sign=2165161061456121325136";
        payReq.nonceStr = "1101000000140429eb40476f8896f4c9";
        payReq.partnerId = "1900000109";
        payReq.timeStamp = String.valueOf(System.currentTimeMillis());
        payReq.sign = "7FFECB600D7157C5AA49810D2D8F28BC2811827B";
        wxapi.sendReq(payReq);


    }

    IWXAPI wxapi;

    private void initWX() {
        wxapi = WXAPIFactory.createWXAPI(this, Contacts.APP_ID, false);
        wxapi.registerApp(Contacts.APP_ID);
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
    }

    @Override
    public void onPermissionsGranted(int requestCode, List<String> perms) {
        ToolUitls.print(TAG, "已成功注册权限：" + perms);
    }

    @Override
    public void onPermissionsDenied(int requestCode, List<String> perms) {
        ToolUitls.print(TAG, "已失败注册权限：" + perms);
    }


    @OnClick({R.id.layout_main_first, R.id.layout_main_two, R.id.layout_main_three, R.id.layout_main_four})
    public void layoutClick(View view) {
        switch (view.getId()) {
            case R.id.layout_main_first:
                setRgRadioGroupType(radioFirst);
                break;
            case R.id.layout_main_two:
                setRgRadioGroupType(radioTwo);
                break;
            case R.id.layout_main_three:
                setRgRadioGroupType(radioThree);
                break;
            case R.id.layout_main_four:
                setRgRadioGroupType(radioFour);
                break;
        }

    }

    public void setRgRadioGroupType(RadioButton radio) {
        for (int i = 0; i < buttonArray.length; i++) {
            if (buttonArray[i] == radio) {
                buttonArray[i].setChecked(true);
                vipMain.setCurrentItem(i);
            } else {
                buttonArray[i].setChecked(false);
            }

        }

    }


    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        onPageChange(position);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    public void onPageChange(int index) {
        for (int i = 0; i < buttonArray.length; i++) {
            if (i == index) {
                buttonArray[i].setChecked(true);
            } else {
                buttonArray[i].setChecked(false);
            }

        }
    }

    public void setRadioArray() {
        buttonArray = new RadioButton[4];
        buttonArray[0] = radioFirst;
        buttonArray[1] = radioTwo;
        buttonArray[2] = radioThree;
        buttonArray[3] = radioFour;
        buttonArray[0].setChecked(true);
    }
}
