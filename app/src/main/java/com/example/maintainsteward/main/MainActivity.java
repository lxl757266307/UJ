package com.example.maintainsteward.main;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.maintainsteward.R;
import com.example.maintainsteward.activity.GuidActivity;
import com.example.maintainsteward.activity.LoginActivity;
import com.example.maintainsteward.adapter.MainFragmentPagerAdapter;
import com.example.maintainsteward.application.MyApplication;
import com.example.maintainsteward.base.BaseActivity;
import com.example.maintainsteward.base.Contacts;
import com.example.maintainsteward.fragment.KindsFragment;
import com.example.maintainsteward.fragment.MainFragment;
import com.example.maintainsteward.fragment.TuiJianFragment;
import com.example.maintainsteward.fragment.UserInfoFragment;
import com.example.maintainsteward.inter.OnMainServiceClickListener;
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
public class MainActivity extends BaseActivity implements EasyPermissions.PermissionCallbacks, OnPageChangeListener,
        OnMainServiceClickListener {

    public static final String TAG = "MainFragment";
    @BindView(R.id.vip_main_activity)
    ViewPager vipMain;
    @BindView(R.id.rg_radio_group_main)
    RadioGroup rgRadioGroupMain;
    @BindView(R.id.radio_first)
    RadioButton radioFirst;
    @BindView(R.id.radio_two)
    RadioButton radioTwo;
    //    @BindView(R.id.radio_three)
//    RadioButton radioThree;
    @BindView(R.id.radio_four)
    RadioButton radioFour;
    @BindView(R.id.layout_main_first)
    LinearLayout layoutMainFirst;
    @BindView(R.id.layout_main_two)
    LinearLayout layoutMainTwo;
    //    @BindView(R.id.layout_main_three)
//    LinearLayout layoutMainThree;
    @BindView(R.id.layout_main_four)
    LinearLayout layoutMainFour;

    RadioButton[] buttonArray;
    int page = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        SharedPreferences sharedPreferences = getSharedPreferences(Contacts.USER, MODE_PRIVATE);
        boolean isFirst = sharedPreferences.getBoolean("isFirst", true);

        if (isFirst) {
            startActivity(new Intent(this, GuidActivity.class));
        } else {
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


    }

    MainFragmentPagerAdapter mainFragmentPagerAdapter;

    public void setFragment() {

        MainFragment mainFragment = new MainFragment();
        mainFragment.setOnMainServiceClickListener(this);
        KindsFragment kindsFragment = new KindsFragment();
//        TuiJianFragment orerFragment = new TuiJianFragment();
        UserInfoFragment userInfoFragment = new UserInfoFragment();
        List<Fragment> list = new ArrayList<>();
        list.add(mainFragment);
        list.add(kindsFragment);
//        list.add(orerFragment);
        list.add(userInfoFragment);


        mainFragmentPagerAdapter = new MainFragmentPagerAdapter(getSupportFragmentManager(), list);

        vipMain.setAdapter(mainFragmentPagerAdapter);

        vipMain.addOnPageChangeListener(this);

        if (page != -1) {
            vipMain.setCurrentItem(page);
        }

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
//        ToolUitls.print(TAG, "已成功注册权限：" + perms);
    }

    @Override
    public void onPermissionsDenied(int requestCode, List<String> perms) {
//        ToolUitls.print(TAG, "已失败注册权限：" + perms);
    }


    @OnClick({R.id.layout_main_first, R.id.layout_main_two, R.id.layout_main_four})
    public void layoutClick(View view) {
        switch (view.getId()) {
            case R.id.layout_main_first:
                setRgRadioGroupType(radioFirst);
                break;
            case R.id.layout_main_two:
                setRgRadioGroupType(radioTwo);
                break;
//            case R.id.layout_main_three:
//                setRgRadioGroupType(radioThree);
//                break;
            case R.id.layout_main_four:
                SharedPreferences sharedPreferences = getSharedPreferences(Contacts.USER, MODE_PRIVATE);
                boolean online = sharedPreferences.getBoolean("online", false);
                if (online) {
                    setRgRadioGroupType(radioFour);
                } else {
                    ToolUitls.toast(this, "您还未，请先登录！");
                    handler.sendEmptyMessageDelayed(1, 200);
                }


                break;
        }

    }

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            Intent intent = new Intent(MainActivity.this, LoginActivity.class);
            intent.putExtra("flag", "MainActivity");
            startActivity(intent);
        }
    };

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
        buttonArray = new RadioButton[3];
        buttonArray[0] = radioFirst;
        buttonArray[1] = radioTwo;
//        buttonArray[2] = radioThree;
        buttonArray[2] = radioFour;
        buttonArray[0].setChecked(true);
    }

    @Override
    public void onServiceClick() {

        vipMain.setCurrentItem(1);
    }
}
