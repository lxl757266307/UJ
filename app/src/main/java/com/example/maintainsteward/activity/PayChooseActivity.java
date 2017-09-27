package com.example.maintainsteward.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.maintainsteward.R;
import com.example.maintainsteward.application.MyApplication;
import com.example.maintainsteward.base.BaseActivity;
import com.example.maintainsteward.base.Contacts;
import com.example.maintainsteward.bean.PayInfoBean;
import com.tencent.mm.opensdk.modelpay.PayReq;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/9/26.
 */

public class PayChooseActivity extends BaseActivity {
    @BindView(R.id.layout_back)
    LinearLayout layoutBack;
    @BindView(R.id.txt_title)
    TextView txtTitle;
    @BindView(R.id.img_xuanzhong1)
    CheckBox imgXuanzhong1;
    @BindView(R.id.layout_weixinzhifu)
    LinearLayout layoutWeixinzhifu;
    @BindView(R.id.img_xuanzhong2)
    CheckBox imgXuanzhong2;
    @BindView(R.id.layout_yuezhifu)
    LinearLayout layoutYuezhifu;
    @BindView(R.id.btn_zhifu)
    Button btnZhifu;
    PayInfoBean bean;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bean = (PayInfoBean) this.getIntent().getSerializableExtra("data");
        setContentView(R.layout.activity_choosepay);
        ButterKnife.bind(this);
        register();

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if (keyCode == KeyEvent.KEYCODE_BACK) {
            finish();
        }

        return super.onKeyDown(keyCode, event);
    }

    @OnClick({R.id.layout_weixinzhifu, R.id.layout_yuezhifu, R.id.btn_zhifu, R.id.layout_back})
    public void click(View view) {
        switch (view.getId()) {
            case R.id.layout_weixinzhifu:

                if (imgXuanzhong2.isChecked()) {
                    imgXuanzhong2.setChecked(false);
                    imgXuanzhong2.setVisibility(View.INVISIBLE);
                }
                imgXuanzhong1.setChecked(true);
                imgXuanzhong1.setVisibility(View.VISIBLE);


                break;
            case R.id.layout_yuezhifu:
                if (imgXuanzhong1.isChecked()) {
                    imgXuanzhong1.setChecked(false);
                    imgXuanzhong1.setVisibility(View.INVISIBLE);
                }
                imgXuanzhong2.setChecked(true);
                imgXuanzhong2.setVisibility(View.VISIBLE);
                break;

            case R.id.btn_zhifu:
                Contacts.PAY_FLAG = "normal";
                String appid = bean.getAppid();
                String noncestr = bean.getNoncestr();
                String packageX = bean.getPackageX();
                String partnerid = bean.getPartnerid();
                String prepayid = bean.getPrepayid();
                String sign = bean.getSign();
                int timestamp = bean.getTimestamp();
                PayReq payReq = new PayReq();
                payReq.appId = appid;
                payReq.partnerId = partnerid;
                payReq.prepayId = prepayid;
                payReq.packageValue = packageX;
                payReq.nonceStr = noncestr;
                payReq.timeStamp = timestamp + "";
                payReq.sign = sign;
                MyApplication.api.sendReq(payReq);
//                finish();

                break;
            case R.id.layout_back:
                finish();
                break;
        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(reciver);
    }

    FefreshReciver reciver;

    public void register() {
        reciver = new FefreshReciver();
        IntentFilter filter = new IntentFilter();
        filter.addAction(Contacts.PAY_BY_WEI_XIN);
        registerReceiver(reciver, filter);
    }

    class FefreshReciver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {

            if (Contacts.PAY_FLAG.equals("normal")) {
                finish();
            }

        }
    }
}
