package com.example.maintainsteward.wxapi;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.KeyEvent;
import android.widget.TextView;

import com.example.maintainsteward.R;


import com.example.maintainsteward.utils.Constants;
import com.example.maintainsteward.utils.ToolUitls;
import com.tencent.mm.opensdk.constants.ConstantsAPI;
import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import java.util.Timer;
import java.util.TimerTask;

public class WXEntryActivity extends Activity implements IWXAPIEventHandler {

    private static final String TAG = "MicroMsg.SDKSample.WXEntryActivity";

    private static final int SUCCESS = 0; // 成功
    private static final int FAIL = 0; // 失败

    private static final int CANCEL = -2; // 取消

    private IWXAPI api;
    private TextView tv_result;
    public static Handler handler = new Handler();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pay_result);
        api = WXAPIFactory.createWXAPI(this, Constants.APP_ID);
        api.handleIntent(getIntent(), this);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        api.handleIntent(intent, this);
    }


    @Override
    public void onResp(BaseResp resp) {
        if (resp.getType() == ConstantsAPI.COMMAND_PAY_BY_WX) {
            // 成功处理
            if (resp.errCode == SUCCESS) {
                ToolUitls.toast(this, "支付成功!");
                handler.sendEmptyMessage(0);
                finish();
            } else if (resp.errCode == FAIL) {
                finish();
                ToolUitls.toast(this, "支付失败!");
            } else {
                ToolUitls.toast(this, "取消支付");
                finish();
            }
        }
    }

    private void failtimeOver() {
        // 添加一个3s的线程
        Timer timer = new Timer();
        TimerTask task = new TimerTask() {

            @Override
            public void run() {
                finish();
            }
        };
        timer.schedule(task, 1000); // 2秒后执行

    }

    /**
     * 倒计时2s的线程
     */
    private void successtimeOver() {
        try {
            Thread.sleep(2000);
            finish();
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    /**
     * 点击返回按钮拦截的事件 （即退出确认）
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public void onReq(BaseReq baseReq) {

    }
}