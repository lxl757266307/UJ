package com.example.maintainsteward.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.KeyEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.maintainsteward.R;
import com.example.maintainsteward.base.BaseActivity;
import com.example.maintainsteward.base.Contacts;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/9/21.
 */

public class MyQianBaoActivity extends BaseActivity {

    @BindView(R.id.layout_money)
    LinearLayout layoutMoney;
    @BindView(R.id.layout_kajuan)
    LinearLayout layoutKajuan;
    String id;
    SharedPreferences sharedPreferences;
    @BindView(R.id.txt_blance)
    TextView txtBlance;
    @BindView(R.id.txt_count)
    TextView txtCount;
    @BindView(R.id.layout_back)
    LinearLayout layoutBack;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qianbao);
        ButterKnife.bind(this);
        sharedPreferences = getSharedPreferences(Contacts.USER, MODE_PRIVATE);
        id = sharedPreferences.getString("id", null);
        getMyWalletBalance();
        getMyCouponsCount();

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if (keyCode == KeyEvent.KEYCODE_BACK) {
            finish();
            return true;
        }

        return super.onKeyDown(keyCode, event);
    }

    @OnClick({R.id.layout_money, R.id.layout_kajuan})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.layout_money:
                startActivity(new Intent(this, MyYueActivity.class));
                break;
            case R.id.layout_kajuan:
                startActivity(new Intent(this, MyKaJuanActivity.class));
                break;
        }
    }

    public void getMyCouponsCount() {

        String count = sharedPreferences.getString("count", null);
        txtCount.setText(count + "张");

//        TreeMap<String, String> map = new TreeMap<>();
//        String timeStamp = System.currentTimeMillis() + "";
//        map.put("user_id", id);
//        map.put("timestamp", timeStamp);
//        String sign = ToolUitls.getSign(map);
//        ToolUitls.getCallBackStr(Contacts.TEST_BASE_URL + "MyCouponsCount?" + "user_id=" + id + "&timestamp=" + timeStamp + "&sign=" + sign + "&key=" + Contacts.KEY);

    }

    public void getMyWalletBalance() {

        String blance = sharedPreferences.getString("blance", null);
        txtBlance.setText("￥"+blance+"元");

    }


    @OnClick(R.id.layout_back)
    public void onViewClicked() {
        finish();
    }
}
