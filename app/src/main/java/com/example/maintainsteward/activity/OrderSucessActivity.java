package com.example.maintainsteward.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.maintainsteward.R;
import com.example.maintainsteward.base.BaseActivity;
import com.example.maintainsteward.main.MainActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/9/18.
 */

public class OrderSucessActivity extends BaseActivity {
    @BindView(R.id.layout_back)
    LinearLayout layoutBack;
    @BindView(R.id.txt_title)
    TextView txtTitle;
    @BindView(R.id.txt_see_order_ordersucess)
    TextView txtSeeOrderOrdersucess;
    @BindView(R.id.txt_back_main_ordersucess)
    TextView txtBackMainOrdersucess;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_sucess);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.layout_back, R.id.txt_see_order_ordersucess, R.id.txt_back_main_ordersucess})
    public void click(View view) {
        switch (view.getId()) {
            case R.id.layout_back:
                finish();
                break;
            case R.id.txt_see_order_ordersucess:
                Intent intent = new Intent(this, MainActivity.class);
                intent.putExtra("page", 2);
                startActivity(intent);
                finish();
                break;
            case R.id.txt_back_main_ordersucess:
                Intent intent2 = new Intent(this, MainActivity.class);
                startActivity(intent2);
                finish();
                break;
        }
    }
}
