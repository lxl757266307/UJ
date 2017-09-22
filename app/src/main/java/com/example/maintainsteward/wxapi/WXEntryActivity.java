package com.example.maintainsteward.wxapi;

import com.example.maintainsteward.base.Contacts;
import com.tencent.mm.opensdk.constants.ConstantsAPI;
import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.modelmsg.ShowMessageFromWX;
import com.tencent.mm.opensdk.modelmsg.WXAppExtendObject;
import com.tencent.mm.opensdk.modelmsg.WXMediaMessage;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


public class WXEntryActivity extends Activity implements IWXAPIEventHandler {
    IWXAPI api;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        api = WXAPIFactory.createWXAPI(this, Contacts.APP_ID, false);
        api.registerApp(Contacts.APP_ID);
    }

    @Override
    public void onReq(BaseReq baseReq) {
        api.handleIntent(getIntent(), this);
    }

    @Override
    public void onResp(BaseResp baseResp) {
        api.handleIntent(getIntent(), this);
    }
}