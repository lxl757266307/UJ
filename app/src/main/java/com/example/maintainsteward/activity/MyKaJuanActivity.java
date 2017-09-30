package com.example.maintainsteward.activity;

import android.app.ProgressDialog;
import android.content.Context;
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
import com.example.maintainsteward.adapter.KaJuanFragmentAdapter;
import com.example.maintainsteward.application.MyApplication;
import com.example.maintainsteward.base.BaseActivity;
import com.example.maintainsteward.base.Contacts;
import com.example.maintainsteward.bean.CanUseYouHuiQuanBean;
import com.example.maintainsteward.bean.KaJuanBean;
import com.example.maintainsteward.bean.KaJuanCountBean;
import com.example.maintainsteward.fragment.KaJuanWeiShiYongFragment;
import com.example.maintainsteward.fragment.KaJuanYiGuoQiFragment;
import com.example.maintainsteward.fragment.KaJuanYiShiYongFragment;
import com.example.maintainsteward.mvp_presonter.KaJuanPresonter;
import com.example.maintainsteward.mvp_view.KaJuanListener;
import com.example.maintainsteward.utils.ToolUitls;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/9/21.
 */

public class MyKaJuanActivity extends BaseActivity implements KaJuanListener {

    @BindView(R.id.layout_back)
    LinearLayout layoutBack;
    @BindView(R.id.txt_title)
    TextView txtTitle;
    @BindView(R.id.txt_count)
    TextView txtCount;
    @BindView(R.id.txt_weishiyong)
    TextView txtWeishiyong;
    @BindView(R.id.txt_yishiyong)
    TextView txtYishiyong;
    @BindView(R.id.txt_guoqi)
    TextView txtGuoqi;
    @BindView(R.id.vp_kajuan)
    ViewPager vpKajuan;


    TextView[] textViews;
    SharedPreferences sharedPreferences;
    KaJuanFragmentAdapter adapter;
    String id;
    KaJuanPresonter kaJuanPresonter;
    List<Fragment> list;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MyApplication.getActivitiesList().add(this);
        setContentView(R.layout.activity_kajuan);
        ButterKnife.bind(this);
        textViews = new TextView[3];
        textViews[0] = txtWeishiyong;
        textViews[1] = txtYishiyong;
        textViews[2] = txtGuoqi;
        kaJuanPresonter = new KaJuanPresonter();
        kaJuanPresonter.setKaJuanListener(this);
        sharedPreferences = getSharedPreferences(Contacts.USER, MODE_PRIVATE);
        id = sharedPreferences.getString("id", null);
        String count = sharedPreferences.getString("count", null);
        txtCount.setText(count);
        adapter = new KaJuanFragmentAdapter(getSupportFragmentManager());

        list = new ArrayList<>();

        KaJuanWeiShiYongFragment kaJuanWeiShiYongFragment = new KaJuanWeiShiYongFragment();
        KaJuanYiShiYongFragment kaJuanYiShiYongFragment = new KaJuanYiShiYongFragment();
        KaJuanYiGuoQiFragment kaJuanYiGuoQiFragment = new KaJuanYiGuoQiFragment();

        list.add(kaJuanWeiShiYongFragment);
        list.add(kaJuanYiShiYongFragment);
        list.add(kaJuanYiGuoQiFragment);

        adapter.setFragments(list);

        vpKajuan.setAdapter(adapter);

        vpKajuan.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
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
        getKaJuanCount();

    }

    @OnClick({R.id.layout_back, R.id.txt_weishiyong, R.id.txt_yishiyong, R.id.txt_guoqi})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.layout_back:
                finish();
                break;
            case R.id.txt_weishiyong:
                vpKajuan.setCurrentItem(0);
                break;
            case R.id.txt_yishiyong:
                vpKajuan.setCurrentItem(1);
                break;
            case R.id.txt_guoqi:
                vpKajuan.setCurrentItem(2);
                break;
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    public void setTextViewBackground(int index) {

        for (int i = 0; i < textViews.length; i++) {

            if (index == i) {
                textViews[i].setBackground(getResources().getDrawable(R.drawable.border_bottom));
                textViews[i].setTextColor(getResources().getColor(R.color.red));
            } else {
                textViews[i].setBackgroundResource(R.color.background);
                textViews[i].setTextColor(getResources().getColor(R.color.black));
            }

        }

    }


    public void getKaJuanCount() {

        TreeMap<String, String> map = new TreeMap<>();
        String timeStamp = System.currentTimeMillis() + "";
        map.put("user_id", id);
        map.put("timestamp", timeStamp);
        String sign = ToolUitls.getSign(map);
//        ToolUitls.getCallBackStr(Contacts.TEST_BASE_URL + "MyCouponsCount?" + "user_id=" + id + "&timestamp=" + timeStamp + "&sign=" + sign + "&key=" + Contacts.KEY);
        kaJuanPresonter.getCount(id, timeStamp, sign, Contacts.KEY);
    }

    ProgressDialog dialog;

    @Override
    public void showDialog() {
        dialog = ProgressDialog.show(this, "", "正在加载...");
    }

    @Override
    public void hideDialog() {
        dialog.dismiss();
    }

    @Override
    public void onGetKaJuanSucess(KaJuanBean bean) {

    }


    @Override
    public void onGetKaJuanCountSucess(KaJuanCountBean bean) {
        switch (bean.getStatus()) {
            case "1":
                KaJuanCountBean.DataBean data = bean.getData();

                String totalcount1 = data.getTotalcount1();
                txtWeishiyong.setText("未使用(" + totalcount1 + ")");
                String totalcount2 = data.getTotalcount2();
                txtYishiyong.setText("已使用(" + totalcount2 + ")");
                String totalcount3 = data.getTotalcount3();
                txtGuoqi.setText("已过期(" + totalcount3 + ")");
                break;
        }
    }

    @Override
    public void onGetCanUseKaJuan(CanUseYouHuiQuanBean bean) {

    }

    @Override
    public void showBlank() {

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if (keyCode == KeyEvent.KEYCODE_BACK) {
            finish();
            return true;
        }

        return super.onKeyDown(keyCode, event);
    }
}
