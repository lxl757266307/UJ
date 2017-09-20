package com.example.maintainsteward.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.example.maintainsteward.base.BaseActivity;

/**
 * Created by Administrator on 2017/9/20.
 */

public class OrderMessageActivity extends BaseActivity {


    String orderId;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        orderId = this.getIntent().getStringExtra("id");
    }
}
