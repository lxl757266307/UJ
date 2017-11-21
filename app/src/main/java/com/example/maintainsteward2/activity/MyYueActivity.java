package com.example.maintainsteward2.activity;

import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.view.KeyEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.maintainsteward2.R;
import com.example.maintainsteward2.adapter.BlanceFragmentAdapter;
import com.example.maintainsteward2.application.MyApplication;
import com.example.maintainsteward2.base.BaseActivity;
import com.example.maintainsteward2.base.Contacts;
import com.example.maintainsteward2.fragment.ChongZhiJiluFragment;
import com.example.maintainsteward2.fragment.XiaoFeiJiluFragment;
import com.example.maintainsteward2.utils.ToolUitls;
import com.example.maintainsteward2.view.NoScrollViewPager;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/9/21.
 */

public class MyYueActivity extends BaseActivity {
    @BindView(R.id.layout_back)
    LinearLayout layoutBack;
    @BindView(R.id.txt_title)
    TextView txtTitle;
    @BindView(R.id.txt_money)
    TextView txtMoney;
    @BindView(R.id.txt_xiaofei)
    TextView txtXiaofei;
    @BindView(R.id.txt_chongzhi)
    TextView txtChongzhi;
    @BindView(R.id.vp_yue)
    NoScrollViewPager vpYue;
    @BindView(R.id.txt_chongzhi_anniu)
    TextView txtChongzhiAnniu;

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if (keyCode == KeyEvent.KEYCODE_BACK) {
            finish();
            return true;
        }

        return super.onKeyDown(keyCode, event);
    }

    TextView[] txtArray;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MyApplication.getActivitiesList().add(this);
        setContentView(R.layout.activity_yue);
        ButterKnife.bind(this);
        vpYue.setNoScroll(true);
        txtArray = new TextView[2];
        txtArray[0] = txtXiaofei;
        txtArray[1] = txtChongzhi;
        SharedPreferences sharedPreferences = getSharedPreferences(Contacts.USER, MODE_PRIVATE);
        String blance = sharedPreferences.getString("blance", null);
        txtMoney.setText(blance);


        BlanceFragmentAdapter fragmentAdapter = new BlanceFragmentAdapter(getSupportFragmentManager());

        XiaoFeiJiluFragment feiJiluFragment = new XiaoFeiJiluFragment();
        ChongZhiJiluFragment chongZhiJiluFragment = new ChongZhiJiluFragment();

        List<Fragment> list = new ArrayList<>();
        list.add(feiJiluFragment);
        list.add(chongZhiJiluFragment);
        fragmentAdapter.setFragments(list);
        vpYue.setAdapter(fragmentAdapter);
        fragmentAdapter.notifyDataSetChanged();


    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @OnClick({R.id.layout_back, R.id.txt_xiaofei, R.id.txt_chongzhi})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.layout_back:
                finish();
                break;
            case R.id.txt_xiaofei:
//                setTextViewBackground(0);
                vpYue.setCurrentItem(0);
                break;
            case R.id.txt_chongzhi:
//                setTextViewBackground(1);
                vpYue.setCurrentItem(1);
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

    @OnClick(R.id.txt_chongzhi_anniu)
    public void onViewClicked() {

        ToolUitls.toast(this,"拼命开发中~");
    }
}
