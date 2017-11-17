package com.example.maintainsteward2.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.Button;
import android.widget.LinearLayout;

import com.example.maintainsteward2.R;
import com.example.maintainsteward2.application.MyApplication;
import com.example.maintainsteward2.base.BaseActivity;
import com.example.maintainsteward2.main.MainActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/10/20.
 */

public class TiXianSucessActivity extends BaseActivity {
    @BindView(R.id.layout_back)
    LinearLayout layoutBack;
    @BindView(R.id.btn_see_money)
    Button btnSeeMoney;
    @BindView(R.id.btn_to_main)
    Button btnToMain;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MyApplication.getActivitiesList().add(this);
        setContentView(R.layout.activity_tixiansucess);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.layout_back)
    public void onLayoutBackClicked() {
        finish();
    }

    @OnClick(R.id.btn_see_money)
    public void onBtnSeeMoneyClicked() {
        startActivity(new Intent(this, MyQianBaoActivity.class));
    }

    @OnClick(R.id.btn_to_main)
    public void onBtnToMainClicked() {
        startActivity(new Intent(this, MainActivity.class));
    }
}
