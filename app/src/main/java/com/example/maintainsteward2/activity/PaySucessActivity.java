package com.example.maintainsteward2.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.maintainsteward2.R;
import com.example.maintainsteward2.application.MyApplication;
import com.example.maintainsteward2.base.BaseActivity;
import com.example.maintainsteward2.bean.OrderInfoBean;
import com.example.maintainsteward2.main.MainActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/10/24.
 */

public class PaySucessActivity extends BaseActivity {
    @BindView(R.id.layout_back)
    LinearLayout layoutBack;
    @BindView(R.id.txt_title)
    TextView txtTitle;
    @BindView(R.id.txt_pingjia)
    TextView txtPingjia;
    @BindView(R.id.txt_back_main_ordersucess)
    TextView txtBackMainOrdersucess;
    @BindView(R.id.img_hongbao)
    ImageView imgHongbao;
    OrderInfoBean.DataBean data;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MyApplication.getActivitiesList().add(this);
        data = (OrderInfoBean.DataBean) getIntent().getSerializableExtra("data");
        setContentView(R.layout.activity_pay_sucess);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.layout_back)
    public void onLayoutBackClicked() {
        finish();
    }

    @OnClick(R.id.txt_pingjia)
    public void onTxtPingjiaClicked() {


        Intent intent2 = new Intent(this, PingJiaActivity.class);
        intent2.putExtra("cover", data.getCover());
        intent2.putExtra("name", data.getName());
        String id = data.getId();
        String worker_id = data.getWorker_id();
        String order_no = data.getOrder_no();

        intent2.putExtra("order_id", id);
        intent2.putExtra("worker_id", worker_id);
        intent2.putExtra("order_no", order_no);
        startActivity(intent2);

    }

    @OnClick(R.id.txt_back_main_ordersucess)
    public void onTxtBackMainOrdersucessClicked() {
        startActivity(new Intent(this, MainActivity.class));
    }

    @OnClick(R.id.img_hongbao)
    public void onImgHongbaoClicked() {



    }
}
