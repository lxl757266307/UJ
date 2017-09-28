package com.example.maintainsteward.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.KeyEvent;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.maintainsteward.R;
import com.example.maintainsteward.base.BaseActivity;
import com.example.maintainsteward.bean.HotNewsList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/9/28.
 */

public class NewsWebActivity extends BaseActivity {
    @BindView(R.id.layout_back)
    LinearLayout layoutBack;
    @BindView(R.id.txt_title)
    TextView txtTitle;
    @BindView(R.id.webview)
    WebView webview;

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            finish();
        }

        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        HotNewsList.DataBean dataBean = (HotNewsList.DataBean) this.getIntent().getSerializableExtra("data");
        setContentView(R.layout.activity_newweb);
        ButterKnife.bind(this);
        WebSettings settings = webview.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setUseWideViewPort(true);
        settings.setLoadWithOverviewMode(true);
        webview.loadUrl(dataBean.getUrl());
    }

    @OnClick(R.id.layout_back)
    public void onViewClicked() {
        finish();
    }
}
