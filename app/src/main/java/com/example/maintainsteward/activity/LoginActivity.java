package com.example.maintainsteward.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.maintainsteward.R;
import com.example.maintainsteward.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/8/5.
 */

public class LoginActivity extends BaseActivity {
    /*后退按钮*/
    @BindView(R.id.txt_base_back)
    ImageView mTxtBaseBack;
    /*  标题*/
    @BindView(R.id.txt_base_title)
    TextView mTxtBaseTitle;
    /*电话号码*/
    @BindView(R.id.edit_login_phone)
    EditText mEditLoginPhone;
    /* 验证码*/
    @BindView(R.id.edit_login_yan_zheng_ma)
    EditText mEditLoginYanZhengMa;
    /*获取验证码按钮*/
    @BindView(R.id.btn_login_get_yan_zheng_ma)
    Button mBtnLoginGetYanZhengMa;
    /*同意选择框*/
    @BindView(R.id.cb_login_accept)
    CheckBox mCbLoginAccept;
    /*U匠协议*/
    @BindView(R.id.edit_login_permisson)
    TextView mEditLoginPermisson;
    /* 登录按钮*/
    @BindView(R.id.layout_login_click)
    LinearLayout mLayoutLoginClick;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
    }

    /*两个按钮的点击事件*/
    @OnClick({R.id.btn_login_get_yan_zheng_ma, R.id.layout_login_click})
    public void click(View view) {

    }

}
