package com.example.maintainsteward2.activity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.maintainsteward2.R;
import com.example.maintainsteward2.base.BaseActivity;
import com.example.maintainsteward2.base.Contacts;
import com.example.maintainsteward2.utils.ToolUitls;

import java.util.ResourceBundle;
import java.util.TreeMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/10/7.
 */

public class HuoDongDescriptionActivity extends BaseActivity {
    @BindView(R.id.layout_back)
    LinearLayout layoutBack;
    @BindView(R.id.txt_title)
    TextView txtTitle;
    @BindView(R.id.webview)
    WebView webview;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_huodongshuoming);
        ButterKnife.bind(this);
        WebSettings settings = webview.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setUseWideViewPort(true);
        settings.setLoadWithOverviewMode(true);
        webview.loadUrl("http://wx.cnncsh.com/app_user/appweb/onepage/id/4.html");
    }

    @OnClick(R.id.layout_back)
    public void onViewClicked() {
        finish();
    }


}
