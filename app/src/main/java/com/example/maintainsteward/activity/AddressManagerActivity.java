package com.example.maintainsteward.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.maintainsteward.R;
import com.example.maintainsteward.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/8/10.
 * <p>
 * 地址管理界面
 */

public class AddressManagerActivity extends BaseActivity {

    public static final int REQUEST_CODE = 1;

    @BindView(R.id.txt_base_back)
    ImageView txtBaseBack;
    @BindView(R.id.txt_base_title)
    TextView txtBaseTitle;
    @BindView(R.id.base_layout)
    LinearLayout baseLayout;

    @BindView(R.id.rv_address_manager)
    RecyclerView rvAddressManager;

    @BindView(R.id.layout_addaddress_addressmanager)
    LinearLayout layoutAddaddressAddressmanager;


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if (keyCode == KeyEvent.KEYCODE_BACK) {
            finish();
            return true;
        }

        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_address_manager);
        ButterKnife.bind(this);

    }

    @OnClick(R.id.layout_addaddress_addressmanager)
    public void click() {
        Intent intent = new Intent(this, AddAddressActivity.class);
        startActivityForResult(intent, REQUEST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case REQUEST_CODE:
                    break;
            }
        }

    }
}
